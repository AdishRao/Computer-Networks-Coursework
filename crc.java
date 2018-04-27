import  java.util.*;

public class crc {

  public static void main(String args[]) {
    String[] dbin = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111",
    "1000", "1001", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};

    String[] rbin = {"000", "001", "010", "011", "100", "101", "110", "111"};

    Scanner sc = new Scanner(System.in);
    System.out.println("Enter dataword");
    int dataword = sc.nextInt();

    System.out.println("Enter highest power");
    int r = sc.nextInt();

    int divisor = 11;
    int k = 0, old = dataword;

    for(int i=0; i<=r; i++) {
      if(dataword>>3==1)
        k = (dataword)^divisor;
      else
        k = dataword;

      System.out.println("iter " + i+ " : " + k);

      dataword = k << 1;
    }
    System.out.println("finally : " + dbin[old]+  rbin[k]);
  }
}
