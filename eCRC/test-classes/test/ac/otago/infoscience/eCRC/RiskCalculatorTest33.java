package test.ac.otago.infoscience.eCRC;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ac.otago.infoscience.eCRC.client.CancerHistoryManager;
import ac.otago.infoscience.eCRC.client.RiskCalculator;
import ac.otago.infoscience.eCRC.client.Util;
import ac.otago.infoscience.eCRC.shared.CancerHistory;

public class RiskCalculatorTest33 {

	Object dummy = null;
	static RiskCalculator riskCalculator = new RiskCalculator();
	static CancerHistoryManager manager = new CancerHistoryManager();

	@BeforeClass
	public static void init() {
		riskCalculator.setCancerHistoryManager(manager);
	}

	@Test
	public void testGetCondition33() {

		// run 1: a primary, a secondary relative, on same side, but no
		// hnpcc or age<55
		//
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 55, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 88, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory[] td1 = { h1, h2 };
		manager.setHistories(td1);

		assertEquals("# condition 3-3 (run 1) ", false,
				riskCalculator.getCondition33(Util.SIDE_FATHER));
		assertEquals("# condition 3-3 (run 1) ", false,
				riskCalculator.getCondition33(Util.SIDE_MOTHER));
		assertEquals("# condition 3-3 (run 1) ", false,
				riskCalculator.getCondition33());

		// run 2: a primary, a secondary relative, on same side, and mother has
		// hnpcc
		//
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 66, false, true,
				(short) 66, false, (short) -1, "");

		CancerHistory h4 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 88, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory[] td2 = { h3, h4 };
		manager.setHistories(td2);

		assertEquals("# condition 3-3 (run 2) ", false,
				riskCalculator.getCondition33(Util.SIDE_FATHER));
		assertEquals("# condition 3-3 (run 2) ", true,
				riskCalculator.getCondition33(Util.SIDE_MOTHER));
		assertEquals("# condition 3-3 (run 2) ", true,
				riskCalculator.getCondition33());

		// run 3: Mother has age < 55, and no hnpcc

