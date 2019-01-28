/*
@author-name: Rishab Katta
@author-name: Akhil Karrothu
 */
import java.net.*;
public class qClientUdp {
    /*
    Main method gets the quote from Server running on a different machine through UDP Protocol and prints it out
     */
    public static void main(String[] args) throws Exception {
        int port=0;
        String addressname=null;
        InetAddress address;
        DatagramSocket clientDs;
        DatagramPacket clientPacket;
        byte[] sendBuf = new byte[256];
        byte[] qReceive = new byte[400000];

        try {
            for (int i = 0; i < args.length ; i++) {
                if(args[i].startsWith("-port")){
                   port = Integer.valueOf(args[i+1]);
                }
                else if (args[i].startsWith("-server")){
                    addressname = args[i+1];
                }
            }
        } catch (Exception e){
            System.out.println("Improper Command Line Arguments");
            System.exit(0);
        }

        clientDs = new DatagramSocket();
        address = InetAddress.getByName(addressname);
        clientPacket = new DatagramPacket(sendBuf, sendBuf.length, address, port);
        clientDs.send(clientPacket);

        clientPacket = new DatagramPacket(qReceive,qReceive.length);
        clientDs.receive(clientPacket);
        String quote = new String(clientPacket.getData(), 0, clientPacket.getLength());
        quote = quote.trim();
        System.out.println(quote);

    }
}


