package synopsys.calculator.processor;

import java.math.BigDecimal;

import junit.framework.TestCase;
import synopsys.calculator.entity.Expression;
import synopsys.calculator.exception.ParseException;
import synopsys.calculator.exception.ProcessException;
import synopsys.calculator.parser.DefaultExpressionParser;

public class ProcessorTest extends TestCase {
	 
	public ProcessorTest(String name) {
		super(name);
	}

	public void testParse1() {
		DefaultExpressionParser parser = new DefaultExpressionParser();
		try {
			Expression<BigDecimal> ex = (Expression<BigDecimal>)parser.Parse("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))");			
			assertTrue (ex.calculate().compareTo(new BigDecimal(String.valueOf(40))) == 0 );
		} catch (ParseException e) {
			fail(e.getMessage());
		}catch (ProcessException e) {
			fail(e.getMessage());
		}
		
	}
	
	public void testParse2() {
		DefaultExpressionParser parser = new DefaultExpressionParser();
		try {
			Expression<BigDecimal> ex = (Expression<BigDecimal>)parser.Parse("let(a, 5, let(b, mult(a, 10), add(b, a)))");			
			assertTrue (ex.calculate().compareTo(new BigDecimal(String.valueOf(55))) == 0 );
		} catch (ParseException e) {
			fail(e.getMessage());
		}catch (ProcessException e) {
			fail(e.getMessage());
		}
		
	}
	
	public void testParse3() {
		DefaultExpressionParser parser = new DefaultExpressionParser();
		try {
			Expression<BigDecimal> ex = (Expression<BigDecimal>)parser.Parse("let(a, 5, add(a, a))");			
			assertTrue (ex.calculate().compareTo(new BigDecimal(String.valueOf(10))) == 0 );
		} catch (ParseException e) {
			fail(e.getMessage());
		}catch (ProcessException e) {
			fail(e.getMessage());
		}
		
	}
	
	public void testParse4() {
		DefaultExpressionParser parser = new DefaultExpressionParser();
		try {
			Expression<BigDecimal> ex = (Expression<BigDecimal>)parser.Parse("mult(add(2, 2), div(9, 3))");			
			assertTrue (ex.calculate().compareTo(new BigDecimal(String.valueOf(12))) == 0 );
		} catch (ParseException e) {
			fail(e.getMessage());
		}catch (ProcessException e) {
			fail(e.getMessage());
		}
		
	}
	
	public void testParse5() {
		DefaultExpressionParser parser = new DefaultExpressionParser();
		try {
			Expression<BigDecimal> ex = (Expression<BigDecimal>)parser.Parse("add(1, mult(2, 3))");			
			assertTrue (ex.calculate().compareTo(new BigDecimal(String.valueOf(7))) == 0 );
		} catch (ParseException e) {
			fail(e.getMessage());
		}catch (ProcessException e) {
			fail(e.getMessage());
		}
		
	}
	
	public void testParse6() {
		DefaultExpressionParser parser = new DefaultExpressionParser();
		try {
			Expression<BigDecimal> ex = (Expression<BigDecimal>)parser.Parse("add(1, 2)");			
			assertTrue (ex.calculate().compareTo(new BigDecimal(String.valueOf(3))) == 0 );
		} catch (ParseException e) {
			fail(e.getMessage());
		}catch (ProcessException e) {
			fail(e.getMessage());
		}
		
	}
	
	public void testParse7() {
		DefaultExpressionParser parser = new DefaultExpressionParser();
		try {
			Expression<BigDecimal> ex = (Expression<BigDecimal>)parser.Parse("div(2, 0)");		
			ex.calculate();
			fail( "My method didn't throw when I expected it to" );
		} catch (ParseException e) {
			fail(e.getMessage());
		}catch (ProcessException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
