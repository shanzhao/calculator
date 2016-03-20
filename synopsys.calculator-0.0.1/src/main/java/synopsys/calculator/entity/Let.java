package synopsys.calculator.entity;

import java.math.BigDecimal;

import synopsys.calculator.common.LoggerImpl;
import synopsys.calculator.exception.ProcessException;

public class Let extends Operator<BigDecimal>{

	public Let(String name){
		super(name);
	}
	
	@Override
	public BigDecimal calculate() throws ProcessException {
		try{
			if (this.getArgumentsNumber() != 3){
				throw new ProcessException("Cannot call let operator with more than three argument.");
			}
			if(this.getArguments().get(0) != null && !this.getArguments().get(0).getExpressionType().equals(ExpressionType.Valiable)){
				throw new ProcessException("The first argumet " + this.getArguments().get(0).toString() + " is not variable.");
			}
			BigDecimal value2 = new BigDecimal(this.getArguments().get(1).calculate().toString());
			LoggerImpl.Debug(this.getArguments().get(1) + " = " + value2, null);
			setValueForVariable(this.getArguments().get(2), value2,  this.getArguments().get(0).getExpressionName());
			BigDecimal result =  new BigDecimal(this.getArguments().get(2).calculate().toString());
			LoggerImpl.Debug(this.toString() + " = " + result, null);
			return result;
		}catch(Exception e){
			throw new ProcessException("Cannot run the let function for " + this.toString() + ", because " + e.getMessage(), e);
		}
	}
	
	/**
	 * set same value for variable with same name, and these variable are the part of expression 
	 * where variable is used in current let operator
	 * @param v
	 * @param value
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setValueForVariable(Expression<?> operator,  BigDecimal value,  String variableName) throws ProcessException{
		try{
			
			if(operator == null ){
				return;
			}
			Expression<?> where = operator;
			if(where.getExpressionType().equals(ExpressionType.Valiable) && where.getExpressionName().equals(variableName)){
				((Variable)where).setValue(value);
			}else{
				if(where.getArgumentsNumber() > 0){
					for(Expression<?> child: where.getArguments()){
						setValueForVariable(child, value, variableName);
					}
				}
			}
		}catch(Exception e){
			throw new ProcessException("Cannot set the value " +  value + " for variable " + this.getExpressionName());
		}
		
	}
	
	
	@Override
	public boolean validateArgument(Expression<?> expression) {
		if(this.getArgumentsNumber() >=3 ){
			return false;
		}
		return super.validateArgument(expression);
	}

}
