import java.io.*;
import java.util.ArrayList;


public class ServerClass {
 static int storagespace ;
 static ArrayList<String> queue;
 public static void main(String[] args) {
     if (args.length != 1) {
         System.out.println("Incorrect Arguments");
         System.exit(1);
     }
  storagespace = Integer.parseInt(args[0]);
     if(storagespace<10){
         System.out.println("!!Insufficient storageSpace!!!!");
         System.exit(1);
     }
  queue = new ArrayList<>(storagespace);

  try {
      ClientConnection new_connection = new ClientConnection();
      new_connection.start();
  /* ProducerConnection pconnection = new ProducerConnection();
   pconnection.start();

   ConsumerConnection cconnection = new ConsumerConnection();
   cconnection.start();*/
  }catch (IOException e){
      e.getStackTrace();
  }
 }


 }



