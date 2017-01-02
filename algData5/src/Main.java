
public class Main {
  public static void main(String[] args) {
    Hashtable<Integer, Double> ht = 
        new Hashtable<Integer, Double>( 3, (float)2);
    for( int i=0; i<10; i++ )
    {
      Double n = new Double( i*Math.random());
      ht.put(n.hashCode(), n);
      System.out.printf("  %7.2f ", n);
    }

    System.out.println("\nHashtable: ");
    ht.printEntries();
  }
}
