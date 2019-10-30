
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
package ac.otago.infoscience.eCRCpublic.client;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * This class is responsible for calculating the risk level for the patient,
 * based on the family history.
 * 
 * @author michel
 *
 */
public class RiskCalculator {

	private Logger logger = Logger.getLogger("RiskCalculator");
	
	private CancerHistoryManager cancerHistoryManager;
	
	public CancerHistoryManager getCancerHistoryManager() {
		return cancerHistoryManager;
	}

	public void setCancerHistoryManager(CancerHistoryManager cancerHistoryManager) {
		this.cancerHistoryManager = cancerHistoryManager;
	}

	// default constructor
	public RiskCalculator() {
		
	}
	
	public RiskCalculator(DetailsPanel[] details ) {
		super();
		this.cancerHistoryManager = new CancerHistoryManager(details);
		 
	}
	
	/**
	 * Ups the logging level if b is true
	 * @param b true will up the logging level to FINE
	 * @return logging level. Level.FINE if b = true, FINEST otherwise
	 * 
	 */
	
	private Level getLevel(boolean b) {
				
		Level result = Level.FINEST;
		if (b == true) {
			result = Level.FINE;
			
		} 
		return result;
	}
	
	/**
	 * 
	 * Note that it is important that we process the higher risk categories first. If it is not 3, then
	 * is it 2? and so on. 
	 * 
	 * Note: Condition 35 has been removed.
	 * 
	 * @return the risk category of this patient, as determined by his family history
	 * 
	 * 
	 */
	public short calculateRisk() {
		short result = 0;
		boolean condition3 = getCondition31() || getCondition32() || getCondition33() || getCondition34() ; 
		if (condition3) {
			result = Util.CATEGORY_3;
		} else {
			boolean condition2 = getCondition21() || getCondition22();
			if (condition2) {
				result = Util.CATEGORY_2;
			} else {
				boolean condition1 = getCondition1();
				if (condition1) {
					result = Util.CATEGORY_1;
				} else {
					result = Util.CATEGORY_0;
				}
			}
		}
		return result;
	}
	
	/*	
	public boolean getCondition31Old() {
		
		boolean result = false;
		
		CancerHistory[] hnpcc = cancerHistoryManager.getHistoryHNPCC();
		
		if (hnpcc.length >= 1) {
			
			result = true;
		}
		return result;
		
	}
	*/
	
	/**
	 * 
	 * Returns true iff condition 31 is met. This means all of the following must be met:
	 *    - 3 (or more) relatives with HNPCC-related cancer, one of whom is a 
	 *       FDR of the other two
	 *    - At least 2 successive affected generations
     *    - One (or more) diagnosed under the age of 50  
	 * 
	 * 
	 * @return true if condition 3.1 is met.
	 */
	
	public boolean getCondition31() {
		boolean result = false;
		CancerHistory[] hnpcc = cancerHistoryManager.getHistoryHNPCC();
		
		// there must be at least 3
		if (hnpcc.length < 3) {
			return false;
		}
		
		// at least one must have been diagnosed under age 50
		boolean found = false;
		for (int i=0;i<hnpcc.length;i++) {
			if (hnpcc[i].getoAge() > 0 && hnpcc[i].getoAge() < Util.AGE_VERY_DANGEROUS) {
				found = true;
			}
		}
		if (!found) {
			return false;
		}
		
		// now it becomes tricky.  
		// for each relative, look at the set of his first degree relatives also with hnpcc
		
		for (int i = 0; i< hnpcc.length;i++) {
			int counter = 0;
			boolean twoGenerations = false;
			boolean lowAge = false;
			
			for (int j=0;j<hnpcc.length;j++) {
				
				   // count record hnpcc[i] as well. It is a primary relationship of itself
					
					if ( Util.getDegree( hnpcc[i].getRelationshipType(),hnpcc[j].getRelationshipType()) == Util.DEGREE_FIRST) {
						counter++;
						if (hnpcc[j].getoAge() > 0 && hnpcc[j].getoAge() < Util.AGE_VERY_DANGEROUS) {
							lowAge = true;
						}
						if (Util.getGenerationDifference(hnpcc[i].getRelationshipType(), hnpcc[j].getRelationshipType()) >= 1) {
							twoGenerations = true;
						}
					}
					
				 
			}
			if (counter>=3 && twoGenerations && lowAge) {
				result = true;
			}
		} 
		
		return result;
	}
	
	
	/**
	 * 
	 * @return true if condition 3.2 is met for a given side of the family: 1 primary relative 
	 * + 2 or more relatives, with Colon cancer, regardless of the age of diagnosis.
	 * 
	 */
	
	public boolean getCondition32(int sideOfFamily) {
		boolean result = false;
		int fPrimary = cancerHistoryManager.getPrimaryRelativesWithColonCancerAnyAge(sideOfFamily);
		if (fPrimary >= 1 ) {
			int fSecondary = cancerHistoryManager.getSecondaryRelativesWithColonCancerAnyAge(sideOfFamily);
			int fTotal = fPrimary + fSecondary; 
			if (fTotal >= 3  ) {
				result = true;
			}
			
		}
		
		logger.log(getLevel(result) ,"condition32 is met: (side:)  " + sideOfFamily + "-" + result);
		return result;
	}
	
