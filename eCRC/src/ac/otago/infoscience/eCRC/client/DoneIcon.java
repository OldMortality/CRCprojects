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

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;


/**
 * Manages the symbol (green tick) which appears in the family tree image, when 
 * a relation does have cancer. This is used so that we can see which relation has
 * been done by the user.
 * 
 * 
 * @author michel
 *
 */
public class DoneIcon extends AbsolutePanel implements ClickHandler {

	int type;
	FamilyPanel parent;
	Image theImage;
	Logger logger = Logger.getLogger("CancerIcon");
	
	DoneIcon() {
		
	}
	
	/**
	 * 
	 * @param type relationship type
	 * @param FamilyPanel the panel, which holds the family tree image.
	 * 
	 * 
	 * 
	 */
	DoneIcon(int type, FamilyPanel parent) {
		this.type = type;
		this.parent = parent;
		theImage = new Image("images/done.png");
		add(theImage);
		theImage.setVisible(false);
		theImage.addClickHandler(this);
	}
	
	int getType() {
		return this.type;
	}
	
	int getSizeX() {
		return theImage.getWidth();
	}
	
	int getSizeY() {
		return theImage.getHeight();
	}
	
	
	
	
	void setVisible(int type, boolean visible) {
		logger.log(Level.FINEST,"type " + type + "visible " + visible);
		logger.log(Level.FINEST,"this.type " + this.type );
		
		if (this.type == type) {
			theImage.setVisible(visible);
		}
	}
	
	
	public void onClick(ClickEvent event) {
		// Object sender = event.getSource();
		// logger.log(Level.FINEST, "click event");
		
		int whoWasClicked = this.type;
		// details.setVisible(true);
		parent.cancerSignClicked(whoWasClicked);
		// if (sender == checkBox) {
		// When the check box is clicked, update the text box's enabled state.
		// textBox.setEnabled(checkBox.isChecked());

	}
	
}
