package ac.infoscience.mc.test;

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

	  private static URI getBaseURI() {
	      
		  // infoscience deployment test
		  //return UriBuilder.fromUri("http://ecrc.otago.ac.nz:8086/ecrcrest/gethistory?erefid=125").build();
		  
		  // local tomcat
		  return UriBuilder.fromUri("http://localhost:8080/ecrcrest/gethistory?erefid=88").build();

	} 
}