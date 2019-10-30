package test.ac.otago.infoscience.eCRCpublic;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import ac.otago.infoscience.eCRCpublic.client.CancerHistory;
import ac.otago.infoscience.eCRCpublic.client.CancerHistoryManager;
import ac.otago.infoscience.eCRCpublic.client.RiskCalculator;
import ac.otago.infoscience.eCRCpublic.client.Util;

public class RiskCalculatorTestCases {

	Object dummy = null;
	static RiskCalculator riskCalculator = new RiskCalculator();
	static CancerHistoryManager manager = new CancerHistoryManager();

	@BeforeClass
	public static void init() {
		riskCalculator.setCancerHistoryManager(manager);
	}

	@Test
	public void testcase1() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 49, false, false,
				(short) -1, false, (short) -1, "");
		CancerHistory[] td1 = { h1 };
		manager.setHistories(td1);

		// category 2, because condition35 has been removed
		assertEquals("case 1 ", 2, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase2() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 54, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 54, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2 };
		manager.setHistories(td1);

		assertEquals("case 2 ", 0, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase3() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 80, false, false,
				(short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 54, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2 };
		manager.setHistories(td1);
		assertEquals("case 3a", true, riskCalculator.getCondition33());
		assertEquals("case 3b ", 3, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase4() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 60, false, false,
				(short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 60, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2 };
		manager.setHistories(td1);
		assertEquals("case 4 ", 2, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase5() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 44, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDMOTHER, "", true, (short) 44, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 44, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2, h3 };
		manager.setHistories(td1);
		assertEquals("case 5 ", 0, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase6() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 50, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1 };
		manager.setHistories(td1);
		assertEquals("case 6 ", 2, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase7() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_AUNTIES, "Joe", true, (short) 100, true,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_AUNTIES, "Fred", true, (short) 60, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_AUNTIES, "Rita", true, (short) 60, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2, h3 };
		manager.setHistories(td1);
		assertEquals("case 7 ", 3, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase8() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 100, false, false,
				(short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "Fred", true, (short) 54, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2 };
		manager.setHistories(td1);
		assertEquals("case 8 ", 3, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase9() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_AUNTIES, "", true, (short) 75, true, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1 };
		manager.setHistories(td1);
		assertEquals("case 9 ", 3, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase10() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 70, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDMOTHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2, h3 };
		manager.setHistories(td1);
		assertEquals("case 10 ", 1, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase11() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 80, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 60, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2 };
		manager.setHistories(td1);
		assertEquals("case 11 ", 1, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase12() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 65, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 70, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2, h3 };
		manager.setHistories(td1);
		assertEquals("case 12 ", 3, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase13() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDMOTHER, "", true, (short) 100, false,
				true, (short) 100, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 55, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2 };
		manager.setHistories(td1);
		assertEquals("case 13 ", 3, riskCalculator.calculateRisk());

	}

	@Test
	@Ignore
	public void testcase14() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 100, false,
				true, (short) 100, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 100, false,
				true, (short) 100, false, (short) -1, "");

		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDMOTHER, "", true, (short) 100, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h4 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 100, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2, h3, h4 };
		manager.setHistories(td1);
		assertEquals("case 14 ", 0, riskCalculator.calculateRisk());
 
	}

	@Test
	public void testcase15() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 100, false,
				true, (short) 100, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 90, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2 };
		manager.setHistories(td1);
		assertEquals("case 15 ", 1, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase16() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 60, true, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1 };
		manager.setHistories(td1);
		assertEquals("case 16 ", 3, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase17() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 54, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 54, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 57, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2, h3 };
		manager.setHistories(td1);
		assertEquals("case 17 ", 1, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase18() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 54, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_AUNTIES, "", true, (short) 80, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2 };
		manager.setHistories(td1);
		assertEquals("case 18 ", 3, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase19() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 80, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 81, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 82, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h4 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDMOTHER, "", true, (short) 83, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2, h3, h4 };
		manager.setHistories(td1);
		assertEquals("case 19 ", 0, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase20() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "Fred", true, (short) 56, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "Joe", true, (short) 56, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2 };
		manager.setHistories(td1);
		assertEquals("case 20 ", 2, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase21() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 44, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 80, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2 };
		manager.setHistories(td1);
		assertEquals("case 21 ", 1, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase22() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2, h3 };
		manager.setHistories(td1);
		assertEquals("case 22 ", 0, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase23() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", false, (short) -1, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1 };
		manager.setHistories(td1);
		assertEquals("case 23 ", 0, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase24() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 60, true,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1 };
		manager.setHistories(td1);
		assertEquals("case 24 ", 3, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase25() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 70, false, false,
				(short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "", true, (short) 57, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2 };
		manager.setHistories(td1);
		assertEquals("case 25 ", 2, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase26() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "", true, (short) 40, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1 };
		manager.setHistories(td1);
		assertEquals("case 26 ", 2, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase27() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 54, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 70, false, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2, h3 };
		manager.setHistories(td1);
		assertEquals("case 27 ", 1, riskCalculator.calculateRisk());

	}
	
	@Test
	@Ignore
	
	/**
	 * This is the troublesome HNPCC case
	 * 
	 */
	public void testcase28() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 80, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 80, false,
				true, (short) 80, false, (short) -1, "");
		
		CancerHistory[] td1 = { h1, h2 };
		manager.setHistories(td1);
		assertEquals("case 28 ", 3, riskCalculator.calculateRisk());

	}
	

	@Test
	public void testcase29() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 44, false,
				false, (short) -1, false, (short) -1, "");

		
		CancerHistory[] td1 = { h1 };
		manager.setHistories(td1);
		assertEquals("case 29 ", 0, riskCalculator.calculateRisk());

	}


	@Test
	public void testcase30() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 80, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDMOTHER, "", true, (short) 104, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.SIBLINGS, "", true, (short) 55, false,
				false, (short) -1, false, (short) -1, "");

		
		CancerHistory[] td1 = { h1, h2, h3 };
		manager.setHistories(td1);
		assertEquals("case 30 ", 3, riskCalculator.calculateRisk());

	}


	@Test
	public void testcase31() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 44, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 80, false,
				false, (short) -1, false, (short) -1, "");
		
		
		CancerHistory[] td1 = { h1, h2};
		manager.setHistories(td1);
		assertEquals("case 31 ", 1, riskCalculator.calculateRisk());

	}

	
	@Test
	public void testcase32() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.SIBLINGS, "", true, (short) 60, false,
				false, (short) -1, false, (short) -1, "");
		
		
		CancerHistory[] td1 = { h1, h2, h3};
		manager.setHistories(td1);
		assertEquals("case 32 ", 3, riskCalculator.calculateRisk());

	}



	@Test
	public void testcase33() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDMOTHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h4 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");
		
		
		
		CancerHistory[] td1 = { h1, h2, h3, h4};
		manager.setHistories(td1);
		assertEquals("case 33 ", 3, riskCalculator.calculateRisk());

	}

	@Test
	public void testcase34() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 90, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "", true, (short) 90, false,
				true, (short) 90, false, (short) -1, "");
		
		
		
		CancerHistory[] td1 = { h1, h2};
		manager.setHistories(td1);
		assertEquals("case 34 ", 3, riskCalculator.calculateRisk());

	}
	
	@Test
	public void testcase35() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_AUNTIES, "", true, (short) 42, false,
				false, (short) -1, false, (short) -1, "");
		
		
		
		CancerHistory[] td1 = { h1};
		manager.setHistories(td1);
		assertEquals("case 35 ", 0, riskCalculator.calculateRisk());

	}
	

	@Test
	public void testcase36() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 55, false,
				false, (short) -1, false, (short) -1, "");
		
		
		
		CancerHistory[] td1 = { h1};
		manager.setHistories(td1);
		assertEquals("case 36 ", 1 , riskCalculator.calculateRisk());

	}

	
	@Test
	public void testcase37() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_AUNTIES, "Joe", true, (short) 80, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_AUNTIES, "Fred", true, (short) 60, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_AUNTIES, "Rita", true, (short) 60, false,
				false, (short) -1, false, (short) -1, "");
		
		
		
		CancerHistory[] td1 = { h1, h2, h3};
		manager.setHistories(td1);
		assertEquals("case 37 ", 0 , riskCalculator.calculateRisk());

	}

	@Test
	public void testcase38() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 54, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 80, false,
				false, (short) -1, false, (short) -1, "");
		
		
		
		CancerHistory[] td1 = { h1, h2};
		manager.setHistories(td1);
		assertEquals("case 38 ", 2 , riskCalculator.calculateRisk());

	}

	
	@Test
	public void testcase39() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.SIBLINGS, "", true, (short) 45, false,
				false, (short) -1, false, (short) -1, "");
		
		
		CancerHistory[] td1 = { h1};
		manager.setHistories(td1);
		assertEquals("case 39 ", 2 , riskCalculator.calculateRisk());

	}

	@Test
	public void testcase40() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 80, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 81, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDMOTHER, "", true, (short) 82, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h4 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 83, true,
				false, (short) -1, false, (short) -1, "");
		
		
		CancerHistory[] td1 = { h1, h2, h3, h4};
		manager.setHistories(td1);
		assertEquals("case 40 ", 3 , riskCalculator.calculateRisk());

	}

	@Test
	public void testcase41() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 55, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 60, false,
				false, (short) -1, false, (short) -1, "");
		
		
		CancerHistory[] td1 = { h1, h2};
		manager.setHistories(td1);
		assertEquals("case 41 ", 1 , riskCalculator.calculateRisk());

	}


	@Test
	public void testcase42() {

		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 44, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 75, false,
				false, (short) -1, false, (short) -1, "");
		
		
		CancerHistory[] td1 = { h1, h2};
		manager.setHistories(td1);
		assertEquals("case 42 ", 3 , riskCalculator.calculateRisk());

	}


	@Test
	public void testcase43() {
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 80, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "", true, (short) 40, false,
				false, (short) -1, false, (short) -1, "");

		
		
		CancerHistory[] td1 = { h1, h2};
		manager.setHistories(td1);
		assertEquals("case 43 ", 3 , riskCalculator.calculateRisk());

	}

	@Test
	public void testcase44() {
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 70, false,
				true, (short) 70, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDMOTHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");
		
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h4 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");

		
		
		CancerHistory[] td1 = { h1, h2, h3, h4};
		manager.setHistories(td1);
		assertEquals("case 44 ", 3 , riskCalculator.calculateRisk());

	}
	
	@Test
	public void testcase45() {
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDMOTHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");
		
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 70, false,
				false, (short) -1, false, (short) -1, "");

		
		
		CancerHistory[] td1 = { h1, h2, h3};
		manager.setHistories(td1);
		assertEquals("case 45 ", 3 , riskCalculator.calculateRisk());

	}
	
	@Test
	public void testcase46() {
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "", true, (short) 100, false,
				true, (short) 100, false, (short) -1, "");

		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 55, false,
				false, (short) -1, false, (short) -1, "");
		
		
		
		CancerHistory[] td1 = { h1, h2};
		manager.setHistories(td1);
		assertEquals("case 46 ", 1 , riskCalculator.calculateRisk());

	}
	
	@Test
	public void testcase47() {
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 44, false,
				false, (short) -1, false, (short) -1, "");
		
		
		
		CancerHistory[] td1 = { h1};
		manager.setHistories(td1);
		assertEquals("case 47 ", 0 , riskCalculator.calculateRisk());

	}
	
	
	@Test
	public void testcase48() {
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "", true, (short) 44, false,
				false, (short) -1, false, (short) -1, "");
		
		
		
		CancerHistory[] td1 = { h1};
		manager.setHistories(td1);
		assertEquals("case 48 ", 0 , riskCalculator.calculateRisk());

	}
	
	
	@Test
	public void testcase49() {
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "", true, (short) 56, false,
				false, (short) -1, false, (short) -1, "");
		
		
		
		CancerHistory[] td1 = { h1};
		manager.setHistories(td1);
		assertEquals("case 49 ", 1 , riskCalculator.calculateRisk());

	}
	
	@Test
	public void testcase50() {
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_AUNTIES, "Fred", true, (short) 60, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_AUNTIES, "Rita", false, (short) -1, false,
				true, (short) 60, false, (short) -1, "");
		
		
		
		
		CancerHistory[] td1 = { h1, h2};
		manager.setHistories(td1);
		assertEquals("case 50 ", 0 , riskCalculator.calculateRisk());

	}
	

	@Test
	public void testcase51() {
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "Fred", true, (short) 100, true,
				false, (short) -1, false, (short) -1, "");
		
		
		
		
		CancerHistory[] td1 = { h1};
		manager.setHistories(td1);
		assertEquals("case 51 ", 3 , riskCalculator.calculateRisk());

	}

	
}
