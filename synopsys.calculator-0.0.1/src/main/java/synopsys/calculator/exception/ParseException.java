package synopsys.calculator.exception;

/**
 * Set up an exception to handle all the exception for parse processing
 *
 *
 */
public class ParseException extends Exception{

	/**
	 * Exception message
	 */
	public ParseException(){
		super();
	}
	
	/**
	 * Exception message
	 * @param message
	 */
	public ParseException(String message){
		super(message);
	}
	
	/**
	 * throw a new ParserException with the original exception to preserve the stack
	 * @param exception
	 */
	public ParseException(Throwable cause) {
		super(cause);
	}
	
	public ParseException(String message, Throwable cause) {
		super(message, cause);
	}
}
