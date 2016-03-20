package synopsys.calculator.entity;

import junit.framework.TestCase;

import synopsys.calculator.exception.ParseException;

public class KeyWordLiberaryTest extends TestCase {

	public void testGetExpression() {
		try {
			assertNotNull(KeyWordLiberary.getExpression("let"));
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}

}
