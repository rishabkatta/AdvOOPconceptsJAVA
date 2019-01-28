/*
@Author Name: Rishab Katta
@Author Name: Akhil Karrothu
 */
public class FastAddTest {
/*
Test method for add functionality of the FastAdd class
 */
    public static boolean testAdd(){
        FastAdd<String> aStorage = new FastAdd<>();
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
 Test method for get functionality of the FastAdd class
  */
    public static boolean testGet(){
        FastAdd<String> aStorage = new FastAdd<>();
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
 Test method for clear functionality of the FastAdd class
  */
    public static boolean testClear(){
        FastAdd<String> aStorage = new FastAdd<String>();
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
 Test method for contains functionality of the FastAdd class
  */
    public static boolean testContains(){
        FastAdd<String> aStorage = new FastAdd<String>();
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
    Test method for isEmpty functionality of the FastAdd class
     */
    public static boolean testIsEmpty(){
        FastAdd<String> aStorage = new FastAdd<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        if(aStorage.isEmpty()==false)
            return true;
        return false;

    }
    /*
    Test method for sort functionality of the FastAdd class
     */
    public static boolean testSort(){
        FastAdd<String> aStorage = new FastAdd<>();
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
    Test method for size functionality of the FastAdd class
     */
    public static boolean testSize(){
        FastAdd<String> aStorage = new FastAdd<String>();
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
    Test method for getClassName functionality of the FastAdd class
     */
    public static boolean testGetClassName(){
        FastAdd<String> aStorage = new FastAdd<String>();
        String classname= aStorage.getClassName();
        if(classname.equals("FastAdd"))
            return true;
        else
            return false;
    }

    /*
This methods calls all the test methods and prints out an error message if any functionality failed.
 */
    public static void test(FastAdd<String> aStorage)   {
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
    public static void exampleOfHowToUseIt( FastAdd<String> fa)   {
        fa.add("a");
        fa.add("c");
        fa.add("d");
        fa.add("b");
        FastAdd<String> fb = new FastAdd<>();
        fb.add("e");
        fb.add("h");
        fb.add("g");
        fb.add("f");
        System.out.println("aStorage: " + fa );
        System.out.println("bStorage: " + fb);
        fa.sort();
        fb.sort();
        System.out.println("After Sorting aStorage: " + fa);
        System.out.println("After Sorting bStorage: " + fb);
    }

    /*
    Main method is used to call test and exampleOfHowToUseIt methods for testing purpose
     */
    public static void main(String[] args) {
        test(new FastAdd<String>());
        exampleOfHowToUseIt(new FastAdd<String>());

    }

}