package ac.otago.infoscience.eCRC.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Vector;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import ac.otago.infoscience.eCRC.shared.CancerHistory;

public class CancerHistoryManagerTest {

	//final static int TESTCASES = 3;
	CancerHistory[] theTestCase;

	static CancerHistoryManager manager = null;
	// a vector of vectors, each index containing one testcase, i.e. one
	// vector of CancerHistory objects.
	static Vector<Vector<CancerHistory>> allTestData1 = new Vector<Vector<CancerHistory>>();

	// correct answers for each given test method, indexed by testcase, e.g. the
	// expect result of
	// testGetRelativesWithColonCancerAnyAgeIntInt for testcase 0 is: 0.

	// all
	int[] correctTestGetRelativesWithColonCancerAnyAge = { 0, 1, 1, 2, 3, 4, 5,
			6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
	int[] correctTestGetRelativesWithColonCancerAnyAge_Father = { 0, 0, 0, 1,
			1, 2, 3, 4, 5, 6, 7, 7, 7, 7, 7, 8, 9 };
	int[] correctTestGetRelativesWithColonCancerAnyAge_Mother = { 0, 1, 1, 1,
			2, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 10, 10 };

	// primary
	int[] correctTestGetPrimaryRelativesWithColonCancerAnyAge = { 0, 1, 1, 1,
			1, 1, 2, 3, 4, 5, 5, 5, 5, 5, 5, 5, 6 };
	int[] correctTestGetPrimaryRelativesWithColonCancerAnyAge_Father = { 0, 0,
			0, 0, 0, 0, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 5 };
	int[] correctTestGetPrimaryRelativesWithColonCancerAnyAge_Mother = { 0, 1,
			1, 1, 1, 1, 2, 3, 4, 5, 5, 5, 5, 5, 5, 5, 5 };

	// secondary
	int[] correctTestGetSecondaryRelativesWithColonCancerAnyAge = { 0, 0, 0, 1,
			2, 3, 3, 3, 3, 3, 4, 5, 6, 7, 8, 9, 9 };
	int[] correctTestGetSecondaryRelativesWithColonCancerAnyAge_Father = { 0,
			0, 0, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4 };
	int[] correctTestGetSecondaryRelativesWithColonCancerAnyAge_Mother = { 0,
			0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 3, 4, 5, 5, 5 };

	// cc by side of family, age <
	int[] correctTestGetRelativesWithColonCancerByAge_FATHER = { 0, 0, 0, 0, 0,
			1, 2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5 };

	int[] correctTestGetRelativesWithColonCancerByAge_MOTHER = { 0, 1, 1, 1, 1,
			1, 2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4 };

	int[] correctTestPrimaryRelativesWithColonCancerByAge = { 0, 1, 1, 1, 1, 1,
			2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5 };

	int[] correctTestGetRelativesWithColonCancerAndOtherCancer_FATHER = { 0, 0,
			0, 0, 0, 1, 2, 3, 3, 4, 5, 5, 5, 5, 5, 5, 6 };

	int[] correctTestGetRelativesWithColonCancerAndOtherCancer_MOTHER = { 0, 0,
			0, 0, 0, 0, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6, 6 };

	int[] correctTestGetRelativesWithColonCancerMultiplePolyps = { 0, 0, 0, 1,
			2, 3, 4, 5, 5, 6, 6, 7, 8, 9, 10, 10, 11

	}

	;

	@BeforeClass
	public static void initCancerHistories() {

		System.out.println("initCancerHistories");
		manager = new CancerHistoryManager();

		// testdata #0: 1 record, just the patient
		Vector<CancerHistory> testdata0 = new Vector<CancerHistory>();
		CancerHistory h0 = new CancerHistory("NHI123", "eref1",
				(short) Util.PATIENT, "patientname", false, (short) -1, false,
				false, (short) -1, false, (short) -1,
				"comment: The patient himself");
		testdata0.add(h0);
		allTestData1.add(testdata0);

		// testdata #1: 2 records. Mother had CC at age 44
		Vector<CancerHistory> testdata1 = new Vector<CancerHistory>();
		CancerHistory h1 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "mother name", true, (short) 44, false,
				false, (short) -1, false, (short) -1, "comment: mother CC 44");
		testdata1.add(h0);
		testdata1.add(h1);
		allTestData1.add(testdata1);

		// testdata #02: 2 records. Mother had CC at age 52
		Vector<CancerHistory> testdata2 = new Vector<CancerHistory>();
		CancerHistory h2 = new CancerHistory("NHI123", "eref1",
				(short) Util.MOTHER, "mother name", true, (short) 52, false,
				false, (short) -1, false, (short) -1, "comment: mother CC 52");
		testdata2.add(h0);
		testdata2.add(h2);
		allTestData1.add(testdata2);

		// testdata #03: 3 records.
		// Mother had CC at age 52
		// Paternal grandfather had CC at age 75,
		// with multiple polyps.
		Vector<CancerHistory> testdata3xxxx = new Vector<CancerHistory>();
		CancerHistory h3 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDFATHER, "paternal grandfather name",
				true, (short) 75, true, false, (short) -1, false, (short) -1,
				"comment: pat grandfather CC Mult 75");

