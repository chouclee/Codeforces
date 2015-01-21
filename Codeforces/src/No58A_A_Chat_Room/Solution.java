package No58A_A_Chat_Room;

/*
 Vasya has recently learned to type and log on to the Internet. 
 He immediately entered a chat room and decided to say hello to 
 everybody. Vasya typed the word s. It is considered that Vasya 
 managed to say hello if several letters can be deleted from the 
 typed word so that it resulted in the word "hello". For example, 
 if Vasya types the word "ahhellllloou", it will be considered that 
 he said hello, and if he types "hlelo", it will be considered that 
 Vasya got misunderstood and he didn't manage to say hello. Determine 
 whether Vasya managed to say hello by the given word s.

Input
The first and only line contains the word s, which Vasya typed. This 
word consisits of small Latin letters, its length is no less that 1 
and no more than 100 letters.

Output
If Vasya managed to say hello, print "YES", otherwise print "NO".


 */
import java.util.Scanner;
public class Solution {
  public static boolean isValid(String s, String target) {
    if (s.length() < target.length()) return false;
    int i = 0, j = 0;
    while (i < s.length() && j < target.length()) {
      if (s.charAt(i) == target.charAt(j)) {
        i++;
        j++;
      } else {
        i++;
      }
    }
    return target.length() == j;
  }
  
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String s = scan.nextLine().trim();
    scan.close();
    if (isValid(s, "hello")) System.out.println("YES");
    else System.out.println("NO");
  }
  
}
