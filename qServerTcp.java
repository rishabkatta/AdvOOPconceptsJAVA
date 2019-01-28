/*
@author1: Rishab Katta
@author2: Akhil Karrothu
 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


class MultiClient extends Thread {
    private Socket socket;

    /*
    Initialize the client socket instance
     */
    public MultiClient(Socket socket) {
        this.socket = socket;
    }

    /*
    Create new buffered reader and PrintWriter instances for each client connected as a seperate thread to the Server. The
    server sends a random quote read from a file to the Client
     */
    @Override
    public void run() {

        try {
            BufferedReader sinput = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter soutput = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                int randomNum = ThreadLocalRandom.current().nextInt(0,  qServerTcp.al.size());
                qServerTcp.quote = qServerTcp.al.get(randomNum);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted");
                }
                soutput.println(qServerTcp.quote);
            }

        } catch (IOException e) {
            System.out.println("Oops: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // Oh, well!
            }
        }
    }
}
public class qServerTcp {
    static String quote;
    static ArrayList<String> al = null;
    /*
    Main is used to read from a file, put quotes into an ArrayList, create a ServerSocket Instance and start a new
    Thread for each client connection
     */
    public static void main(String[] args) {

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

        try(ServerSocket serverSocket = new ServerSocket(53679)) {
            System.out.println("Server listening on port 53679");
            while(true) {
                new MultiClient(serverSocket.accept()).start();
                System.out.println("client connected");
            }
        } catch(IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}
