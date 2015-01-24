package Round287_Div2_507;

import java.util.Scanner;

public class SolutionC {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    long h = scan.nextLong();
    long n = scan.nextLong();
    long start = 1, end = 1L << h, mid = 0, step = 0;
    boolean isLeft = true;
    while (start <= end) {
      if (start == end) break;
      mid = start + ((end -start) >> 1);
      if (isLeft) {
        if (n > mid) {
          step += 1L << h;
          start = mid + 1;
        } else {
          step++;
          isLeft = false;
          end = mid;
        }
      } else {
        if (n <= mid) {
          step += 1L << h;
          end = mid;
        } else {
          step++;
          isLeft = true;
          start = mid + 1;
        }
      }
      h--;
    }
    scan.close();
    System.out.println(step);
  }
}