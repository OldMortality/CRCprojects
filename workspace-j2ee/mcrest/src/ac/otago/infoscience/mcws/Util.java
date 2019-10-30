package ac.otago.infoscience.mcws;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;

public class Util {

	public static final int TABLE2_NUM_ROWS = 14;
	public static final int TABLE2_NUM_COLS = 10;
	public static final int AGE_INDEX_F = 4;
	public static final int AREA_INDEX_F = 5;

	public static final int AGE_INDEX_M = 5;
	public static final int AREA_INDEX_M = 6;
	public static final int MAX_DIGITS = 4;

	public static int GENDER_FEMALE = 0;
	public static int GENDER_MALE = 1;

	public static int AREA_NORTH = 0;
	public static int AREA_MIDLAND = 1;
	public static int AREA_CENTRAL = 2;
	public static int AREA_SOUTH = 3;

	public static String AREA_NORTH_STRING = "NORTH";
	public static String AREA_MIDLAND_STRING = "MIDLAND";
	public static String AREA_CENTRAL_STRING = "CENTRAL";
	public static String AREA_SOUTH_STRING = "SOUTH";

	// variables for women
	public static int SKINCOLOUR_OLIVE = 0;
	public static int SKINCOLOUR_MEDIUM = 1;
	public static int SKINCOLOUR_FAIR = 2;
	public static int FAMHXMOLES_DONTKNOW = 3;
	public static int FAMHXMOLES_NO = 4;
	public static int FAMHXMOLES_YES = 5;
	public static int MOLES_RARM_0 = 6;
	public static int MOLES_RARM_1 = 7;
	public static int MOLES_RARM_2 = 8;
	public static int MOLES_RARM_3PLUS = 9;

	public static int NMSC_NO_F = 10;
	public static int NMSC_YES_F = 11;

	// variables for men
	public static int NUMBER_OF_MOLES = 0;
	public static int NMSC_NO_M = 1;
	public static int NMSC_YES_M = 2;
	public static int AGE_50_OR_LESS = 3;
	public static int AGE_51_OR_MORE = 4;
	public static int OCCUPATION_IN = 5;
	public static int OCCUPATION_INOUT = 6;
	public static int OCCUPATION_OUT = 7;

	public static String OCCUPATION_IN_STR = "IN";
	public static String OCCUPATION_INOUT_STR = "INOUT";
	public static String OCCUPATION_OUT_STR = "OUT";

	public static int BIRTH_NOTNZ = 8;
	public static int BIRTH_NZ = 9;

	public static String BIRTH_NOTNZ_STR = "NOT_NZ";
	public static String BIRTH_NZ_STR = "NZ";

	// these are 2 adjustment factors,
	public static float ADJ_FEMALE = 0.1107f;
	public static float ADJ_MALE = 0.1499f;

	private static Logger logger = Logger
			.getLogger("ac.otago.infoscience.mcws.Util");

