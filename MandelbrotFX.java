import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MandelbrotFX extends Application {

    WritableImage mandelBrotSetImage;
    final int IMG_WIDTH 	= 800;
    final int IMG_HEIGHT 	= 800;
    long milliSeconds;
    static int noofthreads;

    public void init()  {

        milliSeconds = System.currentTimeMillis();
    }
    /*
    If commandline arguments are given then prints out the number of cores based on them else gets the number of cores
    supported by the machine and prints it out.
     */
    public void end(String s)   {
        System.err.println(s + ":       " + ( System.currentTimeMillis() - milliSeconds) + "ms" );
        if (getArgs()>0){
            System.err.println(" # of cores" +   ":       " +
                    getArgs() );
        }else {
            System.err.println(" # of cores" + ":       " +
                    Runtime.getRuntime().availableProcessors());
        }
    }
    /*
    This function returns the no of threads based on the user enter CommandLine Arguments
     */
    public static int getArgs(){
        return noofthreads;
    }

    public void start(Stage theStage) {
        init();
        MandelbrotSet aMandelbrotSet = new MandelbrotSet(IMG_WIDTH, IMG_HEIGHT);


        mandelBrotSetImage = aMandelbrotSet.createImage();
        end("Multiple Thread MandelbrotSet Test");


        ImageView aImage = new ImageView();
        aImage.setImage(mandelBrotSetImage);

        StackPane root = new StackPane();
        root.getChildren().add(aImage);

        Scene scene = new Scene(root, IMG_WIDTH, IMG_HEIGHT);

        theStage.setTitle("Mandelbrot Set");
        theStage.setResizable(false);
        theStage.setScene(scene);
        theStage.show();
    }

    /*
    Stores no of threads based on CLA given.
     */
    public static void main(String[] args) {
        if(args.length != 1) {
            noofthreads = Runtime.getRuntime().availableProcessors();
        }
        else {
            noofthreads = Integer.parseInt(args[0]);
        }
        launch(args);


    }
}


class MandelbrotSet extends Thread {

    private static final int    MAX_COLORS 	= 256;
    private static final double BOUNDERY = 1000;
    private static int    width;
    private static int    height;
    private static WritableImage mandelBrotSetImage;
    private static PixelWriter aPixelWriter;
    private static final Color[] colors = new Color[MAX_COLORS];
    private static double minR  = -2.4;
    private static double maxR  = 0.9;
    private static double minI  = -1.3;
    private static double maxI  = 1.28;
    private static MandelbrotSet[] allThreads;
    static int noofthreads;

    private int num;


    static {
        for (int index = 0; index < colors.length; index++) {
            colors[index] = Color.RED.interpolate(Color.BLUE, (( 1.0 / colors.length) * index) );
        }
    }

    /*
    changed the number of arguments to distinguish threads.
     */
    public MandelbrotSet(int num){
        this.num = num;

    }
    /*
    This constructor is used to create and start mutiple threads based on the commandline args or number of cores
     */
    public MandelbrotSet(int width,int height) {
        this.width = width;
        this.height = height;
        mandelBrotSetImage = new WritableImage(width, height);
        aPixelWriter = mandelBrotSetImage.getPixelWriter();
        if ( allThreads == null ){
            if(MandelbrotFX.getArgs()>0){
                noofthreads = MandelbrotFX.getArgs();
            }
            else {
                noofthreads = Runtime.getRuntime().availableProcessors();
            }
            allThreads = new MandelbrotSet[noofthreads];
            for(int i = 0; i < noofthreads; i++)
                allThreads[i] = new MandelbrotSet(i);
            for(int i = 0; i < noofthreads; i++)
                allThreads[i].start();

        }
    }
    private Color getColor(int count) {

        return count >= colors.length ?  Color.BLACK : colors[count];
    }
    private int calc(double re, double img ) {
        int    counter = 0;
        double length;
        double aComplexNumberRe = 0;
        double aComplexNumberImg = 0;
        double real = 0;
        double imaginary = 0;

        do {
            real       =  aComplexNumberRe * aComplexNumberRe -
                    aComplexNumberImg * aComplexNumberImg;
            imaginary  = aComplexNumberRe *  aComplexNumberImg +
                    aComplexNumberImg *  aComplexNumberRe;
            aComplexNumberRe   = real;
            aComplexNumberImg  = imaginary;
            aComplexNumberRe   += re;
            aComplexNumberImg  += img;
            length = aComplexNumberImg * aComplexNumberImg +
                    aComplexNumberRe * aComplexNumberRe;
            counter++;
        } while (counter < MAX_COLORS && ( length < BOUNDERY ) );
        return counter;
    }
    public Color determineColor(int x, int y)	{
        double re = (minR * (width - x) + x * maxR) / width;
        double img = (minI * (height - y) + y * maxI) / height;
        return getColor(calc(re, img));
    }
    /*
    run method determines the color of different pixels based on the thread number(num) and number of cores.
     */
    public void run() {
        int rangeofthread = 800/noofthreads;
        for (int x = 0; x < rangeofthread; x++) {
            for (int y = 0; y < height; y++) {
                aPixelWriter.setColor((num*rangeofthread)+x, y, determineColor((num*rangeofthread)+x, y));

            }
        }
    }
    /*
    this function returns the final image once all the colors of individual pixels have been determined.
     */
    public WritableImage createImage()	{


        /*mandelBrotSetImage = new WritableImage(width, height);
        aPixelWriter = mandelBrotSetImage.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                aPixelWriter.setColor(x, y, determineColor(x, y));
            }
        }*/
        return mandelBrotSetImage;
    }


}