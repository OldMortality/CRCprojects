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

public class TestMcrestFemale {

	String head = "<?xml version=\"1.0\"?><MR><RISK>";
	String tail = "</RISK><DESCRIPTION>Gallia est omnis divisa in partes tres</DESCRIPTION></MR>";

	public void genericTestFemale(String id, String parms, String risk) {

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);

		WebResource service = client.resource(getBaseURIFemale(parms));

		String result = service.accept(MediaType.TEXT_XML).get(String.class);

		String expected = head + risk + tail;
		assertEquals("x ", expected, result);

	}

	// /////////////////////////
	// ///////////////////////
	@Test
	public void female11() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=20&area=north";

		String risk = "0.01";
		genericTestFemale("1-1", parms, risk);

	}

	@Test
	public void female11b() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=4&age=20&area=north";

		String risk = "0.01";
		genericTestFemale("1-1", parms, risk);

	}

	@Test
	public void female21() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=20&area=midland";

		String risk = "0.01";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female31() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=20&area=central";

		String risk = "0.01";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female41() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=20&area=south";

		String risk = "0.01";
		genericTestFemale("", parms, risk);

	}

	// ////////////////////////

	@Test
	public void female12() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=40&area=north";

		String risk = "0.05";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female22() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=40&area=midland";

		String risk = "0.05";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female32() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=40&area=central";

		String risk = "0.04";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female42() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=40&area=south";

		String risk = "0.05";
		genericTestFemale("", parms, risk);

	}

	// ////////////

	@Test
	public void female13() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=60&area=north";

		String risk = "0.11";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female23() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=60&area=midland";

		String risk = "0.13";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female33() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=60&area=central";

		String risk = "0.10";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female43() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=60&area=south";

		String risk = "0.09";
		genericTestFemale("", parms, risk);

	}

	// //////////////////

	@Test
	public void female14() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=80&area=north";

		String risk = "0.16";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female24() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=80&area=midland";

		String risk = "0.17";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female34() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=80&area=central";

		String risk = "0.14";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female44() {

		String parms = "skincolour=fair&famhxmoles=yes&nmsc=yes&moles_rarm=3&age=80&area=south";

		String risk = "0.14";
		genericTestFemale("", parms, risk);

	}

	// ////////////////////////////
	// //MEDIUM RISK //////////////////////////

	@Test
	public void female51() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=20&area=north";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female61() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=20&area=midland";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female71() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=20&area=central";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female81() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=20&area=south";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	// //////////////////

	// ////////////////////////////
	// //MEDIUM RISK age 40//////////////////////////

	@Test
	public void female52() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=40&area=north";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female62() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=40&area=midland";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female72() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=40&area=central";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female82() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=40&area=south";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	// //////////////////

	// ////////////////////////////
	// //MEDIUM RISK age 60//////////////////////////

	@Test
	public void female53() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=60&area=north";

		String risk = "0.01";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female63() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=60&area=midland";

		String risk = "0.01";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female73() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=60&area=central";

		String risk = "0.01";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female83() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=60&area=south";

		String risk = "0.01";
		genericTestFemale("", parms, risk);

	}

	// //////////////////

	// ////////////////////////////
	// //MEDIUM RISK age 80//////////////////////////

	@Test
	public void female54() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=80&area=north";

		String risk = "0.01";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female64() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=80&area=midland";

		String risk = "0.01";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female74() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=80&area=central";

		String risk = "0.01";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female84() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=80&area=south";

		String risk = "0.01";
		genericTestFemale("", parms, risk);

	}

	// //////////////////

	// ////////////////////////////
	// //LOW RISK age 20//////////////////////////

	@Test
	public void female91() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=20&area=north";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female101() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=20&area=midland";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female111() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=20&area=central";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female121() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=20&area=south";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	// //////////////////

	// ////////////////////////////
	// //LOW RISK age 40//////////////////////////

	@Test
	public void female92() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=40&area=north";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female102() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=40&area=midland";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female112() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=40&area=central";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female122() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=40&area=south";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	// //////////////////

	// ////////////////////////////
	// //LOW RISK age 60//////////////////////////

	@Test
	public void female93() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=60&area=north";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female103() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=60&area=midland";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female113() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=60&area=central";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female123() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=60&area=south";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	// //////////////////

	// ////////////////////////////
	// //LOW RISK age 80//////////////////////////

	@Test
	public void female94() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=80&area=north";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female104() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=60&area=midland";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female114() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=80&area=central";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	@Test
	public void female124() {

		String parms = "skincolour=fair&famhxmoles=no&nmsc=no&moles_rarm=2&age=80&area=south";

		String risk = "0.00";
		genericTestFemale("", parms, risk);

	}

	// //////////////////

	private static URI getBaseURIFemale(String parms) {

		// infoscience deployment production.
		// return
		// UriBuilder.fromUri("http://ecrc.otago.ac.nz:8086/ecrcrestp/gethistory?erefid=22").build();
		// String url =
		// "http://info-nts-12.otago.ac.nz:8086/mcrest/getmelcalcf?" + parms;

		// local tomcat

		String url = "http://localhost:8080/mcrest/getmelcalcf?" + parms;

		return UriBuilder.fromUri(url).build();

	}

}
