package com.company;

/**
 * @author-name: Rishab Katta
 * @author-name: Akhil Karrothu
 */

import java.util.Scanner;


/**
 * Animal Class declares all the variables and all the methods common to all animals
 */
class Animal
{
    String name;
    boolean homestatus;
    String species;
    boolean sendHome;
    boolean hungerStatus;
    String nameOfHome;


    public String areyouHungry(){
        String hungryornot;
        if(hungerStatus)
            hungryornot = "Yes I am Hungry";
        else
            hungryornot = "No I'm not. I just ate.";
        return hungryornot;
    }

    public boolean amIHome(){
        return homestatus;
    }

    public String homeOrNot(){
        String homeornot;
        if( homestatus)
            homeornot="Yes I'm home.";
        else
            homeornot = "I'm not home";
        return homeornot;
    }

    public boolean areYouHome(){

        return sendHome;
    }

    public String getSpecies(){

        return species;
    }

    public String getName(){

        return name;
    }

    public void goHome(){

        System.out.println("Iam going to my " + nameOfHome);
    }

    public void goEat(){

        System.out.println("");
    }
}

/**
 * Carnivore class inherits from Animal class and is declared abstract because we don't want users to instantiate
 * the carnivore class
 */
class Carnivore extends Animal
{
    public final void goEat(){
        System.out.println("Iam going to Hunt");
    }
}

/**
 * Herbivore class inherits from Animal class and is declared abstract because we don't want users to instantiate
 * the Herbivore class
 */

class Herbivore extends Animal
{
    public final void goEat(){

        System.out.println("Iam going to Graze");
    }
}

/**
 * Tiger class inherits from Carnivore and initializes all the varaibles defined in Animal with values specific to a
 * tiger here.
 */
class Tiger extends Carnivore
{
    public Tiger(String tigerName){
        this.name = tigerName;
        this.species = "Tiger";
        this.hungerStatus = true;
        this.homestatus = false;
        this.sendHome = true;
        this.nameOfHome = "Den";
    }
}

/**
 * Lion class inherits from Carnivore and initializes all the varaibles defined in Animal with values specific to a
 * Lion here.
 */

class Lion extends Carnivore
{

    public Lion (String lionName){
        this.name = lionName;
        this.species = "Lion";
        this.hungerStatus = true;
        this.homestatus = false;
        this.sendHome = true;
        this.nameOfHome = "crib, homie";
    }
}

/**
 * Gazelle class inherits from Herbivore and initializes all the varaibles defined in Animal with values specific to a
 * Gazelle here.
 */

class Gazelle extends Herbivore
{
    public Gazelle(String gazelleName){
        this.name = gazelleName;
        this.species = "Gazelle";
        this.hungerStatus = true;
        this.homestatus = false;
        this.sendHome = true;
        this.nameOfHome = "Ghetto";
    }
}

/**
 * Giraffe class inherits from Herbivore and initializes all the varaibles defined in Animal with values specific to a
 * Giraffe here.
 */

class Giraffe extends Herbivore
{
    public Giraffe(String giraffeName){
        this.name = giraffeName;
        this.species = "Giraffe";
        this.hungerStatus = true;
        this.homestatus = false;
        this.sendHome = true;
        this.nameOfHome = "Hood";
    }
}

/**
 * Zoo class creates instances of all 4 types animals and displays name, home status, hunger status etc. It performs various
 * operations on the variables and displays results.
 */
class Zoo
{
    static Animal[] animals = {
            new Tiger("T-Bone"), new Lion("L-Dawg"), new Giraffe("Highman"), new Gazelle("G Z")
    };
    public static void printSpecies(Animal thisOne)      {
        System.out.println("I am a " + thisOne.getSpecies());
    }
    public static void printName(Animal thisOne) {
        System.out.println("My name is " + thisOne.getName());
    }
    public static void printHomeStatus(Animal thisOne)   {
        System.out.println("I am" + ( thisOne.amIHome() ? " " : " not " ) + "home."  );
        if ( thisOne.amIHome() )
            System.out.println(thisOne.areYouHome());
    }


    public static void zooStuff(int i) {
        printSpecies(animals[i]);
        printName(animals[i]);
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you wanna know if I'm Hungry or not?y/n");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            System.out.println(animals[i].areyouHungry());
            if (animals[i].hungerStatus) {
                animals[i].goEat();
            }
            animals[i].hungerStatus = false;
        }
        System.out.println("Do you wanna know if I'm home or not?y/n");
        String choice2 = sc.nextLine();
        if (choice2.equalsIgnoreCase("y")) {
            System.out.println(animals[i].homeOrNot());
        }
        if (animals[i].homestatus == false) {
            System.out.println("Do you wanna send me home y/n");
            String choice1 = sc.nextLine();
            if (choice1.equalsIgnoreCase("y")) {
                animals[i].goHome();
                animals[i].sendHome = true;
                animals[i].homestatus = true;

            }

        }
    }

    public static void main(String args[] )      {


        while(true) {
            System.out.println();
            System.out.println("Press 1 for tiger");
            System.out.println("Press 2 for Lion");
            System.out.println("Press 3 for Giraffe");
            System.out.println("Press 4 for Gazelle");
            System.out.println("press 5 to exit");

            Scanner reader = new Scanner(System.in);
            System.out.println("Enter your choice: ");
            int n = reader.nextInt();

            switch (n) {
                case 1:
                    zooStuff(0);
                    break;
                case 2:
                    zooStuff(1);
                    break;
                case 3:
                    zooStuff(2);
                    break;
                case 4:
                    zooStuff(3);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("enter the correct option");


            }
        }
    }
}