	/**
	 * validate parameters for a woman. All parameters are case insensitive.
	 * 
	 * @param skinColour
	 *            "fair","olive","medium"
	 * @param famHXMoles
	 *            "yes","no","dont_know"
	 * @param molesRarm
	 *            a non negative integer
	 * @param nmsc
	 *            "no","yes"
	 * @param age
	 *            an integer greater than 19
	 * @param area
	 *            "north","midland","central","south"
	 * 
	 * @return int[] representing the parameters, or null if the input is
	 *         invalid. These integers follow Table 3 in the paper, from top to
	 *         bottom. For example, olive skin => 1 and medium skin => 2.
	 *         FAMXHMOLES="don't know" => 4, and so on.
	 * 
	 *         Hence, a woman with olive skin,
	 *         famhxmoles=yes,moles_rarm=3+,nmsc=yes, age=25, and area="south"
	 *         would return: {1,6,10,12,25,Util.AREA_SOUTH=3}
	 * 
	 */
	static int[] validateFemale(String skinColour, String famHXMoles,
			String molesRarm, String nmsc, String age, String area) {

		boolean validInput = true;
		int skinColourInt = -1;
		int[] result = null;

		if (skinColour == null) {
			logger.debug("missing parameter skinColour");
			return (result);
		}
		if (famHXMoles == null) {
			logger.debug("missing parameter famhxmoles");
			return (result);
		}
		if (molesRarm == null) {
			logger.debug("missing parameter molesRarm");
			return (result);
		}
		if (nmsc == null) {
			logger.debug("missing parameter nmsc");
			return (result);
		}
		if (age == null) {
			logger.debug("missing parameter age");
			return (result);
		}
		if (area == null) {
			logger.debug("missing parameter area");
			return (result);
		}

		if (skinColour.toUpperCase().equals("OLIVE")) {
			skinColourInt = Util.SKINCOLOUR_OLIVE;
		} else {
			if (skinColour.toUpperCase().equals("MEDIUM")) {
				skinColourInt = Util.SKINCOLOUR_MEDIUM;
			} else {
				if (skinColour.toUpperCase().equals("FAIR")) {
					skinColourInt = Util.SKINCOLOUR_FAIR;
				} else {
					logger.debug("skincolour should be olive, medium or fair");
					validInput = false;
				}
			}
		}

		int famHXMolesInt = -1;
		if (validInput) {
			if (famHXMoles.toUpperCase().equals("DONT_KNOW")) {
				famHXMolesInt = Util.FAMHXMOLES_DONTKNOW;
			} else {
				if (famHXMoles.toUpperCase().equals("YES")) {
					famHXMolesInt = Util.FAMHXMOLES_YES;
				} else {
					if (famHXMoles.toUpperCase().equals("NO")) {
						famHXMolesInt = Util.FAMHXMOLES_NO;
					} else {
						logger.debug("famHXMoles should be dont_know, yes or no");
						validInput = false;
					}
				}
			}

		}

		int moles = -1;
		if (validInput) {
			try {

				moles = Integer.parseInt(molesRarm);
				if (moles < 0) {

					validInput = false;
					logger.debug("error formatting #moles: negative number");
				}

			} catch (NumberFormatException e) {

				validInput = false;
				logger.debug("error formatting #moles");

			}

			// assign risk group
			moles = Util.femaleMolesGroups(moles);

		}

		int nmscInt = -1;
		if (validInput) {

			if (nmsc.toUpperCase().equals("NO")) {

				nmscInt = Util.NMSC_NO_F;

			} else {

				if (nmsc.toUpperCase().equals("YES")) {

					nmscInt = Util.NMSC_YES_F;

				} else {

					logger.debug("nmsc should be YES or NO, got " + nmsc);
					validInput = false;
				}

			}
		}

		Integer ageInt = -1;
		if (validInput) {
			try {

				ageInt = Integer.parseInt(age);

				if (ageInt < 20) {

					validInput = false;
					logger.debug("error formatting age: less than 20");
				}

			} catch (NumberFormatException e) {

				validInput = false;
				logger.debug("error formatting age, got " + age);
			}

		}

		Integer areaInt = -1;
		if (validInput) {

			if (area.toUpperCase().equals(Util.AREA_CENTRAL_STRING)) {
				areaInt = Util.AREA_CENTRAL;
			} else if (area.toUpperCase().equals(Util.AREA_MIDLAND_STRING)) {
				areaInt = Util.AREA_MIDLAND;
			} else if (area.toUpperCase().equals(Util.AREA_NORTH_STRING)) {
				areaInt = Util.AREA_NORTH;
			} else if (area.toUpperCase().equals(Util.AREA_SOUTH_STRING)) {
				areaInt = Util.AREA_SOUTH;
			} else {
				logger.debug("area should be north, midland, central,south");
				validInput = false;
			}

		}

		int[] parms = { skinColourInt, famHXMolesInt, moles, nmscInt, ageInt,
				areaInt };
		if (!validInput) {
			parms = null;
		}

		return (parms);
	}

