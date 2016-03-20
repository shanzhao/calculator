package synopsys.calculator.entity;

import synopsys.calculator.common.LoggerImpl;
import synopsys.calculator.exception.ProcessException;

/**
 * This is a final class for Variable. 
 * For this case, this class will implement all function for variable with one character (a*z ,A*Z)
 * 
 */
public final class Variable<T> extends Expression<T>{

	public Variable(String name){
		super(name);
	}
	
	public T value = null;
	@Override
	public T calculate() throws ProcessException {
		if(value == null){
			throw new ProcessException("The variable " + this.getExpressionName() + " does not be set value");
		}
		LoggerImpl.Debug(this.toString() + " = " + value, null);
		return value ;
	}

	@Override
	public ExpressionType getExpressionType() {
		return ExpressionType.Valiable;
	}

	@Override
	public int hashCode() {
		return this.getExpressionName().hashCode();
	}
	
	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public boolean validateArgument(Expression<?> expression) {
		return false;
	}
}
