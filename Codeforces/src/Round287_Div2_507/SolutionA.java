package Round287_Div2_507;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SolutionA{
  public void max(int[] days, int totalDays) {
    Integer[] idx = new Integer[days.length];
    for (int i = 0; i < days.length; i++) {
      idx[i] = i;
    }
    ArrayIndexComparator comparator = new ArrayIndexComparator(days);
    Arrays.sort(idx, comparator);
    Arrays.sort(days);
    int sum = 0;
    int i = 0;
    for (; i < days.length; i++) {
      if (sum + days[i] <= totalDays)
          sum += days[i];
      else break;
    }
    System.out.println(i);
    for (int j = 0; j < i; j++) {
      System.out.print(idx[j] + 1 + " ");
    }
  }
  
  class ArrayIndexComparator implements Comparator<Integer> {
    private int[] array;
    public ArrayIndexComparator(int[] array) {
      this.array = array;
    }
    
    public int compare(Integer a, Integer b) {
      return array[a] - array[b];
    }
  }
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int instrument = scan.nextInt();
    int totalDays = scan.nextInt();
    int[] days = new int[instrument];
    for (int i = 0; i < days.length; i++) {
      days[i] = scan.nextInt();
    }
    SolutionA s = new SolutionA();
    s.max(days, totalDays);
    scan.close();
  }
}
