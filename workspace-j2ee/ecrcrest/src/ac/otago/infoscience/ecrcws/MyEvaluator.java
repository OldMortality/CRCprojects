package ac.otago.infoscience.ecrcws;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.TriggeringEventEvaluator;

/**
 * This class is necessary for the log4j smtp appender. It allows us
 * to set the filter (warning level at which you start getting emails)
 * to less than ERROR. This is useful for testing. In practice, you
 * probably don't want to get an email for INFO, OR WARNING.
 * 
 * 
 * @author Michel
 *
 */
public class MyEvaluator implements TriggeringEventEvaluator {

	 
	
	public boolean isTriggeringEvent(LoggingEvent event) {
		
		 
			
			if (event.getLevel() == Level.ERROR ||
					event.getLevel() == Level.FATAL
					) {
				return true;
			} else {
				return false;
			}
		}

}
