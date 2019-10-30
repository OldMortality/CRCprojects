package test.ac.otago.infoscience.eCRC;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import ac.otago.infoscience.eCRC.client.CancerHistoryManager;
import ac.otago.infoscience.eCRC.client.RiskCalculator;
import ac.otago.infoscience.eCRC.client.Util;
import ac.otago.infoscience.eCRC.shared.CancerHistory;

public class RiskCalculatorTest31 {

	Object dummy = null;
	static RiskCalculator riskCalculator = new RiskCalculator();
	static CancerHistoryManager manager = new CancerHistoryManager();

	@BeforeClass
	public static void init() {
		riskCalculator.setCancerHistoryManager(manager);
	}

	@Test
	public void testGetCondition31() {

		// run 1: 3 primary relatives of the mother with hnpcc, and
		//   one of them diagnosed under 50, and spanning 2 generations.
		//
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "", true, (short) 55, false, true,
				(short) 80, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_AUNTIES, "", true, (short) 88, false,
				true, (short) 75, false, (short) -1, "");
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 88, false,
				true, (short) 49, false, (short) -1, "");



		CancerHistory[] td1 = { h1, h2, h3 };

		manager.setHistories(td1);

		assertEquals("# condition 3-1 (run 1) ", true,
				riskCalculator.getCondition31());

		// up the lowest age to 50+. Now it should be false.
		h3.setoAge((short) 50);
		assertEquals("# condition 3-1 (run 1) ", false,
				riskCalculator.getCondition31());
		
		// age back to 49, but let them not span 2 generations
		h3.setoAge((short) 49);
		assertEquals("# condition 3-1 (run 2) ", true,
				riskCalculator.getCondition31());
		h3.setRelationshipType(Util.MAT_AUNTIES);
		assertEquals("# condition 3-1 (run 3) ", false,
				riskCalculator.getCondition31());
		
		// set type back, but let there not be 1 primary relative of the others
		h3.setRelationshipType(Util.PAT_GRANDFATHER);
		assertEquals("# condition 3-1 (run 4) ", false,
				riskCalculator.getCondition31());
		
		
		h3.setRelationshipType(Util.MAT_GRANDFATHER);
		assertEquals("# condition 3-1 (run 5) ", true,
				riskCalculator.getCondition31());
		
		// Mother, her sister and the child of the patient: No one is
		//   primary relation of the others, so should be false
		h3.setRelationshipType(Util.CHILDREN);
		assertEquals("# condition 3-1 (run 6) ", false,
				riskCalculator.getCondition31());
		
		// 3 children. No 2 generations involved, so should be false.
		h1.setRelationshipType(Util.CHILDREN);
		h2.setRelationshipType(Util.CHILDREN);
		h3.setRelationshipType(Util.CHILDREN);
		assertEquals("# condition 3-1 (run 7) ", false,
				riskCalculator.getCondition31());
		
		// father of the patient, and 2 children of the patient. No one is 
		// primary relation of the others. So should be false.
		h2.setRelationshipType(Util.FATHER);		
		assertEquals("# condition 3-1 (run 7) ", false,
				riskCalculator.getCondition31());
		
		
		// father and 2 paternal aunties. Also no 2 generations involved. So false
		h1.setRelationshipType(Util.PAT_AUNTIES);
		h2.setRelationshipType(Util.FATHER);
		h3.setRelationshipType(Util.PAT_AUNTIES);
		assertEquals("# condition 3-1 (run 8) ", false,
				riskCalculator.getCondition31());
		
		// father, his father and his sister. All conditions met => true
		h1.setRelationshipType(Util.PAT_AUNTIES);
		h2.setRelationshipType(Util.FATHER);
		h3.setRelationshipType(Util.PAT_GRANDFATHER);
		assertEquals("# condition 3-1 (run 9) ", true,
				riskCalculator.getCondition31());
		
		// same thing, different order
		h2.setRelationshipType(Util.PAT_AUNTIES);
		h1.setRelationshipType(Util.FATHER);
		h3.setRelationshipType(Util.PAT_GRANDFATHER);
		assertEquals("# condition 3-1 (run 10) ", true,
				riskCalculator.getCondition31());
		
		// different order again
		h3.setRelationshipType(Util.PAT_AUNTIES);
		h1.setRelationshipType(Util.FATHER);
		h2.setRelationshipType(Util.PAT_GRANDFATHER);
		assertEquals("# condition 3-1 (run 10) ", true,
				riskCalculator.getCondition31());
		
	}

}
