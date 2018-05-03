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
  private static String longToBinString(long bin) {
    long i = 0;
    String str = "";
    for (i=0; i<32; i++) {
      str += ( ( (bin<<i) & 0b10000000000000000000000000000000) == 0 )?"0":"1";
    }
    return str;
  } 
 
  static int highestPower(int bin) {
    for(int i=0; i<16; i++) {
      if( (bin<<i &  0b1000000000000000) != 0 ) 
        return 15-i; 
    }
    return 0;
  }

  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter dataword");
    
    int dataword = sc.nextInt();

    int r = highestPower(dataword);

    int divisor = 11, hpd = highestPower(divisor);
    int k = 0, old = dataword;
    
    for(int i=0; i<15; i++) {

      k = dataword >> (15-hpd);

      if(k>>hpd==1) 
        k = (k)^divisor;

      dataword &= 0x0FFF;
      
      dataword |= k<<(15-hpd); 

      dataword <<= 1;

      System.out.println(binToString(dataword));
    }
    System.out.println("k is " + binToString(k));    
    long n = (old<<hpd+1)|k;
    String nstring = longToBinString(old);
    
    System.out.println("finally : " + nstring);
    
    sc.close();
  }
}
