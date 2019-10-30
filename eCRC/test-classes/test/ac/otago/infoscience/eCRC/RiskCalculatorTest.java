package test.ac.otago.infoscience.eCRC;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ac.otago.infoscience.eCRC.client.CancerHistoryManager;
import ac.otago.infoscience.eCRC.client.RiskCalculator;
import ac.otago.infoscience.eCRC.client.Util;
import ac.otago.infoscience.eCRC.shared.CancerHistory;

public class RiskCalculatorTest {

	Object dummy = null;
	static RiskCalculator riskCalculator = new RiskCalculator();
	static CancerHistoryManager manager = new CancerHistoryManager();

	@BeforeClass
	public static void init() {
		riskCalculator.setCancerHistoryManager(manager);
	}

	@Test
	public void testGetCondition1() {

		// run 1: a secondary relative
		//
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "pat grandma name", false,
				(short) -1, true, false, (short) -1, false, (short) -1,
				"comment: mat grandma 55 mult hnpcc 56 dec 60");
		CancerHistory[] td1 = { h1 };
		manager.setHistories(td1);

		assertEquals("# primary relatives with colon cancer 55+ (test 1) ",
				false, riskCalculator.getCondition1());
		
		assertEquals("run 1 ",
				0 , riskCalculator.calculateRisk());
		
