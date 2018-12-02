import java.io.*;
import java.net.*;

class udp_serv {
	public static void main(String[] args) throws Exception {
		int i = 5000;
		while (true) {
			InetAddress clientIP = InetAddress.getByName("127.0.0.1");
			int clientPort = i;
			byte buf[] = new byte[1024];
			DatagramSocket ds = new DatagramSocket();
			BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Server is running...");
			String str1 = dis.readLine();
			if (str1.equals("Exit")) {
				ds.close();
				break;
			} else {
				buf = str1.getBytes();
				DatagramPacket packet = new DatagramPacket(buf, str1.length(), clientIP, clientPort);
				ds.send(packet);
				i = i + 1;
			}
		}
	}
}