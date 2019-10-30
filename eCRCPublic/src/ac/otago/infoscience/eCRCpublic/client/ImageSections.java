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
 * Holds the part of the family tree image, which represents a relationship
 * type. For instance, the Mother is found in pixels between x1,y1 and x2,y2.
 * 
 * 
 * @author michel
 * 
 */
public class ImageSections {

	Logger logger = Logger.getLogger("ImageSections");

	ImageSection[] sections = new ImageSection[Util.NUMBER_OF_RELATIONSHIPS];

	public ImageSections() {
		sections[Util.PATIENT] = new ImageSection(Util.PATIENT, 278, 195, 355,
				264);
		sections[Util.PAT_GRANDFATHER] = new ImageSection(Util.PAT_GRANDFATHER,
				82, 12, 111, 75);
		sections[Util.PAT_GRANDMOTHER] = new ImageSection(Util.PAT_GRANDMOTHER,
				175, 12, 206, 75);
		sections[Util.MAT_GRANDFATHER] = new ImageSection(Util.MAT_GRANDFATHER,
				416, 14, 458, 75);
		sections[Util.MAT_GRANDMOTHER] = new ImageSection(Util.MAT_GRANDMOTHER,
				513, 15, 541, 79);
		sections[Util.FATHER] = new ImageSection(Util.FATHER, 242, 114, 284,
				176);
		sections[Util.MOTHER] = new ImageSection(Util.MOTHER, 335, 119, 379,
				180);
		sections[Util.PAT_AUNTIES] = new ImageSection(Util.PAT_AUNTIES, 21,
				109, 97, 178);
		sections[Util.MAT_AUNTIES] = new ImageSection(Util.MAT_AUNTIES, 493,
				115, 575, 183);
		sections[Util.SIBLINGS] = new ImageSection(Util.SIBLINGS, 101, 214,
				178, 271);
		sections[Util.CHILDREN] = new ImageSection(Util.CHILDREN, 273, 305,
				347, 365);

	}

	boolean isBetween(int x, int x1, int x2) {
		boolean result = false;
		if ((x >= x1 && x <= x2) || (x <= x1 && x >= x2)) {
			result = true;
		}
		return result;
	}

	/**
	 * 
	 * @return true if (x,y) is between points (x1,y1) and (x2,y2) on the screen
	 * 
	 */
	boolean isBetween2D(int x, int y, int x1, int y1, int x2, int y2) {
		boolean result = false;
		if (isBetween(x, x1, x2) && isBetween(y, y1, y2)) {
			result = true;
		}
		return result;
	}

	/**
	 * returns the relationship type in the image map, at the
	 * given coordinates.
	 * 
	 * @param x
	 * @param y
	 * @return The relationship type, shown in (x,y)
	 * 
	 * 
	 */
	public int whoWasIt(int x, int y) {
		int result = -1;
		for (int i = 0; i < Util.NUMBER_OF_RELATIONSHIPS; i++) {

			// logger.log(Level.FINEST, "trying " + i);
			if (isBetween2D(x, y, sections[i].x1, sections[i].y1,
					sections[i].x2, sections[i].y2)) {
				result = i;
			}
		}
		return result;
	}

	int whereToPutCancerImageX(int type, int x) {
		int result = 0;
		result = (sections[type].getX1() + sections[type].getX2() - x) / 2;
		return result;
	}

	public int whereToPutCancerImageY(int type, int y) {
		int result = 0;
		result = (sections[type].getY1() + sections[type].getY2() - y) / 2;

		// result = sections[type].getMiddleY();
		return result;
	}

	int whereToPutCrossImageX(int type) {
		int result = 0;
		result = sections[type].getX2();
		return result;
	}

	int whereToPutCrossImageY(int type) {
		int result = 0;
		result = sections[type].getY1() - 15;
		return result;
	}


	public int whereToPutOverlayImageX(int type) {
		int result = 0;
		result = sections[type].getX1();
		return result;
	}

	public int whereToPutOverlayImageY(int type) {
		int result = 0;
		result = sections[type].getY1() ;
		return result;
	}

	
	
	public int whereToPutNamePanelX() {
		int result = 0;
		result = sections[0].getX1() + 80;
		return result;
	}

	int whereToPutNamePanelY() {
		int result = 0;
		result = sections[0].getY1() + 15;
		return result;
	}

	/**
	 * Figures out the x-coordinate of the circle, which is shown around the
	 * patient on the family tree.
	 * 
	 * @param x
	 *            the width of the image
	 * @return the X value, so that the centre of the image will be on the
	 *         centre of the puppet in the family picture.
	 * 
	 */
	public int whereToPutRiskCircleX(int x) {
		int result = 0;
		result = (sections[0].getX1() + sections[0].getX2() - x) / 2;
		logger.log(Level.FINE, "sections[0].getX1() " + sections[0].getX1());
		logger.log(Level.FINE, "sections[0].getX2() " + sections[0].getX2());
		logger.log(Level.FINE, "x " + x);

		return result;
	}

	/**
	 * Figures out the y-coordinate of the circle, which is shown around the
	 * patient on the family tree.
	 * 
	 * @param y
	 *            the width of the image
	 * @return the Y value, so that the centre of the image will be on the
	 *         centre of the puppet in the family picture.
	 * 
	 */
	public int whereToPutRiskCircleY(int y) {

		int result = 0;
		result = (sections[0].getY1() + sections[0].getY2() - y) / 2;

		return result;
	}
}

/**
 * A part of the image map, which holds one type of relationship, for example,
 * relType 0 corresponds the the part of the image map where the paternal
 * grandfather is shown.
 * 
 * @author michel
 * 
 */
class ImageSection {
	int relationshipType;
	int x1;
	int x2;
	int y1;
	int y2;

	
	ImageSection() {
	}
	
	ImageSection(int relType, int x1, int y1, int x2, int y2) {
		super();
		this.relationshipType = relType;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public int getY1() {
		return y1;
	}

	public int getX1() {
		return x1;
	}

	public int getY2() {
		return y2;
	}

	public int getX2() {
		return x2;
	}

	int getMiddleX() {
		int result = 0;
		int middle = x1 + (x2 - x1) / 2;
		if (middle < 0) {
			result = -1 * result;
		} else {
			result = middle;
		}
		return result;
	}

	int getMiddleY() {
		int result = 0;
		int middle = y1 + (y2 - y1) / 2;
		if (middle < 0) {
			result = -1 * result;
		} else {
			result = middle;
		}
		return result;

	}

}