		Vector<CancerHistory> testdata3 = new Vector<CancerHistory>(testdata2);
		testdata3.add(h3);
		allTestData1.add(testdata3);

		// testdata #04: 4 records.
		// Mother had CC at age 52
		// Paternal grandfather had CC at age 75,
		// with multiple polyps.
		// one maternal aunt with CC at age 56, mult polyps, deceased 78
		Vector<CancerHistory> testdata4 = new Vector<CancerHistory>(testdata3);
		CancerHistory h4 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_AUNTIES, "maternal auntie name", true,
				(short) 56, true, false, (short) -1, true, (short) 78,
				"comment: mat auntie CC Mult 56, mult pols, deceased 78");
		testdata4.add(h4);
		allTestData1.add(testdata4);

		// testdata #05: 5 records.
		// Mother had CC at age 52
		// Paternal grandfather had CC at age 75,
		// with multiple polyps.
		// one maternal aunt with CC at age 56, mult polyps, deceased 78
		// one paternal aunt with CC at age 45, hnpcc 46, mulpt polyps, dec 80
		Vector<CancerHistory> testdata5 = new Vector<CancerHistory>(testdata4);
		CancerHistory h5 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_AUNTIES, "paternal auntie name", true,
				(short) 45, true, true, (short) 46, true, (short) 80,
				"comment: pat auntie CC Mult 46, mult pols, deceased 80");
		testdata5.add(h5);
		allTestData1.add(testdata5);

		// testdata #06: 6 records.
		// Mother had CC at age 52
		// Paternal grandfather had CC at age 75,
		// with multiple polyps.
		// one maternal aunt with CC at age 56, mult polyps, deceased 78
		// one paternal aunt with CC at age 45, hnpcc 46, mulpt polyps, dec. 80
		// one child, age 44, mult pols, hnpcc 55, dec 56
		Vector<CancerHistory> testdata6 = new Vector<CancerHistory>(testdata5);
		CancerHistory h6 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "child name", true, (short) 44, true,
				true, (short) 55, true, (short) 56,
				"comment: child 44 mult hnpcc 55 dec 56");
		testdata6.add(h6);
		allTestData1.add(testdata6);

		// testdata #07: 7 records.
		// Mother had CC at age 52
		// Paternal grandfather had CC at age 75, with multiple polyps.
		// one maternal aunt with CC at age 56, mult polyps, deceased 78
		// one paternal aunt with CC at age 45, hnpcc 46, mulpt polyps, dec. 80
		// one child, age 44, mult pols, hnpcc 55, dec 56
		// one child, age 44, mult pols, hnpcc 55, dec 56

		Vector<CancerHistory> testdata7 = new Vector<CancerHistory>(testdata6);
		CancerHistory h7 = new CancerHistory("NHI123", "eref1",
				(short) Util.CHILDREN, "child name", true, (short) 44, true,
				true, (short) 55, true, (short) 56,
				"comment: child 44 mult hnpcc 55 dec 56");
		testdata7.add(h7);
		allTestData1.add(testdata7);

		Vector<CancerHistory> testdata8 = new Vector<CancerHistory>(testdata7);
		CancerHistory h8 = new CancerHistory("NHI123", "eref1",
				(short) Util.SIBLINGS, "sibling  name", true, (short) 66,
				false, false, (short) -1, false, (short) -1,
				"comment: sibling 66");
		testdata8.add(h8);
		allTestData1.add(testdata8);

		Vector<CancerHistory> testdata9 = new Vector<CancerHistory>(testdata8);
		CancerHistory h9 = new CancerHistory("NHI123", "eref1",
				(short) Util.SIBLINGS, "sibling name", true, (short) 52, true,
				true, (short) 66, true, (short) 88,
				"comment: sibling mult hnpcc 66 dec 88");

		testdata9.add(h9);
		allTestData1.add(testdata9);

		// *****************
		Vector<CancerHistory> testdata10 = new Vector<CancerHistory>(testdata9);
		CancerHistory h10 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_GRANDMOTHER, "pat grandma name ", true,
				(short) 77, false, true, (short) 75, false, (short) -1,
				"comment: sibling mult hnpcc 66 dec 88");
		testdata10.add(h10);
		allTestData1.add(testdata10);

		Vector<CancerHistory> testdata11 = new Vector<CancerHistory>(testdata10);
		CancerHistory h11 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "mat grandma name ", true,
				(short) 75, true, true, (short) 76, true, (short) 99,
				"comment: sibling mult hnpcc 66 dec 88");
		testdata11.add(h11);
		allTestData1.add(testdata11);

		// ***************

		Vector<CancerHistory> testdata12 = new Vector<CancerHistory>(testdata11);
		CancerHistory h12 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_GRANDMOTHER, "pat grandma name", true,
				(short) 55, true, true, (short) 56, true, (short) 60,
				"comment: mat grandma 55 mult hnpcc 56 dec 60");
		testdata12.add(h12);
		allTestData1.add(testdata12);

		//************
		Vector<CancerHistory> testdata13 = new Vector<CancerHistory>(testdata12);
		CancerHistory h13 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_AUNTIES, "mat auntie name", true, (short) 60,
				true, false, (short) -1, true, (short) 70,
				"comment: mat auntie 60 mult dec 70");
		testdata13.add(h13);
		allTestData1.add(testdata13);

		//****************
		Vector<CancerHistory> testdata14 = new Vector<CancerHistory>(testdata13);
		CancerHistory h14 = new CancerHistory("NHI123", "eref1",
				(short) Util.MAT_AUNTIES, "mat auntie name", true, (short) 60,
				true, true, (short) 70, false, (short) -1,
				"comment: mat auntie 60 mult hnpcc");
		testdata14.add(h14);
		allTestData1.add(testdata14);

		//******************
		Vector<CancerHistory> testdata15 = new Vector<CancerHistory>(testdata14);
		CancerHistory h15 = new CancerHistory("NHI123", "eref1",
				(short) Util.PAT_AUNTIES, "pat auntie name", true, (short) 70,
				false, false, (short) 01, true, (short) 80,
				"comment: pat auntie 70 mult dec 80");
		testdata15.add(h15);
		allTestData1.add(testdata15);

		//*******************
		Vector<CancerHistory> testdata16 = new Vector<CancerHistory>(testdata15);
		CancerHistory h16 = new CancerHistory("NHI123", "eref1",
				(short) Util.FATHER, "father name", true, (short) 42, true,
				true, (short) 42, true, (short) 42,
				"comment: father 42 mult hnpcc dec 42"); 
		testdata16.add(h16);
		allTestData1.add(testdata16);

	}

	@Test
	public void testGetRelativesWithColonCancerAnyAgeIntInt() {

		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);
			assertEquals(counter + " : #relatives with colon cancer any age",
					this.correctTestGetRelativesWithColonCancerAnyAge[counter],
					manager.getRelativesWithColonCancerAnyAge());
			counter++;

		}

	}

	/**********************************
	 * 
	 * ALL
	 * 
	 * 
	 *********************************/
	@Test
	public void testGetRelativesWithColonCancerAnyAgeInt_FATHERSIDE() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);
			assertEquals(
					counter
							+ " : # relatives with colon cancer any age, father side",
					this.correctTestGetRelativesWithColonCancerAnyAge_Father[counter],
					manager.getRelativesWithColonCancerAnyAge(Util.SIDE_FATHER));
			counter++;

		}
	}

	@Test
	public void testGetRelativesWithColonCancerAnyAgeInt_MOTHERSIDE() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);
			if (counter == 16) {
				int fred = this.manager
						.getRelativesWithColonCancerAnyAge(Util.SIDE_MOTHER);
				int z = fred;
			}

			assertEquals(
					counter
							+ " : # relatives with colon cancer any age, mother side",
					this.correctTestGetRelativesWithColonCancerAnyAge_Mother[counter],
					manager.getRelativesWithColonCancerAnyAge(Util.SIDE_MOTHER));
			counter++;

		}
	}

	/**********************************
	 * 
	 * PRIMARY
	 * 
	 * 
	 *********************************/

	@Test
	public void testGetPrimaryRelativesWithColonCancerAnyAge() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);

			assertEquals(
					counter
							+ " : # primary relatives with colon cancer any age",
					this.correctTestGetPrimaryRelativesWithColonCancerAnyAge[counter],
					manager.getPrimaryRelativesWithColonCancerAnyAge());
			counter++;

		}
	}

	
	@Test
	public void testGetPrimaryRelativesWithColonCancerAnyAgeInt_Fatherside() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);

			assertEquals(
					counter
							+ " : # primary relatives with colon cancer any age, father side",

					this.correctTestGetPrimaryRelativesWithColonCancerAnyAge_Father[counter],
					manager.getPrimaryRelativesWithColonCancerAnyAge(Util.SIDE_FATHER));
			counter++;

		}
	}

	@Test
	public void testGetPrimaryRelativesWithColonCancerAnyAgeInt_Motherside() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);

			assertEquals(
					counter
							+ " : # primary relatives with colon cancer any age mother side",
					this.correctTestGetPrimaryRelativesWithColonCancerAnyAge_Mother[counter],
					manager.getPrimaryRelativesWithColonCancerAnyAge(Util.SIDE_MOTHER));
			counter++;

		}
	}

	/**********************************
	 * 
	 * SECONDARY
	 * 
	 * 
	 *********************************/
