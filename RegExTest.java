
import java.util.Scanner;
import java.io.File;
import java.util.regex.Pattern;


public class RegExTest {
    public static void testPattern(String regEx, String aString, String comment ) {
        if ( Pattern.matches(regEx, aString) )	{
            System.out.println("RegEx Test:");
            System.out.println("\tInput line: " + aString);
            System.out.println("\t" + regEx );
            System.out.println("\t" + comment);
        }
    }
    public static void testOwn1(String aString, String comment ) {
        if ( aString.indexOf('a') >= 0 )	{
            System.out.println("Own Test:");
            System.out.println("\tInput line: " + aString);
            System.out.println("\t" + comment);
        }
        else if (aString == ".") {
            System.out.println("Own Test:");
            System.out.println("\tInput line: " + aString);
            System.out.println("\t" + comment);
        }

        else if (aString.indexOf('.')>=0) {
            System.out.println("Own Test:");
            System.out.println("\tInput line: " + aString);
            System.out.println("\t" + comment);
        }


    }
    public static void main( String[] args ) {
        String aString;
        Pattern aPattern;

        aString = "Hello World.";
        //testPattern("[a]+", aString, "string has at least one a ");
        //testPattern("[ab]+", aString, "Word only consists of 'a's or 'b's");
        //testPattern("[^ab]+", aString, "Word does not consist of 'a's or 'b's");
        //testOwn1(aString, "string contains a dot");
        //testPattern("[(.)]+", aString, "string is a dot.");
        testPattern("[a-zA-Z]*[(.)]+[a-zA-Z]*", aString, "string contains a dot.");

    }
}