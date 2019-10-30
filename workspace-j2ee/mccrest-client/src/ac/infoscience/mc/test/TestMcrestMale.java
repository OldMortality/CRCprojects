package ac.infoscience.mc.test;

import static org.junit.Assert.*;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TestMcrestMale {

	String head = "<?xml version=\"1.0\"?><MR><RISK>";
	String tail = "</RISK><DESCRIPTION>Gallia est omnis divisa in partes tres</DESCRIPTION></MR>";
	
	@Test
	public void test() {
		
		
		
	    ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    
	    String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=60&area=south";
	    WebResource service = client.resource(getBaseURIMale(parms));
	    
	    String result = service.accept(MediaType.TEXT_XML).get(String.class);
	    
	    String risk = "0.06";
	    String expected = head + risk+tail;
		assertEquals("x ", expected ,result);
	}
	
	
	public void genericTestMale(String id,String parms,String risk) {
		
		ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    
	    
	    WebResource service = client.resource(getBaseURIMale(parms));
	    
	    String result = service.accept(MediaType.TEXT_XML).get(String.class);
	    
	    String expected = head + risk+tail;
		assertEquals("x ", expected ,result);
		
	}
	
	
	@Test
	public void MaleTes11() {
		
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=20&area=north";
		String risk = "0.00";
		genericTestMale("1-1",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTes12() {
		
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=40&area=north";
		String risk = "0.01";
		genericTestMale("1-2",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTes13() {
		
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=60&area=north";
		String risk = "0.08";
		genericTestMale("1-3",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTes14() {
	    
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=80&area=north";
		String risk = "0.15";
		genericTestMale("1-4",parms,risk);
	    
		
	}
	
	
	
	@Test
	public void MaleTes21() {
		
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=20&area=midland";
		String risk = "0.00";
		genericTestMale("2-1",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTes22() {
		
		
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=40&area=midland";
		String risk = "0.01";
		genericTestMale("2-2",parms,risk);
	    
		
	}
	
	
	
	
	@Test
	public void MaleTes23() {
		
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=60&area=midland";
		String risk = "0.08";
		genericTestMale("2-3",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTes24() {
		
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=80&area=midland";
		String risk = "0.16";
		genericTestMale("2-4",parms,risk);
	    
		
	}
	
	
	@Test
	public void MaleTest31() {
		
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=20&area=central";
		String risk = "0.00"; // 0.05%
		genericTestMale("3-1",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTest32() {
		
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=40&area=central";
		String risk = "0.01"; // 0.69%
 		genericTestMale("3-2",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTest33() {
		
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=60&area=central";
		String risk = "0.06";
		genericTestMale("3-3",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTest34() {
		
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=80&area=central";
		String risk = "0.13";
		genericTestMale("3-3b",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTest41() {
		
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=20&area=south";
		String risk = "0.00";
		genericTestMale("4-3",parms,risk);
	    
	}
	

	@Test
	public void MaleTest42() {
		
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=40&area=south";
		String risk = "0.01";
		genericTestMale("4-2",parms,risk);
	    
		
	}
	

	@Test
	public void MaleTest43() {
		
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=60&area=south";
		String risk = "0.06";
		genericTestMale("4-3",parms,risk);
	    
		
	}
	

	@Test
	public void MaleTest44() {
		
		String parms = "moles_rarm=5&nmsc=yes&occ=out&birthplace=nz&age=80&area=south";
		String risk = "0.13";
		genericTestMale("4-4",parms,risk);
	    
		
	}
	

	@Test
	public void MaleTest51() {
		
		String parms = "moles_rarm=1&nmsc=yes&occ=inout&birthplace=nz&age=20&area=north";
		String risk = "0.00";
		genericTestMale("5-1",parms,risk);
	    
		
	}
	
	

	@Test
	public void MaleTest52() {
		
		
		String parms = "moles_rarm=1&nmsc=yes&occ=inout&birthplace=nz&age=40&area=north";
		String risk = "0.00";
		genericTestMale("5-2",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTest53() {
		
		String parms = "moles_rarm=0&nmsc=yes&occ=in&birthplace=nz&age=60&area=north";
		String risk = "0.04";
		genericTestMale("5-1",parms,risk);
	    
		
		parms = "moles_rarm=1&nmsc=yes&occ=inout&birthplace=nz&age=80&area=north";
		risk = "0.07";
		genericTestMale("5-3",parms,risk);
	    
		
	}
	

	@Test
	public void MaleTest54() {
		
		
		String parms = "moles_rarm=1&nmsc=yes&occ=inout&birthplace=nz&age=80&area=north";
		String risk = "0.07";
		genericTestMale("5-3",parms,risk);
	    
		
	}
	
	
	@Test
	public void MaleTest61() {
		
		String parms = "moles_rarm=1&nmsc=yes&occ=inout&birthplace=nz&age=20&area=midland";
		String risk = "0.00";
		genericTestMale("6-1",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTest62() {
		
		
		String parms = "moles_rarm=1&nmsc=yes&occ=inout&birthplace=nz&age=40&area=midland";
		String risk = "0.00";
		genericTestMale("6-2",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTest63() {
		
		String parms = "moles_rarm=0&nmsc=yes&occ=in&birthplace=nz&age=60&area=midland";
		String risk = "0.04";
		genericTestMale("6-3",parms,risk);
	    
		
		
	}
	
	@Test
	public void MaleTest64() {
		
		
		String parms = "moles_rarm=1&nmsc=yes&occ=inout&birthplace=nz&age=80&area=midland";
		String risk = "0.08";
		genericTestMale("6-3",parms,risk);
	    
		
	}
	
	
	
	
	@Test
	public void MaleTest71() {
		
		String parms = "moles_rarm=1&nmsc=yes&occ=inout&birthplace=nz&age=20&area=central";
		String risk = "0.00";
		genericTestMale("7-1",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTest72() {
		
		String parms = "moles_rarm=1&nmsc=yes&occ=inout&birthplace=nz&age=40&area=central";
		String risk = "0.00";
		genericTestMale("7-2",parms,risk);
	    
		
	}
	
	
	@Test
	public void MaleTest73() {
		
		String parms = "moles_rarm=0&nmsc=yes&occ=in&birthplace=nz&age=60&area=central";
		String risk = "0.03";
		genericTestMale("7-3",parms,risk);
	    
		
	}

	
	@Test
	public void MaleTest74() {
		
		
		String parms = "moles_rarm=1&nmsc=yes&occ=inout&birthplace=nz&age=80&area=central";
		String risk = "0.06";
		genericTestMale("7-4",parms,risk);
	    
		
	}

	public void MaleTest81() {
		
		String parms = "moles_rarm=1&nmsc=yes&occ=inout&birthplace=nz&age=20&area=south";
		String risk = "0.00";
		genericTestMale("7-1",parms,risk);
	    
		
		
	}


	public void MaleTest82() {
		
		
		String parms = "moles_rarm=1&nmsc=yes&occ=inout&birthplace=nz&age=40&area=south";
		String risk = "0.00";
		genericTestMale("8-2",parms,risk);
	    
		
	}

	
	@Test
	public void MaleTest83() {
		
		String parms = "moles_rarm=0&nmsc=yes&occ=in&birthplace=nz&age=60&area=south";
		String risk = "0.03";
		genericTestMale("8-3",parms,risk);
	    
		
		
	}
	

	@Test
	public void MaleTest84() {
		
		String parms = "moles_rarm=1&nmsc=yes&occ=inout&birthplace=nz&age=80&area=south";
		String risk = "0.06";
		genericTestMale("8-4",parms,risk);
	    
		
	}
	

	@Test
	public void MaleTest91() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=nz&age=20&area=north";
		String risk = "0.00";
		genericTestMale("9-1",parms,risk);
	    
		
	}

	@Test
	public void MaleTest101() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=nz&age=20&area=midland";
		String risk = "0.00";
		genericTestMale("10-1",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTest111() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=nz&age=20&area=central";
		String risk = "0.00";
		genericTestMale("11-1",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTest121() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=nz&age=20&area=south";
		String risk = "0.00";
		genericTestMale("12-1",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTest92() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=nz&age=40&area=north";
		String risk = "0.00";
		genericTestMale("9-1",parms,risk);
	    
		
	}

	@Test
	public void MaleTest102() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=nz&age=40&area=midland";
		String risk = "0.00";
		genericTestMale("10-1",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTest112() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=nz&age=40&area=central";
		String risk = "0.00";
		genericTestMale("11-1",parms,risk);
	    
		
	}
	
	@Test
	public void MaleTest122() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=nz&age=40&area=south";
		String risk = "0.00";
		genericTestMale("12-1",parms,risk);
	    
		
	}
	

	@Test
	public void MaleTest93() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=not_nz&age=60&area=north";
		String risk = "0.00";
		genericTestMale("9-3",parms,risk);
	    
		
	}

	@Test
	public void MaleTest103() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=not_nz&age=60&area=midland";
		String risk = "0.00";
		genericTestMale("9-1",parms,risk);
	    
		
	}
	@Test
	public void MaleTest113() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=not_nz&age=60&area=central";
		String risk = "0.00";
		genericTestMale("9-1",parms,risk);
	    
		
	}
	@Test
	public void MaleTest123() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=not_nz&age=60&area=south";
		String risk = "0.00";
		genericTestMale("9-1",parms,risk);
	    
		
	}

	
	@Test
	public void MaleTest94() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=not_nz&age=80&area=north";
		String risk = "0.01";
		genericTestMale("9-4",parms,risk);
	    
		
	}

	@Test
	public void MaleTest104() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=not_nz&age=80&area=midland";
		String risk = "0.01";
		genericTestMale("9-1",parms,risk);
	    
		
	}
	@Test
	public void MaleTest114() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=not_nz&age=80&area=central";
		String risk = "0.01";
		genericTestMale("11-4",parms,risk);
	    
		
	}
	@Test
	public void MaleTest124() {
		
		String parms = "moles_rarm=0&nmsc=no&occ=in&birthplace=not_nz&age=80&area=south";
		String risk = "0.01";
		genericTestMale("9-1",parms,risk);
	    
		
	}

	
	
	
	

		private static URI getBaseURIMale(String parms) {
	      
		  // infoscience deployment production.
		  //return UriBuilder.fromUri("http://ecrc.otago.ac.nz:8086/ecrcrestp/gethistory?erefid=22").build();
		  //String url = "http://info-nts-12.otago.ac.nz:8086/mcrest/getmelcalcm?" + parms;
		  
		  // local tomcat
		  
		  String url = "http://localhost:8080/mcrest/getmelcalcm?" + parms;
		  
		  
		  
		  return UriBuilder.fromUri(url).build();

	}
		
		private static URI getBaseURIFemale(String parms) {
		      
			  // infoscience deployment production.
			  //return UriBuilder.fromUri("http://ecrc.otago.ac.nz:8086/ecrcrestp/gethistory?erefid=22").build();
			  String url = "http://info-nts-12.otago.ac.nz:8086/mcrest/getmelcalcf?" + parms;
			  
			  // local tomcat
			  
			  
			  return UriBuilder.fromUri(url).build();

		}
	
}
