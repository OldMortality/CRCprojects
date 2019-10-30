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

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * 
 * This class takes all the details Panels (which have all the data, entered by the user,
 * and maintains an attribute CancerHistory[]. It is then able to answer questions about
 * how many relatives with cancer there are, ages, etc.
 * 
 * 
 * @author michel
 *
 */
public class CancerHistoryManager {

	private CancerHistory[] histories;

	public CancerHistory[] getHistories() {
		return histories;
	}

	public void setHistories(CancerHistory[] histories) {
		this.histories = histories;
	}

	private Logger logger = Logger.getLogger("CancerHistoryManager");

	/**
	 * default constructor
	 * 
	 */
	public CancerHistoryManager() {

	}

	/** 
	 * 
	 * Constructor, which converts the array of panels to CancerHistory[] 
	 * 
	 * @param det
	 
	 */
	public CancerHistoryManager(DetailsPanel[] det) {
		super();
		toHistories(det);
	}

	/**
	 * Converts the detailsPanels to CancerHistory[]
	 * 
	 * @param details
	 */
	public void toHistories(DetailsPanel[] details) {
		
		// count the number of relations. If there is a name in the textbox,
		//   then there is something worth counting. This relies on the screen
		//   validation: you must always enter a name.
		int numberOfRelations = 0;
		for (int i = 0; i < details.length; i++) {
			DetailsPanel thePanel = details[i];
			Grid grid = thePanel.getGrid();
			// go through the grid, skip the header
			for (int rows = 1; rows < grid.getRowCount(); rows++) {
				if (grid.getWidget(rows, Util.COL_NAME) != null
						&& (((TextBox) grid.getWidget(rows, Util.COL_NAME)))
								.getValue().length() > 0) {

					// if there is a name, there is a row
					numberOfRelations++;

				}
			}
		}
		
		// now we have the length of the required array, so we can initialise
		// it
		logger.log(Level.FINEST,
				"There are this number of relations with cancer history "
						+ numberOfRelations);
		this.histories = new CancerHistory[numberOfRelations];

		// there are about 10 panels, each of which can have multiple rows in
		// the grid!

		int historiesIndex = 0;

		// i loops through the panels
		for (int i = 0; i < details.length; i++) {
			DetailsPanel thePanel = details[i];

			Grid grid = thePanel.getGrid();
			// go through the grid, skip the header, rows loops through each
			// grid
			for (int rows = 1; rows < grid.getRowCount(); rows++) {
				if (grid.getWidget(rows, 0) != null
						&& (((TextBox) grid.getWidget(rows, 0))).getValue()
								.length() > 0) {
					// if there is a name, there is a row
					TextBox t1 = (TextBox) grid.getWidget(rows, Util.COL_NAME);
					String fred = t1.getValue();
					logger.log(Level.FINEST, "fred " + fred);

					// relationship type (father=5, mother=6, etc)
					short relationshipType = ((short) thePanel.getRelationshipType());
					
					// name
					String name = ((TextBox) grid
							.getWidget(rows, Util.COL_NAME)).getValue();
					if (null != name && name.length() > 20 ) {
					   name = name.substring(0,20);
					}
					
					// side of the family
					int side = Util.getSide(relationshipType);
					
					// primary or secondary relationship
					int degree = Util.getDegree(relationshipType);
					
					// has colon cancer
					boolean colonCancer = ((SimpleCheckBox) grid.getWidget(
							rows, Util.COL_CC_CHECKBOX)).getValue();

					// age. Set to -1 if no cancer
					String ccAgeString = (((TextBox) grid.getWidget(rows,
							Util.COL_CC_AGE)).getValue()).toString();

					short ccAge = -1;
					if (null != ccAgeString && ccAgeString.length() > 0) {
						ccAge = Short.parseShort(ccAgeString);

					}

					// multiple polyps.
					boolean multPols = ((SimpleCheckBox) grid.getWidget(
							rows, Util.COL_MULT_POL)).getValue();

					// other cancer (HNPCC)
					boolean otherCancer = ((SimpleCheckBox) grid.getWidget(
							rows, Util.COL_OT_CHECKBOX)).getValue();

					String oAgeString = (((TextBox) grid.getWidget(rows,
							Util.COL_OT_AGE)).getValue()).toString();

					short oAge = -1;
					if (null != oAgeString && oAgeString.length() > 0) {
						oAge = Short.parseShort(oAgeString);

					}

			 
					
					// erefid (the id, created by ereferral application. Was passed in
					// as a request parameter 
					String erefID = thePanel.getErefID();
					
					// create the CancerHistorie object
					histories[historiesIndex] = new CancerHistory( "nhi","erefid",
							relationshipType, 
							name, 							 
							colonCancer, 
							ccAge, 
							multPols, 
							otherCancer,
							oAge,
							false,
							(short) -1,
							""
						 ); 

					historiesIndex++;

				}
			}
		}

		logger.log(Level.FINEST,
				"We now have created this number of relations:"
						+ histories.length);

	}

	/**
	 * Counts the number of relatives with colon cancer, for a given side of the 
	 * family, and degree of relatedness
	 * 
	 * @param sideOfFamily (0: Father's side, 1: Mother's side)
	 * @param degree (1: Primary relationship, 2: secondary relationship)
	 * @return
	 */
	int getRelativesWithColonCancerAnyAge(int sideOfFamily, int degree) {
		int result = 0;
		for (int i = 0; i < histories.length; i++) {
			CancerHistory thisOne = histories[i];
			if (Util.isOfSide(sideOfFamily, thisOne.getRelationshipType())
					&& Util.getDegree(thisOne.getRelationshipType()) == degree
					&& thisOne.isColonCancer()) {
				result++;
			}
		}
		logger.log(Level.FINEST,
				"getRelativesWithColonCancerAnyAge(int sideOfFamily, int degree) "
						+ sideOfFamily + " - " + degree + " " + result);
		return result;
	}

	/**
	 * 
	 * returns all history records of relatives with HNPCC.
	 * 
	 * @return all history records of relatives with HNPCC.
	 * 
	 */
	public CancerHistory[] getHistoryHNPCC() {
		int counter = 0;
		// count them first
		for (int i = 0; i < histories.length; i++) {
			if ( histories[i].isOtherCancer() ) {
				counter++;
			}
		}
		// create array with all the HNPCC relatives
		CancerHistory[] result = new CancerHistory[counter];
		int j=0;
		for (int i = 0; i < histories.length; i++) {
			if ( histories[i].isOtherCancer() ) {
				result[j] = histories[i];
				j++;
			}
		}
		return result;
	}
	/*
	 *
	 */
	int getPrimaryRelativesWithColonCancerAnyAge(int sideOfFamily) {
		int result = 0;
		result = getRelativesWithColonCancerAnyAge(sideOfFamily,
				Util.DEGREE_FIRST);
		logger.log(Level.FINE,
				"getPrimaryRelativesWithColonCancerAnyAge(Side) + "
						+ sideOfFamily + " result: " + result);

		return result;

	}

	/*
	 * This was a bug. Children get counted double
	 */
	int getPrimaryRelativesWithColonCancerAnyAge() {
		int result = 0;

		for (int i = 0; i < histories.length; i++) {
			CancerHistory thisOne = histories[i];
			if (Util.getDegree(thisOne.getRelationshipType()) == Util.DEGREE_FIRST
					&& thisOne.isColonCancer()) {
				result++;
			}
		}
		logger.log(Level.FINE, "getPrimaryRelativesWithColonCancerAnyAge() + "
				+ result);

		return result;
	}

	int getSecondaryRelativesWithColonCancerAnyAge(int sideOfFamily) {
		int result = 0;
		result = getRelativesWithColonCancerAnyAge(sideOfFamily,
				Util.DEGREE_SECOND);
		logger.log(Level.FINEST,
				"getSecondaryRelativesWithColonCancerAnyAge(int sideOfFamily) "
						+ sideOfFamily + " : " + result);
		return result;

	}

	/**
	 * same bug. Types 'both' need to be subtracted.
	 * 
	 * @return
	 */
	int getSecondaryRelativesWithColonCancerAnyAge() {
		int result = 0;
		for (int i = 0; i < histories.length; i++) {
			CancerHistory thisOne = histories[i];
			if (Util.getDegree(thisOne.getRelationshipType()) == Util.DEGREE_SECOND
					&& thisOne.isColonCancer()) {
				result++;
			}
		}
		logger.log(Level.FINE,
				"getSecondaryRelativesWithColonCancerAnyAge() + " + result);

		return result;
	}

	/**
	 * 
	 * @param sideOfFamily
	 * @return
	 */
	int getRelativesWithColonCancerAnyAge(int sideOfFamily) {
		int result = 0;
		result = getPrimaryRelativesWithColonCancerAnyAge(sideOfFamily)
				+ getSecondaryRelativesWithColonCancerAnyAge(sideOfFamily);
		logger.log(Level.FINEST, "getRelativesWithColonCancerAnyAge(side): "
				+ sideOfFamily + " : " + result);
		return result;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	int getRelativesWithColonCancerAnyAge() {
		int result = 0;
		for (int i = 0; i < histories.length; i++) {
			CancerHistory thisOne = histories[i];
			if (thisOne.isColonCancer()) {
				result++;
			}
		}

		logger.log(Level.FINEST, "getRelativesWithColonCancerAnyAge: " + result);
		return result;
	}

	
	/**
	 * Counts relatives with colon cancer, for a given side of the family, and age
	 * at diagnosis less then the given age
	 *
	 * 
	 * @param sideOfFamily
	 * @param age
	 * @return the number of relatives at the given side of the family, with
	 * age at diagnosis less than the given age.
	 * 
	 */
	public int getRelativesWithColonCancerByAge(int sideOfFamily, int age) {
		int result = 0;
		for (int i = 0; i < histories.length; i++) {
			CancerHistory thisOne = histories[i];
			if (Util.isOfSide(sideOfFamily, thisOne.getRelationshipType())
					&& thisOne.getCcAge() < age && thisOne.isColonCancer()) {
				result++;
			}
		}
		logger.log(Level.FINEST,
				"getRelativesWithColonCancerAnyAge(int sideOfFamily, int age) "
						+ sideOfFamily + " - " + age + " " + result);
		return result;
	}

	
	/**
	 * Counts primary relatives with colon cancer, with age less than a given age.
	 * 
	 * @param age
	 * @return Number of primary relatives with Colon Cancer, and age (of diagnosis) less
	 *    than the given age.
	 *    
	 */
	public int getPrimaryRelativesWithColonCancerByAge(int age) {
		int result = 0;
		for (int i = 0; i < histories.length; i++) {
			CancerHistory thisOne = histories[i];
			if (Util.getDegree(thisOne.getRelationshipType()) == 1
					&& thisOne.getCcAge() < age && thisOne.isColonCancer()) {
				result++;
			}
		}
		logger.log(Level.FINEST,
				"getPrinaryRelativesWithColonCancerByAge(int age) ,age= " + age
						+ " : " + result);
		return result;
	}

	int getRelativesWithColonCancerAndOtherCancer(int sideOfFamily) {
		int result = 0;
		for (int i = 0; i < histories.length; i++) {
			CancerHistory thisOne = histories[i];
			if (Util.isOfSide(sideOfFamily, thisOne.getRelationshipType())
					&& thisOne.isOtherCancer() && thisOne.isColonCancer()) {
				result++;
			}
		}
		logger.log(Level.FINEST,
				"getRelativesWithOtherCancer(int sideOfFamily) " + sideOfFamily
						+ " - " + result);
		return result;

	}

	/**
	 * 
	 * @return the number of primary relatives with CC and multiple polyps
	 */
	int getRelativesWithColonCancerMultiplePolyps() {
		int result = 0;
		for (int i = 0; i < histories.length; i++) {
			CancerHistory thisOne = histories[i];
			if (
			// colon cancer
			thisOne.isColonCancer() &&
			// multiple polyps
					thisOne.isMultPols()) {
				result++;
			}
		}
		return result;
	}

	/**
	 * 
	 * @return the entire cancer history tree as an XML string.
	 */
	String toXML() {

		StringBuffer sb = new StringBuffer();
		sb.append("<HISTORY>");
		for (int i = 0; i < this.histories.length; i++) {
			CancerHistory thisOne = histories[i];
			sb.append("<RELATION>");
			sb.append("<TYPE code=>" + thisOne.getRelationshipType() + ">");
			sb.append(Util.getDescription(thisOne.getRelationshipType()));
			sb.append("</TYPE>");

			sb.append("<CC>");
			sb.append(thisOne.isColonCancer());
			sb.append("</CC>");

			sb.append("<CCAGE>");
			sb.append(thisOne.getCcAge());
			sb.append("</CCAGE>");

			sb.append("<Multiple Polyps>");
			sb.append(thisOne.isMultPols());
			sb.append("</Multiple Polyps>");

			sb.append("<OC>");
			sb.append(thisOne.isOtherCancer());
			sb.append("</OC>");

			sb.append("<OCAGE>");
			sb.append(thisOne.getoAge());
			sb.append("</OCAGE>");

			sb.append("<DECEASED>");
			sb.append(thisOne.isDeceased());
			sb.append("</DECEASED>");

			sb.append("<DAGE>");
			sb.append(thisOne.getdAge());
			sb.append("</DAGE>");

			sb.append("</RELATION>");
		}
		sb.append("</HISTORY>");

		return sb.toString();
	}
	
	
	// test only
	public static void main(String[] args) {
		String s = null;
		Short sh = Short.parseShort(s);
		System.out.println("the short is:" + sh);
	}

}