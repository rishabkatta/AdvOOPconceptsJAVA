/*
@Author-1 : Rishab Katta
@Author-2: Akhil Karrothu
 */


import java.util.Arrays;

public class StorageTest {

/*
Test method for add functionality of the storage class
 */
    public static boolean testAdd()     {
        Storage<String> aStorage = new Storage<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        if(aStorage.add("a")==false){
            return rValue;
        }
        return false;
    }
///*
//Test method for addFirst functionality of the storage class
// */
    public static boolean testAddAll()     {
        Storage<String> aStorage = new Storage<String>();
        Storage<String> bStorage = new Storage<String>();
        String theStrings[] = { "a", "b", "c" };
        String theStrings2[] = { "b", "c", "d" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.addFirst(theStrings[index]);
        for ( int index = 0; index < theStrings2.length; index ++ )
            bStorage.addFirst(theStrings2[index]);
        aStorage.addAll(bStorage);
        if(aStorage.size()==4)
            return true;

        return false;
    }
/*
Test method for addLast functionality of the storage class
 */
    public static boolean testRemoveAll()     {
        Storage<String> aStorage = new Storage<String>();
        Storage<String> bStorage = new Storage<String>();
        String theStrings[] = { "a", "b", "c" };
        String theStrings2[] = { "b", "c", "d" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        for ( int index = 0; index < theStrings2.length; index ++ )
            bStorage.add(theStrings2[index]);
        aStorage.removeAll(bStorage);
        if(aStorage.size()==1)
            return true;

        return false;
    }
/*
Test method for add at Index functionality of the storage class
 */

    public static boolean testToArray()     {
        Storage<String> aStorage = new Storage<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        Object[] array = aStorage.toArray();
        for(int i=0; i<array.length; i++){
            if(array[i]==theStrings[i]){
                continue;
            }
            else
                return false;
        }
        return true;
    }
//
/*
Test method for retrieving the element at head functionality of the storage class
 */
    public static boolean testContains()     {
        Storage<String> aStorage = new Storage<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(index,theStrings[index]);
        for(int i=0; i<theStrings.length; i++){
            if(aStorage.contains(theStrings[i])){
                continue;
            }
            else
                return false;
        }
        return true;
    }
//
/*
Test method for Remove at Index functionality of the storage class
 */
    public static boolean testRemove()     {
        Storage<String> aStorage = new Storage<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        if(aStorage.remove("a")){
            return true;
        }
        return false;

    }


    public static boolean testClear()     {
        Storage<String> aStorage = new Storage<String>();
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
Test method for Size functionality of the storage class
 */
    public static boolean testSize() {
        Storage<String> aStorage = new Storage<String>();
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
This method adds some values to the storage system and prints out the storage system
 */
    public static void exampleOfHowToUseIt( Storage<String> aStorage)   {
        aStorage.add("a");
        aStorage.add(0, "0");
        aStorage.add(aStorage.size(), "1");
        aStorage.add(aStorage.size() + 1, "2");
        System.out.println("aStorage: " + aStorage );
        Storage<String> bStrorage = new Storage<>();
        bStrorage.add("a");
        bStrorage.add("0");
        bStrorage.add("5");
        bStrorage.add("6");
        bStrorage.add("a");
        System.out.println("bStorage: " + bStrorage );
        aStorage.addAll(bStrorage);
        System.out.println("aStorage after addAll with bStorage: " + aStorage );
        aStorage.removeAll(bStrorage);
        System.out.println("aStorage after removeAll with bStorage:  " + aStorage );

    }

/*
This methods calls all the test methods and prints out an error message if any functionality failed.
 */
    public static void test(Storage<String> aStorage)   {
        if ( ! testAdd() )
            System.err.println("testAdd failed");
        if ( ! testAddAll() )
            System.err.println("testAddAll failed");
        if ( ! testRemoveAll() )
            System.err.println("testRemoveAll failed");
        if ( ! testToArray() )
            System.err.println("testToArray failed");
        if ( ! testContains() )
            System.err.println("testContains failed");
        if ( ! testRemove() )
            System.err.println("testRemove failed");
        if ( ! testClear() )
            System.err.println("testClear failed");
        if ( ! testSize() )
            System.err.println("testSize failed");

    }
/*
The main method is used to call the test and exampleOfHowToUseIt methods.
 */
    public static void main(String args[] )     {
        test(new Storage<String>());
        exampleOfHowToUseIt(new Storage<String>());


    }

}