package synopsys.calculator.entity;

import java.math.BigDecimal;

import synopsys.calculator.common.LoggerImpl;
import synopsys.calculator.exception.ProcessException;

/**
 * Do multiply function
 *
 *
 */
public class Multiply extends ArithmeticFunction<BigDecimal>{

	public Multiply(String name){
		super(name);
	}
	
	@Override
	public BigDecimal calculate() throws ProcessException {
		try{
			beforeCalculate(this.getClass());
			BigDecimal result =  value1.multiply(value2);
			LoggerImpl.Debug(this.toString() + " = " + result, null);
			return result;
		}catch(Exception e){
			throw new ProcessException("Cannot run the multiply function for " + this.toString() + ", because " + e.getMessage(), e);
		}
	}
}
