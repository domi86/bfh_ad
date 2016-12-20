/**
 * Parser for polynomials of the form 2x(2x^2 + 1) + x^3
 */

public class PolynomParser {
	private String parseString;
	private int position;
	private int length;

	public static PolynomParser createParser() {
		return new PolynomParser();
	}

	public void parse(String expr) throws ParseException {
		this.position = 0;
		this.parseString = expr;
		this.length = parseString.length();

		polynomial();
		if (position < length)
			throw new ParseException(position, "parse");
	}

	private void polynomial() throws ParseException {
		term();
		if (checkPos() && parseString.charAt(position) == '+') {
			position++;
			polynomial();
		}
	}

	private void term() throws ParseException {
		if (checkPos() && isDigit(parseString.charAt(position))) {
			number();
			if (checkPos() && isIdx(parseString.charAt(position))) {
				idxTerm();
			}

		} else if (checkPos() && isIdx(parseString.charAt(position))) {
			idxTerm();
		} else {
			throw new ParseException(position, "number or idxterm required");
		}
		if (checkPos() && parseString.charAt(position) == '(') {
			factor();
		}
	}

	private void idxTerm() throws ParseException {
		if (checkPos() && isIdx(parseString.charAt(position)))
			position++;
		if (checkPos() && parseString.charAt(position) == '^') {
			position++;
			number();
		}
	}

	private void factor() throws ParseException {
		if (checkPos() && parseString.charAt(position) == '(') {
			position++;
			polynomial();
			if (checkPos() && parseString.charAt(position) == ')')
				position++;
		}
	}

	private void number() throws ParseException {
		if (checkPos() && isDigit(parseString.charAt(position)))
			position++;
		else
			throw new ParseException(position, "digit required");
		while (checkPos() && isZeroDigit(parseString.charAt(position)))
			position++;
	}

	private boolean isDigit(char c) {
		return ('1' <= c && c <= '9');
	}

	private boolean isZeroDigit(char c) {
		return ('0' <= c && c <= '9');
	}

	private boolean isIdx(char c) {
		return (c == 'x');
	}

	private boolean checkPos() {
		return position < length;
	}
}