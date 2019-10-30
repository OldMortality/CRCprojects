/*

Disclaimer of Warranties. The author disclaims to the fullest extent authorized by law any and all other warranties, 
whether express or implied, including, without limitation, any implied warranties of title, non-infringement, quiet enjoyment, 
integration, merchantability or fitness for a particular purpose. Without limitation of the foregoing, the author expressly 
does not warrant that:
(a) the software will meet your requirements or expectations;
(b) the software or the software content] will be free of bugs, errors, viruses or other defects;
(c) any results, output, or data provided through or generated by the software will be accurate, up-to-date, complete or reliable;
(d) the software will be compatible with third party software;
(e) any errors in the software will be corrected.

Under no circumstances shall the author be liable to any user for direct, indirect, incidental, consequential, 
special, or exemplary damages, arising from the software, or user's use or misuse of the software or any other services 
provided by the author. 

Such limitation of liability shall apply whether the damages arise from the use or
misuse of the software or any other services supplied by the author
(including such damages incurred by third parties), or errors of the software.

The software is supplied �as is� and all use is at your own risk.



Author: Michel de Lange

Dunedin, January 2014.

 */

package ac.otago.infoscience.eCRC.server;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import ac.otago.infoscience.eCRC.client.UpdateService;
import ac.otago.infoscience.eCRC.client.Util;
import ac.otago.infoscience.eCRC.server.db.Comment;
import ac.otago.infoscience.eCRC.server.db.HibernateUtil;
import ac.otago.infoscience.eCRC.server.db.Relations;
import ac.otago.infoscience.eCRC.shared.CancerHistory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Server side class, which updates the database with the family history of
 * cancer.
 * 
 * @author michel
 * 
 */

