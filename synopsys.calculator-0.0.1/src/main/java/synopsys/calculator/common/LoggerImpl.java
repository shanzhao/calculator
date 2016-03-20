package synopsys.calculator.common;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *logger implements layer.
 *In this file, the apach.logger has been used to do the log
 *We can set up the appender to do the database log. 
 *
 *
 */
public class LoggerImpl {

	private static Logger logger =  Logger.getRootLogger();
	
	public static void setLevel(Level level){
		logger.setLevel(level);
	}

	public static void Error(String message, Exception e){
		logger.error(message, e);
	}
	
	public static void Info(String message, Exception e){
		logger.info(message, e);
	}
	
	public static void Debug(String message, Exception e){
		logger.debug(message, e);
	}
}
