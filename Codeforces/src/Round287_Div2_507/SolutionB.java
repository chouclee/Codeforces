package Round287_Div2_507;

import java.util.Scanner;

public class SolutionB {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int r = scan.nextInt();
    int ox = scan.nextInt();
    int oy = scan.nextInt();
    int newX = scan.nextInt();
    int newY = scan.nextInt();
    double distance = Math.sqrt(Math.pow(ox -newX, 2) + Math.pow(oy - newY , 2));
    int result;
    if (Math.ceil(distance/(2* r)) == distance / (2*r))
       result = (int) (distance / (2*r));
    else result = (int) Math.ceil(distance/(2* r));
    System.out.println(result);
    scan.close();
  }
}