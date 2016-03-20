package synopsys.calculator.entity;

import java.util.ArrayList;
import java.util.List;

import synopsys.calculator.exception.ParseException;
import synopsys.calculator.exception.ProcessException;
import synopsys.calculator.processor.Processor;

/**
 * This is abstract class for every element in the expression.
 *
 *
 */
public abstract class Expression<T> implements Cloneable, Processor<T>{

	public enum ExpressionType{
		ArithmeticFunction("ArithmeticFunction"),
		Number("Number"),
		Operator("Operator"),
		Valiable("Valiable");
		
		private String expressionType;
		
		ExpressionType(String type){
			setExpressionType(type);
		}

		public String getExpressionType() {
			return expressionType;
		}

		public void setExpressionType(String expressionType) {
			this.expressionType = expressionType;
		}
	}
	
	private StringBuilder stringBuilder = null;
	private ExpressionType expressionType = null;
	private String expressionName = null;
	private List<Expression<?>> arguments = null;
	
	
	public Expression(String name){
		this.expressionName = name;
	}
	/**
	 * Subclasses should implemented based on the hash values of the business
	 * keys.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		return toString().hashCode();
	}
	
	/**
	 * This method is declared final and does a lot of performance optimization:
	 * <p>
	 * 
	 * If the object
	 * for equality comparison is
	 * <p>
	 * 
	 * <ol>
	 * <li>not the same memory location as the current object (fast sanity
	 * check)</li>
	 * <li>of type Entity or one of its subclasses</li>
	 * <li>Does not have the string </li>
	 * </ol>
	 * 
	 * 
	 * This little technique is a massive performance improvement given the
	 * number of times equals checks happen in most applications.
	 */
	
	@Override
	public final boolean equals(Object o){
		if(this == o){
			return true;
		}
		if(o instanceof Expression){
			return this.toString().equals(o.toString());
		}
		return false;
	}
	

	/**
	 * get expression type
	 * @return The object of ExpressionType
	 */
	public ExpressionType getExpressionType() {
		return expressionType;
	}

	/**
	 * set ExceptionType to property expressionType
	 * @param expressionType the object of ExpressionType
	 */
	public void setExpressionType(ExpressionType expressionType) {
		this.expressionType = expressionType;
	}

	/**
	 * get expression name
	 * @return expression name
	 */
	public String getExpressionName() {
		return expressionName;
	}

	/**
	 * Validate the expresionName
	 * Then Set expression name to property expressionName
	 * @param expressionName
	 * @throws ParseException: if 
	 */
	public void setExpressionName(String expressionName) throws ParseException{		
		this.expressionName = expressionName;
	}
	
	/**
	 * Override the toString function to serialize the Expression object to string
	 */
	@Override
	public String toString(){
		if(stringBuilder == null){
			stringBuilder = new StringBuilder();
		}
		stringBuilder.append(expressionName).toString();
		switch(this.getExpressionType()){
			case Number:
				break;
			case Valiable:
				break;
			case ArithmeticFunction:
				stringBuilder.append("(");
				ArgumentToString();
				stringBuilder.append(")");
				break;
			case Operator:
				stringBuilder.append("(");
				ArgumentToString();
				stringBuilder.append(")");
				break;
			default:
				break;
		}
		String result =  stringBuilder.toString();
		stringBuilder.setLength(0);
		return result;
	}
	
	private void ArgumentToString(){
		if(arguments != null){
			for(Expression<?> argu: arguments){
				stringBuilder.append(argu.toString());
				stringBuilder.append(",");
			}
			stringBuilder.setLength(stringBuilder.length() - 1);
		}
	}

	/**
	 * get the argument as a list<Expression<T>>
	 * @return
	 */
	public List<Expression<?>> getArguments() {
		return arguments;
	}
	
	/**
	 * 
	 * @param expression
	 * @throws ParseException
	 */
	public void addArgument(Expression<?> expression) throws ParseException{
		if(arguments == null){
			arguments = new ArrayList<Expression<?>>();
		}
		if(validateArgument(expression)){
			arguments.add(expression);
		}else{
			throw new ParseException("Cannot add argument " + expression.toString());
		}
	}
	
	/**
	 * get the number of arguments that associated with this expression
	 * @return 0: if the arguments = null,  arguments.size: arguments is not null
	 */
	public int getArgumentsNumber(){
		if(arguments == null){
			return 0;
		}
		return arguments.size();
	}
	
	
	public abstract boolean validateArgument(Expression<?> expression);
	
	@Override
	public abstract T calculate() throws ProcessException ;
}

