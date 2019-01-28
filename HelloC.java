import java.rmi.*;

public class HelloC {
    public static void main(String args[] ) {
        String message = "";
        try {
            HelloInterface obj = (HelloInterface)Naming.
                    lookup("IamAhelloImplementationObject");

            System.out.println("args[0] = " + args[0] );
            message = obj.worksForALongTime(args[0] ,2);
            System.out.println(message);

        } catch (Exception e) {
        }
    }
}