package ac.otago.infoscience.eCRCpublic.client;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ac.otago.infoscience.eCRCpublic.client.CancerHistory;
import ac.otago.infoscience.eCRCpublic.client.CancerHistoryManager;
import ac.otago.infoscience.eCRCpublic.client.DetailsPanel;
import ac.otago.infoscience.eCRCpublic.client.Form1;
import ac.otago.infoscience.eCRCpublic.client.Util;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Test the toHistories() method, which coverts the data on the User Interface
 * to a CancerHistory[] array.
 * 
 * @author michel
 * 
 */

public class CancerHistoryManagerTest2 extends GWTTestCase {

	DetailsPanel detailsPanel;

	@Test
	public void test1() {
		int runNumber = 1;
		String EREF_1 = "test run 1";
		String nhi = "test1";
		CancerHistoryManager manager = new CancerHistoryManager();
		Form1 parent = new Form1();

		// Detailspanel #1
		detailsPanel = new DetailsPanel(parent, Util.PAT_GRANDFATHER, nhi,
				EREF_1);
		// 1 row for the header, and 1 row of data. 8 columns

		Grid grid = new Grid(2, 8);
		detailsPanel.setGrid(grid);
		// the header, just to have a row 0.
		TextArea header = new TextArea();
		header.setValue("header");
		grid.setWidget(0, 0, header);
		// now the data

		// name
		String name = "Fred";
		TextBox textBoxName = new TextBox();
		textBoxName.setValue(name);
		grid.setWidget(1, Util.COL_NAME, textBoxName);

		// colon cancer
		SimpleCheckBox cc = new SimpleCheckBox();
		cc.setValue(true);
		grid.setWidget(1, Util.COL_CC_CHECKBOX, cc);

		// colon cancer age
		TextBox ccAge = new TextBox();
		ccAge.setValue("44");
		grid.setWidget(1, Util.COL_CC_AGE, ccAge);

		// multiple polyps
		SimpleCheckBox mult = new SimpleCheckBox();
		mult.setValue(true);
		grid.setWidget(1, Util.COL_MULT_POL, mult);

		// other cancer
		SimpleCheckBox ot = new SimpleCheckBox();
		ot.setValue(true);
		grid.setWidget(1, Util.COL_OT_CHECKBOX, ot);

		// colon cancer age
		TextBox otAge = new TextBox();
		otAge.setValue("55");
		grid.setWidget(1, Util.COL_OT_AGE, otAge);
 
	 

		DetailsPanel[] details = { detailsPanel };

		// convert to CancerHistory[]
		manager.toHistories(details);

		// retrieve the CancerHistory[]
		CancerHistory[] history = manager.getHistories();

		// assertions
		assertEquals("run number: " + runNumber, name, history[0].getName());

		assertEquals("run number: " + runNumber, true,
				history[0].isColonCancer());

		assertEquals("run number: " + runNumber, 44, history[0].getCcAge());

		assertEquals("run number: " + runNumber, "44",
				history[0].getCcAgeString());

		assertEquals("run number: " + runNumber, true, history[0].isMultPols());

		assertEquals("run number: " + runNumber, true,
				history[0].isOtherCancer());

		assertEquals("run number: " + runNumber, 55, history[0].getoAge());

		assertEquals("run number: " + runNumber, "55",
				history[0].getoAgeString());

		assertEquals("run number: " + runNumber, true, history[0].isDeceased());

		assertEquals("run number: " + runNumber, 66, history[0].getdAge());

		assertEquals("run number: " + runNumber, "66",
				history[0].getdAgeString());

		assertEquals("run number: " + runNumber, EREF_1, history[0].getErefID());

		 
		assertEquals("run number: " + runNumber, Util.PAT_GRANDFATHER,
				history[0].getRelationshipType());

		runNumber = 2;
		SimpleCheckBox ccBox = (SimpleCheckBox) detailsPanel.getGrid()
				.getWidget(1, Util.COL_CC_CHECKBOX);
		ccBox.setValue(false);
		manager.toHistories(details);
		history = manager.getHistories();
		assertEquals("run number: " + runNumber, false,
				history[0].isColonCancer());

		runNumber = 3;
		TextBox c = (TextBox) detailsPanel.getGrid().getWidget(1,
				Util.COL_CC_AGE);
		c.setValue("-1");
		manager.toHistories(details);
		history = manager.getHistories();
		assertEquals("run number: " + runNumber, -1, history[0].getCcAge());
		assertEquals("run number: " + runNumber, "",
				history[0].getCcAgeString());

		runNumber = 4;
		SimpleCheckBox mBox = (SimpleCheckBox) detailsPanel.getGrid()
				.getWidget(1, Util.COL_MULT_POL);
		mBox.setValue(false);
		manager.toHistories(details);
		history = manager.getHistories();
		assertEquals("run number: " + runNumber, false, history[0].isMultPols());

		runNumber = 5;
		SimpleCheckBox oBox = (SimpleCheckBox) detailsPanel.getGrid()
				.getWidget(1, Util.COL_OT_CHECKBOX);
		oBox.setValue(false);
		manager.toHistories(details);
		history = manager.getHistories();
		assertEquals("run number: " + runNumber, false,
				history[0].isOtherCancer());

		runNumber = 6;
		TextBox a = (TextBox) detailsPanel.getGrid().getWidget(1,
				Util.COL_OT_AGE);
		a.setValue("-1");
		manager.toHistories(details);
		history = manager.getHistories();
		assertEquals("run number: " + runNumber, -1, history[0].getoAge());
		assertEquals("run number: " + runNumber, "", history[0].getoAgeString());

		runNumber = 7;
		 
		manager.toHistories(details);
		history = manager.getHistories();
		assertEquals("run number: " + runNumber, false, history[0].isDeceased());

		runNumber = 8;
		 
		 
		manager.toHistories(details);
		history = manager.getHistories();
		assertEquals("run number: " + runNumber, -1, history[0].getdAge());
		assertEquals("run number: " + runNumber, "", history[0].getdAgeString());

		runNumber = 9;
		detailsPanel.setRelationshipType(Util.FATHER);
		manager.toHistories(details);
		history = manager.getHistories();
		assertEquals("run number: " + runNumber, Util.FATHER,
				history[0].getRelationshipType());

		//
		// add many detailspanels, and test that we get to the last one.
		//

		runNumber = 10;

		// Detailspanel #2
		DetailsPanel detailsPanel2 = new DetailsPanel(parent, Util.MAT_AUNTIES,
				nhi, EREF_1);
		// 1 row for the header, and 1 row of data. 8 columns

		// 2 aunties, one row for the header
		Grid grid2 = new Grid(3, 8);
		detailsPanel2.setGrid(grid2);
		// the header, just to have a row 0.
		TextArea header2 = new TextArea();
		header2.setValue("header");
		grid2.setWidget(0, 0, header);
		// now the data

		// name
		String NAME_2A = "Fred";
		TextBox textBoxName2a = new TextBox();
		textBoxName2a.setValue(NAME_2A);
		grid2.setWidget(1, Util.COL_NAME, textBoxName2a);

		String NAME_2B = "FRIEDA";
		TextBox textBoxName2b = new TextBox();
		textBoxName2b.setValue(NAME_2B);
		grid2.setWidget(2, Util.COL_NAME, textBoxName2b);

		// colon cancer
		SimpleCheckBox cc2a = new SimpleCheckBox();
		cc2a.setValue(true);
		grid2.setWidget(1, Util.COL_CC_CHECKBOX, cc2a);

		SimpleCheckBox cc2b = new SimpleCheckBox();
		cc2b.setValue(false);
		grid2.setWidget(2, Util.COL_CC_CHECKBOX, cc2b);

		// colon cancer age
		TextBox ccAge2a = new TextBox();
		ccAge2a.setValue("51");
		grid2.setWidget(1, Util.COL_CC_AGE, ccAge2a);

		TextBox ccAge2b = new TextBox();
		ccAge2b.setValue("-1");
		grid2.setWidget(2, Util.COL_CC_AGE, ccAge2b);

		// multiple polyps
		SimpleCheckBox mult2a = new SimpleCheckBox();
		mult2a.setValue(false);
		grid2.setWidget(1, Util.COL_MULT_POL, mult2a);

		SimpleCheckBox mult2b = new SimpleCheckBox();
		mult2b.setValue(true);
		grid2.setWidget(2, Util.COL_MULT_POL, mult2b);

		// other cancer
		SimpleCheckBox ot2a = new SimpleCheckBox();
		ot2a.setValue(true);
		grid2.setWidget(1, Util.COL_OT_CHECKBOX, ot2a);

		SimpleCheckBox ot2b = new SimpleCheckBox();
		ot2b.setValue(false);
		grid2.setWidget(2, Util.COL_OT_CHECKBOX, ot2b);

		// other cancer age
		TextBox otAge2a = new TextBox();
		otAge2a.setValue("80");
		grid2.setWidget(1, Util.COL_OT_AGE, otAge2a);
		
		TextBox otAge2b = new TextBox();
		otAge2b.setValue("85");
		grid2.setWidget(2, Util.COL_OT_AGE, otAge2b);

		 
	 
		
		 
		DetailsPanel[] details2 = { detailsPanel, // 0
				detailsPanel, // 1
				detailsPanel, // 2
				detailsPanel, // 3
				detailsPanel, // 4
				detailsPanel, // 5
				detailsPanel, // 6
				detailsPanel, // 7
				detailsPanel2 };// 8

		// convert to CancerHistory[]
		manager.toHistories(details2);

		// retrieve the CancerHistory[]
		CancerHistory[] history2 = manager.getHistories();

		// assertions
		assertEquals("run number: " + runNumber, NAME_2A, history2[8].getName());
		assertEquals("run number: " + runNumber, NAME_2B, history2[9].getName());
		assertEquals("run number: " + runNumber, true, history2[8].isColonCancer());
		assertEquals("run number: " + runNumber, false, history2[9].isColonCancer());
		assertEquals("run number: " + runNumber, 51, history2[8].getCcAge());
		assertEquals("run number: " + runNumber, -1, history2[9].getCcAge());
		assertEquals("run number: " + runNumber, false, history2[8].isMultPols());
		assertEquals("run number: " + runNumber, true, history2[9].isMultPols());
		assertEquals("run number: " + runNumber, true, history2[8].isOtherCancer());
		assertEquals("run number: " + runNumber, false, history2[9].isOtherCancer());
		assertEquals("run number: " + runNumber, 80, history2[8].getoAge());
		assertEquals("run number: " + runNumber, 85, history2[9].getoAge());
		assertEquals("run number: " + runNumber, false, history2[8].isDeceased());
		assertEquals("run number: " + runNumber, true, history2[9].isDeceased());
		assertEquals("run number: " + runNumber, 99, history2[8].getdAge());
		assertEquals("run number: " + runNumber, 100, history2[9].getdAge());
	 	
		
		
		
	}

	@Override
	public String getModuleName() {

		return "ac.otago.infoscience.eCRCpublic.ECRC";
	}

}
