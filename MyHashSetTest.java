/*
@author-name: Rishab Katta
@author-name: Akhil Karrothu
 */

public class MyHashSetTest {
    /*
    This test method is used to test the Add functionality of the MyhashSet storage system.
     */
    public static boolean testAdd()     {
        MyHashSet<String> aStorage = new MyHashSet<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        if(aStorage.add("a")==false){
            return rValue;
        }
        return false;
    }
    /*
    This test method is used to test the AddAll functionality of the MyhashSet storage system.
     */
    public static boolean testAddAll()     {
        MyHashSet<String> aStorage = new MyHashSet<String>();
        MyHashSet<String> bStorage = new MyHashSet<String>();
        String theStrings[] = { "a", "b", "c" };
        String theStrings2[] = { "b", "c", "d" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        for ( int index = 0; index < theStrings2.length; index ++ )
            bStorage.add(theStrings2[index]);
        aStorage.addAll(bStorage);
        if(aStorage.size()==4)
            return true;

        return false;
    }
    /*
    This test method is used to test the RemoveAll functionality of the MyhashSet storage system.
     */
    public static boolean testRemoveAll()     {
        MyHashSet<String> aStorage = new MyHashSet<String>();
        MyHashSet<String> bStorage = new MyHashSet<String>();
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
    This test method is used to test the toArray functionality of the MyhashSet storage system.
     */

    public static boolean testToArray()     {
        MyHashSet<String> aStorage = new MyHashSet<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        Object[] array = aStorage.toArray();
        for(int i=0; i<array.length; i++){
            if(aStorage.contains(array[i])){
                continue;
            }
            else
                return false;
        }
        return true;
    }
    //
/*
    This test method is used to test the Contains functionality of the MyhashSet storage system.
     */
    public static boolean testContains()     {
        MyHashSet<String> aStorage = new MyHashSet<String>();
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
    //
/*
Test method for Remove functionality of the MyhashSet storage system.
 */
    public static boolean testRemove()     {
        MyHashSet<String> aStorage = new MyHashSet<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        if(aStorage.remove("a")){
            return true;
        }
        return false;

    }
    /*
    This test method is used to test the Clear functionality of the MyhashSet storage system.
     */

    public static boolean testClear()     {
        MyHashSet<String> aStorage = new MyHashSet<String>();
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
        MyHashSet<String> aStorage = new MyHashSet<String>();
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
    This test method is used to test the ContainsAll functionality of the MyhashSet storage system.
     */
    public static boolean testContainsAll(){
        MyHashSet<String> aStorage = new MyHashSet<String>();
        MyHashSet<String> bStorage = new MyHashSet<String>();
        String theStrings[] = { "a", "b", "c" };
        String theStringsReturns[] = {"b","c"};
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        for ( int index = 0; index < theStringsReturns.length; index ++ )
            bStorage.add(theStringsReturns[index]);
        if(aStorage.containsAll(bStorage)){
            return true;
        }
        return false;
    }

    /*
    This test method is used to test the Equals functionality of the MyhashSet storage system.
     */
    public static boolean testEquals(){
        MyHashSet<String> aStorage = new MyHashSet<String>();
        MyHashSet<String> bStorage = new MyHashSet<String>();
        String theStrings[] = { "a", "b", "c" };
        String theStringsReturns[] = {"a","b","c"};
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        for ( int index = 0; index < theStrings.length; index ++ )
            bStorage.add(theStringsReturns[index]);
        if(aStorage.equals(bStorage)){
            return true;
        }
        return false;
    }
    /*
    This test method is used to test the hashCode functionality of the MyhashSet storage system.
     */
    public static boolean testHashCode(){
        MyHashSet<String> aStorage = new MyHashSet<String>();
        MyHashSet<String> bStorage = new MyHashSet<String>();
        String theStrings[] = { "a", "b", "c" };
        String theStringsReturns[] = {"a","b","c"};
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        for ( int index = 0; index < theStrings.length; index ++ )
            bStorage.add(theStringsReturns[index]);
        if(aStorage.hashCode()==bStorage.hashCode()){
            return true;
        }
        return false;
    }
    /*
    This test method is used to test the IsEmpty functionality of the MyhashSet storage system.
     */
    public static boolean testIsEmpty(){
        MyHashSet<String> aStorage = new MyHashSet<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            aStorage.add(theStrings[index]);
        aStorage.clear();
        if (aStorage.isEmpty())
            return rValue;
        else
            return false;
    }

/*
This methods calls all the test methods and prints out an error message if any functionality failed.
*/
    public static void test(MyHashSet<String> aStorage)   {
        if ( ! testAdd() )
            System.err.println("testAdd failed");
        if ( ! testAddAll() )
            System.err.println("testAddAll failed");
        if ( ! testContainsAll() )
            System.err.println("testContainsAll failed");
        if ( ! testRemoveAll() )
            System.err.println("testRemoveAll failed");
        if ( ! testContains() )
            System.err.println("testContains failed");
        if ( ! testClear() )
            System.err.println("testClear failed");
        if ( ! testSize() )
            System.err.println("testSize failed");
        if ( ! testEquals() )
            System.err.println("testEquals failed");
        if ( ! testHashCode() )
            System.err.println("testHashCode failed");
        if ( ! testIsEmpty() )
            System.err.println("testIsEmpty failed");
        if ( ! testRemove() )
            System.err.println("testRemove failed");
        if ( ! testToArray() )
            System.err.println("testToArray failed");

    }

    /*
    Main method is used to test various methods implemented in our MyHashSet Storage.
     */
    public static void main(String[] args) {

        test(new MyHashSet<String>());
        SetI<String> aSet = new MyHashSet<String>();
        SetI<String> bSet = new MyHashSet<String>();

        String[] aStrings = { "a", "b", "c" };
        String[] bStrings = { "A", "B", "C" };
        aSet.add(aStrings[0]); aSet.add(aStrings[1]);           // setup a, b
        bSet.add(bStrings[0]); bSet.add(bStrings[1]);           // setup A, B

        System.out.println("aSet = " + aSet );                  // --> a, b

        for (int index = 0; index < aStrings.length; index ++ ) {       // contans a and b, not c
            System.out.println("does " +
                    ( aSet.contains(aStrings[index]) ? "" : " not " ) + "contain: " +
                    aStrings[index] );
        }
        System.out.println("aSet = " + aSet );                  // --> a, b

        System.out.println("aSet.remove(aStrings[0]); = " + aSet.remove(aStrings[0]) ); // contains b
        System.out.println("aSet.remove(aStrings[2]); = " + aSet.remove(aStrings[2]) ); // can not remove x
        System.out.println("aSet = " + aSet );

        aSet.addAll(bSet);                                      // --> b, A, B
        System.out.println("aSet = " + aSet );


        aSet.add(null);                                         // --> b, A, B, null
        aSet.add("ab");
        System.out.println("aSet = " + aSet );
        System.out.println("aSet.remove(null); = " + aSet.remove(null) );       // can remove null
    }
}
