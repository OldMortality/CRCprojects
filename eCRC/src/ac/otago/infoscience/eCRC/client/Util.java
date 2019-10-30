/*

Disclaimer of Warranties. The author disclaims to the fullest extent authorized by law any and all other warranties, 
whether express or implied, including, without limitation, any implied warranties of title, non-infringement, quiet enjoyment, 
integration, merchantability or fitness for a particular purpose. Without limitation of the foregoing, the author expressly 
does not warrant that:
(a) the software will meet your requirements or expectations;
(b) the software or the software content] will be free of bugs, errors, viruses or other defects;
(c) any results, output, or data provided through or generated by the software will be accurate, up-to-date, complete or reliable;
(d) the software will be compatible with third party software;
(e) any errors in the software will be corrected.

Under no circumstances shall the author be liable to any user for direct, indirect, incidental, consequential, 
special, or exemplary damages, arising from the software, or user's use or misuse of the software or any other services 
provided by the author. 

Such limitation of liability shall apply whether the damages arise from the use or
misuse of the software or any other services supplied by the author
(including such damages incurred by third parties), or errors of the software.

The software is supplied �as is� and all use is at your own risk.



Author: Michel de Lange

Dunedin, January 2014.

*/

package ac.otago.infoscience.eCRC.client;

import com.google.gwt.user.client.Window;

public class Util {

	/**
	 * Types of relationship
	 * 
	 */

	public final static int PATIENT = 0;
	public final static int PAT_GRANDFATHER = 1;
	public final static int PAT_GRANDMOTHER = 2;
	public final static int MAT_GRANDFATHER = 3;
	public final static int MAT_GRANDMOTHER = 4;

	public final static int FATHER = 5;
	public final static int MOTHER = 6;
	public final static int PAT_AUNTIES = 7;
	public final static int MAT_AUNTIES = 8;
	public final static int SIBLINGS = 9;
	public final static int CHILDREN = 10;

	public final static int GENERATION_0 = 0;
	public final static int GENERATION_1 = 1;
	public final static int GENERATION_2 = 2;
	public final static int GENERATION_3 = 3;
	
	
	final static int MAX_SIBLINGS = 6;
	
	static final int MAX_AGE = 130;

	public static final int NUMBER_OF_RELATIONSHIPS = 11;

	// average risk
	public static final int CATEGORY_0 = 0;
	public static final String CATEGORY_0_STRING = "0";
	
	// slightly increased risk
	public static final int CATEGORY_1 = 1;
	public static final String CATEGORY_1_STRING = "1";
	
	// moderately increased risk
	public static final int CATEGORY_2 = 2;
	public static final String CATEGORY_2_STRING = "2";
	
	// potentially high risk
	public static final int CATEGORY_3 = 3;
	public static final String CATEGORY_3_STRING = "3";
	
	public static final int NUMBER_OF_CATEGORIES = 4;

	// Father's side of the family
	public static final int SIDE_FATHER = 0;
	// Mother's side of the family
	public static final int SIDE_MOTHER = 1;
	// Are on each side, e.g. the patient's children.
	public static final int SIDE_BOTH = 2;

	// degree of the relationship (primary, secondary_
	public static final int DEGREE_FIRST = 1;
	public static final int DEGREE_SECOND = 2;
	
	// used in the calculation of the risk category
	public static final int AGE_DANGEROUS = 55;
	public static final int AGE_VERY_DANGEROUS = 50;

	// column indices for the grid
	public static final int COL_NAME = 0;
	public static final int COL_CC_CHECKBOX = 1;
	public static final int COL_CC_AGE = 2;
	public static final int COL_MULT_POL = 3;
	public static final int COL_OT_CHECKBOX = 4;
	public static final int COL_OT_AGE = 5;
	public static final int COL_DE_CHECKBOX = 6;
	public static final int COL_DE_AGE = 7;
	public static final int CIRLCE_SIZE_X = 94;
	public static final int CIRLCE_SIZE_Y = 86;
	public static final int COL_RELATIONSHIP_TYPE = 8;
	
	
	// Must match, or be less than, what is specified in hibernate mapping in ecrc.hbm.xml,
	//  so that it will always fit into the column.
	public static final int MAX_LENGTH_COMMENT = 250;
	
