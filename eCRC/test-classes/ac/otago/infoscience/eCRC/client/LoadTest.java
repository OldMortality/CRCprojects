package ac.otago.infoscience.eCRC.client;

 

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ac.otago.infoscience.eCRC.client.CancerHistoryManager;
import ac.otago.infoscience.eCRC.client.RiskCalculator;
import ac.otago.infoscience.eCRC.client.UpdateService;
import ac.otago.infoscience.eCRC.client.UpdateServiceAsync;
import ac.otago.infoscience.eCRC.client.Util;
import ac.otago.infoscience.eCRC.shared.CancerHistory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LoadTest  extends GWTTestCase   {

	Object dummy = null;
	static RiskCalculator riskCalculator = new RiskCalculator();
	static CancerHistoryManager manager = new CancerHistoryManager();

	@BeforeClass
	public static void init() {
		riskCalculator.setCancerHistoryManager(manager);
	}

	 
	@Test
	public void testcase2() {
		init();
		
		String erefid = "load33";
		
		CancerHistory h1 = new CancerHistory("NHI123", erefid,
				(short) Util.MAT_GRANDFATHER, "", true, (short) 54, false,
				false, (short) -1, false, (short) -1, "");
		CancerHistory h2 = new CancerHistory("NHI123", erefid,
				(short) Util.MAT_GRANDMOTHER, "", true, (short) 54, false,
				false, (short) -1, false, (short) -1, "");

		CancerHistory[] td1 = { h1, h2 };
		manager.setHistories(td1);

		assertEquals("case 2 ",Util.CATEGORY_0, riskCalculator.calculateRisk());
		
		UpdateServiceAsync updateService = (UpdateServiceAsync) GWT
				.create(UpdateService.class);
		// ServiceDefTarget endpoint = (ServiceDefTarget) updateService;

		AsyncCallback<String> callback = new AsyncCallback<String>() {

			/**
			 * 
			 * This is where you go if the server fails 
			 *
			 * Note that this goes asynchronously, so you don't know quite when this
			 * will happen.
			 *
			 *
			 */
			public void onFailure(Throwable caught) {
				// do something with errors
				caught.printStackTrace();
				System.out.println("Unexpected failure at the server");
				
			}

			/**
			 * 
			 * This is where you go if the server has completed the updates. It
			 * may have caught an exception, and we can test this in the result.
			 * 
			 * Note that this goes asynchronously, so you don't know quite when this
			 * will happen.
			 * 
			 * @param result "ok" or "error".
			 *         
			 */
			public void onSuccess(String result) {
		 		
				if (null != result && result.equals("ok")) {
					// do nothing, all is well
				} else {
					
					// not good. An unexpected server side error.
					
				 
				}
				}
		};

		/*
		 * Make the call to the server. 
		 * 
		 */
		 

		
		updateService.updateServer(riskCalculator.getCancerHistoryManager()
				.getHistories(), (short) Util.CATEGORY_0, erefid, callback);
		 // Set a delay period significantly longer than the
		  // event is expected to take.
		  delayTestFinish(500);

		  // Schedule the event and return control to the test system.
		  //timer.schedule(100);
	}


	@Override
	public String getModuleName() {
		return "ac.otago.infoscience.eCRC.ECRC";
	}
	 

	}

	
	

