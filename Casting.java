/**
 * @author-name: Rishab Katta
 * @author-name: Akhil Karrothu
 */


interface B
{
    int a = 0;
    void methodB();
}

interface BB
{
    int a = 1;
    void methodBB();
}

class A
{
    int a = 2;

    void parentMethod()
    {
        System.out.println("parentMethod");
    }
}

class AA extends A implements B, BB
{
    int a = 3;

    void childMethod()
    {
        System.out.println("childMethod");
    }

    ;

    public void methodB()
    {
        System.out.println("In methodB");
    }

    public void methodBB()
    {
        System.out.println("In methodBB");
    }
}

public class Casting
{

    public static void main(String[] args)
    {
        // here we are creating an instance of class A
        A a = new A();

        // why is this the only method we can call?
        /* a is the reference to the object of class A. 'a' can access all the methods and instance variables defined
        defined inside class A. Since parent method is the only method inside defined in class A, it's the only method
        we can access.
        */
        a.parentMethod();

        // why doesn't this work?
        /*This doesn't work because 'a' is an instance variable and can only be accessed with an instance of class A
        and not class A itself. Only Static variables can be accessed using the class name. That is why they are called
        class variables.
         */
        //System.out.println(A.a);

        // here we are creating an instance of class AA
        AA aa = new AA();

        // class AA doesn't define a parentMethod, how can we call one?
        /*Class AA inherits all the methods and variables from class A using the extends keyword. That means the reference
        variable pointing to an object of class AA has access to variables and methods both in A and AA. That's why aa
        can access parentMethod.
         */
        aa.parentMethod();

        // how could we override this method?
        /*We could override childMethod by defining the same method with the same signature in the parent class A.
        Overrriding can take place in a subclass only.
         */
        aa.childMethod();

        // Which class does this variable refer to?
        /*aa points to the object of class AA and class AA has its own instance variable 'a'.So 'aa.a' points to the
        instance variable in class AA, which is 3.
         */
        System.out.println(aa.a);

        // Which class does this variable refer to?
        /*Here we are casting parent class object to a child class. By doing that '(A).aa' points to the methods in AA
        that are also in A. '(A).aa' cannot be used to call methods exclusive to AA. But here we are not calling any methods
        we are accessing an instance variable. Java doesn't allow instance variables to be overriden. so even when we're
        casting parent class object to a child class, (A).aa will still point to the instance variable in class A, which
        is 2.
         */
        System.out.println(((A) aa).a);


        // What forces us to define these methods in the AA class?
        /*
        methodB is declared in interface B and methodBB is declared in interface BB and class AA implements both of those
        interfaces using the implements keyword. An interface is like an agreement that the class implementing that
        particular interface defines all of methods in that interface mandatorily. That's the reason they are defined
        in class AA.
         */

        aa.methodB();
        aa.methodBB();

        // here we are creating an instance of class AA but what is different about this reference?
        /*
        Here we are using a parent class reference variable to refer to the child class object. We are reducing scope
        of the variable. 'a' can be used only to access the methods in the child class AA which are also in present in
        the parent class.
         */
        a = new AA();

        // why do we need to cast this?
        /*
        We need to explicitly cast this variable because in the previous step we reduced the scope of this variable. It
        cannot be used to access the methods that are exclusive to class AA like childMethod(). we need to cast this
        to child class to get access to that particular method.
         */
        ((AA) a).childMethod();

        // Which class does this variable refer to?
        /*
        It points to the variable in class A because java doesn't allow variables to be overridden.
         */
        System.out.println(a.a);

        // Which class does this variable refer to?
        System.out.println(((AA)a).a);
        /*
        The variable points to class AA cause we're explicitly casting a to child class and using it to refer to 'a'.
         */

        // call methodB and methodBB using the variable a
        ((AA) a).methodB();
        ((AA) a).methodBB();

        // how can we access these variables from the interfaces?
        /*
        Since the variables from interfaces are public, static and Final, they are constants which cannot be modified
        within the class definition. Hence the variables inside the interface are unchanged. The syntax for accessing
        variables declared within the interface is Interfacename.variablename.
         */
        System.out.println(B.a);
        System.out.println(BB.a);

    }
}