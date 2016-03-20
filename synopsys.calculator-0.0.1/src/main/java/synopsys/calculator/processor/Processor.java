package synopsys.calculator.processor;

import synopsys.calculator.exception.ProcessException;

/**
 * This interface will be implemented by every specific processor.
 * Every action for processor will be defined in the Interface.
 * *
 */
public interface Processor<T> {
	
	public T calculate() throws ProcessException;
	
}
