
/*
@Author-1: Akhil Karrothu
@Author-2: Rishab Katta
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyServer extends Remote {
      void consume(int type,int id) throws RemoteException;
      void produce(int type,int items,int id) throws RemoteException;
}