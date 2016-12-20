/**
 * An Evaluator for polynomials of the form 2x(2x^2 + 1) + x^3
 */

public class Evaluator {
	private String parseString;
	private int position;
	private int length;
	private int xValue;

	public static Evaluator createEvaluator() {
		return new Evaluator();
	}

	public int evaluate(String expr, int value) {
		this.position = 0;
		this.xValue = value;
		this.parseString = expr;
		this.length = parseString.length();
		return evalPolynomial();
	}

	private int evalPolynomial() {
		int res = evalTerm();
		if (checkPos() && parseString.charAt(position) == '+') {
			position++;
			res = res + evalPolynomial();
		}
		return res;
	}

	private int evalTerm() {
		int res = 0;
		if (checkPos() && isDigit(parseString.charAt(position))) {
			res = evalNumber();
			if (checkPos() && isIdx(parseString.charAt(position))) {
				res = res * evalIdxTerm();
			}

		} else if (checkPos() && isIdx(parseString.charAt(position))) {
			res = evalIdxTerm();
		}
		if (checkPos() && parseString.charAt(position) == '(') {
			res = res * evalFactor();
		}

		return res;
	}

	private int evalFactor() {
		int res = 1;
		if (checkPos() && parseString.charAt(position) == '(') {
			position++;
			res = evalPolynomial();
			if (checkPos() && parseString.charAt(position) == ')')
				position++;
		}
		return res;
	}

	private int evalIdxTerm() {
		int res = 1;
		if (checkPos() && isIdx(parseString.charAt(position))){
			position++;
			res = xValue;
		}
			
		if (checkPos() && parseString.charAt(position) == '^') {
			position++;
			res = (int) Math.pow(res, evalNumber());
		}
		return res;
	}

	private int evalNumber() {
		int res = 0;
		while (position < length && isDigit(parseString.charAt(position))) {
			res = res * 10 + atoi(parseString.charAt(position));
			position++;
		}
		return res;
	}

	private boolean isDigit(char c) {
		return ('0' <= c && c <= '9');
	}

	private boolean isIdx(char c) {
		return (c == 'x');
	}

	private int atoi(char c) {
		return c - '0';
	}

	private boolean checkPos() {
		return position < length;
	}
}
