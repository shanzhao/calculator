package synopsys.calculator.entity;

import java.math.BigDecimal;

import synopsys.calculator.common.LoggerImpl;
import synopsys.calculator.exception.ProcessException;

public class Divide extends ArithmeticFunction<BigDecimal>{

	public Divide(String name){
		super(name);
	}
	
	@Override
	public BigDecimal calculate() throws ProcessException {
		try{
			beforeCalculate(this.getClass());
			if(value2.compareTo(BigDecimal.ZERO) == 0){
				throw new ProcessException("Cannot divide zero in expression " + this.toString());
			}
			BigDecimal result =  value1.divide(value2);
			LoggerImpl.Debug(this.toString() + " = " + result, null);
			return result;
		}catch(Exception e){
			throw new ProcessException("Cannot run the divide function for " + this.toString() + ", because " + e.getMessage(), e);
		}
	}
}
