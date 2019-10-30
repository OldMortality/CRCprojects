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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

/**
 * 
 * Put an invisible button on each puppet on the family tree. This is used only
 * for testing with Selenium.
 * 
 * 
 * @author michel
 * 
 */
public class OverlayImage extends Button implements ClickHandler {

	int type;
	Logger logger = Logger.getLogger("OverlayImage");
	FamilyPanel parent;

	OverlayImage() {
		super();
	}

	/**
	 * @todo vary with type?
	 * @param type
	 */
	OverlayImage(int type, FamilyPanel parent) {
		super();
		this.type = type;
		this.parent = parent;
		
		// invisible buttons cannot be clicked.
		setVisible(true);

		// make it so you cannot see it.
		this.setStylePrimaryName("transparent-button");

		// set the ID, used by Selenium for finding the element.
		ensureDebugId(Util.getID(type));

		// pass click on to the parent
		addClickHandler(this);

	}

	int getType() {
		return this.type;
	}

	@Override
	public void onClick(ClickEvent event) {
		logger.log(Level.FINE, "clicked overlay image. pass click to parent");
		parent.passOnClick(event, this.type);

	}
}
