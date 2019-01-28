public interface HelloInterface extends java.rmi.Remote {
    String worksForALongTime(String thisClient, int aNumber)
            throws java.rmi.RemoteException;
}