@SuppressWarnings("serial")
public class UpdateServiceImpl extends RemoteServiceServlet implements
		UpdateService {

	private static Logger logger = Logger
			.getLogger("ac.otago.infoscience.eCRC.server.UpdateServiceImpl");

	/**
	 * Send the cancer history for the family to the server.
	 * 
	 * @author michel
	 * 
	 * @param history
	 *            , the family history
	 * @param riskLevel
	 *            : Calculated risk level
	 * @param erefID
	 *            : unique ID, provided by the ereferral system.
	 * 
	 */

	public String updateServer(CancerHistory[] history, short riskLevel,
			String erefID) throws IllegalArgumentException {

		logger.log(Level.INFO,
				"About to insert records in the database for erefid:" + erefID);
		String result = "ok";

		String ipAddress = getThreadLocalRequest().getRemoteHost();
		logger.log(Level.INFO, "IP address is: " + ipAddress);

		if (!IPChecked(erefID, ipAddress)) {

			return null;

		}

		// delete all records for this erefID, so that we don't have duplicates
		// when
		// the user presses "Calculate Risk" twice.

		deleteRecords(erefID);

		// now do updates.

		try {
			int counter = 0;
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			logger.log(Level.INFO, "Started the transaction for erefID:"
					+ erefID);

			Relations rels = null;

			// Even if there are no records of family history, we still insert 1
			// record for
			// the patient himself.

			// these variables have no meaning for the patient himself.
			short dummy0 = 0;
			short dummy1 = -1;

			rels = new Relations((short) Util.PATIENT, erefID, "", dummy0,
					dummy1, dummy0, dummy0, dummy1, dummy0, dummy1, riskLevel,
					new Timestamp(System.currentTimeMillis()), ipAddress, null);

			logger.log(Level.INFO, "Inserting the first record for erefID:"
					+ erefID);

			session.save(rels);

			// now the patient record has been written. Do the family history.

			Comment comm = null;

			short prevType = 0;
			for (int i = 0; i < history.length; i++) {
				counter++;
				Short colonCancer = (short) (history[i].isColonCancer() ? 1 : 0);
				Short colonCancerAge = (short) (history[i].getCcAge());
				Short multiplePolyps = (short) (history[i].isMultPols() ? 1 : 0);
				Short otherCancer = (short) (history[i].isOtherCancer() ? 1 : 0);
				Short otherCancerAge = (short) (history[i].getoAge());
				Short deceased = (short) (history[i].isDeceased() ? 1 : 0);
				Short deceasedAge = (short) (history[i].getdAge());
				String comment = escapeHtml(history[i].getComment());
				String eref = escapeHtml(history[i].getErefID());

				// only create a new Comment if the relationship type has
				// changed,
				// because the comments are all the same for each given type.
				short type = (short) history[i].getRelationshipType();
				if (type != prevType) {
					comm = new Comment(erefID, comment);
					prevType = type;
				}

				rels = new Relations(type, eref, history[i].getName(),
						colonCancer, colonCancerAge, multiplePolyps,
						otherCancer, otherCancerAge, deceased, deceasedAge,
						riskLevel, new Timestamp(System.currentTimeMillis()),
						ipAddress, comm);

				logger.log(Level.INFO, "Inserting another record for erefID:"
						+ erefID + " #" + counter);

				session.save(rels);
			}

			// done all relatives

			logger.log(Level.INFO, "About to commit for erefID:" + erefID);

			session.getTransaction().commit();
			logger.log(Level.INFO, "committed updates for erefID:" + erefID);
			session.close();

		} catch (Exception e) {
			result = "Unexpected error updating the database. erefid:" + erefID
					+ " ";

			logger.log(Level.ERROR, result + e);

			e.printStackTrace();

			result = "error";

		}

		return result;
	}

	/**
	 * 
	 * returns true, unless this erefid is already in the database with a
	 * different IPAddress. In that case, this request here is attempting to
	 * update someone else's data.
	 * 
	 * return true if this erefID is not in the database, or it is in de
	 * database with the same IP.
	 * 
	 * @param erefID
	 * @param ipAddress
	 * @return true, unless erefID is already in the database with different
	 *         IPaddress.
	 */
	private boolean IPChecked(String erefID, String ipAddress) {

		logger.debug("IPChecked erefID/IPAddress: " + erefID + "/" + ipAddress);

		boolean result = true;

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

			// where relation.erefID = :e order by relation.relationshipType

			Query q = session
					.createQuery(

							"from Relations as relation where relation.erefID = :e "
									+ " and relation.ipAddress != :i "
									+ "order by relation.relationshipType ")

					.setString("e", erefID).setString("i", ipAddress);

			List<Relations> list = q.list();

			if (list.size() > 1) {

				logger.debug("Found data for erefID " + erefID
						+ " and different IP! ");

				result = false;

			} else {

				result = true;

			}

			session.close();

		} catch (Exception e) {

			String str = "error checking IP address: " + erefID;

			logger.log(Level.ERROR, str + e);

			return false;

		}

		return result;
	}

	void deleteRecords(String erefID) {

		try {
			logger.log(Level.INFO, "Started the transaction to delete. erefID:"
					+ erefID);

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			String hql1 = "delete from Comment where EREF_ID= :patientNumber";
			logger.log(Level.DEBUG, hql1);
			int res1 = session.createQuery(hql1)
					.setString("patientNumber", erefID).executeUpdate();
			logger.log(Level.INFO, "deleted this many records from comments : "
					+ res1);

			String hql2 = "delete from Relations where EREF_ID= :patientNumber";
			int res2 = session.createQuery(hql2)
					.setString("patientNumber", erefID).executeUpdate();
			logger.log(Level.INFO,
					"deleted  this many  records from relations : " + res2);

			logger.log(Level.INFO, "About to commit deletion. erefID:" + erefID);
			session.getTransaction().commit();
			logger.log(Level.INFO, "Delete committed successfully. erefID:"
					+ erefID);

			session.close();
		} catch (Exception e) {

			String str = "error deleting records for id: " + erefID + ". ";

			logger.log(Level.ERROR, str + e);
		}
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

}