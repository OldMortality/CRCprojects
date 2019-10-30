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

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * This panel shows the patient's name on the family tree. It retrieves the
 * request parameters for name, date of birth, NHI number and erefID.
 * 
 * It used to show also those variables, but we decided just to show the name. I
 * have left the code for the others commented out, in case they need to go back
 * in.
 * 
 * 
 * @author michel
 * 
 */
public class PatientNamePanel extends VerticalPanel

{

	private String patientNHI = "";
	private String patientDOB = "";
	private String erefID = "";
	private String patientName = "";

	PatientNamePanel() {

	}

	PatientNamePanel(Form1 parent) {
		super();

		// get http request parameter for name
		String patientNameNew = Window.Location.getParameter("name");

		if (null != patientNameNew) {

			// prevent XSS
			patientNameNew = Util.escapeHtml(patientNameNew).replaceAll("[^a-zA-Z\\s]", "");
			logger.log(Level.FINEST, patientNameNew);
		} else {
			patientName = "";
			logger.log(Level.WARNING, "No patient name parameter passed in");
		}

		this.patientName = patientNameNew;

		// get http request parameter for dob
		patientDOB = Window.Location.getParameter("dob");
		if (null != patientDOB) {
			// prevent XSS
			patientDOB = Util.escapeHtml(patientDOB);
			logger.log(Level.FINEST, patientDOB);
		} else {
			patientDOB = "";
			logger.log(Level.WARNING, "No patient d.o.b. parameter passed in");
		}

		// get http request parameter for nhi
		patientNHI = Window.Location.getParameter("nhi");
		if (null != patientNHI) {
			// prevent XSS. taking no chances :)
			patientNHI = Util.escapeHtml(patientNHI).replaceAll("[^a-zA-Z0-9\\s]", "");
			logger.log(Level.FINEST, patientNHI);
		} else {
			patientNHI = "";
			logger.log(Level.WARNING, "No patient NHI parameter passed in");
		}

		// get http request parameter for erefid. This is a unique id, created
		// by the ereferral application. It is the key to retrieving the family
		// history data from this system, using the web service.

		String eref = Window.Location.getParameter("erefid");
		// remove all non alpa-numeric characters.
		erefID = eref.replaceAll("[^a-zA-Z0-9\\s]", "");
		
		if (null != erefID) {
			// prevent XSS
			erefID = Util.escapeHtml(erefID);
			logger.log(Level.FINEST, erefID);
			if (erefID.length() > 32) {
				erefID = erefID.substring(0, 31);
				logger.log(Level.SEVERE,
						"The length of erefID exceeds 32 characters! " + erefID);
			}
		} else {
			erefID = "";
			logger.log(Level.WARNING, "No patient erefID parameter passed in");
		}

		// show the patient name
		this.add(new Label(patientNameNew));

		// we are not showing these for now

		// this.add(new Label("d.o.b.:" + patientDOB));
		// this.add(new Label("N.H.I. " + patientNHI));
		// this.setStylePrimaryName("namePanelStyle");

		// erefID is not shown. It has no meaning to the user.

	}

	public String getPatientDOB() {
		return patientDOB;
	}

	public void setPatientDOB(String patientDOB) {
		this.patientDOB = patientDOB;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public void setErefID(String erefID) {
		this.erefID = erefID;
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

	public String getPatientName() {
		return patientName;
	}

	Logger logger = Logger.getLogger("PatientNamePanel");

}
