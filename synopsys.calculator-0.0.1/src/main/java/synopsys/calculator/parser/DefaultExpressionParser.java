package synopsys.calculator.parser;

import java.io.StringReader;
import java.util.Stack;

import synopsys.calculator.entity.Expression;
import synopsys.calculator.entity.Expression.ExpressionType;
import synopsys.calculator.entity.KeyWordLiberary;
import synopsys.calculator.exception.ParseException;

/**
 * Implement the Expression parse functionality to parse the expression to object Expression
 *
 *
 */
public class DefaultExpressionParser implements ExpressionParser{

	private static Stack<Expression<?>> expressionStack;
	
	static {
		expressionStack = new Stack<Expression<?>>();
	}
	
	@Override
	public Expression<?> Parse(String expression) throws ParseException {
		StringReader stringReader = null;
		StringBuilder stringBuilder = new StringBuilder();
		String keyword = null;
		String previousSeperator = null;
		char[] character = new char[1];
		long position = 0;
		if( expression == null){
			throw new ParseException("the expression cannot be NULL");
		}
		try{
			stringReader = new StringReader(expression);
			while(stringReader.read(character, 0, 1) > -1){
				position ++ ;
				switch(String.valueOf(character)){
					case ",":
						if(previousSeperator == null){
							throw new ParseException("the expression format is wrong in position " + position + ".");
						}
						if(previousSeperator.equals(")" )){
							previousSeperator = ",";
							break;
						}
						keyword = stringBuilder.toString();
						stringBuilder.setLength(0);
						if(expressionStack.empty()){
							throw new ParseException("the expression format is wrong in position " + position + ".");
						}
						expressionStack.peek().addArgument(KeyWordLiberary.getExpression(keyword));
						previousSeperator = ",";
						break;
					case "(":
						keyword = stringBuilder.toString();
						stringBuilder.setLength(0);
						if(!keyword.equals("")){
							expressionStack.push(KeyWordLiberary.getExpression(keyword));
						}
						break;
					case ")":
						if(previousSeperator == null){
							throw new ParseException("the expression format is wrong in position " + position + ".");
						}
						keyword = stringBuilder.toString();
						stringBuilder.setLength(0);
						if(expressionStack.empty()){
							throw new ParseException("the expression format is wrong in position + " + position + ".");
						}
						Expression<?> parentExpression = expressionStack.pop();

						if(!previousSeperator.equals(")")){
							parentExpression.addArgument(KeyWordLiberary.getExpression(keyword));
						}
						
						if(!expressionStack.empty()){
							expressionStack.peek().addArgument(parentExpression);
						}else{
							if(stringReader.read() > -1){
								throw new ParseException("the expression format is wrong in position " + position + ".");
							}
							if(parentExpression.getExpressionType().equals(Expression.ExpressionType.Valiable)){
								throw new ParseException("the expression cannot be just a varible.");
							}else{
								return parentExpression;
							}
						}
						previousSeperator = ")";
						break;
					case " ":
						break;
					default:
						stringBuilder.append(character);
						previousSeperator = "";
						break;
				}
				
			}
			if(expressionStack.size() > 1){
				throw new ParseException("the expression format is wrong in position + " + position + ".");
			}else if (expressionStack.size()  == 1){
				if(expressionStack.peek().getExpressionType().equals(Expression.ExpressionType.Valiable)){
					throw new ParseException("the expression cannot be just a varible.");
				}
				return expressionStack.pop();
			}else{
				if(stringBuilder.length() > 0){
					keyword = stringBuilder.toString();
					stringBuilder.setLength(0);
					Expression<?> result = KeyWordLiberary.getExpression(keyword);
					if(result.getExpressionType().equals(ExpressionType.Number)){
						return result;
					}
					throw new ParseException("the expression cannot be just " + result.getExpressionType().getExpressionType());
				}
			}
			return null;
		}catch(Exception e){
			throw new ParseException("Cannot parse the expression: " + expression + ", because " + e.getMessage() + " in position " + position, e);
		}finally{
			if(stringReader != null){
				stringReader.close();
			}
			stringBuilder.setLength(0);
			expressionStack.removeAllElements();
		}
	}

}
