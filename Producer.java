
import java.net.*;
import java.io.*;

public class Producer extends Thread{
    static int noofproducers,rateofproduction,threadtype = 1;
    static String Servername = null;
    int num;
    private static Producer[] producers;
    public Producer( int id, int val ,int type) {
        this.num = id;
        this.rateofproduction = val;
        this.threadtype = type;
    }
    public static void main(String[] args) {

        if(args.length != 3) {
            System.out.println("Incorrect Arguments");
            System.exit(1);
        }
        noofproducers = Integer.parseInt(args[0]);
        if(noofproducers < 0){
            System.out.println("Negitive numbers not allowed");
            System.exit(1);
        }
        rateofproduction = Integer.parseInt(args[1]);
        if(rateofproduction < 0){
            System.out.println("Negitive numbers not allowed");
            System.exit(1);
        }
        Servername = args[2];

        producers = new Producer[noofproducers];
        for (int i = 0; i < noofproducers; i++)
            producers[i] = new Producer(i,rateofproduction,threadtype);
        for (int i = 0; i < noofproducers; i++)
            producers[i].start();
    }

   /* public int getport() {
        Socket sock;
        try {
            sock = new Socket(Servername, 7777);
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(sock.getInputStream()));
            out.writeUTF("Get port number");
            return Integer.parseInt(in.readLine());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }*/

    public void run(){
        try{
            Socket sock = new Socket(Servername,7777);
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            out.writeUTF( this.threadtype+" "+this.num +" "+ this.rateofproduction);
           /* DataInputStream input = new DataInputStream(sock.getInputStream());
            String strings = input.readUTF();
            System.out.println("From SERVER :  "+strings);*/
            out.close();
        }catch(IOException e)
        {
            System.out.println("Socket Error!!!!");
        }

    }
}