		// run 2: Primary relative, but too young for this test
		//
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "pat grandma name", true, (short) 54,
				false, false, (short) 54, false, (short) -1,
				"comment: child of 54");
		CancerHistory[] td2 = { h1, h2 };
		manager.setHistories(td2);

		assertEquals("# primary relatives with colon cancer 55+ (test 2) ",
				false, riskCalculator.getCondition1());
		
		
		// run 2b: Primary relative, no CC, so test should be false
		//
		CancerHistory h2b = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "child name", false, (short) -1, true,
				true, (short) 52, true, (short) 54, "comment: child, no cc");
		CancerHistory[] td2b = { h1, h2, h2b };
		manager.setHistories(td2b);

		assertEquals("# primary relatives with colon cancer 55 (test 2b)",
				false, riskCalculator.getCondition1());

		// run 3: Primary relative, 55+, so test should be true
		//
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "child name", true, (short) 55, true,
				false, (short) -1, false, (short) -1, "comment: child of 54");
		CancerHistory[] td3 = { h1, h2, h3 };
		manager.setHistories(td3);

		assertEquals("# primary relatives with colon cancer 55 (test 3)", true,
				riskCalculator.getCondition1());

		// run 4: Primary relative, of 52. no CC but hnpcc
		// leave h3 out, so test should return false
		//
		CancerHistory h4 = new CancerHistory("NHI123", "eref1",
				(short) Util.SIBLINGS, "sibling name", false, (short) 52, true,
				false, (short) -1, false, (short) -1, "comment: sibling of 52");
		CancerHistory[] td4 = { h1, h2, h4 };
		manager.setHistories(td4);

		assertEquals("# primary relatives with colon cancer 55 (test 4)",
				false, riskCalculator.getCondition1());

		// run 5: Primary relative, of 52. condition 1 should still return true
		//
		CancerHistory h5 = new CancerHistory("NHI123", "eref1",
				(short) Util.SIBLINGS, "child name", true, (short) 52, true,
				false, (short) -1, false, (short) -1, "comment: sibling of 52");
		CancerHistory[] td5 = { h1, h2, h3, h5 };
		manager.setHistories(td5);

		assertEquals("# primary relatives with colon cancer 55 (test 5)", true,
				riskCalculator.getCondition1());

	}

	@Test
	public void testGetCondition2_1() {

		// run 1: a secondary relative
		//
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "pat grandma name", false,
				(short) -1, true, false, (short) -1, false, (short) -1,
				"comment: mat grandma 55 mult hnpcc 56 dec 60");
		CancerHistory[] td1 = { h1 };
		manager.setHistories(td1);

		assertEquals("# primary relatives with colon cancer < 55 (test 1) ",
				false, riskCalculator.getCondition21());

		// run 2: Primary relative, but too old for this test
		//
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "child", true, (short) 55, false, false,
				(short) -1, false, (short) -1, "comment: child of 55");
		CancerHistory[] td2 = { h1, h2 };
		manager.setHistories(td2);

		assertEquals("# primary relatives with colon cancer < 55 (test 2) ",
				false, riskCalculator.getCondition21());

		// run 3: Primary relative, 54, so test should be true
		//
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "child name", true, (short) 54, true,
				false, (short) -1, false, (short) -1, "comment: child of 54");
		CancerHistory[] td3 = { h1, h2, h3 };
		manager.setHistories(td3);

		assertEquals("# primary relatives with colon cancer 55 (test 3)", true,
				riskCalculator.getCondition21());

		// run 4: Primary relative, of 52. condition 1 should still return true
		//
		CancerHistory h4 = new CancerHistory("NHI123", "eref1",
				(short) Util.SIBLINGS, "child name", true, (short) 52, true,
				false, (short) -1, false, (short) -1, "comment: sibling of 52");
		CancerHistory[] td4 = { h1, h2, h3, h4 };
		manager.setHistories(td4);

		assertEquals("# primary relatives with colon cancer 55 (test 5)", true,
				riskCalculator.getCondition21());

		// run 5: only h4
		CancerHistory[] td4_2 = { h4 };
		manager.setHistories(td4_2);

		assertEquals("# primary relatives with colon cancer 55 (test 5)", true,
				riskCalculator.getCondition21());

		// run 6. One secondary relative of 44

		CancerHistory h5 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_AUNTIES, "auntie", true, (short) 54, false,
				false, (short) -1, false, (short) -1, "comment: auntie of 44");
		CancerHistory[] td5 = { h5 };
		manager.setHistories(td5);

		assertEquals("# primary relatives with colon cancer 55 (test 6)",
				false, riskCalculator.getCondition21());

	}

	@Test
	public void testGetCondition2_2() {

		// run 1: a secondary relative
		//
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "pat grandma name", true,
				(short) -1, true, false, (short) -1, false, (short) -1,
				"comment: mat grandma 55 mult hnpcc 56 dec 60");
		CancerHistory[] td1 = { h1 };
		manager.setHistories(td1);

		assertEquals("# secondary relatives (test 1) ", false,
				riskCalculator.getCondition22());

		// run 2: 2 secondary relatives
		//
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "pat grandma name", true,
				(short) -1, true, false, (short) -1, false, (short) -1,
				"comment: mat grandma 55 mult hnpcc 56 dec 60");
		CancerHistory[] td2 = { h1, h2 };
		manager.setHistories(td2);

		assertEquals("# secondary relatives (test 2) ", false,
				riskCalculator.getCondition22());

		// run 3: 2 secondary relatives, and one primary
		//
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "father name", true, (short) 77, true,
				false, (short) -1, false, (short) -1, "comment: father 77 ");
		CancerHistory[] td3 = { h1, h2, h3 };
		manager.setHistories(td3);
		assertEquals("# secondary relatives (test 3) ", false,
				riskCalculator.getCondition22());

		// run 4: 2 secondary relatives, and one primary with CC, and one
		// without CC
		//
		CancerHistory h4 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "father name", false, (short) -1, true,
				false, (short) -1, false, (short) -1, "comment: father 77 ");
		CancerHistory[] td4 = { h1, h2, h3, h4 };
		manager.setHistories(td4);

		assertEquals("# secondary relatives (test 4) ", false,
				riskCalculator.getCondition22());

		// run 5: 2 secondary relatives, and 2 primaries with CC, one without
		//
		CancerHistory h5 = new CancerHistory("NHI123", "eref1",
				(short) Util.SIBLINGS, "sibling name", true, (short) 52, true,
				false, (short) -1, false, (short) -1, "comment: sibling 52 ");
		CancerHistory[] td5 = { h1, h2, h3, h4, h5 };
		manager.setHistories(td5);
		assertEquals("# secondary relatives (test 5) ", true,
				riskCalculator.getCondition22());
	}

	@Test
	public void testGetCondition3_2() {

		// run 1: father, paternal uncle + mother
		//
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "father name", true, (short) -1, true,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_AUNTIES, "pat uncle name", true, (short) -1,
				true, false, (short) -1, false, (short) -1, "");
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "mother name", true, (short) -1, true,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2, h3 };
		manager.setHistories(td1);

		assertEquals("condition 3_2 father (test 1A) ", false,
				riskCalculator.getCondition32(Util.SIDE_FATHER));
		assertEquals("condition 3_2 father (test 1B) ", false,
				riskCalculator.getCondition32(Util.SIDE_MOTHER));
		assertEquals("condition 3_2 father (test 1C) ", false,
				riskCalculator.getCondition32());
		

		// RUN 2: Add a child: Now it should be true on the father's side
		CancerHistory h4 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "child name", true, (short) 77, true,
				false, (short) -1, false, (short) -1, "");
		CancerHistory[] td2 = { h1, h2, h3, h4 };
		manager.setHistories(td2);

		assertEquals("condition 3_2 father (test 2A) ", true,
				riskCalculator.getCondition32(Util.SIDE_FATHER));
		assertEquals("condition 3_2 father (test 2B) ", false,
				riskCalculator.getCondition32(Util.SIDE_MOTHER));
		assertEquals("condition 3_2 father (test 2C) ", true,
				riskCalculator.getCondition32());
		assertEquals("run 2 ",
				3 , riskCalculator.calculateRisk());
		
		
		// RUN 3: As 2, but the child does not have CC
		h4 = new CancerHistory("NHI123", "eref1", (short) Util.CHILDREN,
				"child name", false, (short) -1, true, false, (short) -1,
				false, (short) -1, "");
		CancerHistory[] td3 = { h1, h2, h3, h4 };
		manager.setHistories(td3);

		assertEquals("condition 3_2 father (test 2A) ", false,
				riskCalculator.getCondition32(Util.SIDE_FATHER));
		assertEquals("condition 3_2 father (test 2B) ", false,
				riskCalculator.getCondition32(Util.SIDE_MOTHER));
		assertEquals("condition 3_2 father (test 2C) ", false,
				riskCalculator.getCondition32());

		// RUN 4: 3 secondary relatives on mother's side
		// should be false, because of no primary relative
		CancerHistory h5 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_AUNTIES, "auntie", true, (short) 44, true,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h6 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_AUNTIES, "auntie", true, (short) 44, true,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h7 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_AUNTIES, "auntie", true, (short) 44, true,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td5 = { h5, h6, h7 };
		manager.setHistories(td5);

		assertEquals("condition 3_2 father (test 2A) ", false,
				riskCalculator.getCondition32(Util.SIDE_FATHER));
		assertEquals("condition 3_2 father (test 2B) ", false,
				riskCalculator.getCondition32(Util.SIDE_MOTHER));
		assertEquals("condition 3_2 father (test 2C) ", false,
				riskCalculator.getCondition32());

		// RUN 5, add mother, but no CC
		CancerHistory h8 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "mom", false, (short) -1, true, false,
				(short) -1, false, (short) -1, "");
		CancerHistory[] td6 = { h5, h6, h7, h8 };
		manager.setHistories(td6);

		assertEquals("condition 3_2 father (test 2A) ", false,
				riskCalculator.getCondition32(Util.SIDE_FATHER));
		assertEquals("condition 3_2 father (test 2B) ", false,
				riskCalculator.getCondition32(Util.SIDE_MOTHER));
		assertEquals("condition 3_2 father (test 2C) ", false,
				riskCalculator.getCondition32());

		// RUN 6, add mother, with CC
		h8 = new CancerHistory("NHI123", "eref1", (short) Util.MOTHER, "mom",
				true, (short) 22, true, false, (short) -1, false, (short) -1,
				"");
		CancerHistory[] td7 = { h5, h6, h7, h8 };
		manager.setHistories(td7);

		assertEquals("condition 3_2 father (test 2A) ", false,
				riskCalculator.getCondition32(Util.SIDE_FATHER));
		assertEquals("condition 3_2 father (test 2B) ", true,
				riskCalculator.getCondition32(Util.SIDE_MOTHER));
		assertEquals("condition 3_2 father (test 2C) ", true,
				riskCalculator.getCondition32());
		assertEquals("# run 6 ",
				3 , riskCalculator.calculateRisk());
		
		// RUN 7, Child, plus 2 grandparents on different sides.
		CancerHistory h9 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "child", true, (short) 22, true, false,
				(short) -1, false, (short) -1, "");
		CancerHistory h10 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "grandpa", true, (short) 22,
				true, false, (short) -1, false, (short) -1, "");
		CancerHistory h11 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "grannie", true, (short) 22,
				true, false, (short) -1, false, (short) -1, "");

		CancerHistory[] td8 = { h9, h10, h11 };
		manager.setHistories(td8);

		assertEquals("condition 3_2 father (test 2A) ", false,
				riskCalculator.getCondition32(Util.SIDE_FATHER));
		assertEquals("condition 3_2 father (test 2B) ", false,
				riskCalculator.getCondition32(Util.SIDE_MOTHER));
		assertEquals("condition 3_2 father (test 2C) ", false,
				riskCalculator.getCondition32());

		// RUN 8, Add another primary relative.
		CancerHistory h12 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "child", true, (short) 22, true, false,
				(short) -1, false, (short) -1, "");

		CancerHistory[] td10 = { h9, h10, h11, h12 };
		manager.setHistories(td10);

		assertEquals("condition 3_2 father (test 2A) ", true,
				riskCalculator.getCondition32(Util.SIDE_FATHER));
		assertEquals("condition 3_2 father (test 2B) ", true,
				riskCalculator.getCondition32(Util.SIDE_MOTHER));
		assertEquals("condition 3_2 father (test 2C) ", true,
				riskCalculator.getCondition32());
		assertEquals(" run 8 ",
				3 , riskCalculator.calculateRisk());
		
		
		// RUN 9, Mother, + 2 paternal grandparents ==> false.
		CancerHistory h13 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "mom", true, (short) 22, true, false,
				(short) -1, false, (short) -1, "");

		CancerHistory h14 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "GRANDPA", true, (short) 88,
				true, false, (short) -1, false, (short) -1, "");
		CancerHistory h15 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDMOTHER, "GRANDMA", true, (short) 22,
				true, false, (short) -1, false, (short) -1, "");

		CancerHistory[] td11 = { h13, h14, h15 };
		manager.setHistories(td11);

		assertEquals("condition 3_2 father (test 2A) ", false,
				riskCalculator.getCondition32(Util.SIDE_FATHER));
		assertEquals("condition 3_2 father (test 2B) ", false,
				riskCalculator.getCondition32(Util.SIDE_MOTHER));
		assertEquals("condition 3_2 father (test 2C) ", false,
				riskCalculator.getCondition32());

		// RUN 10 Add child, without CC
		CancerHistory h16 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN , "kid", false, (short) -1, true, false,
				(short) -1, false, (short) -1, "");
		CancerHistory[] td12 = { h13, h14, h15, h16 };
		manager.setHistories(td12);

		assertEquals("condition 3_2 father (test 2A) ", false,
				riskCalculator.getCondition32(Util.SIDE_FATHER));
		assertEquals("condition 3_2 father (test 2B) ", false,
				riskCalculator.getCondition32(Util.SIDE_MOTHER));
		assertEquals("condition 3_2 father (test 2C) ", false,
				riskCalculator.getCondition32());

		// RUN 11 same as before, but child has CC
		CancerHistory h17 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN , "kid", true, (short) 88, true, false,
				(short) -1, false, (short) -1, "");
		CancerHistory[] td13 = { h13, h14, h15, h17 };
		manager.setHistories(td13);

		assertEquals("condition 3_2 father (test 2A) ", true,
				riskCalculator.getCondition32(Util.SIDE_FATHER));
		assertEquals("condition 3_2 father (test 2B) ", false,
				riskCalculator.getCondition32(Util.SIDE_MOTHER));
		assertEquals("condition 3_2 father (test 2C) ", true,
				riskCalculator.getCondition32());

		assertEquals(" run 11 ",
				3 , riskCalculator.calculateRisk());
		
		
	}
	
	

}
