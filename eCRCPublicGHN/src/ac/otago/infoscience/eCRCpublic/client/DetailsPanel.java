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
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is the panel, where you enter the data about a type of relation. There is 1 such panel for 
 * each relationship type. The panels always exist, but just 0 or 1 is shown at any point in time. The 
 * rest is invisible.
 * 
 *  
 * @author michel
 *
 */
public class DetailsPanel extends VerticalPanel implements ClickHandler {

	private Logger logger = Logger.getLogger("DetailsPanel");
	
	// relationship type
	private int relationshipType;
	
	public int getRelationshipType() {
		return relationshipType;
	}


	public void setRelationshipType(int relationshipType) {
		this.relationshipType = relationshipType;
	}

	private String patientNHI;
	
	// id, provided by ereferall system
	private String erefID;
	
	 	
	 
	

	 

	// validation errors show here
	Label errorLabel = new Label();

	 

	Form1 parent;
	
	// this is where the user types family history details.
	Grid grid;

	// used for testing
	public void setGrid(Grid grid) {
		this.grid = grid;
	}


	public Grid getGrid() {
		return grid;
	}

	// default constructor
	public DetailsPanel() {
	}

	/**
	 * @param Form1: The parent form
	 * @param type: relationship type 
	 * @param patientNHI nhi number of the patient. 
	 * @param erefID: Unique id, provided by ereferral system
	 * 
	 * 
	 */
	public DetailsPanel(Form1 par, int type, String patientNHI, String erefID) {

		super();
		this.relationshipType = type;
		this.parent = par;
		this.patientNHI = patientNHI;
		this.erefID = erefID;

		if (type > -1) {

			// emptylabels provide vertical alignment
			Label emptyLabel = new Label("");
			emptyLabel.setStylePrimaryName("emptyLabel");
			add(emptyLabel);
			
			Label headerLabel = new Label("");
			add(headerLabel);
			
			Label emptyLabel2 = new Label("");
			emptyLabel2.setStylePrimaryName("emptyLabel2");
			add(emptyLabel2);
			
			headerLabel.setText("Cancer history of "
					+ Util.getDescription(type));
			
			headerLabel.setStylePrimaryName("headerLabel");
			headerLabel.setHorizontalAlignment(ALIGN_CENTER);
			
			int instances = Util.getInstances(type);

			// create the right size grid, 1 row for the header, and 8 columns.
			grid = new Grid(instances + 1, 6);
			
			
			/*
			 * Mind that if you change the layout of these fields, you must also
			 * change the count functions further down. You must also change
			 * CancerHistoryManager.toHistories()
			 */
			grid.setSize("572px", "146px");
			grid.setWidget(0, Util.COL_NAME, new Label("Name"));
			grid.setWidget(0, Util.COL_CC_CHECKBOX, new Label("Colon Cancer"));
			grid.setWidget(0, Util.COL_CC_AGE, new Label("Age at diagnosis"));
			grid.setWidget(0, Util.COL_MULT_POL,
					new Label("Multiple \n polyps"));
			grid.setWidget(0, Util.COL_OT_CHECKBOX, new Label("HNPCC"));
			grid.setWidget(0, Util.COL_OT_AGE, new Label("Age at diagnosis"));
		 
			grid.setBorderWidth(2);

			for (int i = 1; i <= instances; i++) {

				TextBox nameTextBox = new TextBox();
				
				// name of this relation
				grid.setWidget(i, Util.COL_NAME, nameTextBox);
				nameTextBox.setTitle(Util.TOOLTIP_NAME);
				nameTextBox.setWidth("73px");
				nameTextBox.ensureDebugId(Util.ID_NAME + "-" + i);
				
				// was he diagnosed with colon cancer
				final SimpleCheckBox ccCheckBox = new SimpleCheckBox();
				grid.setWidget(i, Util.COL_CC_CHECKBOX, ccCheckBox);
				ccCheckBox.setTitle(Util.TOOLTIP_CC);
				ccCheckBox.ensureDebugId(Util.ID_CC + "-" + i);
				
				// age of diagnosis of CC
				final TextBox ageCCTextBox = new TextBox();
				ageCCTextBox.setTitle(Util.TOOLTIP_CC_AGE);
				grid.setWidget(i, Util.COL_CC_AGE, ageCCTextBox);
				ageCCTextBox.setWidth("54px");
				ageCCTextBox.setEnabled(false);
				ageCCTextBox.addKeyPressHandler(new NumbersOnly());
				ageCCTextBox.ensureDebugId(Util.ID_CC_AGE + "-"+ i);
				
				// multiple polyps. Only applies if colon cancer.
				final SimpleCheckBox polypsCheckBox = new SimpleCheckBox();
				grid.setWidget(i, Util.COL_MULT_POL, polypsCheckBox);
				polypsCheckBox.setEnabled(false);
				polypsCheckBox.setTitle(Util.TOOLTIP_MULT);
				polypsCheckBox.ensureDebugId(Util.ID_MULT+ "-" + i);
				
				
				// hnpcc type cancer
				final SimpleCheckBox otherCancerCheckBox = new SimpleCheckBox();
				grid.setWidget(i, Util.COL_OT_CHECKBOX, otherCancerCheckBox);
				otherCancerCheckBox.setTitle(Util.TOOLTIP_OT);
				otherCancerCheckBox.ensureDebugId(Util.ID_OT + "-" + i);
				
				// age of diagnosis
				final TextBox otherCancerAgeTextBox = new TextBox();
				otherCancerAgeTextBox.setTitle(Util.TOOLTIP_OT_AGE);
				grid.setWidget(i, Util.COL_OT_AGE, otherCancerAgeTextBox);
				otherCancerAgeTextBox.setWidth("36px");
				otherCancerAgeTextBox.setEnabled(false);
				otherCancerAgeTextBox.addKeyPressHandler(new NumbersOnly());
				otherCancerAgeTextBox.ensureDebugId(Util.ID_OT_AGE + "-"+i);
			 
				/*
				 * Here come some handlers, which make sure that we only ask for
				 * an age if it is relevant, and only ask for multiple polyps if
				 * the person had colon cancer.
				 * 
				 */
				ccCheckBox.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						logger.log(Level.FINEST, "Cancer box clicked");
						if (ccCheckBox.getValue() == true) {
							polypsCheckBox.setEnabled(true);
							ageCCTextBox.setEnabled(true);
						} else {
							polypsCheckBox.setEnabled(false);
							polypsCheckBox.setValue(false);
							ageCCTextBox.setEnabled(false);
							ageCCTextBox.setValue(null);
						}

					}
				});

