package synopsys.calculator;

import java.math.BigDecimal;

import org.apache.log4j.Level;

import synopsys.calculator.common.LoggerImpl;
import synopsys.calculator.common.Version;
import synopsys.calculator.entity.Expression;
import synopsys.calculator.exception.ParseException;
import synopsys.calculator.exception.ProcessException;
import synopsys.calculator.parser.DefaultExpressionParser;

/**
 * Calculator main Class
 *
 */
public class Calculator 
{
	/**
	 * 
	 * @param args is the array to set up the input express and level of Log
	 */
    public static void main( String[] args )
    {
    	if(args.length == 0){
    		printInfo();
    		return;
    	}
    	try{
			for(String arg : args){
				switch(arg){
				case "-version":
					System.out.println(Version.getVersion());
					return;
				case "-level:DEBUG":
					LoggerImpl.setLevel(Level.DEBUG);
					break;
				case "-level:ERROR":
					LoggerImpl.setLevel(Level.ERROR);
					break;	
				case "-level:INFO":
					LoggerImpl.setLevel(Level.INFO);
					break;	
				default:
					if(arg.startsWith("-")){
						printInfo();
						return;
					}
					LoggerImpl.Info("Start to parse " + arg, null);
					DefaultExpressionParser parser = new DefaultExpressionParser();
					try {
						Expression<?> ex = parser.Parse(arg);
						LoggerImpl.Info("finish parse. the Expression is " + ex.toString(), null);
						LoggerImpl.Info("start  to calculate. the Expression is " + ex.toString(), null);
						Object result = ex.calculate();
						System.out.println(ex.toString() + " = " + result);
						LoggerImpl.Info("finish the calculation. the Expression " + ex.toString() + " = " + result.toString(), null);
					} catch (ParseException e) {
						LoggerImpl.Error(e.getMessage(), e);
					}catch (ProcessException e) {
						LoggerImpl.Error(e.getMessage(), e);
					}
					return;
				}
			}
    	}catch(Exception e){
    		LoggerImpl.Error(e.getMessage(), e);
    		printInfo();
    	}

    }
    
    private static void printInfo(){
    	System.out.println("-Usage: Calculator [-options] \"Expression\"");
		System.out.println ("       <to calculate the expression)");
		System.out.println("where options include:");
		System.out.println("     -version       print product version and exist");
		System.out.println("     -level:<level> set the log level. level must be DEBUG|ERROR|INFO" );
    }  
}
