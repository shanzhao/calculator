package synopsys.calculator.entity;

import synopsys.calculator.exception.ProcessException;

/**
 * This is abstract class for every operation, for instance "let" Operator
 *
 */
public abstract class Operator<T> extends Expression<T>{

	public Operator(String name){
		super(name);
	}
	
	@Override
	public abstract T calculate() throws ProcessException;
	
	@Override
	public ExpressionType getExpressionType() {
		return ExpressionType.Operator;
	}
	
	
	@Override
	public boolean validateArgument(Expression<?> expression) {
		return true;
	}

}
