package ch.bfh.ad.main;
public class Main {

	public static void main(String[] args) {
		String pString;
		if (args.length < 1) {
			pString = "10x^3 + 4(2x + 3x^5) + 2x^2";
//			pString = "2x(2x^2 + 1) + x^3";
		} else
			pString = args[0];
		pString = pString.replaceAll("\\s", "");
		System.out.println("Polynomial Expression: \n" + pString);
		try {
			PolynomParser.createParser().parse(pString);
			System.out.println("The polynomial expression is correct");
			System.out.println("Its value is : " + Evaluator.createEvaluator().evaluate(pString, 2));
		} catch (ParseException e) {
			for (int i = 0; i < e.getPosition(); i++)
				System.out.print(".");
			System.out.println("^");
			System.out.println("Error at position " + e.getPosition() + " in method " + e.getMethod());
		}
	}
}
