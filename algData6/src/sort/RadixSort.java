package sort;

import java.util.LinkedList;

public class RadixSort {

  // one bucket for each of the 10 digits
  private LinkedList[] buckets = { 
      new LinkedList<Integer>(),
      new LinkedList<Integer>(), // 0
      new LinkedList<Integer>(), // 1
      new LinkedList<Integer>(), // 2
      new LinkedList<Integer>(), // 3
      new LinkedList<Integer>(), // 4
      new LinkedList<Integer>(), // 5
      new LinkedList<Integer>(), // 6
      new LinkedList<Integer>(), // 7
      new LinkedList<Integer>(), // 8
      new LinkedList<Integer>()  // 9
  };

  // sort array with radix sort algorithm
  // numDigits is the maximum number of digits
  void sort(int array[], int numDigits) {
    for (int n = 1; n <= numDigits; n++) {
      // Add values to buckets according to n-th digit
      for (int i = 0; i < array.length; i++)
        buckets[getRadix(array[i], n)].add(array[i]);
      
      // Collect elements of the buckets and put them back into the array
      int arrayPos = 0;
      for (int i = 0; i < buckets.length; i++) {
        while (!buckets[i].isEmpty()) {
          array[arrayPos] = (Integer)buckets[i].removeFirst();
          arrayPos++;
        }
      }
    }
  }

  // returns the n-th digit of the given number:
  // 3nd radix of 79981 is 9,  2nd radix of 79981 is 8
  // 1st radix of 79981 is 1
  public static int getRadix(int number, int radix) {
    return (int) (number / Math.pow(10, radix - 1)) % 10;
  }
  
  
}