		CancerHistory h5 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 54, false, false,
				(short) -1, false, (short) -1, "");
		CancerHistory h6 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 88, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td3 = { h5, h6 };
		manager.setHistories(td3);

		assertEquals("# condition 3-3 (run 3) ", false,
				riskCalculator.getCondition33(Util.SIDE_FATHER));
		assertEquals("# condition 3-3 (run 3) ", true,
				riskCalculator.getCondition33(Util.SIDE_MOTHER));
		assertEquals("# condition 3-3 (run 3) ", true,
				riskCalculator.getCondition33());
		assertEquals(" run 3 ",
				3 , riskCalculator.calculateRisk());

		// run 4: Father and 1 secondary relative. Father has diagnosis
		// at age 54, but on different side of family from the maternal
		// grandmother, so the result should be false.

		CancerHistory h7 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 54, false, false,
				(short) -1, false, (short) -1, "");
		CancerHistory h8 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 88, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td4 = { h7, h8 };
		manager.setHistories(td4);

		assertEquals("# condition 3-3 (run 4a) ", false,
				riskCalculator.getCondition33(Util.SIDE_FATHER));
		assertEquals("# condition 3-3 (run 4b) ", false,
				riskCalculator.getCondition33(Util.SIDE_MOTHER));
		assertEquals("# condition 3-3 (run 4c) ", false,
				riskCalculator.getCondition33());

		// run 5: Father and Mother. Diagnosed at age 55.

		CancerHistory h9 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 55, false, false,
				(short) -1, false, (short) -1, "");
		CancerHistory h10 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 55, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td5 = { h9, h10 };
		manager.setHistories(td5);

		assertEquals("# condition 3-3 (run 5a) ", false,
				riskCalculator.getCondition33(Util.SIDE_FATHER));
		assertEquals("# condition 3-3 (run 5b) ", false,
				riskCalculator.getCondition33(Util.SIDE_MOTHER));
		assertEquals("# condition 3-3 (run 5c) ", false,
				riskCalculator.getCondition33());

		// run 6: Father and Mother. Diagnosed at age 54. Should
		// result in false, because they are not on same side of
		// the family

		CancerHistory h11 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 54, false, false,
				(short) -1, false, (short) -1, "");
		CancerHistory h12 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 55, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td6 = { h11, h12 };
		manager.setHistories(td6);

		assertEquals("# condition 3-3 (run 6a) ", false,
				riskCalculator.getCondition33(Util.SIDE_FATHER));
		assertEquals("# condition 3-3 (run 6b) ", false,
				riskCalculator.getCondition33(Util.SIDE_MOTHER));
		assertEquals("# condition 3-3 (run 6c) ", false,
				riskCalculator.getCondition33());

		// run 7: One primary relative, one secondary, on same side of the
		// family, but primary has no colon cancer but NHPCC. Should return
		// false;

		CancerHistory h13 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", false, (short) -1, false, true,
				(short) 40, false, (short) -1, "");
		CancerHistory h14 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 54, true, true,
				(short) 40, false, (short) -1, "");

		CancerHistory[] td7 = { h13, h14 };
		manager.setHistories(td7);

		assertEquals("# condition 3-3 (run 7a) ", false,
				riskCalculator.getCondition33(Util.SIDE_FATHER));
		assertEquals("# condition 3-3 (run 7b) ", false,
				riskCalculator.getCondition33(Util.SIDE_MOTHER));
		assertEquals("# condition 3-3 (run 7c) ", false,
				riskCalculator.getCondition33());

		// run 8.
		// two secondary relatives with CC, diagnosed under 55 and with HNPCC.
		// Should
		// return false, because there is no primary relative.

		CancerHistory h15 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) -44, false,
				true, (short) 40, true, (short) 44, "");
		CancerHistory h16 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDMOTHER, "", true, (short) 44, true, true,
				(short) 40, true, (short) 44, "");

		CancerHistory[] td8 = { h15, h16 };
		manager.setHistories(td8);

		assertEquals("# condition 3-3 (run 8a) ", false,
				riskCalculator.getCondition33(Util.SIDE_FATHER));
		assertEquals("# condition 3-3 (run 8b) ", false,
				riskCalculator.getCondition33(Util.SIDE_MOTHER));
		assertEquals("# condition 3-3 (run 8c) ", false,
				riskCalculator.getCondition33());

		// run 9.
		// Child and father with CC, one of them diagnosed under 55.
		// should return true on Father's side.

		CancerHistory h17 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 54, false, true,
				(short) 40, false, (short) -1, "");
		CancerHistory h18 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "", true, (short) 64, false, false,
				(short) 40, false, (short) 77, "");

		CancerHistory[] td9 = { h17, h18 };
		manager.setHistories(td9);

		assertEquals("# condition 3-3 (run 9a) ", true,
				riskCalculator.getCondition33(Util.SIDE_FATHER));
		assertEquals("# condition 3-3 (run 9b) ", false,
				riskCalculator.getCondition33(Util.SIDE_MOTHER));
		assertEquals("# condition 3-3 (run 9c) ", true,
				riskCalculator.getCondition33());
		assertEquals(" run 9 ",
				3 , riskCalculator.calculateRisk());

		// run 10.
		// Child and father with CC, both of them diagnosed over 55.
		// Child has hnpcc. should return true on Father's side.

		CancerHistory h19 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 58, false, false,
				(short) -1, false, (short) -1, "");
		CancerHistory h20 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "", true, (short) 58, false, true,
				(short) 58, false, (short) 77, "");

		CancerHistory[] td10 = { h19, h20 };
		manager.setHistories(td10);

		assertEquals("# condition 3-3 (run 10a) ", true,
				riskCalculator.getCondition33(Util.SIDE_FATHER));
		assertEquals("# condition 3-3 (run 10b) ", false,
				riskCalculator.getCondition33(Util.SIDE_MOTHER));
		assertEquals("# condition 3-3 (run 10c) ", true,
				riskCalculator.getCondition33());
		assertEquals(" run 10 ",
				3 , riskCalculator.calculateRisk());

	}

}
