/*
@author-name: Rishab katta
@author-name: Akhil karrothu
 */
import java.io.*;
import java.util.zip.*;
public class PiEvenOddImprovement {

    /*
    This function reads characters from the file converts them to integers and checks if they are even or odd.
     */
    public static void piEvenOddCount(BufferedInputStream bis) {
        int ch;
        int oddcount = 0;
        int evencount = 0;
        try {
            while ((ch = bis.read()) != -1) {
                char aChar = (char) ch;
                if (Character.isDigit(aChar)) {
                    int a = Integer.parseInt(String.valueOf(aChar));
                    if (a % 2 == 0) {
                        evencount += 1;
                    } else {
                        oddcount += 1;
                    }
                }
            }
            if (evencount==0 && oddcount==0){
                throw new NoNumbersException("There are no numbers in the file!");
            }
        }
        catch (IOException e) {}
        catch (NumberFormatException e) {}
        catch (NoNumbersException nn){
            System.out.println(nn.getMessage());
        } finally {
            System.out.println("odd: " + oddcount);
            System.out.println("even: " + evencount);
        }

    }
    /*
    Main is used to get the file paths either from the command line or from the console, create file objects and pass
    them to different streams and send BufferedInputStream object to PievenOddCount function.
     */
    public static void main(String[] args) throws IOException {

        String fname =null;
        if (args.length==1){
            fname = args[0];
            File piFile = new File(fname);
            try {
                if (piFile.length() == 0) {
                    throw new EmptyFileException("File is Empty!");
                }
            }
            catch (EmptyFileException ef){
                System.out.println(ef.getMessage());
                return;
            }
            if(fname.endsWith(".gz")){
                FileInputStream fis = new FileInputStream(piFile);
                GZIPInputStream gis = new GZIPInputStream(fis);
                BufferedInputStream bisgz = new BufferedInputStream(gis);
                long startTime = System.currentTimeMillis();
                piEvenOddCount(bisgz);
                System.out.println("Time: " + (System.currentTimeMillis()-startTime)/1000 +" Secs" );
            }
            else {
                FileInputStream fisnogz =new FileInputStream(piFile);
                BufferedInputStream bisnogz  = new BufferedInputStream(fisnogz);
                long startTime = System.currentTimeMillis();
                piEvenOddCount(bisnogz);
                System.out.println("Time: " + (System.currentTimeMillis()-startTime)/1000 +" Secs" );
            }
        }
        else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter a file path");
            fname = br.readLine();
            File piFile = new File(fname);
            try {
                if (piFile.length() == 0) {
                    throw new EmptyFileException("File is Empty!");
                }
            }
            catch (EmptyFileException ef){
                System.out.println(ef.getMessage());
                return;
            }
            FileInputStream fin = null;
            try {
                fin = new FileInputStream(piFile);
                BufferedInputStream bin = new BufferedInputStream(fin);
                long startTime = System.currentTimeMillis();
                piEvenOddCount(bin);
                System.out.println("Time: " + (System.currentTimeMillis()-startTime)/1000 +" Secs" );

            } catch (FileNotFoundException fe) {
                System.err.println("File not found");
                return;
            }
        }
    }
}
/*
EmptyFileException class is used to raise exceptions when the file is empty.
 */
class EmptyFileException extends Exception
{
    public EmptyFileException(){}
    public EmptyFileException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
/*
NoNumbersException class is used to raise exceptions when the file has no numbers in it.
 */
class NoNumbersException extends Exception
{
    public NoNumbersException(){}
    public NoNumbersException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
