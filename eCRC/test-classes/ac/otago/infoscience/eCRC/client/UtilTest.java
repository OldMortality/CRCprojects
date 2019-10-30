package ac.otago.infoscience.eCRC.client;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilTest {

	@Test
	public void testGetSide() {
		assertEquals("Patient himself is on both sides", Util.SIDE_BOTH,
				Util.getSide(Util.PATIENT));
		assertEquals("Paternal grandfather is on father's side",
				Util.SIDE_FATHER, Util.getSide(Util.PAT_GRANDFATHER));
		assertEquals("Paternal grandmother is on father's side",
				Util.SIDE_FATHER, Util.getSide(Util.PAT_GRANDMOTHER));
		assertEquals("Maternal grandfather is on mother's side",
				Util.SIDE_MOTHER, Util.getSide(Util.MAT_GRANDFATHER));
		assertEquals("Maternal grandmother is on mother's side",
				Util.SIDE_MOTHER, Util.getSide(Util.MAT_GRANDMOTHER));
		assertEquals("Father is on father's side", Util.SIDE_FATHER,
				Util.getSide(Util.FATHER));
		assertEquals("Mother is on mother's side", Util.SIDE_MOTHER,
				Util.getSide(Util.MOTHER));
		assertEquals("Paternal aunties are on father's side", Util.SIDE_FATHER,
				Util.getSide(Util.PAT_AUNTIES));
		assertEquals("Maternal aunties is on mother's side", Util.SIDE_MOTHER,
				Util.getSide(Util.MAT_AUNTIES));
		assertEquals("Siblings are on both sides", Util.SIDE_BOTH,
				Util.getSide(Util.SIBLINGS));
		assertEquals("Children are on both sides", Util.SIDE_BOTH,
				Util.getSide(Util.CHILDREN));

	}

	@Test
	// Every relation should be on the same side as itself
	public void testSameSide1() {
		for (int type = 0; type < Util.CHILDREN; type++) {
			assertEquals("Each on same side as himself", true,
					Util.sameSide(type, type));
		}
	}

	@Test
	// every relation is on the same side as children and siblings
	public void testSameSide2() {
		for (int type = 0; type < Util.CHILDREN; type++) {
			assertEquals("Each on same side as children", true,
					Util.sameSide(type, Util.CHILDREN));
		}
		for (int type = 0; type < Util.CHILDREN; type++) {
			assertEquals("Each on same side as siblings", true,
					Util.sameSide(type, Util.SIBLINGS));
		}
	}

	// things on the father's side should be passed in here
	void sameSideFather(int type) {
		assertEquals(Util.getDescription(type)
				+ " is on the same side as Father ", true,
				Util.sameSide(type, Util.PAT_GRANDFATHER));
		assertEquals(Util.getDescription(type)
				+ " is on the same side as Paternal grandfather", true,
				Util.sameSide(type, Util.PAT_GRANDFATHER));
		assertEquals(Util.getDescription(type)
				+ " is on same side as Paternal grandmother", true,
				Util.sameSide(type, Util.PAT_GRANDMOTHER));
		assertEquals(Util.getDescription(type)
				+ " is on same side as Paternal aunts & uncles", true,
				Util.sameSide(type, Util.PAT_AUNTIES));
	}

	@Test
	// Father, paternal grandfather, etc is on the same side as everyone on the
	// paternal side
	public void testSameSide3() {
		sameSideFather(Util.FATHER);
		sameSideFather(Util.PAT_GRANDFATHER);
		sameSideFather(Util.PAT_GRANDMOTHER);
		sameSideFather(Util.PAT_AUNTIES);
		sameSideFather(Util.CHILDREN);
		sameSideFather(Util.SIBLINGS);

	}

	// things on the mother's side should be passed in here
	void sameSideMother(int type) {
		assertEquals(Util.getDescription(type)
				+ " is on the same side as Mother ", true,
				Util.sameSide(type, Util.MAT_GRANDFATHER));
		assertEquals(Util.getDescription(type)
				+ " is on the same side as Maternal grandfather", true,
				Util.sameSide(type, Util.MAT_GRANDFATHER));
		assertEquals(Util.getDescription(type)
				+ " is on same side as Maternal grandmother", true,
				Util.sameSide(type, Util.MAT_GRANDMOTHER));
		assertEquals(Util.getDescription(type)
				+ " is on same side as Maternal aunts & uncles", true,
				Util.sameSide(type, Util.MAT_AUNTIES));
	}

	@Test
	// Mother, maternal grandfather, etc is on the same side as everyone on the
	// maternal side
	public void testSameSide4() {
		sameSideMother(Util.MOTHER);
		sameSideMother(Util.MAT_GRANDFATHER);
		sameSideMother(Util.MAT_GRANDMOTHER);
		sameSideMother(Util.MAT_AUNTIES);
		sameSideMother(Util.CHILDREN);
		sameSideFather(Util.SIBLINGS);

	}

	// things on the father's side must be passed in here
	void differentSideMother(int type) {
		assertEquals(Util.getDescription(type)
				+ " is NOT on the same side as Mother ", false,
				Util.sameSide(type, Util.MAT_GRANDFATHER));
		assertEquals(Util.getDescription(type)
				+ " is NOT on the same side as Maternal grandfather", false,
				Util.sameSide(type, Util.MAT_GRANDFATHER));
		assertEquals(Util.getDescription(type)
				+ " is NOT on same side as Maternal grandmother", false,
				Util.sameSide(type, Util.MAT_GRANDMOTHER));
		assertEquals(Util.getDescription(type)
				+ " is NOT on same side as Maternal aunts & uncles", false,
				Util.sameSide(type, Util.MAT_AUNTIES));
	}

	@Test
	// Differences, father's side
	public void testSameSide5() {
		differentSideMother(Util.FATHER);
		differentSideMother(Util.PAT_GRANDFATHER);
		differentSideMother(Util.PAT_GRANDMOTHER);
		differentSideMother(Util.PAT_AUNTIES);
	}

	// Things on the mother's side must be passed in here
	void differentSideFather(int type) {
		assertEquals(Util.getDescription(type)
				+ " is NOT on the same side as Father ", false,
				Util.sameSide(type, Util.PAT_GRANDFATHER));
		assertEquals(Util.getDescription(type)
				+ " is NOT on the same side as Paternal grandfather", false,
				Util.sameSide(type, Util.PAT_GRANDFATHER));
		assertEquals(Util.getDescription(type)
				+ " is NOT on same side as Paternal grandmother", false,
				Util.sameSide(type, Util.PAT_GRANDMOTHER));
		assertEquals(Util.getDescription(type)
				+ " is NOT  on same side as Paternal aunts & uncles", false,
				Util.sameSide(type, Util.PAT_AUNTIES));
	}

	@Test
	// Differences, mother's side
	public void testSameSide6() {
		differentSideFather(Util.MOTHER);
		differentSideFather(Util.MAT_GRANDFATHER);
		differentSideFather(Util.MAT_GRANDMOTHER);
		differentSideFather(Util.MAT_AUNTIES);
	}

	@Test
	// primary and secondary relationships
	public void testDistance6() {
		assertEquals(" Patient is a 0 relationshiop", 0,
				Util.getDegree(Util.PATIENT));
		assertEquals(" Paternal grandfather is a secondary relationship", 2,
				Util.getDegree(Util.PAT_GRANDFATHER));
		assertEquals(" Paternal grandmother is a secondary relationship", 2,
				Util.getDegree(Util.PAT_GRANDMOTHER));
		assertEquals(" Maternal grandfather is a secondary relationship", 2,
				Util.getDegree(Util.MAT_GRANDFATHER));
		assertEquals(" Maternal grandmother is a secondary relationship", 2,
				Util.getDegree(Util.MAT_GRANDMOTHER));
		assertEquals(" Paternal aunties is a secondary relationship", 2,
				Util.getDegree(Util.PAT_AUNTIES));
		assertEquals(" Maternal aunties is a secondary relationship", 2,
				Util.getDegree(Util.MAT_AUNTIES));
		assertEquals(" Father is a primary relationship", 1,
				Util.getDegree(Util.FATHER));
		assertEquals(" Mother is a primary relationship", 1,
				Util.getDegree(Util.MOTHER));
		assertEquals(" Children is a primary relationship", 1,
				Util.getDegree(Util.CHILDREN));
		assertEquals(" Siblings is a primary relationship", 1,
				Util.getDegree(Util.SIBLINGS));

	}

	@Test
	// primary and secondary relationships
	public void testIsOfSide() {
		// paternal side
		assertEquals("Pat grandfather is of father's side ", true,
				Util.isOfSide(Util.SIDE_FATHER, Util.PAT_GRANDFATHER));
		assertEquals("Pat grandmother is of father's side ", true,
				Util.isOfSide(Util.SIDE_FATHER, Util.PAT_GRANDMOTHER));
		assertEquals("Pat aunties is of father's side ", true,
				Util.isOfSide(Util.SIDE_FATHER, Util.PAT_AUNTIES));
		assertEquals("Father is on father's side ", true,
				Util.isOfSide(Util.SIDE_FATHER, Util.FATHER));
		assertEquals("Children is of father's side ", true,
				Util.isOfSide(Util.SIDE_FATHER, Util.CHILDREN));
		assertEquals("Siblings is on father's side ", true,
				Util.isOfSide(Util.SIDE_FATHER, Util.SIBLINGS));
		// maternal side
		assertEquals("Mat grandfather is of father's side ", true,
				Util.isOfSide(Util.SIDE_MOTHER, Util.MAT_GRANDFATHER));
		assertEquals("Mat grandmother is of father's side ", true,
				Util.isOfSide(Util.SIDE_MOTHER, Util.MAT_GRANDMOTHER));
		assertEquals("Mat aunties is of father's side ", true,
				Util.isOfSide(Util.SIDE_MOTHER, Util.MAT_AUNTIES));
		assertEquals("Mother is on father's side ", true,
				Util.isOfSide(Util.SIDE_MOTHER, Util.MOTHER));
		assertEquals("Children is of father's side ", true,
				Util.isOfSide(Util.SIDE_MOTHER, Util.CHILDREN));
		assertEquals("Siblings is on father's side ", true,
				Util.isOfSide(Util.SIDE_MOTHER, Util.SIBLINGS));

	}

	@Test
	// primary and secondary relationships
	public void testEscapeHTML() {
		String s = "a&b<c>";
		String escaped = Util.escapeHtml(s);
		assertEquals("a&b<c>d escaped is a&amp;b&lt;c&gt;", "a&amp;b&lt;c&gt;",
				escaped);

	}

	@Test
	// test generation difference
	public void testGenerationDifference() {
		assertEquals("run 1 ", 0,
				Util.getGenerationDifference(Util.MOTHER, Util.FATHER));
		assertEquals("run 2 ", 0, Util.getGenerationDifference(
				Util.MAT_GRANDFATHER, Util.PAT_GRANDFATHER));
		assertEquals("run 3 ", 0, Util.getGenerationDifference(
				Util.MAT_GRANDMOTHER, Util.PAT_GRANDFATHER));
		assertEquals("run 4 ", 0,
				Util.getGenerationDifference(Util.PATIENT, Util.SIBLINGS));
		assertEquals("run 6 ", 0,
				Util.getGenerationDifference(Util.FATHER, Util.MOTHER));

		for (int i = 0; i < 11; i++) {
			assertEquals("run 4a ", 0, Util.getGenerationDifference(i, i));
		}

		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				assertEquals("run 4a ", Util.getGenerationDifference(j, i),
						Util.getGenerationDifference(i, j));
			}
		}

		assertEquals("run 5 ", 1,
				Util.getGenerationDifference(Util.MOTHER, Util.SIBLINGS));
		assertEquals("run 6 ", 1,
				Util.getGenerationDifference(Util.FATHER, Util.SIBLINGS));
		assertEquals("run 7 ", 1,
				Util.getGenerationDifference(Util.FATHER, Util.SIBLINGS));
		assertEquals("run 8", 1,
				Util.getGenerationDifference(Util.MOTHER, Util.SIBLINGS));
		assertEquals("run 9 ", 1,
				Util.getGenerationDifference(Util.PAT_AUNTIES, Util.SIBLINGS));
		assertEquals("run 10 ", 1,
				Util.getGenerationDifference(Util.MAT_AUNTIES, Util.SIBLINGS));

		assertEquals("run 11 ", 2,
				Util.getGenerationDifference(Util.MAT_AUNTIES, Util.CHILDREN));
		assertEquals("run 12 ", 2,
				Util.getGenerationDifference(Util.PAT_AUNTIES, Util.CHILDREN));
		assertEquals("run 13 ", 2,
				Util.getGenerationDifference(Util.FATHER, Util.CHILDREN));
		assertEquals("run 14 ", 2,
				Util.getGenerationDifference(Util.MOTHER, Util.CHILDREN));

		assertEquals("run 15 ", 2, Util.getGenerationDifference(
				Util.PAT_GRANDFATHER, Util.SIBLINGS));
		assertEquals("run 16", 2, Util.getGenerationDifference(
				Util.MAT_GRANDFATHER, Util.SIBLINGS));
		assertEquals("run 17 ", 2, Util.getGenerationDifference(
				Util.PAT_GRANDMOTHER, Util.SIBLINGS));
		assertEquals("run 18 ", 2, Util.getGenerationDifference(
				Util.MAT_GRANDFATHER, Util.SIBLINGS));

		assertEquals("run 19 ", 3, Util.getGenerationDifference(
				Util.PAT_GRANDFATHER, Util.CHILDREN));
		assertEquals("run 20", 3, Util.getGenerationDifference(
				Util.MAT_GRANDFATHER, Util.CHILDREN));
		assertEquals("run 21", 3, Util.getGenerationDifference(
				Util.PAT_GRANDMOTHER, Util.CHILDREN));
		assertEquals("run 22 ", 3, Util.getGenerationDifference(
				Util.MAT_GRANDFATHER, Util.CHILDREN));

	}

	@Test
	// test degree of relationship vs. another relationship
	public void testGetDegree1() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				assertEquals("run 1 (i-j):  " + i + "-" + j, Util.getDegree(i, j),
						Util.getDegree(j, i));

			}
		}
	}
	
	@Test
	// test degree of relationship vs. the patient.
	public void testGetDegree2() {
		for (int i = 1; i < 11; i++) {
				assertEquals("run 1 " + i, Util.getDegree(i, Util.PATIENT),
						Util.getDegree(i));

		}
	}
	

	
	
}
