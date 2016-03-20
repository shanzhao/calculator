package synopsys.calculator.exception;

/**
 * Set up an exception to handle all the exception for real calculate processing
 *
 *
 */
public class ProcessException extends Exception{

	/**
	 * Exception message
	 */
	public ProcessException(){
		super();
	}
	
	/**
	 * Exception message
	 * @param message
	 */
	public ProcessException(String message){
		super(message);
	}
	
	/**
	 * throw a new ParserException with the original exception to preserve the stack
	 * @param exception
	 */
	public ProcessException(Throwable cause) {
		super(cause);
	}
	
	public ProcessException(String message, Throwable cause) {
		super(message, cause);
	}

}
