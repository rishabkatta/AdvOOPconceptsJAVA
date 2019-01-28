/**

 * @author1 AkhilKarrothu

 * @author2 RishabKatta

 */

public class Sticks {



    /**

     * @param args

     */

    static int[] stickLengths = { 1, 2, 3, 5, 8 };

    static int[] unknowStickLengths = { 1, 5, 6, 7, 8, 9 };


/*Method iterates from 1 to (sticklengths.length-1) and calls the method doTestLength2*/
    public static void doTestLength(int num) {



        for(int j=1;j<stickLengths.length;j++){

            if(doTestLength2(num,j))

                return;

        }

        System.out.println("No");

    }



    /**

     *

     * @param num

     * @param iterate

     * @return

     */

/*Method checks if the unknown stick can be made by the given stick lengths and prints out the stick lengths used to
make the unknown stick. Returns False if unknown stick length can't be made
 */
    public static boolean doTestLength2(int num,int iterate) {

        int i=0;

        int[] store = new int[stickLengths.length];

        for (int j = stickLengths.length-iterate; j>=0; j-- ){

            if (stickLengths[j] <= num){

                num = num - stickLengths[j];

                store[i++]=stickLengths[j];

            }

        }



        if(num==0) {

            System.out.print("YES;");

            System.out.print("\t( ");

            for(int p=0;p<i;p++){

                System.out.print(store[p]+"  ");

            }

            System.out.print(" )"+"\n");

            return true;

        }

        return false;

    }

/*Main method used to call the method doTestLength with the arg unknownStickLength with index from 0 to length of the
array-1
 */

    public static void main( String[] arguments ) {

        for ( int index = 0; index < unknowStickLengths.length; index ++ ){

            System.out.print(unknowStickLengths[index]+"\tinch"+"\t");

            doTestLength(unknowStickLengths[index]);



        }

    }



}