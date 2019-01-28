import java.io.*;
import java.net.*;

class ClientConnection extends Thread {

    ServerSocket serv = new ServerSocket(7777);

    ClientConnection() throws IOException {
    }

    public void run() {
        while (true){
            try {
                Socket s = serv.accept();
                ClientThreads obj = new ClientThreads(s);
                obj.start();
            }catch (IOException e){
                System.out.println("Socket Problem");
            }
        }
    }
}

class ClientThreads extends Thread{
    Socket s;
    int itemtype=0;
    static int counter = 0;
    static int[] itemcount = {0,0,0};
    final static int[] rateofconsumption = {3,5,2};
    public ClientThreads(Socket s)
    {
        this.s = s;
    }
    public void run(){
        try {
            DataInputStream inp = new DataInputStream(s.getInputStream());
            String input= inp.readUTF();
            String array[] = input.split(" ");
            Integer type = Integer.parseInt(array[0]);
            Integer num = Integer.parseInt(array[1]);
            if(type == 1) {
                itemtype = num % 3;
                Integer rateofproduction = Integer.parseInt(array[2]);
                if (rateofproduction > ServerClass.storagespace) {
                    System.out.println("!!Production rate cant be greater than Storage!!");
                    System.exit(1);
                }
                while (true) {
                    synchronized (ServerClass.queue) {
                        if (ServerClass.storagespace - counter < rateofproduction || itemcount[itemtype]
                                >= rateofconsumption[itemtype]) {
                            ServerClass.queue.wait();
                        } else {
                            for (int i = 0; i < rateofproduction; i++) {
                                ServerClass.queue.add("itemtype");
                                itemcount[itemtype]++;
                            }
                            counter = counter + rateofproduction;
                            System.out.println(counter);
                            System.out.println("Producer " + num + " producing " + rateofproduction + " units of type "
                                    + itemtype);

                            ServerClass.queue.notifyAll();
                        }
                    }
                }
            }
            else{

                while (true) {
                    synchronized (ServerClass.queue) {
                        if (counter <10 || itemcount[0] <3 || itemcount[1]<5||itemcount[2] <2) {
                            ServerClass.queue.wait();
                        } else {
                            for (int i = 0; i < 3; i++) {
                                ServerClass.queue.remove("0");
                                itemcount[0]--;
                            }
                            for (int i = 0; i < 5; i++) {
                                ServerClass.queue.remove("1");
                                itemcount[1]--;
                            }
                            for (int i = 0; i < 2; i++) {
                                ServerClass.queue.remove("2");
                                itemcount[2]--;
                            }
                            counter = counter -10;
                            System.out.println(num + ": Consumes  10 from the storage  "+counter );

                            ServerClass.queue.notifyAll();
                        }
                    }

                }
            }

        }catch (IOException e){
            System.out.println("Socket Error");
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}