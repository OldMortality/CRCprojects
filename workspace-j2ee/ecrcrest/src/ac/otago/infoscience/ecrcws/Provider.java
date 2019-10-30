package ac.otago.infoscience.ecrcws;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import ac.otago.infoscience.ecrcws.db.HibernateUtil;
import ac.otago.infoscience.ecrcws.db.Relations;

public class Provider {

	// 30 minutes. This is used as the time limit. The web service will not return 
	// records older than this (in ms), for security reasons.
	
	private static final long MAX_OLD = 30 * 60 * 1000;
	
	private static Logger logger = Logger.getLogger("ac.otago.infoscience.ecrcws.Provider");
	
	
	
	public String getRelationsByErefID ( String erefID, String ipAddressInRequest, boolean checkIP) throws DBException {
		StringBuffer sb = new StringBuffer();
		
		long timestamp = -1;
		
		String ipAddressInDB = null;
		
		boolean first = true;
		
		try {
		
			logger.info("getting relations for erefID: "+ erefID);
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			//where relation.erefID = :e order by relation.relationshipType
			
			Query q = session.createQuery(
					"from Relations as relation where relation.erefID = :e order by relation.relationshipType ")
						.setString("e", erefID);
		 
			//Iterator<Relations> i = (Iterator<Relations>) q.iterate();
			
			List<Relations> list =  q.list() ;
			
			Iterator<Relations> i = (Iterator<Relations>) list.iterator();
			
			logger.debug("Listing relations for erefID  " + erefID);
            
			int count = 0;
			
			while (i.hasNext()) {
			
				count++;
				
				Relations rel = (Relations) i.next() ;
				
				if (first) {
					
					// the time in ms when this record was created.
					timestamp =  rel.getTimest().getTime(); 
				
					ipAddressInDB = rel.getIpAddress();
									
					sb.append("<FAMILY_HISTORY>");
					
					first = false;
					
					sb.append("<EREFID>");
					
					sb.append(erefID);
					
					sb.append("</EREFID>");
					
					sb.append("<RISK>");
					
					sb.append(rel.getRiskLevel());
					
					sb.append("</RISK>");
					
					sb.append("<RELATIONS>");
					
				} else {
					
					// don't send the first relation, because it is the record for the
					// patient, which is used only to store the calculated risk level
					// and the erefid.
				  String xml =   rel.toXML();
				  
				  sb.append ( xml  );
				
				}
				
				logger.debug("relation:" + rel.toXML() );
				
			}
			logger.debug("Found this many relations for erefId "+ erefID + " : " + count);
			
			sb.append("</RELATIONS>");
			
			sb.append("</FAMILY_HISTORY>");
			
			session.close();
		
		} catch (Exception e) {
			
			logger.log(Level.ERROR
					,"error getting relation ", e);
			
			throw new DBException();
		
		}
		
		// handle the case where there were no records found for this erefid;
		
		if (first) {
		
			logger.debug("No relations found for erefID: " + erefID);
			
			return "<FAMILY_HISTORY><EREFID>"+ erefID + "</EREFID><RELATIONS></RELATIONS></FAMILY_HISTORY>";
		
		}
		
		// handle the case where the data is too old. This web service will not return
		// records older than MAX_OLD (ms), for security reasons.
		
		// Also for security reasons, the web service will only return records if the IP address on the request
		// is the same as that on the database. This depends on a setting in ecrcrest.properties. You can turn
		// this feature off by putting in there: allow_header=false
		
		if( timestamp > 0  ) {
		
			long now = System.currentTimeMillis();
			
			if (now - timestamp > MAX_OLD  ) {
			
				logger.log(Level.ERROR, "Request too old. erefid, ipAddress = " + erefID + " " + ipAddressInRequest);
				
				return "<FAMILY_HISTORY><EREFID>"+ erefID +  "</EREFID><RELATIONS></RELATIONS></FAMILY_HISTORY>";
			
			}
			
			if ( checkIP && !( ipAddressInRequest.equals(ipAddressInDB) ) ) {
				
				logger.log(Level.ERROR, "IPaddress in request does not match ipAddress on database. " +
			  " erefId, ipAddressRequest, ipAddressDB  = " + erefID + " " + ipAddressInRequest + " " + ipAddressInDB);
				
				return "<FAMILY_HISTORY><EREFID>"+ erefID +  "</EREFID><RELATIONS></RELATIONS></FAMILY_HISTORY>";
			
			}
		}
		
				
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		Provider p = new Provider();
		
		String erefid = "12344";

		System.out.println("length:" + erefid.length() );
	
		String result = "fred";
		try {
			result = p.getRelationsByErefID(erefid,"127.0.0.1",true);
			
		} catch (DBException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		System.out.println(result );
	
	}

}