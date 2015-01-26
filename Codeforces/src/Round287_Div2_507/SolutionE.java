package Round287_Div2_507;

import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.ArrayList;

public class SolutionE {
  private class Node {
    public int to;
    public boolean ok;
    public Node(int to, boolean ok) {
      this.to = to;
      this.ok = ok;
    }
  }
  // suppose the number of all good roads is totalOK,
  // the length of shortest path is d, and it contains s good roads
  // then we have to repair d -s roads and destroy totalOK - s roads
  // the total number of roads that would be affected is totalOk + d - 2*s
  // now the problem become : find the shortest path with maximum good roads
  private ArrayList<ArrayList<Node>> list;
  public SolutionE() {
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    int m = scan.nextInt();
    list = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      list.add(new ArrayList<Node>());
    }
    int[] from = new int[m], to =  new int[m];
    boolean[] opened = new boolean[m];
    boolean ok;
    int totalOk = 0;
    for (int i = 0; i < m; i++) {
      from[i] = scan.nextInt() - 1;
      to[i] = scan.nextInt() - 1;
      ok = (scan.nextInt() == 0) ? false : true;
      if (ok) { 
        totalOk++;
        opened[i] = true;
      }
      list.get(from[i]).add(new Node(to[i], ok));
      list.get(to[i]).add(new Node(from[i], ok));
    }
    scan.close();
    
    // bfs from city 1 + DP
    int[] step1 = new int[n];
    int[] max1 = new int[n];
    int[] parent1 = new int[n];
    LinkedList<Integer> queue = new LinkedList<>();
    queue.add(0);
    while (!queue.isEmpty()) {
      int curr = queue.removeFirst();
      for (Node node : list.get(curr)) {
        if (step1[node.to] == 0 && node.to != 0) {
          queue.add(node.to);
          step1[node.to] = step1[curr] + 1;
          max1[node.to] = node.ok ? max1[curr] + 1 : max1[curr];
          parent1[node.to] = curr;
        } else if (step1[node.to] == step1[curr] + 1) {
          int temp = node.ok ? max1[curr] + 1 : max1[curr];
          if (temp > max1[node.to]) {
            max1[node.to] = temp;
            parent1[node.to] = curr;
          }        
        }
      }
    }
    
    // bfs from destination city + DP
    int[] step2 = new int[n];
    int[] max2 = new int[n];
    int[] parent2 = new int[n];
    parent2[n-1] = n-1;
    queue.add(n - 1);
    while (!queue.isEmpty()) {
      int curr = queue.removeFirst();
      for (Node node : list.get(curr)) {
        if (step2[node.to] == 0 && node.to != n - 1) {
          queue.add(node.to);
          step2[node.to] = step2[curr] + 1;
          max2[node.to] = node.ok ? max2[curr] + 1 : max2[curr];
          parent2[node.to] = curr;
        } else if (step2[node.to] == step2[curr] + 1) {
          int temp = node.ok ? max2[curr] + 1 : max2[curr];
          if (temp > max2[node.to]) {
            max2[node.to] = temp;
            parent2[node.to] = curr;
          }        
        }
      }
    }
    
    // find a path that maximize the number of good bridges/roads
    int maxOk = Integer.MIN_VALUE;
    int maxIdx = 0;
    for (int i = 0; i < n; i++) {
      if (step1[i] + step2[i] == step2[0] && max1[i] + max2[i] > maxOk) {
        maxOk = max1[i] + max2[i];
        maxIdx = i;
      }
    }
    
    // construct the path
    int[] path = new int[n];
    Arrays.fill(path, - 1);
    int i = maxIdx;
    while (i != 0) {
      path[parent1[i]] = i;
      i = parent1[i];
    }
    i = maxIdx;
    while (i != n - 1) {
      path[i] = parent2[i];
      i = path[i];
    }
    
    System.out.println(totalOk + step2[0] - 2*maxOk);
    for (i = 0; i < m; i++) {
      if (path[from[i]] == to[i] || path[to[i]] == from[i]) {
        if (!opened[i]) System.out.println((from[i]+1) + " " + (to[i]+1) + " " + "1");
      } else if (opened[i])
        System.out.println((from[i]+1) + " " + (to[i]+1) + " " + "0");
    }    
  }
  
  public static void main(String[] args) {
    SolutionE s = new SolutionE();
  }
}
