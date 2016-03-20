package synopsys.calculator.entity;

import java.math.BigDecimal;

import synopsys.calculator.common.LoggerImpl;
import synopsys.calculator.exception.ProcessException;

public class Add extends ArithmeticFunction<BigDecimal>{
	
	public Add(String name){
		super(name);
	}
	
	@Override
	public BigDecimal calculate() throws ProcessException {
		try{
			beforeCalculate(this.getClass());
			BigDecimal result =  value1.add(value2);
			LoggerImpl.Debug(this.toString() + " = " + result, null);
			return result;
		}catch(Exception e){
			throw new ProcessException("Cannot run the add function for " + this.toString() + ", because " + e.getMessage(), e);
		}
	}
}
