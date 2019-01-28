/*
@author-name: Rishab Katta
@author-name: Akhil Karrothu
 */

import java.math.BigInteger;

public class PrimeAsFastAsPossible extends Thread{
    int num;
    static long lastnum;
    static int count;
    public static PrimeAsFastAsPossible[] allThreads;
    static int numOfThreads;
    static long milliSeconds = System.currentTimeMillis();
    static boolean[] rangeOfNumbers;

    public PrimeAsFastAsPossible(int number) {
        this.num = number;
    }

    /*
    isPrime Checks if the number is prime or not.
     */
    public static boolean isPrime(long n) {
        if (n==2|| n==3){ return true;}
        if(n%2==0 || n%3==0 || n==1 || n==0){return false;}
        int i=5;
        int w=2;

        while (i*i<=n){
            if(n%i==0){return false;}
            i +=w;
            w=6-w;
        }
        return true;
    }

    /*
    This method overrides the Thread class run method. This method uses divide and conquer algorithm
     */
    public void run(){
        long nArg = PrimeAsFastAsPossible.getLastnum();
        long range = nArg/numOfThreads;
        for (int i = 0; i < range ; i++) {
            if (isPrime((num*range)+i)){
                count +=1;
            }

        }
    }

    /*
    this method is used to get the range
     */
    public static long getLastnum(){
        return lastnum;
    }
    /*
    this method is used to get the count of primes
     */
    public int getCount(){
        return count;
    }

    /*
    Main is used to get args, assign them to proper variables, start thread executions, throw exceptions and print count of primes
     */
    public static void main(String[] args) throws NegativeNumberException, NotAnIntegerException {

        try {
            if (args.length==0){
                System.out.println("no args entered.");
                return;
            }
            if (args[0].startsWith("-")){
                if (Integer.valueOf(args[0])<0){
                    throw new NegativeNumberException("Negative number is not a valid argument.");
                }
            }
            for(int i=0; i<args[0].length();i++){
                if(!Character.isDigit(args[0].charAt(i))){
                    throw new NotAnIntegerException("Argument entered is not an integer.");
                }
            }
        }catch (NotAnIntegerException ni){
            System.out.println(ni.getMessage());
            return;
        }catch (NegativeNumberException ne){
            System.out.println(ne.getMessage());
            return;
        }


        lastnum = Long.parseLong(args[0]);



        if(lastnum<=1000){
            numOfThreads = 4;
        }
        else if (lastnum>1000 && lastnum<=100000){
            numOfThreads = Runtime.getRuntime().availableProcessors();
        }
        else if (lastnum>100000){
            numOfThreads = 3*Runtime.getRuntime().availableProcessors();
        }
        allThreads = new PrimeAsFastAsPossible[numOfThreads];

        for(int i = 0; i < numOfThreads; i++)
            allThreads[i] = new PrimeAsFastAsPossible(i);

        for (int i=0; i<allThreads.length; i++){
            allThreads[i].start();
        }

        try {
            for (int i=0; i<allThreads.length; i++){
                allThreads[i].join();
            }
        }catch (InterruptedException ie){}

        System.out.println("# primes: "+count);
        System.out.println("time taken: "+(System.currentTimeMillis()-milliSeconds)/1000 + " Secs");
        System.out.println("#threads used: " + numOfThreads);


    }

    /*
    This exception class is created to raise exceptions when negative number is given as arg in the command line.
     */
    static class NegativeNumberException extends Exception{
        public NegativeNumberException(){
        }

        public NegativeNumberException(String message){
            super(message);
        }

        @Override
        public String getMessage() {
            return super.getMessage();
        }
    }
    /*
    This exception class is created to raise exceptions when anything other than a number is given as arg in the command line.
     */
    static class NotAnIntegerException extends Exception{
        public NotAnIntegerException(){
        }

        public NotAnIntegerException(String message){
            super(message);
        }

        @Override
        public String getMessage() {
            return super.getMessage();
        }
    }
}
