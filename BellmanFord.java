import java.util.*;

class BellmanFord {
  int n;
  int d[];
  int A[][];
  int max = 999;

  BellmanFord(int n) {
    A = new int[n + 1][n + 1];
    this.n = n;
    d = new int[n + 1];
  }

  public void shortest(int s) {
    for (int i = 1; i <= n; i++) {
      d[i] = max;
    }
    d[s] = 0;
    for (int k = 1; k <= n - 1; k++) {
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
          if (A[i][j] != max) {
            if (d[j] > d[i] + A[i][j]) {
              d[j] = d[i] + A[i][j]; // j is current node and i is parent node
            }
          }
        }
      }
    }
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        if (A[i][j] != max) {
          if (d[j] > d[i] + A[i][j]) {
            System.out.println("NEG present!");
            return;
          }
        }
      }
    }
    for (int i = 1; i <= n; i++)
      System.out.println("Dist from " + s + " to " + i + " is " + d[i]);
  }

  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter number of verticies");
    int n = sc.nextInt();
    BellmanFord b = new BellmanFord(n);
    System.out.println("Enter matrix");
    for (int i = 1; i <= n; i++)
      for (int j = 1; j <= n; j++)
        b.A[i][j] = sc.nextInt();
    System.out.println("Enter Source");
    int s = sc.nextInt();
    b.shortest(s);
    sc.close();
  }
}
