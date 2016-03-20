package synopsys.calculator.parser;

import junit.framework.TestCase;
import synopsys.calculator.entity.Expression;
import synopsys.calculator.exception.ParseException;

public class DefaultExpressionParserTest extends TestCase {

	public DefaultExpressionParserTest(String name) {
		super(name);
	}

	public void testParse() {
		DefaultExpressionParser parser = new DefaultExpressionParser();
		try {
			Expression<?> ex = parser.Parse("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))");
			assertNotNull(ex);
		} catch (ParseException e) {
			fail(e.getMessage());
		}
		
	}

}
