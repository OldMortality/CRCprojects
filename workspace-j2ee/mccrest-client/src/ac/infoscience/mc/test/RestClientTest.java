package ac.infoscience.mc.test;

import java.net.URI;

import javax.ws.rs.QueryParam;
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
	    
	    WebResource service = client.resource(getBaseURI());
	    // Fluent interfaces
	    //System.out.println(service.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class).toString());
	    // Get plain text
	    //System.out.println(service.accept(MediaType.TEXT_PLAIN).get(String.class));
	    // Get XML
	    System.out.println(service.accept(MediaType.TEXT_XML).get(String.class));
	    // The HTML
	    //System.out.println(service.accept(MediaType.TEXT_HTML).get(String.class));

	  }

	  private static String concat2Parms (String s1, String s2) {
		  return s1 + "&" + s2;
	  }
	  
	  private static String concatParms (String[] s ) {
		  StringBuffer sb = new StringBuffer(s[0]);
		  for (int i=1;i<s.length;i++) {
			  sb = sb.append("&" + s[i]);
		  }

		  String result = sb.toString();

		  System.out.println(result);
		  return result;
		  
		  
	  }
	  
	  private static URI getBaseURI() {
	      
		  // infoscience deployment test
		  //return UriBuilder.fromUri("http://ecrc.otago.ac.nz:8086/mcrest/getmelcalcf").build();
		  
		  // local tomcat
		  
		  // female
		  
		  String[] pFemale = {"skincolour=olive","famhxmoles=dont_know","moles_rarm=2","nmsc=no","age=33","area=central"};
		  
		  
		  String parmsFemale = concatParms(pFemale);
		  //return UriBuilder.fromUri("http://localhost:8080/mcrest/getmelcalcf?"+parmsFemale).build();
		  
		  String[] pMale = { "moles_rarm=7","nmsc=yes","occ=in","birthplace=nz","age=50","age=63","area=north" }; 
		  String parmsMale = concatParms(pMale);
		  return UriBuilder.fromUri("http://localhost:8080/mcrest/getmelcalcm?"+parmsMale).build();

		  
	} 
}