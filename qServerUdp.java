import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class qServerUdp extends Thread {

/*
Main method reads quotes from a file and sends it to a remote client through Udp Protocol
 */
    public static void main(String[] args) throws Exception {

        String quote;
        ArrayList<String> al = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            al = new ArrayList<>();
            while ((quote = br.readLine()) !=null){
                al.add(quote);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Server needs a proper filename as a command line arg to read quotes from. ");
            System.exit(0);
        }
        DatagramSocket serverDs = new DatagramSocket();
        System.out.println("Server listening on port :" + serverDs.getLocalPort());



        while (true){
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            serverDs.receive(packet);
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            int randomNum = ThreadLocalRandom.current().nextInt(0,  al.size());
            quote = al.get(randomNum);
            byte[] qba = quote.getBytes();
            packet = new DatagramPacket(qba, qba.length, address, port);
            serverDs.send(packet);
        }

    }
}
