package sort;

public class Main {

  static int MAX = 20;
  static int array[] = new int[MAX];

  static void init() {
    for (int i = 0; i < MAX; i++) {
      array[i] = (int) (MAX * MAX * Math.random());
    }
  }

  static void printArray() {
    for (int i = 0; i < MAX; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    RadixSort algorithm = new RadixSort();
    init();
    printArray();
    algorithm.sort(array, 3);
    printArray();
  }

}
