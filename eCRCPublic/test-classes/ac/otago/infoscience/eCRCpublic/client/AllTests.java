package ac.otago.infoscience.eCRCpublic.client;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.google.gwt.junit.client.GWTTestCase;

import test.ac.otago.infoscience.eCRCpublic.RiskCalculatorTest;
import test.ac.otago.infoscience.eCRCpublic.RiskCalculatorTest31;
import test.ac.otago.infoscience.eCRCpublic.RiskCalculatorTest33;
import test.ac.otago.infoscience.eCRCpublic.RiskCalculatorTest34;
import test.ac.otago.infoscience.eCRCpublic.RiskCalculatorTest35;
import test.ac.otago.infoscience.eCRCpublic.RiskCalculatorTestCases;

@RunWith(Suite.class)
@SuiteClasses({ CancerHistoryManagerTest.class, UtilTest.class,
	RiskCalculatorTest.class, RiskCalculatorTest31.class, RiskCalculatorTest33.class, RiskCalculatorTest34.class
	, RiskCalculatorTest35.class,RiskCalculatorTestCases.class, 
	CancerHistoryManagerTest2.class,  CancerHistoryManagerTest3.class })
public class AllTests extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "ac.otago.infoscience.eCRC.ECRC";
	}

	
	
}
