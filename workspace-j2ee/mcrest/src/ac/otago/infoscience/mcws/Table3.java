package ac.otago.infoscience.mcws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class Table3 {

	
	private static Logger logger = Logger
			.getLogger("ac.otago.infoscience.mcws.Table3");
	
	
	
	static float[] women = new float[12];
	static float[] men	 = new float[10];
	
	
	// static code
	static {
		init_women();
		init_men();
	}
	
	public static void init() {
		init_women();
		init_men();
	}
	
	public static void init_women() {
		
		String path = "table3_women.csv";
		
		BufferedReader br = null;
		 
		try {
 
			 
			InputStream in = Table3.class.getClassLoader().getResourceAsStream(path);
			
			 
			InputStreamReader is = new InputStreamReader(in);
			  
			 
			br = new BufferedReader(is);
			 
			String read =br.readLine();
		    
			 
			logger.info("reading from " + path);
			
			
			while(read != null) { 
				
			    	// remove quotes
			    	read = read.replaceAll("\"", "");
				
			    	logger.info("from "+ path + ":  "  + read);
				    
				    String[] numbers = read.split(",");
				    
				    // skip column 0, the age groups, so women/north becomes column [0]
				    for (int i=0;i<numbers.length;i++) {
				    	
				    	//System.out.println("number " + numbers[i] + "---- " + new Float(numbers[i]));
				    	
				    	 women[i]= new Float(numbers[i]); 
							    	
				    }
				    
				    read =br.readLine();
				    
			
			}
			logger.info("finished reading from table3a");
		}	
		 catch (IOException e) {
			 logger.error("Error while reading from table2",e);
			
			 
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException ex) {
				logger.info("setting theInstance to NULL again");
				ex.printStackTrace();
			}
		}
 
		
		
	}
	
	
public static void init_men() {
		
		String path = "table3_men.csv";
		
		BufferedReader br = null;
		 
		try {
 
			 
			InputStream in = Table3.class.getClassLoader().getResourceAsStream(path);
			
			 
			InputStreamReader is = new InputStreamReader(in);
			  
			 
			br = new BufferedReader(is);
			 
			String read =br.readLine();
		    
			 
			logger.info("reading from " + path);
			
			
			while(read != null) { 
				
			    	// remove quotes
			    	read = read.replaceAll("\"", "");
				
			    	logger.info("from "+ path + ":  "  + read);
				    
				    String[] numbers = read.split(",");
				    
				    // skip column 0, the age groups, so women/north becomes column [0]
				    for (int i=0;i<numbers.length;i++) {
				    	
				    	//System.out.println("number " + numbers[i] + "---- " + new Float(numbers[i]));
				    	
				    	 men[i]= new Float(numbers[i]); 
							    	
				    }
				    
				    read =br.readLine();
				    
			
			}
			logger.info("finished reading from table3a");
		}	
		 catch (IOException e) {
			 logger.error("Error while reading from table2",e);
			
			 
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException ex) {
				logger.info("setting theInstance to NULL again");
				ex.printStackTrace();
			}
		}
 
		
		
	}
	

	public static float getRelativeRiskWomen(int[] variables) {
		
		float result = 1;
		if (variables.length != 4) {
			logger.error("expected 4 variables for women. instead, found: " + variables.length);
		}
		
		for (int i=0;i<variables.length;i++) {
			result = result * getRelativeRiskByFactor(Util.GENDER_FEMALE, variables[i]);
			logger.debug("risk factor " + i + " " +  getRelativeRiskByFactor(Util.GENDER_FEMALE, variables[i]));
			
		}
		logger.debug("----- relative risk " + result + " .profile was: " );
		for (int i=0;i<variables.length;i++) {
			logger.debug(variables[i]);
		}
		logger.debug("-----");
		return(result);
	}

	public static float getRelativeRiskMen(int[] variables) {
		
		float result = 1;
		if (variables.length != 5) {
			logger.error("expected 4 variables for men. instead, found: " + variables.length);
		}
		
		// first risk factor is a continous variable, not a factor
		result = (float) Math.pow( getRelativeRiskByFactor(Util.GENDER_MALE, Util.NUMBER_OF_MOLES ),variables[0] );
				
		logger.debug("risk factor 0: " + result);
		for (int i=1;i<variables.length;i++) {
			result = result * getRelativeRiskByFactor(Util.GENDER_MALE, variables[i]);
			logger.debug("risk factor " + i + " " +  getRelativeRiskByFactor(Util.GENDER_MALE, variables[i]));
				
		}
		return(result);
	}

	
	
	public static float getRelativeRiskByFactor(int gender,int variable) {
		
		if (gender==Util.GENDER_FEMALE) {
			
			return(women[variable]);
			
		} else {
			
			return(men[variable]);
		}
		
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();
		float risk = getRelativeRiskByFactor(Util.GENDER_MALE, Util.NUMBER_OF_MOLES);
		System.out.println("risk is: " + risk);
		

		System.out.println("men");
		int[] variables = {3,Util.NMSC_YES_M,Util.AGE_51_OR_MORE,Util.OCCUPATION_OUT,Util.BIRTH_NZ};
		float risk2 = getRelativeRiskMen(variables);
		System.out.println("risk is: " + risk2);
		
		System.out.println("women");
		int[] variables3 = { Util.SKINCOLOUR_OLIVE,Util.FAMHXMOLES_NO,Util.MOLES_RARM_2,Util.NMSC_YES_F};
		float risk3 = getRelativeRiskWomen(variables3);
		System.out.println("risk is: " + risk3);
		

	}

}
