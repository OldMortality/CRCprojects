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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * Panel, which shows the risk calculation
 * 
 * @author michel
 * 
 */
public class RiskPanel extends VerticalPanel {

	static Logger logger = Logger.getLogger("RiskPanel");

	// the grid, containing the family history, as entered previously
	// by the user.
	static SummaryPanel summaryPanel;

	// panel, containing the summaryPanel
	static VerticalPanel familyAndRiskPanel = new VerticalPanel();

	// The parent form
	static Form1 parent;

	// default constructor
	RiskPanel() {
	}

	/**
	 * 
	 * @param par
	 *            parent panel
	 * @param riskLevel
	 *            risk category
	 * @param patientName
	 * @param patientDOB
	 * @param patientNHI
	 * @param histories
	 *            CancerHistory[] as entered by the user.
	 */
	RiskPanel(Form1 par, int riskLevel, CancerHistory[] histories) {
		super();
		parent = par;
		
		// set hidden textarea for testing with Selenium.

		TextArea hidden = new TextArea();
		add(hidden);
		hidden.setVisible(false);
		hidden.ensureDebugId(Util.ID_RISK_LEVEL);
		hidden.getElement().setAttribute("risklevel", new Integer(riskLevel).toString());

		// contains close and print buttons
		HorizontalPanel buttonPanel = new HorizontalPanel();

		logger.log(Level.FINE, "Starting risk calculation dialogue  + result= " + riskLevel);

		// vertical alignment
		Label emptyLabelRiskPanel = new Label("");
		emptyLabelRiskPanel.setStylePrimaryName("emptyLabelRiskPanelStyle");
		add(emptyLabelRiskPanel);

		// Add some text to the top of the dialog
		Label detailsLabel = new Label(" Risk assessment " );
		detailsLabel.setStylePrimaryName("detailsLabelRiskCalculationStyle");
		detailsLabel.setHorizontalAlignment(ALIGN_CENTER);
		add(detailsLabel);

		// the traffic light image.
		Image image;
		StringBuffer imageString = new StringBuffer("images/risknew");
		imageString.append(riskLevel);
		imageString.append(".png");
		// Add an image to the dialog

		image = new Image(imageString.toString());

		// the image with the 4 colours.
		add(image);
		image.ensureDebugId(Util.ID_RISK_IMAGE);

		// The box with text and the disclaimer
		String imageName = "images/risktext-new" + riskLevel + ".png";
		Image riskDescriptionImage = new Image(imageName);
		add(riskDescriptionImage);

		/*
		// open email for highest risk category
		if (riskLevel == 3) {
			riskDescriptionImage.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(final ClickEvent event) {
					logger.log(Level.FINEST,
							"Clicked risk descripton image. Should open up mail client");
					Window.open("mailto:FRBC@cdhb.govt.nz", "_blank", "");
				}
			});

		}
		*/
		
		
		// The family history, if there is one.
		if (histories.length > 0) {
			// these is a history of cancer, and we show whatever the user has
			// entered.
			summaryPanel = new SummaryPanel(histories);

			familyAndRiskPanel.clear();
			// familyAndRiskPanel.add(image);
			familyAndRiskPanel.add(summaryPanel);
		} else {
			// no history of cancer, just show a label.
			Label noHistoriesLabelEmpty = new Label();
			noHistoriesLabelEmpty
					.setStylePrimaryName("noHistoriesLabelEmptyStyle");

			Label noHistoriesLabel = new Label(
					"You have entered no family history of cancer.");
			noHistoriesLabel.setHorizontalAlignment(ALIGN_CENTER);

			noHistoriesLabel.setStylePrimaryName("noHistoriesLabelStyle");
			familyAndRiskPanel.clear();

			familyAndRiskPanel.add(noHistoriesLabelEmpty);
			familyAndRiskPanel.add(noHistoriesLabel);
		}

		add(familyAndRiskPanel);

		// Add a close button at the bottom of the dialog
		Button closeButton = new Button("CLOSE", new ClickHandler() {
			public void onClick(ClickEvent event) {
				parent.setBackToFamilyPanel();
			}
		});
		
		closeButton.ensureDebugId(Util.ID_BUTTON_CLOSE);

		// Add a print button at the bottom of the dialog
		Button printButton = new Button("PRINT", new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.print();
			}
		});

		buttonPanel.add(closeButton);
		buttonPanel.add(printButton);
		add(buttonPanel);

		// disable the Back button, so as not to lose the parameters
		// a bit naughty, we should really get the parameters from somewhere
		// and keep the back button working.

		Window.addWindowClosingHandler(new ClosingHandler() {
			@Override
			public void onWindowClosing(ClosingEvent event) {
				// event.setMessage("My program");
			}
		});

	}

}
