package synopsys.calculator.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import synopsys.calculator.exception.ParseException;

/**
 * Define the every Key word for expression
 *
 *
 */
public final class KeyWordLiberary {

	
	private static List<String> dictionary = null;
	
	/**
	 * Initial dictionary 
	 */
	static{
		dictionary = new ArrayList<String>();
		dictionary.add("^add$");
		dictionary.add("^sub$");
		dictionary.add("^mult$");
		dictionary.add("^div$");
		dictionary.add("^let$");
		dictionary.add("^[-]{0,1}[0-9]{1,}$");
		dictionary.add("^[a-zA-Z]{1}$");
	}
	
	
	/**
	 * check the regex to get the related the expression
	 * @param keyWord : the part content of the expression 
	 * @return the related expression
	 * @throws ParseException : If the key word cannot pass the checking, throw the exception
	 */
	public static Expression<?> getExpression(String keyWord) throws ParseException
	{
		for(String localkey : dictionary){
			if (keyWord.matches(localkey)){
				switch(localkey){
					case "^[-]{0,1}[0-9]{1,}$":
						try{
							Integer.valueOf(keyWord);
							return new Number(keyWord);
						}catch(Exception e){
							throw new ParseException("Cannot conver " + keyWord + " into Integer. The Number should be betwwen " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE);
						}
					case "^[a-zA-Z]{1}$":
						return new Variable<BigDecimal>(keyWord);
					case "^add$":
						return new Add(keyWord);
					case "^sub$":
						return new Subtract(keyWord);
					case "^mult$":
						return  new Multiply(keyWord);
					case "^div$":
						return new Divide(keyWord);
					case "^let$":
						return new Let(keyWord);
					default:
						throw new ParseException("the expression has this part " + keyWord);
						
				}
			}
		}
		throw new ParseException("the expression has this non keyword part " + keyWord);
	}
}
