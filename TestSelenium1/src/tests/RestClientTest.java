package tests;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;



public class RestClientTest{
	
		 
	  public static void main(String[] args) {
	    ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(getBaseURI("testcase1"));
	    // Fluent interfaces
	    //System.out.println(service.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class).toString());
	    // Get plain text
	    //System.out.println(service.accept(MediaType.TEXT_PLAIN).get(String.class));
	    // Get XML
	    System.out.println(service.accept(MediaType.TEXT_XML).get(String.class));
	    // The HTML
	    //System.out.println(service.accept(MediaType.TEXT_HTML).get(String.class));
	    String risk = parseRiskCategory("aaaaaaa<RISK>3</RISK>");
	    System.out.println("category is: " + risk);

	  }
	  
	  
	  public static String getRest(String erefid) {
		  String result = "";
		  ClientConfig config = new DefaultClientConfig();
		    
		  Client client = Client.create(config);
		    
		    WebResource service = client.resource(getBaseURI(erefid));
		    // Fluent interfaces
		    //System.out.println(service.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class).toString());
		    // Get plain text
		    //System.out.println(service.accept(MediaType.TEXT_PLAIN).get(String.class));
		    // Get XML
		    result =  service.accept(MediaType.TEXT_XML).get(String.class);
		    System.out.println("webservice says: erefid+ "+erefid + ": " + result);
		      
		  
		  return result;
	  }
	  
	  
	  public static  String parseRiskCategory(String rest) {
		  int i = rest.indexOf("</RISK>");
		  return rest.substring(i-1, i);

	  }
	  
	  public static String getRiskCategory(String erefid) {
		  String result = "?";
		  String rest = getRest(erefid);
		  return parseRiskCategory(rest);
	  }

	  private static URI getBaseURI(String erefid) {
	      
		  // infoscience deployment test
		  //return UriBuilder.fromUri("http://ecrc.otago.ac.nz:8086/ecrcrest/gethistory?erefid=22").build();
		  
		  // local tomcat
		  return UriBuilder.fromUri(tests.Utils.ACTIVE_URL_REST + erefid).build();

	} 
	  
	  
	 
}