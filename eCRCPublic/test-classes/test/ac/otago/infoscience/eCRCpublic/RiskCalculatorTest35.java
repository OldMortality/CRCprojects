package test.ac.otago.infoscience.eCRCpublic;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ac.otago.infoscience.eCRCpublic.client.CancerHistory;
import ac.otago.infoscience.eCRCpublic.client.CancerHistoryManager;
import ac.otago.infoscience.eCRCpublic.client.RiskCalculator;
import ac.otago.infoscience.eCRCpublic.client.Util;

public class RiskCalculatorTest35 {

	Object dummy = null;
	static RiskCalculator riskCalculator = new RiskCalculator();
	static CancerHistoryManager manager = new CancerHistoryManager();

	@BeforeClass
	public static void init() {
		riskCalculator.setCancerHistoryManager(manager);
	}

	@Test
	public void testGetCondition35() {

		// run 1.
		// father with CC, diagnosed at 50

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 50, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1 };
		manager.setHistories(td1);

		assertEquals("# condition 3-5 (run 1) ", false,
				riskCalculator.getCondition35());

		// run 2.
		// father with CC, diagnosed at 49 ==> true

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 49, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td2 = { h2 };
		manager.setHistories(td2);

		assertEquals("# condition 3-5 (run 2) ", true,
				riskCalculator.getCondition35());
		// risk category is 2, because condition 35 has been removed.
		assertEquals(" run 2",
				2 , riskCalculator.calculateRisk());

		// run 3.
		// grandfather with CC, diagnosed at 49 ==> false, because
		// it is a secondary relative

		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 49, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td3 = { h3 };
		manager.setHistories(td3);

		assertEquals("# condition 3-5 (run 3) ", false,
				riskCalculator.getCondition35());

		
		// run 4.
		// father and grandfather with CC, diagnosed at 49 ==> true

		CancerHistory h4 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 49, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h5 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 49, false,
				false, (short) -1, false, (short) -1, "");

		
		CancerHistory[] td4 = { h4, h5 };
		manager.setHistories(td4);

		assertEquals("# condition 3-5 (run 4) ", true,
				riskCalculator.getCondition35());
		assertEquals(" run 4 ",
				3 , riskCalculator.calculateRisk());
	}

}