	/**
	 * Validate input parameters for a man.
	 * 
	 * @param molesRarm
	 *            0,1,2,3,...
	 * @param nmsc
	 *            "yes","no"
	 * @param occ
	 *            "in","out","inout"
	 * @param birthplace
	 *            "nz","not_nz"
	 * @param age
	 *            20,21,22,..
	 * @param area
	 *            "north","midland","central","south"
	 * 
	 * @return int[] representing the parameters, or null if the input is
	 *         invalid. See the method for females for how this array is built
	 *         up. The only difference is that the first element of the result
	 *         for men is the number of moles as an integer. For females, we
	 *         have 0,1,2,3+ as categories, but for men, we have just the number
	 *         of moles.
	 * 
	 */
	static int[] validateMale(String molesRarm, String nmsc, String occ,
			String birthplace, String age, String area) {

		boolean validInput = true;
		int[] result = null;

		if (molesRarm == null) {
			logger.debug("missing parameter molesRarm");
			return (result);

		}
		if (nmsc == null) {
			logger.debug("missing parameter nmsc");
			return (result);

		}
		if (occ == null) {
			logger.debug("missing parameter occ");
			return (result);

		}
		if (birthplace == null) {

			logger.debug("missing parameter birthplace");
			return (result);

		}
		if (age == null) {
			logger.debug("missing parameter age");
			return (result);

		}
		if (area == null) {
			logger.debug("missing parameter area");
			return (result);

		}

		int moles = -1;
		if (validInput) {
			try {

				moles = Integer.parseInt(molesRarm);
				if (moles < 0) {

					validInput = false;
					logger.debug("error formatting #moles: negative number");
				}

			} catch (NumberFormatException e) {

				validInput = false;
				logger.error("invalid parameter molesRamr " + molesRarm, e);
			}
		}

		int nmscInt = -1;
		if (validInput) {

			if (nmsc.toUpperCase().equals("NO")) {

				nmscInt = Util.NMSC_NO_M;

			} else {

				if (nmsc.toUpperCase().equals("YES")) {

					nmscInt = Util.NMSC_YES_M;

				} else {

					logger.debug("nmsc should be YES or NO, got " + nmsc);
					validInput = false;
				}

			}
		}

		int occInt = -1;
		if (validInput) {
			if (occ.toUpperCase().equals(Util.OCCUPATION_IN_STR)) {
				occInt = Util.OCCUPATION_IN;
			} else {
				if (occ.toUpperCase().equals(Util.OCCUPATION_INOUT_STR)) {
					occInt = Util.OCCUPATION_INOUT;
				} else {
					if (occ.toUpperCase().equals(Util.OCCUPATION_OUT_STR)) {
						occInt = Util.OCCUPATION_OUT;
					} else {
						validInput = false;
						logger.debug("occ should be IN, OUT or INOUT. got "
								+ occ);

					}
				}
			}
		} else {
			validInput = false;
			logger.error("invalid parameter occupation " + occ);
		}

		int birthInt = -1;
		if (validInput) {
			if (birthplace.toUpperCase().equals(Util.BIRTH_NOTNZ_STR)) {
				birthInt = Util.BIRTH_NOTNZ;
			} else {
				if (birthplace.toUpperCase().equals(Util.BIRTH_NZ_STR)) {
					birthInt = Util.BIRTH_NZ;
				} else {
					validInput = false;
					logger.debug("birthplace should be " + Util.BIRTH_NOTNZ_STR
							+ " or " + Util.BIRTH_NZ_STR + "instead got "
							+ birthplace);

				}
			}

		}

		Integer ageInt = -1;
		if (validInput) {
			try {

				ageInt = Integer.parseInt(age);
				if (ageInt < 20) {

					validInput = false;
					logger.debug("error formatting age: less than 20");
				}

			} catch (NumberFormatException e) {

				validInput = false;
				logger.debug("error formatting age, got " + age);
			}

		}

		Integer areaInt = -1;
		if (validInput) {

			if (area.toUpperCase().equals(Util.AREA_CENTRAL_STRING)) {
				areaInt = Util.AREA_CENTRAL;
			} else if (area.toUpperCase().equals(Util.AREA_MIDLAND_STRING)) {
				areaInt = Util.AREA_MIDLAND;
			} else if (area.toUpperCase().equals(Util.AREA_NORTH_STRING)) {
				areaInt = Util.AREA_NORTH;
			} else if (area.toUpperCase().equals(Util.AREA_SOUTH_STRING)) {
				areaInt = Util.AREA_SOUTH;
			} else {
				validInput = false;
				logger.debug("invalid area parameter:" + area);
			}

		}

		int ageGroup = Util.AGE_50_OR_LESS;
		if (ageInt > 50) {
			ageGroup = Util.AGE_51_OR_MORE;
		}
		int[] parms = { moles, nmscInt, occInt, birthInt, ageGroup, ageInt,
				areaInt };

		if (!validInput) {
			parms = null;
		}

		return (parms);

	}

	/**
	 * convert age in years to age group. 20-24 years is group 0, 25-29 is group
	 * 1, etc.
	 * 
	 * @param age
	 *            , 20,21,...
	 * @return age group 0,1,2, or -1 if age<20
	 */
	public static int toAgeGroup(int age) {

		int result = -1;
		if (age >= 20) {
			result = age / 5 - 4;
		}
		if (age >= 85) {
			result = 13;
		}
		return (result);
	}

