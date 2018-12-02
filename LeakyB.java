import java.util.*;

class LeakyB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        int opr, bs, dprpkt = 0, datainbs = 0, sent; // 5 var
        int[] ps = new int[15];
        for (int i = 0; i < 15; i++) {
            ps[i] = r.nextInt(8) + 1;
        }
        for (int i = 0; i < 15; i++) {
            System.out.print(ps[i] + " ");
        }
        System.out.println("\nEnter Output Rate");
        opr = sc.nextInt();
        System.out.println("Enter Bucket Size");
        bs = sc.nextInt();
        int i = 0;
        System.out.println("Time\tPkt Rcv\tPkt in Buff\tPkt Trans\tPkt Dropped");
        while (i != 15) {
            System.out.print(i + "\t");
            System.out.print(ps[i] + "\t");
            if ((ps[i] + datainbs) > bs) {
                dprpkt = ps[i] - (bs - datainbs);
                datainbs = bs;
            } else {
                datainbs += ps[i];
                dprpkt = 0;
            }
            System.out.print(datainbs + "\t\t");
            sent = (datainbs < opr) ? datainbs : opr;
            datainbs = (datainbs < opr) ? 0 : datainbs - opr;
            System.out.print(sent + "\t\t");
            System.out.print(dprpkt + "\n");
            i++;
        }
        sc.close();
    }
}