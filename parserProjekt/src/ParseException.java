public class ParseException extends Exception {
	private static final long serialVersionUID = 1L;
	int position = 0;
	String method;

	public ParseException(int pos, String s) {
		super();
		position = pos;
		method = s;
	}

	public int getPosition() {
		return position;
	}

	public String getMethod() {
		return method;
	}
}