	/**
	 * 
	 * @param age
	 * @return Util.AGE_LESS50 if the given age <= 50, or Util.AGE_MORE50 if age
	 *         = 51,52,...
	 */
	public static int lessThan51(int age) {

		int result = AGE_51_OR_MORE;
		if (age <= 50) {
			result = Util.AGE_50_OR_LESS;
		}
		return (result);
	}

	public static int femaleMolesGroups(int moles) {

		int result = -1;
		if (moles == 0) {
			result = Util.MOLES_RARM_0;
		} else {
			if (moles == 1) {
				result = Util.MOLES_RARM_1;
			} else {
				if (moles == 2) {
					result = Util.MOLES_RARM_2;
				} else {
					if (moles >= 3) {
						result = Util.MOLES_RARM_3PLUS;
					} else {
						logger.debug("invalid number of moles" + moles);
					}
				}
			}
		}
		return (result);

	}

	public static String LOW_RISK = "LOW_RISK";
	public static String MODERATE_RISK = "MODERATE_RISK";
	public static String HIGH_RISK = "HIGH_RISK";
	public static String VERY_HIGH_RISK = "VERY_HIGH_RISK";

	static float LOW_RISK_UPP = 0.0027f;
	static float MODERATE_RISK_UPP = 0.0100f;
	static float HIGH_RISK_UPP = 0.0170f;

	/**
	 * work out risk category, based on relative risk;
	 * 
	 * @param risk
	 *            float
	 * @return risk category
	 */
	public static String calculateRiskCategory(Float risk) {

		String result = null;
		if (risk == null) {
			return result;
		}
		if (risk < 0f) {
			return result;
		}

		if (risk <= LOW_RISK_UPP) {
			result = LOW_RISK;
		} else {
			if (risk <= MODERATE_RISK_UPP) {
				result = MODERATE_RISK;
			} else if (risk <= HIGH_RISK_UPP) {
				result = HIGH_RISK;
			} else {
				result = VERY_HIGH_RISK;
			}
		}

		return result;

	}

	public static String LOW_RISK_TEXT = "this person is in the lowest 30% of absolute risk of melanoma development in the Nz population. They are predicted to have a low risk of developing melanoma in the next 5 years.";
	public static String MODERATE_RISK_TEXT = "The predicted absolute risk for this person lies between the lowest 30% and the highest 30% of melanoma risk in the NZ population. They are predicted to have a moderate risk of developing melanoma in the next 5 years.";
	public static String HIGH_RISK_TEXT = "This person is in the highest 30% of absolute risk of melanoma development in the Nz population. They are predicted to have a high risk of developing melanoma in the next 5 years.";
	public static String VERY_HIGH_RISK_TEXT = "This person is in the highest 15% of absolute risk of melanoma development in the Nz population. They are predicted to have a very high risk of developing melanoma in the next 5 years.";

	public static String getRiskDescription(String riskCategory) {
		String result = "ERROR";
		if (riskCategory == null) {
			return result;
		}

		if (riskCategory.equals(LOW_RISK)) {
			result = LOW_RISK_TEXT;
		} else {
			if (riskCategory.equals(MODERATE_RISK)) {
				result = MODERATE_RISK_TEXT;
			} else {
				if (riskCategory.equals(HIGH_RISK)) {
					result = HIGH_RISK_TEXT;
				} else {
					if (riskCategory.equals(VERY_HIGH_RISK)) {
						result = VERY_HIGH_RISK_TEXT;
					} else {
						logger.error("unexpected riskCategory " + riskCategory);
					}
				}
			}
		}

		return result;
	}

	
	public static String toElement(String riskCategory) {
		return("<RISK_CATEGORY>" + riskCategory + "</RISK_CATEGORY>");
	}
	
	public static String riskToXML(float risk) {

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(Util.MAX_DIGITS);
		df.setMinimumFractionDigits(Util.MAX_DIGITS);
		String result = df.format(risk);
		
		
		String riskCategory = calculateRiskCategory(risk);
		String riskDescription = getRiskDescription(riskCategory);

		String xml = "<?xml version=\"1.0\"?>" + "<MR><RISK>" + result
				+ "</RISK>" +toElement(riskCategory) + "<DESCRIPTION>" + riskDescription
				+ "</DESCRIPTION>" + "</MR>";
		return xml;

	}

	/**
	 * testing only
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		for (int i = 1; i < 90; i++) {
			System.out.println(i + "   " + toAgeGroup(i));
		}

	}
}