				otherCancerCheckBox.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						logger.log(Level.FINEST, "Cancer box clicked");
						if (otherCancerCheckBox.getValue() == true) {
							otherCancerAgeTextBox.setEnabled(true);

						} else {
							otherCancerAgeTextBox.setEnabled(false);
							otherCancerAgeTextBox.setValue(null);
						}

					}
				});

				 

			}
			
			add(grid);
			
			// vertical alignment
			Label emptyLabel3 = new Label("");
			emptyLabel3.setStylePrimaryName("emptyLabel3");
			add(emptyLabel3);
			
		 
		 	
			 	

			this.add(errorLabel);
			errorLabel.setStylePrimaryName("errorTextStyle");

			Button okButton = new Button();
			okButton.setText("OK");
			okButton.ensureDebugId(Util.ID_BUTTON_OK);
			add(okButton);
			okButton.setStylePrimaryName("okButtonDetailsStyle");
			
			

			okButton.addClickHandler(this);

			// disable the submit button. It is only enabled after the details
			// button is clicked, so that
			// we can validate the intput.
			par.disableSubmitButton();
			
			
			
		}
	}

	public String getPatientNHI() {
		return patientNHI;
	}

	public String getErefID() {
		return erefID;
	}
	
	public void setPatientNHI(String patientNHI) {
		this.patientNHI = patientNHI;
	}

	void setFocus() {
		  TextBox t = (TextBox) grid.getWidget(1,0) ;
		  t.setFocus(true);

	}
	
	/**
	 * @ to do implement this method
	 * 
	 */
	String validateInput() {
		String result = "";
		boolean errorFound = false;
		StringBuffer errorMsg = new StringBuffer("");
		for (int rows = 1; rows < grid.getRowCount(); rows++) {
			// if there is any box checked, we want the name
			if (((SimpleCheckBox) grid.getWidget(rows, Util.COL_CC_CHECKBOX))
					.getValue() == true
					|| ((SimpleCheckBox) grid.getWidget(rows,
							Util.COL_OT_CHECKBOX)).getValue() == true
					 )

			{

				String name = ((TextBox) grid.getWidget(rows, Util.COL_NAME))
						.getValue();
				
				name = Util.removeNonAlpha(name);
				
				((TextBox) grid.getWidget(rows, Util.COL_NAME))
				.setValue(name);


				if (name == null || name.length() == 0) {
					// errorMsg.append("Name should not be blank. ");
					errorFound = true;
					((TextBox) grid.getWidget(rows, Util.COL_NAME))
							.setStylePrimaryName("inputError");
				}

			}
			// if there is colon cancer, we want the age
			if (((SimpleCheckBox) grid.getWidget(rows, Util.COL_CC_CHECKBOX))
					.getValue() == true) {
				String age = ((TextBox) grid.getWidget(rows, Util.COL_CC_AGE))
						.getValue();
				
				try {
				
					Integer.parseInt(age);
			        
				} catch (NumberFormatException e) {

					errorFound = true;
					((TextBox) grid.getWidget(rows, Util.COL_CC_AGE))
							.setStylePrimaryName("inputError");

				}
					
					
				
				
				if (age == null || age.length() == 0) {
					// errorMsg.append("Please enter the age when this person was diagnosed. ");
					errorFound = true;
					((TextBox) grid.getWidget(rows, Util.COL_CC_AGE))
							.setStylePrimaryName("inputError");

				}
			}
			// if there is other cancer, we want the age
			if (((SimpleCheckBox) grid.getWidget(rows, Util.COL_OT_CHECKBOX))
					.getValue() == true) {
				String age = ((TextBox) grid.getWidget(rows, Util.COL_OT_AGE))
						.getValue();
			
				
				try {
					
					Integer.parseInt(age);
				
				} catch (Exception e) {

					errorFound = true;
					((TextBox) grid.getWidget(rows, Util.COL_OT_AGE))
							.setStylePrimaryName("inputError");

				}
				
				
				
				if (age == null || age.length() == 0) {
					errorFound = true;
					// errorMsg.append("Please enter the age when this person was diagnosed. ");
					((TextBox) grid.getWidget(rows, Util.COL_OT_AGE))
							.setStylePrimaryName("inputError");

				}
			}

			 
			// if there are no boxes ticked, we clear the name:
			if (      ((SimpleCheckBox) grid.getWidget(rows, Util.COL_CC_CHECKBOX))
					.getValue() == false   &&
					((SimpleCheckBox) grid.getWidget(rows, Util.COL_OT_CHECKBOX))
					.getValue() == false) {
				  			((TextBox)  grid.getWidget(rows, Util.COL_NAME)).setValue(""); 
			}
			
			// check for reasonable ages
			String ageCC = ((TextBox)  grid.getWidget(rows, Util.COL_CC_AGE)).getValue();
			Boolean CC = ((SimpleCheckBox) grid.getWidget(rows, Util.COL_CC_CHECKBOX)).getValue();
			
			
			if (CC && ageCC != null  ) {
				try {
				int age = Integer.parseInt(ageCC);
				if (age > Util.MAX_AGE) {
					errorFound = true;
					((TextBox) grid.getWidget(rows, Util.COL_CC_AGE))
							.setStylePrimaryName("inputError");
				} else {
					((TextBox) grid.getWidget(rows, Util.COL_CC_AGE))
					.setStylePrimaryName("inputOK");
				}
				} catch (NumberFormatException e) {
					logger.log(Level.SEVERE,"Invalid age format for ageCC!");
					
				}
			}
			
			String ageOT = ((TextBox)  grid.getWidget(rows, Util.COL_OT_AGE)).getValue();
			Boolean OT = ((SimpleCheckBox) grid.getWidget(rows, Util.COL_OT_CHECKBOX)).getValue();
			
			if ( OT == true && ageOT != null  ) {
				try {
				int age = Integer.parseInt(ageOT);
				if (age > Util.MAX_AGE) {
					errorFound = true;
					((TextBox) grid.getWidget(rows, Util.COL_OT_AGE))
							.setStylePrimaryName("inputError");
				} else {
					((TextBox) grid.getWidget(rows, Util.COL_OT_AGE))
					.setStylePrimaryName("inputOK");
				}
				} catch (NumberFormatException e) {
					logger.log(Level.SEVERE,"Invalid age format for ageOT!");
					
				}
			}
			

		 	
			
			 
					
			 
		}

		if (errorFound) {
			errorMsg.append("Please correct the above error(s)");
		} else {
			errorMsg = new StringBuffer();
		}
		result = errorMsg.toString();
		return result;
	}

	/**
	 * @todo set done only if there is something in the entry box. This involves
	 *       looping through all the instances.
	 */
	public void onClick(ClickEvent event) {
		// Object sender = event.getSource();
		logger.log(Level.FINEST, "click event");

		// details.setVisible(true);

		// draw the crab
		
		// if (sender == checkBox) {
		// When the check box is clicked, update the text box's enabled state.
		// textBox.setEnabled(checkBox.isChecked());
		//
		String validationResult = validateInput();
		boolean ok = validationResult.length() == 0;
		if (ok) {
			resetStyles();
			
			int totalOccurrencesOfCancer = this.countTotalCancer();
			if (totalOccurrencesOfCancer > 0) {
				parent.setCancerSign(relationshipType);
			} else {
				parent.removeCancerSign(relationshipType);
			}

			 
			
			
			parent.updateRiskAssessment();
			parent.enableSubmitButton();
			parent.enableClicksOnFamilyPanel();
			parent.clearEntryPanel();
			this.errorLabel.setText("");
		} else {
			// validation error
			this.errorLabel.setText(validationResult);
		}

	}

	int countColonCancer() {
		int totalCC = 0;
		for (int i = 1; i < grid.getRowCount(); i++) {
			logger.log(Level.FINEST, "trying row " + i);

			SimpleCheckBox box = (SimpleCheckBox) (grid.getWidget(i,
					Util.COL_CC_CHECKBOX));
			if (box.getValue()) {
				totalCC++;
			}

		}
		logger.log(Level.FINEST, "counted # colon cancer: " + totalCC);
		return totalCC;
	}

	int countOtherCancer() {
		logger.log(Level.FINEST, "going to count other cancer");
		int totalOther = 0;
		for (int i = 1; i < grid.getRowCount(); i++) {
			SimpleCheckBox box = (SimpleCheckBox) (grid.getWidget(i,
					Util.COL_OT_CHECKBOX));
			if (box.getValue()) {
				totalOther++;
			}

		}
		logger.log(Level.FINEST, "counted # other cancer: " + totalOther);
		return totalOther;
	}

	int countTotalCancer() {
		int result = this.countColonCancer() + this.countOtherCancer();
		logger.log(Level.FINEST, "counted # total cancer: " + result);
		return result;
	}

	 

	class NumbersOnly implements KeyPressHandler {
		@Override
		public void onKeyPress(KeyPressEvent event) {
			 char charCode= (char) event.getNativeEvent().getKeyCode();
		       if(charCode == (char) KeyCodes.KEY_BACKSPACE  ||
		    		   charCode == (char) KeyCodes.KEY_DELETE ||
		    		   charCode == (char) KeyCodes.KEY_LEFT ||
		    		   charCode == (char) KeyCodes.KEY_RIGHT) {
		    	   
		       } else {
			
			if (!Character.isDigit(event.getCharCode()))
				((TextBox) event.getSource()).cancelKey();
		       }
		}
	}

	void resetStyles() {
		for (int rows = 1; rows < grid.getRowCount(); rows++) {
			for (int cols = 0; cols < grid.getColumnCount(); cols++) {
				logger.log(Level.FINE, "RESETSTYLES" + rows + ".   " + cols);
				((Widget) grid.getWidget(rows, cols))
						.setStylePrimaryName("inputElement");
			}
		}
	}
	
}
