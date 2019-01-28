import java.net.*;
import java.io.*;

public class Consumer extends Thread{
    static int noofconsumers,threadtype=0;
    static String Servername = null;
    int num;
    private static Consumer[] consumers;
    public Consumer( int id,int type) {
        this.num = id;
        this.threadtype = type;
    }

    /* public int getport() {
        Socket sock;
        try {
            sock = new Socket(Servername, 6666);
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

    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("Incorrect Arguments");
            System.exit(1);
        }
        noofconsumers=Integer.parseInt(args[0]);
        if(noofconsumers < 0){
            System.out.println("Negitive numbers not allowed");
            System.exit(1);
        }
        Servername = args[1];

        consumers = new Consumer[noofconsumers];
        for (int i = 0; i < noofconsumers; i++)
            consumers[i] = new Consumer(i,threadtype);
        for (int i = 0; i < noofconsumers; i++)
            consumers[i].start();
    }
    public void run(){
        try{
            Socket sock = new Socket(Servername, 7777);
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            out.writeUTF( this.threadtype+" "+this.num +" "+10);
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