
/*
@Author-1: Akhil Karrothu
@Author-2: Rishab Katta
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server1Imp1
        extends Thread
        implements MyServer {
    public static ArrayList<String> queue = null;
    public static int storagespace = 0;


    int itemtype=0,rateofproduction,type,num;
    static int counter = 0;
    static int[] itemcount = {0,0,0};
    final static int[] rateofconsumption = {3,5,2};
    public Server1Imp1(){}

    public Server1Imp1(int threadtype,int rateofproduction,int num) {
        this.type = threadtype;
        this.rateofproduction = rateofproduction;
        this.num = num;
    }

    public Server1Imp1(int size){
        queue = new ArrayList<>(size);
        storagespace = size;
    }
    @Override
    public void produce(int type, int items, int id) {
        Server1Imp1 producerthread = new Server1Imp1(1, items,id);
        producerthread.start();
        try {
            producerthread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void consume(int type, int id) {
        Server1Imp1 consumerthread = new Server1Imp1(2, 10,id);
        consumerthread.start();
        try {
            consumerthread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
                if (this.type == 1) {

                    itemtype = num % 3;
                    if (rateofproduction > storagespace) {
                        System.out.println("!!Production rate cant be greater than Storage!!");
                        System.exit(1);
                    }
                    try {
                        synchronized (queue) {
                            if (storagespace - counter < rateofproduction ||
                                    itemcount[itemtype] >= storagespace/3) {

                                queue.wait();
                            } else {
                                for (int i = 0; i < rateofproduction; i++) {
                                    queue.add(""+itemtype);
                                    itemcount[itemtype]++;
                                }
                                counter = counter + rateofproduction;
                                System.out.println(counter);
                                System.out.println("Producer " + num + " producing " + rateofproduction + " units of type "
                                        + itemtype);
                                queue.notifyAll();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                else
                {
                        try {
                            synchronized (queue) {
                                if (counter < 10 || itemcount[0] < 3 || itemcount[1] < 5 || itemcount[2] < 2) {
                                    queue.wait();
                                } else {
                                    for (int i = 0; i < 3; i++) {
                                        queue.remove("0");
                                        itemcount[0]--;
                                    }
                                    for (int i = 0; i < 5; i++) {
                                        queue.remove("1");
                                        itemcount[1]--;
                                    }
                                    for (int i = 0; i < 2; i++) {
                                        queue.remove("2");
                                        itemcount[2]--;
                                    }
                                    counter = counter - 10;
                                    System.out.println(num + ": Consumes  10 from the storage  " + counter);

                                    queue.notifyAll();
                                }
                            }
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                }



    }


}