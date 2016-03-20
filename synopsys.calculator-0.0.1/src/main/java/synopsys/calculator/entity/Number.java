package synopsys.calculator.entity;


import synopsys.calculator.common.LoggerImpl;

/**
 * 
 * The constant of expression 
 *
 */

import synopsys.calculator.exception.ProcessException;

/**
 * Present a number that will be Integer
 *
 *
 */
public final class Number extends Expression<Integer>{

	public Number(String name){
		super(name);
	}
	
	@Override
	public Integer calculate() throws ProcessException {
		if(this.getExpressionName() == null){
			throw new ProcessException("Cannot set null to a variable.");
		}
		try{
			Integer result =  Integer.valueOf(this.getExpressionName());
			LoggerImpl.Debug(this.toString() + " = " + result, null);
			return result;
		}catch(Exception e){
			throw new ProcessException("Cannot convert " + this.getExpressionName() + " to a number.");
		}

	}
	
	@Override
	public ExpressionType getExpressionType() {
		return ExpressionType.Number;
	}

	@Override
	public boolean validateArgument(Expression<?> expression) {
		return false;
	}
}
