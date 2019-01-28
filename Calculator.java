/**

 * @author1 AkhilKarrothu

 * @author2 RishabKatta

 */


import java.util.Arrays;
import java.util.Vector;
import java.util.Stack;

public class Calculator {

    // See https://docs.oracle.com/javase/10/docs/api/java/util/Stack.html
    static Stack<Double> numberStack = new Stack<Double>();
    static Stack<String> operatorStack = new Stack<String>();
    // See https://docs.oracle.com/javase/10/docs/api/java/lang/String.html
    static String operators =  "+-%*/^" ;
    static String openingP[] = { "(", "[", "{" };
    static String closingP[] = { ")", "]", "}" };


    public static void main (String args []) {
        performCalculation("1", "*", "{", "2", "+", "3", "-",
                "[", "1", "*", "(", "2", "-", "1", ")", "]", "+", "3", "}");
        performCalculation("2", "+", "[", "(", "3", "-", "6", ")", "/", "5", "]");
        performCalculation("1", "+", "(", "2", "+", "3", ")", "*", "3");
        performCalculation("2", "^", "3","^", "4");
        performCalculation("(", "2", "^", "3", ")", "^", "4");
    }

    public static void performCalculation (String ... valuesAndOperators) {
        String normalString = new String();

        for (String valuesAndOperator : valuesAndOperators) {

            normalString += valuesAndOperator;
        }

        while (normalString.indexOf('(') > 0) {
            int c1 = normalString.indexOf('(');
            int c2 = normalString.indexOf(')', c1);
            String s1 = normalString.substring(c1 + 1, c2);
            String s2 = normalString.substring(c1, c2 + 1);
            Vector<String> e = new Vector<String>(Arrays.asList(s1.split("")));
            double result = calculate(e);
            String replace1 = new String();
            replace1 = Double.toString(result);
            normalString.replace(s2, replace1);
        }

        while (normalString.indexOf('[') > 0) {
            int c21 = normalString.indexOf('[');
            int c22 = normalString.indexOf(']', c21);
            String s21 = normalString.substring(c21 + 1, c22);
            String s22 = normalString.substring(c21, c22 + 1);
            Vector<String> e2 = new Vector<String>(Arrays.asList(s21.split("")));
            double result2 = calculate(e2);
            String replace21 = new String();
            replace21 = Double.toString(result2);
            normalString.replace(s22, replace21);
        }
        while (normalString.indexOf('{') > 0) {
            int c31 = normalString.indexOf('}');
            int c32 = normalString.indexOf(']', c31);
            String s31 = normalString.substring(c31 + 1, c32);
            String s32 = normalString.substring(c31, c32 + 1);
            Vector<String> e3 = new Vector<String>(Arrays.asList(s31.split("")));
            double result3 = calculate(e3);
            String replace31 = new String();
            replace31 = Double.toString(result3);
            normalString.replace(s32, replace31);


        }
    }

    // See https://docs.oracle.com/javase/8/docs/technotes/guides/language/varargs.html
 /*
        Vector<String> aLine = new Vector<String>();
        for ( String valuesAndOperator: valuesAndOperators )	{
            aLine.add(valuesAndOperator);
            System.out.print(valuesAndOperator + " ");
        }
        System.out.println(" =  " + calculate(aLine) );
    }
    /** drives the calculation and returns the result
     */
    public static double calculate (Vector<String> inputLine) {
        while ( inputLine.size() >= 1 )	{
            if ( operator( inputLine.firstElement() )	)
                performOperator(inputLine.firstElement());
            else
                performNumber(inputLine.firstElement());

            inputLine.removeElementAt(0);
        }
        while ( !  operatorStack.empty() )	{
            if ( numberStack.size() > 1 )
                evaluate();
            else	{
                System.out.println("dangling operand ....");
                System.out.println(numberStack);
                System.exit(1);

            }
        }

        return numberStack.pop();
    }

    /** perform the required operation based on precedence of the operators on the stack
     */
    public static boolean operator (String op) {
        return ( operators.indexOf(op) >= 0 );
    }

    /** deteremence a precedence level for the operator
     */
    public static int precedence (String op) {
        return operators.indexOf(op);
    }

    /** perform the required operation based on precedence on the stack
     */
    public static void performOperator (String op) {
        while (! operatorStack.empty()  &&
                (  precedence(op) > precedence(operatorStack.peek() ) )
        )
            evaluate();
        operatorStack.push(op);
    }

    /** pushes the number on the number stack
     */
    public static void performNumber (String number) {
        numberStack.push(Double.valueOf(number));
    }

    /** get the number of the stack, if a number is available, else RIP
     */
    public static double  getNumber () {
        if ( numberStack.empty() ){
            System.out.println("not enough numbers ...");
            System.exit(2);
        }
        return numberStack.pop();
    }

    /** perform the required ovperation based on the operator in the stack
     */
    public static void evaluate () {
        String currentOp = operatorStack.pop();
        double right = getNumber();
        double left = getNumber();
        if ( currentOp.equals("+") )
            numberStack.push( left + right );
        else if ( currentOp.equals("-") )
            numberStack.push( left - right );
        else if ( currentOp.equals("*") )
            numberStack.push( left * right );
        else if ( currentOp.equals("%") )
            numberStack.push( left % right );
        else if ( currentOp.equals("/") )
            numberStack.push( left / right );
        else if ( currentOp.equals("^") )
            numberStack.push( Math.pow(left , right ) );
        else
            System.out.println("Unknown Operator");
    }
}
