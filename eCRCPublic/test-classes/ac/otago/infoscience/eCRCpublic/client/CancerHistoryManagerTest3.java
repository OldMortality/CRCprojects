package ac.otago.infoscience.eCRCpublic.client;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

import ac.otago.infoscience.eCRCpublic.client.CancerHistory;
import ac.otago.infoscience.eCRCpublic.client.CancerHistoryManager;
import ac.otago.infoscience.eCRCpublic.client.Util;

/**
 * test class for testing condition 3.1 (hnpcc)
 * @author michel
 *
 */
public class CancerHistoryManagerTest3 {

	static CancerHistoryManager manager = null;
	
	@BeforeClass
	public static void initCancerHistories() {

		System.out.println("initCancerHistories");
		manager = new CancerHistoryManager();
	}
	
	
	@Test
	public void test() {
		
		CancerHistory[] histories = new CancerHistory[7];
		histories[0] = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDFATHER, "pat grandma name", true,
				(short) 55, true, true, (short) 56, true, (short) 60,
				"");
		histories[1] = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "pat grandma name", true,
				(short) 55, true, true, (short) 56, true, (short) 60,
				"");
		histories[2] = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "pat grandma name", true,
				(short) 55, true, true, (short) 56, true, (short) 60,
				"");
		histories[3] = new CancerHistory("NHI123", "eref1",
				(short) Util.SIBLINGS, "pat grandma name", true,
				(short) 55, true, false, (short) -1, true, (short) 60,
				"");
		histories[4] = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "pat grandma name", true,
				(short) 55, true, true, (short) 56, true, (short) 60,
				"");
		histories[5] = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "pat grandma name", true,
				(short) 55, true, false, (short) -1, true, (short) 60,
				"");
		histories[6] = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "pat grandma name", true,
				(short) 55, true, true, (short) 56, true, (short) 60,
				"");
		
		manager.setHistories(histories);
		CancerHistory[] hncpp = manager.getHistoryHNPCC();
		
		assertEquals("run 1",5,hncpp.length);
		assertEquals("run 2", Util.MAT_GRANDFATHER,hncpp[0].getRelationshipType());
		assertEquals("run 3", Util.FATHER,hncpp[1].getRelationshipType());
		assertEquals("run 4", Util.MOTHER,hncpp[2].getRelationshipType());
		assertEquals("run 5", Util.CHILDREN,hncpp[3].getRelationshipType());
		assertEquals("run 6", Util.MAT_GRANDMOTHER,hncpp[4].getRelationshipType());
		
		
		
	}

}
