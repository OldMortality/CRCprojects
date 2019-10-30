package ac.otago.infoscience.eCRC.client;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.google.gwt.junit.client.GWTTestCase;

import test.ac.otago.infoscience.eCRC.RiskCalculatorTest;
import test.ac.otago.infoscience.eCRC.RiskCalculatorTest31;
import test.ac.otago.infoscience.eCRC.RiskCalculatorTest33;
import test.ac.otago.infoscience.eCRC.RiskCalculatorTest34;
import test.ac.otago.infoscience.eCRC.RiskCalculatorTest35;
import test.ac.otago.infoscience.eCRC.RiskCalculatorTestCases;

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