	// Tooltip text
	public static final String TOOLTIP_NAME = "The name of the relative";
	public static final String TOOLTIP_CC = "This person was diagnosed with colon cancer";
	public static final String TOOLTIP_CC_AGE = "The age, at which this person was diagnosed with colon cancer";
	public static final String TOOLTIP_MULT = "This person had multiple polyps";
	public static final String TOOLTIP_OT	= "This person developed an extracolonic tumour suggestive of hereditary non-polyposis colorectal cancer " +
			"(i.e. endometrial, ovarian, stomach, small bowel, renal pelvis, pancreas, brain";
	public static final String TOOLTIP_OT_AGE	= "The age, at which this person was diagnosed with this other type of cancer";
	public static final String TOOLTIP_DE	= "Tick the box if this person is deceased";
	public static final String TOOLTIP_DE_AGE	= "The age, at which this person died";
	public static final String TOOLTIP_REL = "The relationship, which this person has to the patient";

	// ID's for testing with Selenium
	public static final String ID_NAME = "ID_NAME";
	public static final String ID_CC = "ID_CC";
	public static final String ID_CC_AGE = "ID_CC_AGE";
	public static final String ID_MULT = "ID_MULT";
	public static final String ID_OT = "ID_OT";
	public static final String ID_OT_AGE = "ID_OT_AGE";
	public static final String ID_DE = "ID_DE";
	public static final String ID_DE_AGE = "ID_DE_AGE";
	public static final String ID_BUTTON_OK = "ID_BUTTON_OK";
	public static final String ID_BUTTON_SUBMIT = "ID_BUTTON_SUBMIT";
	public static final String ID_RISK_IMAGE = "RISK_IMAGE";
	public static final String ID_RISK_LEVEL = "ID_RISK_LEVEL";
	public static final String ID_BUTTON_CLOSE = "ID_BUTTON_CLOSE";
	public static final String ID_BUTTON_CLOSE_ALL = "ID_BUTTON_CLOSE_ALL";
	
	public static final String ID_COMMENTS = "ID_COMMENTS";
	
	public static final String HEADER_MODE_WIDE = "WIDE_HEADER";
	public static final String HEADER_MODE_NARROW = "NARROW_HEADER";
	
	
	/**
	 * Is this relation a primary or secondary?
	 * 
	 * @param relation
	 * @return 1 if relation is a primary relationship, 2 if it is a secondary
	 *         relationship
	 */
	public int relationshipDistance(int relation) {
		int result = 1;
		if (relation == 1 || relation == 2 || relation == 3 || relation == 4
				|| relation == 7 || relation == 8) {
			// grandparents, or aunts and uncles
			result = 2;
		}
		return result;

	}
	
	public static int getGenerationByType(int type) {
		int result = -1;
		
		if (type == PAT_GRANDFATHER ) result = GENERATION_0;
		if (type == PAT_GRANDMOTHER ) result = GENERATION_0;
		if (type == MAT_GRANDFATHER ) result = GENERATION_0;
		if (type == MAT_GRANDMOTHER ) result = GENERATION_0;
		if (type == FATHER) result = GENERATION_1;
		if (type == PAT_AUNTIES) result = GENERATION_1;
		if (type == MOTHER) result = GENERATION_1;
		if (type == MAT_AUNTIES) result = GENERATION_1;
		if (type == PATIENT) result = GENERATION_2;
		if (type == SIBLINGS) result = GENERATION_2;
		if (type == CHILDREN) result = GENERATION_3;
		
		return result;
	}

	/**
	 * returns the absolute value of the number of generations between someone
	 * of type1 and someone of type2. For example the difference between a grandfather
	 * and mother = 1, and between mother and grandfather also 1.
	 * 
	 * 
	 * @param type1 relationship type 
	 * @param type2 relationship type
	 * @return generation distance.
	 * 
	 * 
	 */
	public static int getGenerationDifference(int type1, int type2) {
		int result = 0;
		result =  getGenerationByType(type1) - getGenerationByType(type2) ;
		if (result < 0) {
			result = -1 * result;
		}
		return result;
	}
	