/*
	@Test
	public void testGetSecondaryRelativesWithColonCancerAnyAge() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);
			assertEquals(
					counter
							+ " : # relatives with colon cancer any age, father side",
					this.correctTestGetSecondaryRelativesWithColonCancerAnyAge[counter],
					manager.getSecondaryRelativesWithColonCancerAnyAge());
			counter++;

		}
	}

*/
	/**********************************
	 * 
	 * SECONDARY
	 * 
	 * 
	 *********************************/
	
	@Test
	public void testGetSecondaryRelativesWithColonCancerAnyAge() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);
			assertEquals(
					counter
							+ " : # relatives with colon cancer any age, father side",
					this.correctTestGetSecondaryRelativesWithColonCancerAnyAge[counter],
					manager.getSecondaryRelativesWithColonCancerAnyAge());
			counter++;
	
		}
	}

	/*
	@Test
	public void testGetSecondaryRelativesWithColonCancerAnyAgeInt_Fatherside() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);

			assertEquals(
					counter
							+ " : # secondary relatives with colon cancer any age, father side",
					this.correctTestGetSecondaryRelativesWithColonCancerAnyAge_Father[counter],
					manager.getSecondaryRelativesWithColonCancerAnyAge(Util.SIDE_FATHER));
			counter++;

		}
	}
*/
	@Test
	public void testGetSecondaryRelativesWithColonCancerAnyAgeInt_Fatherside() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);
	
			assertEquals(
					counter
							+ " : # secondary relatives with colon cancer any age, father side",
					this.correctTestGetSecondaryRelativesWithColonCancerAnyAge_Father[counter],
					manager.getSecondaryRelativesWithColonCancerAnyAge(Util.SIDE_FATHER));
			counter++;
	
		}
	}

	@Test
	public void testGetSecondaryRelativesWithColonCancerAnyAgeInt_Motherside() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);
			assertEquals(
					counter
							+ " : # secondary relatives with colon cancer any age mother side",
					this.correctTestGetSecondaryRelativesWithColonCancerAnyAge_Mother[counter],
					manager.getSecondaryRelativesWithColonCancerAnyAge(Util.SIDE_MOTHER));
			counter++;

		}
	}

	@Test
	public void testGetRelativesWithColonCancerByAgeIntInt_FATHER55() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);
			assertEquals(
					counter
							+ " : # relatives with colon cancer by age father side",
					this.correctTestGetRelativesWithColonCancerByAge_FATHER[counter],
					manager.getRelativesWithColonCancerByAge(Util.SIDE_FATHER,
							55));
			counter++;

		}

	}

	@Test
	public void testGetRelativesWithColonCancerByAgeIntInt_MOTHER55() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);
			assertEquals(
					counter
							+ " : # relatives with colon cancer by age father side",
					this.correctTestGetRelativesWithColonCancerByAge_MOTHER[counter],
					manager.getRelativesWithColonCancerByAge(Util.SIDE_MOTHER,
							55));
			counter++;

		}

	}

	@Test
	public void testGetPrimaryRelativesWithColonCancerByAge() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);
			assertEquals(
					counter
							+ " : # primary relatives with colon cancer any age",
					this.correctTestPrimaryRelativesWithColonCancerByAge[counter],
					manager.getPrimaryRelativesWithColonCancerByAge(55));
			counter++;

		}
	}

	@Test
	public void testGetRelativesWithColonCancerAndOtherCancerInt_FATHER() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);
			assertEquals(
					counter
							+ " : # relatives with colon cancer and HNPCC father side",
					this.correctTestGetRelativesWithColonCancerAndOtherCancer_FATHER[counter],
					manager.getRelativesWithColonCancerAndOtherCancer(Util.SIDE_FATHER));
			counter++;

		}
	}

	@Test
	public void testGetRelativesWithColonCancerAndOtherCancerInt_MOTHER() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);
			assertEquals(
					counter
							+ " : # relatives with colon cancer and HNPCC mother side",
					this.correctTestGetRelativesWithColonCancerAndOtherCancer_MOTHER[counter],
					manager.getRelativesWithColonCancerAndOtherCancer(Util.SIDE_MOTHER));
			counter++;

		}
	}

	@Test
	public void testGetRelativesWithColonCancerMultiplePolyps() {
		int counter = 0;
		java.util.Iterator<Vector<CancerHistory>> iterator = allTestData1
				.iterator();
		while (iterator.hasNext()) {
			Vector<CancerHistory> v = (Vector<CancerHistory>) iterator.next();
			// note the compiler magic to turn the vector into an array of the
			// correct type.
			CancerHistory[] thisTestData = (CancerHistory[]) v
					.toArray(new CancerHistory[0]);
			manager.setHistories(thisTestData);
			assertEquals(
					counter
							+ " : # relatives with colon cancer and multiple polyps",
					this.correctTestGetRelativesWithColonCancerMultiplePolyps[counter],
					manager.getRelativesWithColonCancerMultiplePolyps());
			counter++;

		}
	}

	

}
