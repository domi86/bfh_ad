import java.util.regex.*;

public class RegAusdruck {

  public static void main(String[] args) {

    String inputText = "The most basic form of pattern matching supported by this API "
        + "is the match of a string literal. For example, if the regular expression is "
        + "foo and the input string is foo, the match will succeed because the strings "
        + "are identical. "
        + "Note: In certain situations special characters will not be treated as "
        + "metacharacters.";

    String patternText1 ="[a-z]*er[a-z]*";
    patternText1 = "[\\s][\\w\\d]{1,5}[\\s\\.\\!\\,\\:]";
    patternText1 = "[^\\w\\d]{2,5}";
    Pattern pattern = Pattern.compile(patternText1);
    Matcher matcher = pattern.matcher(inputText);

    while (matcher.find()) {
      System.out.println("\"" + matcher.group()
          + "\" found between " + matcher.start() + " and "
          + matcher.end());
    }
    System.out.println();
    
    String r = matcher.replaceAll("*");
    System.out.println("\n"+r);
    
    System.out.println();
    String[] s = pattern.split(inputText);
    for(int i=0; i<s.length; i++)
        System.out.println(i + ": " + s[i]);


  }
}
