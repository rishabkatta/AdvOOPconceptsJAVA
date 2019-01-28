/*
@author-name:Rishab Katta
@author-name:Akhil Karrothu
 */

import java.util.*;
import java.util.concurrent.*;

public class Elevator  {
    static int noOfFloors;
    static int noOfPersons;
    static ArrayList<String> personfloorcombo =new ArrayList<String>();
    static ArrayList<Integer> floors =new ArrayList<Integer>();
    static int floorcounter=0;
    static Object o= new Object();

    /*
    We used main Thread to perform Elevator executions. We defined a ThreadPoolExecutor with a corepoolsize of 1 and
    maxpoolsize of 8. We defined a personFactory to create person threads, since each thread represents a person.
    The main thread executes all the person threads
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of people");
        try {
            noOfPersons = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Not an integer");
            System.exit(0);
        }
        System.out.println("Enter number of floors");
        try {
            noOfFloors = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Not an integer");
            System.exit(0);
        }
        if (noOfPersons>9){
            System.out.println("Max Capacity is 9 People only.");
            System.exit(0);
        }
        if (noOfFloors==0){
            System.out.println("Nothing to do here");
            System.exit(0);
        }
        if (noOfFloors<noOfPersons){
            int temp=noOfFloors;
            for (int i = 0; i <noOfPersons ; i++) {
                if(temp>0){
                    floors.add(temp);
                    temp -=1;
                }
                if (temp<0 && temp<=noOfFloors){
                    temp+=1;
                    floors.add(temp);
                }
                else{
                    floors.add(1);
                }

            }
        }

        else if (noOfFloors>=noOfPersons){
            for (int i = 1; i <=noOfFloors ; i++) {
                floors.add(i);
            }
        }
        Collections.shuffle(floors);
        LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<Runnable>();
        PersonFactory pf = new PersonFactory();
        ThreadPoolExecutor elevator = new ThreadPoolExecutor(1,8,1, TimeUnit.SECONDS,queue,pf);
        for (int i=0; i<noOfPersons; i++){
            elevator.execute(new Person(i));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        personfloorcombo.sort(Comparator.comparing(s -> Integer.parseInt(s.substring(1))));
        for (int i=0; i<noOfPersons;i++){
            elevator.execute(new DropOff(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        elevator.shutdown();

    }
}
/*
This class's run performs dropping off operations on the Person Threads. It uses synchronization on a static object
to allow only one person thread at a time
 */
class DropOff implements Runnable{

    int i;
    public DropOff(int i){
        this.i=i;
    }

    public void run(){
        synchronized (Elevator.o){

            System.out.println("Elevator beeps");
            System.out.println("Elevator opens the door");
            System.out.println("Elevator dropping off Person " + Elevator.personfloorcombo.get(i).substring(0,1) + " at floor " + Elevator.personfloorcombo.get(i).substring(1));
            System.out.println("Elevator closes the door");
            System.out.println(" ");
        }
    }
}
/*
This class generates an id for each person thread and also gets which floor each person wants to go to.
 */
class Person implements Runnable {

    int idofPerson;
    Random r = new Random();

    public Person(){}

    public Person(int id) {
        this.idofPerson=id;

    }

    public void run(){
        int afloor = Elevator.floors.get(Elevator.floorcounter);
        Elevator.floorcounter+=1;
        System.out.println("Person " + idofPerson + " enters elevator to go to floor " + afloor);
        Elevator.personfloorcombo.add("" + idofPerson + afloor);
    }

}
/*
This class generates person thread objects on demand without explicit creation everytime.
 */
class PersonFactory implements ThreadFactory {

    public Thread newThread(Runnable r) {
        return new Thread(r);
    }

}