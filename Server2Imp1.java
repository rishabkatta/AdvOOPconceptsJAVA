
/*
@Author-1: Akhil Karrothu
@Author-2: Rishab Katta
 */

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server2Imp1 extends Server1Imp1
{
   static int storagespace;
    static ArrayList<String> queue;
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Incorrect Arguments");
            System.exit(1);
        }
        storagespace = Integer.parseInt(args[0]);
        if(storagespace<10) {
            System.out.println("!!Insufficient storageSpace!!!!");
            System.exit(1);
        }

        try {
            Registry registry = LocateRegistry.createRegistry(5099);
            MyServer obj = (MyServer) UnicastRemoteObject.exportObject(new Server1Imp1(storagespace), 0 );
            registry.bind("ak8367", obj);


        }catch (RemoteException e){
            e.getStackTrace();
        }catch (AlreadyBoundException e){
            e.printStackTrace();
            System.out.println("AlreadyBoundException");
            System.exit(1);
        }

        System.out.println("Server ready");
    }
}




