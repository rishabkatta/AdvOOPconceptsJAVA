/*
@author1: Rishab Katta
@author2: Akhil Karrothu
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class qClientTcp {
    /*
     Main method is used to connect to the server and print the quote that server sends.
     */
    public static void main(String[] args) throws Exception{
        String quote;
        int port=0;
        String addressname=null;
        InetAddress address;
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
        address = InetAddress.getByName(addressname);
        try (Socket socket = new Socket(address, port)) {
            BufferedReader cinput = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter coutput = new PrintWriter(socket.getOutputStream(), true);
            quote = cinput.readLine();
            System.out.println(quote);
        } catch (IOException ie) {
            System.out.println("Client error");
        }
    }
}
