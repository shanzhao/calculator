package synopsys.calculator.parser;


import synopsys.calculator.entity.Expression;
import synopsys.calculator.exception.ParseException;

/**
 * Define the Parser action
 * This is the interface for every Parser. 
 * In this interface, we will define the any actions that the parser have to implement. 
 *
 */
public interface ExpressionParser {

	/**
	 * Parse the expression to generate the Expression object
	 * @param expression: the expression string
	 * @return The Expression Object
	 * @throws ParseException: If we get any exception in parse processing, throw new ParseException 
	 */
	public Expression<?> Parse(String expression) throws ParseException;
}
