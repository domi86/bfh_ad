package ch.bfh.ad.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.bfh.ad.main.Evaluator;
import ch.bfh.ad.main.ParseException;
import ch.bfh.ad.main.PolynomParser;

public class PolynomParserTest {

	@Test
	public void positiveParseTest() {
		String pString = "";
		try {
			pString = "2x(2x^2 + 1) + x^3";
			pString = pString.replaceAll("\\s", "");
			PolynomParser.createParser().parse(pString);
			
			pString = "10x^3 + 4(2x + 3x^5) + 2x^2";
			pString = pString.replaceAll("\\s", "");
			PolynomParser.createParser().parse(pString);
			
			pString = "3x^2 + 10x(2 + 4x^3)";
			pString = pString.replaceAll("\\s", "");
			PolynomParser.createParser().parse(pString);
		} catch (ParseException e) {
			fail("Exception while parsing \"" + pString + "\"\n" + e.getMessage());
		}
	}
	
	@Test (expected=ParseException.class)
	public void negativeParseTest() throws ParseException {
			String pString = "xxx10x^3 + 4(2x + 3x^5) + 2x^2";
			pString = pString.replaceAll("\\s", "");
			PolynomParser.createParser().parse(pString);
	}

	@Test
	public void evalTest() {
		String pString = "";
		try {
			pString = "10x^3 + 4(2x + 3x^5) + 2x^2";
			pString = pString.replaceAll("\\s", "");
			PolynomParser.createParser().parse(pString);
			int res = Evaluator.createEvaluator().evaluate(pString, 2);
			assertEquals(488, res);
			res = Evaluator.createEvaluator().evaluate(pString, 3);
			assertEquals(3228, res);
			res = Evaluator.createEvaluator().evaluate(pString, 4);
			assertEquals(12992, res);
			
			pString = "2x(2x^2 + 1) + x^3";
			pString = pString.replaceAll("\\s", "");
			PolynomParser.createParser().parse(pString);
			res = Evaluator.createEvaluator().evaluate(pString, 2);
			assertEquals(44, res);
			res = Evaluator.createEvaluator().evaluate(pString, 3);
			assertEquals(141, res);
			res = Evaluator.createEvaluator().evaluate(pString, 4);
			assertEquals(328, res);
			
			
			pString = "3x^2 + 10x(2 + 4x^3)";
			pString = pString.replaceAll("\\s", "");
			PolynomParser.createParser().parse(pString);
			res = Evaluator.createEvaluator().evaluate(pString, 2);
			PolynomParser.createParser().parse(pString);
			res = Evaluator.createEvaluator().evaluate(pString, 3);
			assertEquals(3327, res);
			res = Evaluator.createEvaluator().evaluate(pString, 4);
			assertEquals(10368, res);
		} catch (ParseException e) {
			fail("Exception while parsing \"" + pString + "\"\n" + e.getMessage());
		}
	}
}
