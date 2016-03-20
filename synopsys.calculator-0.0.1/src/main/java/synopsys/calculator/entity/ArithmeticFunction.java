package synopsys.calculator.entity;


import java.math.BigDecimal;

import synopsys.calculator.common.LoggerImpl;
import synopsys.calculator.exception.ProcessException;

/**
 * This a abstract class for every ArithmeticFunction Object
 *
 *
 */
public abstract class ArithmeticFunction<T> extends Expression<T> {
	
	protected BigDecimal value1 = null;
	protected BigDecimal value2 = null;
	
	public ArithmeticFunction(String name){
		super(name);
	}
	
	@Override
	public abstract T calculate() throws ProcessException;

	@Override
	public ExpressionType getExpressionType() {
		return ExpressionType.ArithmeticFunction;
	}
	
	@Override
	public boolean validateArgument(Expression<?> expression) {
		if(this.getArgumentsNumber() >= 2 ){
			return false;
		}
		return true;
	}
	
	/**
	 * Before the calculation, do some verification and argument setting
	 * @param clazz
	 * @throws ProcessException
	 */
	protected void beforeCalculate(Class<?> clazz) throws ProcessException{
		try{
			if (this.getArgumentsNumber() != 2 || this.getArguments().get(0) == null || this.getArguments().get(1) == null){
				throw new ProcessException("Cannot " + clazz.getSimpleName() + " more than two number together.");
			}
			value1 = new BigDecimal(this.getArguments().get(0).calculate().toString());
			LoggerImpl.Debug(this.getArguments().get(0).toString() + " = " + value1, null);
			value2 = new BigDecimal(this.getArguments().get(1).calculate().toString());
			LoggerImpl.Debug(this.getArguments().get(1).toString() + " = " + value2, null);
		}catch(Exception e){
			throw new ProcessException("Cannot run the " + clazz.getSimpleName() + " function for " + this.toString() + ", because " + e.getMessage(), e);
		}
		
	}
}