	/**
	 * Return a description of this relationship type
	 * 
	 * @param relType, e.g. 5
	 * @return Description of the relationship, e.g. "Father"
	 * 
	 */
	
	public static String GWT_DEBUG_PATIENT = "gwt-debug-" + getID(0);
	public static String GWT_DEBUG_PAT_GRANDFATHER = "gwt-debug-" + getID(1);
	public static String GWT_DEBUG_PAT_GRANDMOTHER = "gwt-debug-" + getID(2);
	public static String GWT_DEBUG_MAT_GRANDFATHER = "gwt-debug-" + getID(3);
	public static String GWT_DEBUG_MAT_GRANDMOTHER = "gwt-debug-" + getID(4);
	public static String GWT_DEBUG_FATHER = "gwt-debug-" + getID(5);
	public static String GWT_DEBUG_MOTHER = "gwt-debug-" + getID(6);
	public static String GWT_DEBUG_PAT_AUNTIES = "gwt-debug-" + getID(7);
	public static String GWT_DEBUG_MAT_AUNTIES= "gwt-debug-" + getID(8);
	public static String GWT_DEBUG_SIBLINGS = "gwt-debug-" + getID(9);
	public static String GWT_DEBUG_CHILDREN= "gwt-debug-" + getID(10);
	
	
	static String getID(int relType) {
		String result = "unknown";
		if (relType == 0)
			result = "Patient";
		if (relType == 1)
			result = "Paternal-Grandfather";
		if (relType == 2)
			result = "Paternal-Grandmother";
		if (relType == 3)
			result = "Maternal-Grandfather";
		if (relType == 4)
			result = "Maternal-Grandmother";
		if (relType == 5)
			result = "Father";
		if (relType == 6)
			result = "Mother";
		if (relType == 7)
			result = "Paternal-aunties";
		if (relType == 8)
			result = "Maternal-aunties";
		if (relType == 9)
			result = "Siblings";
		if (relType == 10)
			result = "Children";
		return result;

	}

	
	
	
	static String getDescription(int relType) {
		String result = "unknown";
		if (relType == 0)
			result = "Patient";
		if (relType == 1)
			result = "Paternal Grandfather";
		if (relType == 2)
			result = "Paternal Grandmother";
		if (relType == 3)
			result = "Maternal Grandfather";
		if (relType == 4)
			result = "Maternal Grandmother";
		if (relType == 5)
			result = "Father";
		if (relType == 6)
			result = "Mother";
		if (relType == 7)
			result = "Paternal uncles and aunts";
		if (relType == 8)
			result = "Maternal uncles and aunts";
		if (relType == 9)
			result = "Siblings";
		if (relType == 10)
			result = "Children";
		return result;

	}

	/**
	 * like getDescription, but shows a singular rather than a plural
	 *     (child rather than children, and so on)
	 * @param relType
	 * @return Description of this relationship type.
	 * 
	 * 
	 */
	static String getAltGescription(int relType) {
		String result = "unknown";
		if (relType == 0)
			result = "Patient";
		if (relType == 1)
			result = "Paternal Grandfather";
		if (relType == 2)
			result = "Paternal Grandmother";
		if (relType == 3)
			result = "Maternal Grandfather";
		if (relType == 4)
			result = "Maternal Grandmother";
		if (relType == 5)
			result = "Father";
		if (relType == 6)
			result = "Mother";
		if (relType == 7)
			result = "Paternal uncle or aunt";
		if (relType == 8)
			result = "Maternal uncle or aunt";
		if (relType == 9)
			result = "Sibling";
		if (relType == 10)
			result = "Child";
		return result;

	}

	/**
	 * Gets the number of possible relations of a given type. E.g. you can
	 * have 1 father, but MAX_SIBLINGS siblings.
	 * 
	 * @param relType
	 * @return The maximum number of people with this relationship to the patient.
	 * 
	 */
	public static int getInstances(int relType) {
	
		int result = 1;
		if (relType == PAT_AUNTIES)
			result = MAX_SIBLINGS;
		if (relType == MAT_AUNTIES)
			result = MAX_SIBLINGS;
		if (relType == SIBLINGS)
			result = MAX_SIBLINGS;
		if (relType == CHILDREN)
			result = MAX_SIBLINGS;
		return result;

	}

