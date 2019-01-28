import java.net.*;
import java.io.*;
public class Address {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a website name");
        String sitename= br.readLine();
        try {
            InetAddress ip = InetAddress.getByName(sitename);
            System.out.println("The ip address of " + sitename + " is " + ip);
        }catch (UnknownHostException ue){
            System.out.println("website not found");
        }
    }
}
