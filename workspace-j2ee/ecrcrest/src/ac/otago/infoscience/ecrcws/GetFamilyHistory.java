package ac.otago.infoscience.ecrcws;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 
 * ecrcrest web service class. Returns the family history as xml.
 * 
 * @author michel
 *
 */

//Sets the path to base URL + /gethistory
@Path("/gethistory")
public class GetFamilyHistory {

	private static Logger logger = Logger
			.getLogger("ac.otago.infoscience.ecrcws.GetFamilyHistory");

	// @Context javax.servlet.http.HttpServletRequest request

	@GET
	@Produces(MediaType.TEXT_XML)
	public Response getXMLHistory(
			@Context javax.servlet.http.HttpServletRequest request,
			@QueryParam("erefid") String ID) {
		
		logger.info("getXMLHistory ID=" + ID);

		String ipAddress = request.getRemoteHost();
		
		logger.log(Level.INFO, "IP address is: " + ipAddress);

		Provider prov = new Provider();
		
		Properties prop = new Properties();
		
		// only return meaningful data if the requesting IP address is the one on the DB

		boolean checkIP = true;
		
		
		// add this string as a Access-Control-Allow-Origin header
		
		String path = "config.properties";
		
		String allowHeader = "";
		
		try {

			InputStream is =
					  this.getClass().getClassLoader().getResourceAsStream(path);
			
			prop.load(is);
			
			allowHeader = prop.getProperty("allow_header");
			
			if (prop.getProperty("checkip")  != null & prop.getProperty("checkip").equals("false"))  {
				
				checkIP = false;
				
			}
			
		}
			
		catch( Exception e) {
			
			logger.error("Error loading the properties file " + path,e);
		
		}
		

		// payload

		try {

			String xml = "<?xml version=\"1.0\"?>"
					+ prov.getRelationsByErefID(ID, ipAddress, checkIP);
			
			// Response headers, so e-referral system can call this from the
			// browser.
			ResponseBuilder builder = Response.ok(xml);
			
			if (allowHeader != null && allowHeader.length() > 0 ) {
				
				builder.language("en").header("Access-Control-Allow-Origin",
					allowHeader);
		
			}
			
			return builder.build();

		} catch (Exception e) {
			
			ResponseBuilder builder = Response.serverError();

			if (allowHeader != null && allowHeader.length() > 0 ) {
				
				builder.language("en").header("Access-Control-Allow-Origin",
					allowHeader);
		
			} 
			
			builder.language("en").header("Access-Control-Allow-Origin",allowHeader);
			
			return builder.build();

		}

	}

}