	/**
	 * Returns true if the following condition is met: " [1 first degree relative, + 2 or more
	 * relatives ] all on the same side of the family, with CC (at any age)
	 * 
	 * 
	 * @return
	 
	 */
	public boolean getCondition32() {
		boolean result = false;
		result = getCondition32(Util.SIDE_FATHER) || getCondition32(Util.SIDE_MOTHER);
		logger.log(Level.FINE,"condition32 is met: " + result);
		return result;
	}
	
 	
	/**
	 * 
	 * Returns true if condition 3-3 is met, for the given side of the family. 
	 * This is the case if there is, with Colon cancer: 
	 * Same side of family( 1 primary relative with CC + 1 other relative) : one of those
	 * has HNPCC cancer also OR one of those was diagnosed with CC before age 55
	 *  
	 * 
	 * @return true if condition 3.3 is met for the given side of the family.
	 * 
	 * 
	 * 
	 */
	public boolean getCondition33(int sideOfFamily) {
		boolean result = false;
		int fPrimary = cancerHistoryManager.getPrimaryRelativesWithColonCancerAnyAge(sideOfFamily);
		if (fPrimary >= 1 ) {
			int fSecondary = cancerHistoryManager.getSecondaryRelativesWithColonCancerAnyAge(sideOfFamily);
			int fTotal = fPrimary + fSecondary; 
			if (fTotal >= 2  ) {
				if (  cancerHistoryManager.getRelativesWithColonCancerByAge(sideOfFamily, Util.AGE_DANGEROUS) > 0 ||  
						cancerHistoryManager.getRelativesWithColonCancerAndOtherCancer(sideOfFamily) > 0	
						) {
					result = true;
				}
			}
			
		}
		logger.log(getLevel(result),"condition33 is met: (side:)  " + sideOfFamily + "-" + result);
		return result;
	}
	
	/**
	 * 
	 * @return true if condition 33 is met, for either side of the family
	 * 
	 */
	public boolean getCondition33() {
		boolean result = false;
		result = getCondition33(Util.SIDE_FATHER) || getCondition33(Util.SIDE_MOTHER);
		logger.log(getLevel(result),"condition33 is met: " + result);
		return result;
	}
	
	

	/**
	 * Works out if condition 3-4 is met.
	 * 
	 * @return true if there is at least 1 first relative with CC and multiple polyps.
	 &
	 *
	 */
	public boolean getCondition34() {
		boolean result = false;
		int count = cancerHistoryManager.getRelativesWithColonCancerMultiplePolyps();
		if (count > 0   ) {
			result = true;
		}
			
		logger.log(getLevel(result),"condition34 is met: " + result + " counted:" + count);
		return result;
	}
	
	
	/**
	 * Work out if condition 3-5 is met.
	 * 
	 * Returns true if there is a primary relative with CC, diagnosed strictly under the age of 50.
	 * .
	 * 
	 * This condition has been removed, because we do not have information about the
	 * immunohistory
	 * 
	 * 
	 * @return true if condition 3.5 is met (primary relative with CC, under age 50)
	 */
	public boolean getCondition35() {
		boolean result = false;
		int fPrimary = cancerHistoryManager.getPrimaryRelativesWithColonCancerByAge(Util.AGE_VERY_DANGEROUS);
		if (fPrimary >= 1 ) {
			result = true;
		
			logger.log(getLevel(result),"condition35 is met:  " +  result);
		}
		return result;
	}
	
	/**
	 * 
	 * Work out if condition 2-1 is met.
	 * 
	 * Returns true if there is 1 or more primary relative with colon cancer, diagnosed at an
	 * age strictly less than 55.
	 * 
	 * @return true if there is 1 or more such relative.
	 * 
	 */
	public boolean getCondition21() {
		boolean result = false;
		int fPrimary = cancerHistoryManager.getPrimaryRelativesWithColonCancerByAge(Util.AGE_DANGEROUS);
		if (fPrimary >= 1 ) {
			result = true;
			logger.log(getLevel(result),"condition21 is met:  " +  result);
		}
		return result;
	}
	
	 
	
	/** 
	 * Finds out if there are 2 or more primary relatives with colon cancer, diagnosed
	 * at any age.
	 * 
	 * 
	 * @return true, if there are 2 or more primary relatives with Colon cancer.
	 * 
	 */
	public boolean getCondition22() {
		boolean result = false;
		int i = cancerHistoryManager.getPrimaryRelativesWithColonCancerAnyAge() ;
		if (i >= 2 ) {
			result = true;
		}
		logger.log(getLevel(result),"condition22 is met:  " +  result);
		return result;
	}
	
	/** 
	 * 
	 * Work out whether condition 1 (1 primary relative 55 or over) is met.
	 * 
	 * The wording of the condition states 1!, (exactly one) but if that makes a difference,
	 * then this should have been caught by a higher category.
	 * 
	 * @return true is there is 1 or more primary relatives with bowel cancer over 
	 * (including) 55.
	 * 
	 */
	public boolean getCondition1() {
		boolean result = false;
		int numberOverAgeDangerous = 
			cancerHistoryManager.getPrimaryRelativesWithColonCancerAnyAge() -
			cancerHistoryManager.getPrimaryRelativesWithColonCancerByAge(Util.AGE_DANGEROUS);
		if (numberOverAgeDangerous > 0) {
			result = true;
		}		
		logger.log(getLevel(result),"condition 1 is met:  " +  result);
		return result;
	}



	/**
	 * Get the family history as an xml string.
	 * 
	 */
	
	public String getHistoryXML() {
		return cancerHistoryManager.toXML();
	}
	
	 
}