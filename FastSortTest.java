/*
@Author Name: Rishab Katta
@Author Name: Akhil Karrothu
 */
public class FastSortTest {

/*
Test method for add functionality of the FastSort class
 */
    public static boolean testAdd(){
        FastSort<String> aStorage = new FastSort<>();
        String theStrings[] = { "a", "b", "c" };
        String output="";
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        for(int i=0;i<theStrings.length; i++){
            output +=aStorage.get();
        }
        if(output.equals("abc")){
            return true;
        }
        return false;
    }

    /*
    Test method for get functionality of the FastSort class
     */
    public static boolean testGet(){
        FastSort<String> aStorage = new FastSort<>();
        String theStrings[] = { "a", "b", "c" };
        String output="";
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        for(int i=0;i<theStrings.length; i++){
            output +=aStorage.get();
        }
        if(output.equals("abc")){
            return true;
        }
        return false;

    }
    /*
    Test method for clear functionality of the FastSort class
     */
    public static boolean testClear(){
        FastSort<String> aStorage = new FastSort<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        aStorage.clear();
        if (aStorage.size()==0)
            return rValue;
        else
            return false;

    }

    /*
    Test method for contains functionality of the FastSort class
     */
    public static boolean testContains(){
        FastSort<String> aStorage = new FastSort<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        for(int i=0; i<theStrings.length; i++){
            if(aStorage.contains(theStrings[i])){
                continue;
            }
            else
                return false;
        }
        return true;

    }

    /*
    Test method for IsEmpty functionality of the FastSort class
     */
    public static boolean testIsEmpty(){
        FastSort<String> aStorage = new FastSort<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        if(aStorage.isEmpty()==false)
            return true;
        return false;

    }

    /*
    Test method for Sort functionality of the FastSort class
     */
    public static boolean testSort(){
        FastSort<String> aStorage = new FastSort<>();
        String theStrings[] = { "b", "c", "a" };
        String output="";
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        aStorage.sort();
        for(int i=0;i<theStrings.length; i++){
            output +=aStorage.get();
        }
        if(output.equals("abc")){
            return true;
        }
        return false;

    }

    /*
    Test method for Size functionality of the FastSort class
     */
    public static boolean testSize(){
        FastSort<String> aStorage = new FastSort<String>();
        String theStrings[] = {"a", "b", "c"};
        boolean rValue = true;
        for (int index = 0; index < theStrings.length; index++)
            aStorage.add(theStrings[index]);
        if(aStorage.size()==theStrings.length)
            return rValue;
        else
            return false;

    }
    /*
    Test method for GetClassName functionality of the FastSort class
     */
    public static boolean testGetClassName(){
        FastSort<String> aStorage = new FastSort<String>();
        String classname= aStorage.getClassName();
        if(classname.equals("FastSort"))
            return true;
        else
            return false;
    }

    /*
This methods calls all the test methods and prints out an error message if any functionality failed.
 */
    public static void test(FastSort<String> aStorage)   {
        if ( ! testAdd() )
            System.err.println("testAdd failed");
        if ( ! testGet() )
            System.err.println("testGet failed");
        if ( ! testIsEmpty() )
            System.err.println("testRemoveAll failed");
        if ( ! testSort() )
            System.err.println("testSort failed");
        if ( ! testContains() )
            System.err.println("testContains failed");
        if ( ! testClear() )
            System.err.println("testClear failed");
        if ( ! testSize() )
            System.err.println("testSize failed");
        if ( ! testGetClassName() )
            System.err.println("testGetClassName failed");

    }
    /*
    exampleOfHowToUseIt is used to test the functionality of add,sort methods and print out the storage system.
     */

    public static void exampleOfHowToUseIt( FastSort<String> fa)   {
        fa.add("a");
        fa.add("c");
        fa.add("d");
        fa.add("b");
        fa.sort();

        FastSort<String> fb = new FastSort<>();
        fb.add("e");
        fb.add("h");
        fb.add("g");
        fb.add("f");

        System.out.println("aStorage: " + fa );
        System.out.println("bStorage: " + fb);
    }

/*
Main method is used to call test and exampleOfHowToUseIt methods for testing purpose
 */
    public static void main(String[] args) {
        test(new FastSort<String>());
        exampleOfHowToUseIt(new FastSort<String>());
    }

}