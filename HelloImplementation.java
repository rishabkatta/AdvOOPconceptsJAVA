import java.util.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class HelloImplementation
        extends UnicastRemoteObject
        implements HelloInterface {

    public HelloImplementation() throws RemoteException {
    }

    public String worksForALongTime(String thisClient, int aNumber)
            throws RemoteException {
        System.out.println("---> Client: " + thisClient + " " + new Date());
        try { Thread.sleep(10000); } catch (Exception e ) { }
        System.out.println("<--- Client: " + thisClient + " " + new Date());

        return  thisClient + " returns " + ( aNumber * aNumber);
    }
}