	/***
	 * 
	 * Returns a description of a given risk category, e.g. "Average risk"
	 * 
	 * @param category
	 * @return A description of the risk category
	 */
	public static String getRiskGescription(int category) {
		String result = "?";
		if (category == 0)
			result = "AVERAGE RISK";
		if (category == 1)
			result = "SLIGHTLY ABOVE AVERAGE RISK";
		if (category == 2)
			result = "MODERATELY INCREASED RISK";
		if (category == 3)
			result = "HIGH RISK";
		return result;
	}

	/**
	 * 
	 * Is this type the father's side (0) or the mother's side (1) or a
	 * child/sibling (2). The patient himself also returns 2.
	 * 
	 * @param type relationship type, e.g. 5 = Father,
	 * @return The side on the family of this type
	 */
	public static int getSide(int type) {
		int result = 0;
		if (type == Util.PATIENT)
			result = SIDE_BOTH;

		if (type == Util.FATHER)
			result = SIDE_FATHER;

		if (type == Util.MOTHER)
			result = SIDE_MOTHER;

		if (type == Util.PAT_GRANDFATHER)
			result = SIDE_FATHER;

		if (type == Util.PAT_GRANDMOTHER)
			result = SIDE_FATHER;

		if (type == Util.MAT_GRANDFATHER)
			result = SIDE_MOTHER;

		if (type == Util.MAT_GRANDMOTHER)
			result = SIDE_MOTHER;

		if (type == Util.PAT_AUNTIES)
			result = SIDE_FATHER;

		if (type == Util.MAT_AUNTIES)
			result = SIDE_MOTHER;

		if (type == Util.SIBLINGS)
			result = SIDE_BOTH;

		if (type == Util.CHILDREN)
			result = SIDE_BOTH;

		return result;

	}

	/**
	 * 
	 * @param sideOfFamily: The father's side or the mother's side, or both
	 * 
	 * @param type Relationship type, e.g. 5 is Father.
	 * @return true if a relationship of this type belongs to the given side of
	 *         the family. If the type is for Children or siblings, then this 
	 *         returns true for each side of the family. 
	 * 
	 */
	public static boolean isOfSide(int sideOfFamily, int type) {
		
		boolean result = false;
		if (sideOfFamily == SIDE_FATHER) {
			result = sameSide(Util.FATHER, type);
		} else {
			if (sideOfFamily == SIDE_MOTHER) {
				result = sameSide(Util.MOTHER, type);
			} else {
				if (sideOfFamily == SIDE_BOTH) {
					result = true;
				} else {
					Window.alert("invalid family side in calculator "
							+ sideOfFamily);
				}
			}
		}
		return result;
	}

	
	/**
	 * Get the degree of the relationship of the given type. The father and the
	 * mother, children and siblings are of the first degree. Others are of the
	 * second degree. This is all vs. the patient.
	 * 
	 * @param type Relationship type
	 * @return 0: for the patient himself (type=0), 1 for primary relationships,
	 *    2 for secondary relationships.
	 */
	public static int getDegree(int type) {
		
		int result = 0;
		if (type == Util.PATIENT)
			result = 0;
		
		if (type == Util.FATHER)
			result = DEGREE_FIRST;
		
		if (type == Util.MOTHER)
			result = DEGREE_FIRST;
		
		if (type == Util.PAT_GRANDFATHER)
			result = DEGREE_SECOND;
		
		if (type == Util.PAT_GRANDMOTHER)
			result = DEGREE_SECOND;
		
		if (type == Util.MAT_GRANDFATHER)
			result = DEGREE_SECOND;
		
		if (type == Util.MAT_GRANDMOTHER)
			result = DEGREE_SECOND;
		
		if (type == Util.PAT_AUNTIES)
			result = DEGREE_SECOND;
		
		if (type == Util.MAT_AUNTIES)
			result = DEGREE_SECOND;
		
		if (type == Util.SIBLINGS)
			result = DEGREE_FIRST;
		
		if (type == Util.CHILDREN)
			result = DEGREE_FIRST;
		
		return result;

	}

