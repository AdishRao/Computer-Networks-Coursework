import  java.util.*;

public class crc {
  private static String binToString(int bin) {
    int i = 0;
    String str = "";
    for (i=0; i<16; i++) {
      str += ( ( (bin<<i) & 0b1000000000000000) == 0 )?"0":"1";
    }
    return str;
  } 
 
  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);

    /* Test 
        System.out.println("Binary("+0x0001+") [\"0x0001\"] = "+binToString(0x0001));
        System.out.println("Binary("+0x0002+") [\"0x0002\"] = "+binToString(0x0002));
        System.out.println("Binary("+0x000F+") [\"0x000F\"] = "+binToString(0x000F));
        System.out.println("Binary("+0x00FF+") [\"0x00FF\"] = "+binToString(0x00FF));
        System.out.println("Binary("+0x00F0+") [\"0x000F\"] = "+binToString(0x00F0));
        System.out.println("Binary("+0x00F2+") [\"0x00F2\"] = "+binToString(0x00F2));
        System.out.println("Binary("+0x02F2+") [\"0x02F2\"] = "+binToString(0x02F2));
        System.out.println("Binary("+0xBEAD+") [\"0xBEAD\"] = "+binToString(0xBEAD));
    */

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
    System.out.println("finally : " + binToString(0|(old<<r)|k));
  }
}
