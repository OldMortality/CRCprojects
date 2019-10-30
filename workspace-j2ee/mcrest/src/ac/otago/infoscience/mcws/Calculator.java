package ac.otago.infoscience.mcws;


import java.text.DecimalFormat;

import org.apache.log4j.Logger;

/**
 * 
 * @author michel
 *
 *
 * This class is responsible for calculating absolute risk. There are separate methods for 
 * female and for male patients.
 * 
 */
public class Calculator {
	

	private static Logger logger = Logger
			.getLogger("ac.otago.infoscience.mcws.Calculator");

	
	/**
	 * 
	 * Calculate absolute risk for a female person.
	 * 
	 * @param parms int[] = { skinColourInt,faxMHXMolesInt,moles,nmscInt,ageInt, areaInt } The
	 * order of the elements does not matter.
	 * @return calculated absolute risk for the given risk profile for women.
	 * 
	 */

	public static double calculateFemale(int[] parms) {

		

		for (int i=0;i<parms.length;i++) {
			logger.debug("calculateFemale, parms" + i + ": " + parms[i]);
		}
 
		// Table2 holds baseline incidence and mortality
		Table2 table2 = Table2.newInstance();
		
		// get baseline melanoma incidence for women, the age group and the area.
		float baseline = table2.getMelanomaIncidence(Util.GENDER_FEMALE,
				Util.toAgeGroup(parms[Util.AGE_INDEX_F]), parms[Util.AREA_INDEX_F]);
		logger.debug("baseline incidence " + baseline);
		
		// get baseline mortality for women of this age group.
		float h2 = table2.getMortality(Util.GENDER_FEMALE,
				Util.toAgeGroup(parms[Util.AGE_INDEX_F]));
		logger.debug("baseline mortality (h2)" + h2);
		
		h2 = h2/100000;
		logger.debug("h2 * 1E-5 = " + h2);
		
		
		int[] riskFactors = {parms[0], parms[1],parms[2],parms[3]};
		
		// calculate relative risk for the given risk profile.
		float rr = Table3.getRelativeRiskWomen(riskFactors);
		logger.debug("relative risk is " + rr);

		// now do some magic to calculate absolute risk
		float h1 = baseline * Util.ADJ_FEMALE / 100000;
		logger.debug("baseline * (1-0.8893) is h1 = " + h1);
		
		float numerator = h1 * rr;
		logger.debug("h1 * rr = " + numerator);
		
		float denominator = h1 * rr + h2;
		logger.debug("h1*rr + h2 = " + denominator);

		// first factor of P(a,r)
		double f1 = numerator / denominator;
		logger.debug("first factor= " + f1);
		// second factor of P(a,r)
		double f2 = (1 - Math.exp(-5 * denominator));
		logger.debug("1- exp(-5 * denominator) " + f2);
		
		// P(a,r)
		double result = f1 * f2;
		logger.debug("P(a,r) = " + result);
		
		logger.debug("result is " + result);
		
		return (result);

	}
	
	
	/**
	 * 
	 * Calculate absolute risk for a male person.
	 *         
	 * @param parms int[]  { moles, nmsc, occupation,birth,age,area } 
	 * @return absolute risk
	 */
	public static double calculateMale(int[] parms) {

		for (int i=0;i<parms.length;i++) {
			logger.debug("calculateMale, parms" + i + ": " + parms[i]);
		}
		
		// Table2 holds baseline incidence and mortality
		Table2 table2 = Table2.newInstance();
		
		// get baseline melanoma incidence for men, the age group and the area.
		float baseline = table2.getMelanomaIncidence(Util.GENDER_MALE,
				Util.toAgeGroup(parms[Util.AGE_INDEX_M]), parms[Util.AREA_INDEX_M]);
		logger.debug("baseline incidence " + baseline);
		
		// get baseline mortality for men of this age group.
		float h2 = table2.getMortality(Util.GENDER_MALE,
				Util.toAgeGroup(parms[Util.AGE_INDEX_M]));
		h2 = h2/100000;
		logger.debug("h2 * 1E-5 = " + h2);
		
		logger.debug("baseline mortality (h2) " + h2);
		 
		
		int[] riskFactors = {parms[0], parms[1], Util.lessThan51(parms[Util.AGE_INDEX_M]),  parms[2],parms[3]};
		
		// calculate relative risk for this man.
		float rr = Table3.getRelativeRiskMen(riskFactors);
		logger.debug("relative risk is: " + rr);

		// calculate absolute risk.
		float h1 = baseline * Util.ADJ_MALE / 100000;
		logger.debug("baseline * (1-0.8510) is h1 = " + h1);
		
		float numerator = h1 * rr;
		logger.debug("h1 * rr = " + numerator);
		
		float denominator = h1 * rr + h2;
		logger.debug("h1*rr + h2 = " + denominator);

		// first factor of P(a,r)
		double f1 = numerator / denominator;
		logger.debug("first factor= " + f1);

		// second factor of P(a,r)
		double f2 = (1 - Math.exp(-5 * denominator));
		logger.debug("1- exp(-5 * denominator) " + f2);

		// P(a,r)
		double result = f1 * f2;
		logger.debug("P(a,r) = " + result);
		
	 
		logger.debug("result is " + result);
		
		return (result);

		 

	}
	

	/**
	 * test only
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// female: parms is { skinColourInt,faxMHXMolesInt,moles,nmscInt,ageInt, areaInt }
		int[] parms = {Util.SKINCOLOUR_FAIR,
				Util.FAMHXMOLES_YES,
				Util.MOLES_RARM_3PLUS,
				Util.NMSC_YES_F,
				40,
				Util.AREA_SOUTH};
		String s = String.valueOf(calculateFemale(parms));
		System.out.println("result is: " + s);

		// male: parms is { moles, nmsc, occupation,birth,age,area }
		//int[] parms2 = {5,Util.NMSC_YES_M,Util.OCCUPATION_OUT,Util.BIRTH_NZ,60,Util.AREA_MIDLAND};
		//String s2 = String.valueOf(calculateMale(parms2));
		//System.out.println("result is: " + s2);
		
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(4);
		df.setMinimumFractionDigits(4);
		
		logger.debug("result is " + df.format(0.001f));
		
		
		
	}

}