	/**
	 * Get the degree of the relationship. We say that it is of the 2nd degree if it is not
	 * of the first. This is not strictly correct: The father and the mother are not related
	 * at all, yet in this method, they come out as secondary relationship. This does not 
	 * matter, because the system is only interested in whether or not it is first.
	 * 
	 * Also, we say that everyone is a primary relative of himself.
	 * 
	 * @param type1
	 * @param type2
	 * 
	 * @return the degree of the relationship between type1 and type2.
	 * 
	 * 
	 */
	public static int getDegree(int type1, int type2) {
		int result = Util.DEGREE_SECOND;
		
		if (type1 != Util.PATIENT && type1 == type2) {
			return Util.DEGREE_FIRST;
		}
		
		if (type1 == Util.PAT_GRANDFATHER || type1 == Util.PAT_GRANDMOTHER) {
			if (type2 == Util.FATHER || type2 == Util.PAT_AUNTIES) {
				result = Util.DEGREE_FIRST;
			} 	
		}
		if (type1 == Util.MAT_GRANDFATHER || type1 == Util.MAT_GRANDMOTHER) {
			if (type2 == Util.MOTHER || type2 == Util.MAT_AUNTIES) {
				result = Util.DEGREE_FIRST;
			} 	
		}
		if (type1 == Util.FATHER) {
			if (type2 == Util.PAT_GRANDFATHER || type2 == Util.PAT_GRANDMOTHER || type2 == PAT_AUNTIES || type2 == Util.PATIENT || type2==Util.SIBLINGS) {
				result = Util.DEGREE_FIRST;
			}
		}
		if (type1 == Util.MOTHER) {
			if (type2 == Util.MAT_GRANDFATHER || type2 == Util.MAT_GRANDMOTHER || type2 == MAT_AUNTIES || type2 == Util.PATIENT || type2== Util.SIBLINGS) {
				result = Util.DEGREE_FIRST;
			}
		}
		if (type1 == Util.PAT_AUNTIES) {
			if (type2 == Util.PAT_GRANDFATHER || type2 == Util.PAT_GRANDMOTHER || type2 == Util.FATHER ) {
				result = Util.DEGREE_FIRST;
			}
		}
		if (type1 == Util.MAT_AUNTIES) {
			if (type2 == Util.MAT_GRANDFATHER || type2 == Util.MAT_GRANDMOTHER || type2 == MOTHER ) {
				result = Util.DEGREE_FIRST;
			}
		}
		if (type1 == Util.PATIENT) {
			if (type2 == Util.FATHER || type2 == Util.MOTHER || type2 == SIBLINGS || type2 == Util.CHILDREN) {
				result = Util.DEGREE_FIRST;
			}
		}
		if (type1 == Util.SIBLINGS) {
			if (type2 == Util.FATHER || type2 == Util.MOTHER || type2 == PATIENT ) {
				result = Util.DEGREE_FIRST;
			}
		}
		if (type1 == Util.CHILDREN) {
			if (type2 == Util.PATIENT  ) {
				result = Util.DEGREE_FIRST;
			}
		}
		
		
		
		return result;
	}
	
	/**
	 * Work out if 2 relationship types are at the same side of the family.
	 * 
	 * @param type1
	 * @param type2
	 * 
	 * @return true if type1 and type2 are on the same side of the family. Children and
	 * siblings are always on the same side, regardless of the other type.
	 * 
	 * 
	 */

	public static boolean sameSide(int type1, int type2) {
		boolean result = true;
		if (getSide(type1) == SIDE_BOTH  || getSide(type2) == SIDE_BOTH ) {
			// children and siblings are at both sides of the family.
			result = true;
		} else {
			if (getSide(type1) == getSide(type2)) {
				result = true;
			} else {
				result = false;
			}

		}
		return result;
	}
	

	/**
	 * returns all non alphanumeric characters from a string
	 * 
	 * @param s
	 * @return
	 */
	static String removeNonAlpha(String s) {
		
		String result = s.replaceAll("[^a-zA-Z]", "");
	
		return result;
		
	}
	
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	static String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
	
	
	
}

