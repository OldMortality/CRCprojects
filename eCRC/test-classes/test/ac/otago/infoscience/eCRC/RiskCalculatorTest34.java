package test.ac.otago.infoscience.eCRC;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ac.otago.infoscience.eCRC.client.CancerHistoryManager;
import ac.otago.infoscience.eCRC.client.RiskCalculator;
import ac.otago.infoscience.eCRC.client.Util;
import ac.otago.infoscience.eCRC.shared.CancerHistory;

public class RiskCalculatorTest34 {

	Object dummy = null;
	static RiskCalculator riskCalculator = new RiskCalculator();
	static CancerHistoryManager manager = new CancerHistoryManager();

	@BeforeClass
	public static void init() {
		riskCalculator.setCancerHistoryManager(manager);
	}

	@Test
	public void testGetCondition34() {

		// run 1.
		// father with CC and multiple polyps

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 58, true, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1 };
		manager.setHistories(td1);

		assertEquals("# condition 3-4 (run 1) ", true,
				riskCalculator.getCondition34());
		assertEquals(" run 1 ",
				3 , riskCalculator.calculateRisk());

		// run 2.
		// father with CC, no multiple polyps

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 58, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td2 = { h2 };
		manager.setHistories(td2);

		assertEquals("# condition 3-4 (run 2) ", false,
				riskCalculator.getCondition34());

		// run 3.
		// maternal grandmother with CC, and multiple polyps

		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 58, true,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td3 = { h3 };
		manager.setHistories(td3);

		assertEquals("# condition 3-4 (run 2) ", true,
				riskCalculator.getCondition34());
		assertEquals(" run 3 ",
				3 , riskCalculator.calculateRisk());

		// run 4.
		// maternal grandmother with CC, and no multiple polyps

		CancerHistory h4 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 58, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td4 = { h4 };
		manager.setHistories(td4);

		assertEquals("# condition 3-4 (run 4) ", false,
				riskCalculator.getCondition34());

		// run 5.
		// several relatives, but no multiple polyps

		CancerHistory h5 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 58, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h6 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 58, false, false,
				(short) -1, false, (short) -1, "");
		CancerHistory h7 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 58, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td5 = { h5, h6, h7 };
		manager.setHistories(td5);

		assertEquals("# condition 3-4 (run 5) ", false,
				riskCalculator.getCondition34());

		// run 6.
		// several relatives, one with multiple polyps among them

		CancerHistory h8 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 58, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h9 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 58, true, false,
				(short) -1, false, (short) -1, "");
		CancerHistory h10 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 58, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td6 = { h8, h9, h10 };
		manager.setHistories(td6);

		assertEquals("# condition 3-4 (run 6) ", true,
				riskCalculator.getCondition34());
		assertEquals(" run 6 ",
				3 , riskCalculator.calculateRisk());

	}

}
