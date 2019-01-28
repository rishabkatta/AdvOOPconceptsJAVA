/*
@author-name: Rishab Katta
@author-name: Akhil Karrothu
 */

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Find {
    static String fileName = new String();
    static String startingDir = new String();
    static String fileType = new String();
    static String lastModTime = new String();
    static String lastModSymbol = new String();
    static String fileLength = new String();
    static String fileLengthSymbol =new String();
    static ArrayList<String> lofBasedOnType = new ArrayList<String>();
    static ArrayList<String> lofBasedOnName = new ArrayList<String>();
    static ArrayList<String> lofBasedOnLMT = new ArrayList<String>();
    static ArrayList<String> lofBasedOnLength = new ArrayList<String>();
    static SimpleDateFormat sdf = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
    static Set<String> hs = new HashSet<>();
    static List<String> al = new ArrayList<>();

    /*
    This function filters all the files in the current directory based on the file length recursively.
     */

    public static void lengthFunction(String dirPath) throws NullPointerException{
        File insDir = new File(dirPath);
        File[] insFileArray = insDir.listFiles();
        if (insFileArray==null){return;}
        if (insFileArray.length>0) {
            for (File bFile : insFileArray) {
                if (!bFile.isDirectory()) {
                    if (fileLengthSymbol.equals("+")) {
                        if (bFile.length() > Integer.parseInt(fileLength)) {
                            lofBasedOnLength.add(insDir.getName() + "/" + bFile.getName() + " length: " + bFile.length());
                        }
                    } else if (fileLengthSymbol.equals("-")) {
                        if (bFile.length() < Integer.parseInt(fileLength)) {
                            lofBasedOnLength.add(insDir.getName() + "/" + bFile.getName() + " length: " + bFile.length());
                        }
                    }
                }
                if (bFile.isDirectory()) {
                    String aPath = bFile.getAbsolutePath();
                    lengthFunction(aPath);
                }
            }
        }
    }

    /*
    This function filters all the files in the current directory based on the file name recursively.
     */
    public static void nameFunction (String dirPath){
        File insDir = new File(dirPath);
        File[] insFileArray = insDir.listFiles();
        if (insFileArray==null){return;}
        for (File bFile : insFileArray) {
            if (!bFile.isDirectory()) {
                if (bFile.getName().matches(fileName)) {
                    lofBasedOnName.add(insDir.getName() + "/" + bFile.getName());
                }
            }
            if(bFile.isDirectory()){
                String aPath = bFile.getAbsolutePath();
                nameFunction(aPath);
            }
        }
    }

    /*
    This function filters all the files in the current directory based on the file type recursively.
     */
    public static void typeFunction(String dirPath){
        File curDir = new File(dirPath);
        File[] fileArray = curDir.listFiles();
        if (fileArray==null){return;}
        for (File aFile:fileArray) {
            if (fileType.equals("d")){
                if (aFile.isDirectory()){
                    lofBasedOnType.add(curDir.getName()+"/"+aFile.getName());
                }
            }
            else if(fileType.equals("f")){
                if(!aFile.isDirectory()){
                    lofBasedOnType.add(curDir.getName()+"/"+aFile.getName());
                }
                if (aFile.isDirectory()){
                    String aPath = aFile.getAbsolutePath();
                    typeFunction(aPath);
                }
            }
        }
    }
    /*
    This function filters all the files in the current directory based on the file last Modified Time recursively.
     */
    public static void lmtFunction(String dirPath){
        File insDir = new File(dirPath);
        File[] insFileArray = insDir.listFiles();
        if (insFileArray==null){return;}
        for (File bFile: insFileArray) {
            if (!bFile.isDirectory()) {
                if (lastModSymbol.equals("-")) {
                    if ((System.currentTimeMillis() - bFile.lastModified()) / (1000 * 60 * 60 * 24) < Long.parseLong(lastModTime)) {
                        lofBasedOnLMT.add(0,insDir.getName() + "/" + bFile.getName() + "-" + sdf.format(bFile.lastModified()));
                    }
                }
                if (lastModSymbol.equals("+")) {
                    if ((System.currentTimeMillis() - bFile.lastModified()) / (1000 * 60 * 60 * 24) > Long.parseLong(lastModTime)) {
                        lofBasedOnLMT.add(insDir.getName() + "/" + bFile.getName()+"-" + sdf.format(bFile.lastModified()));
                    }
                }
            }
            if (bFile.isDirectory()){
                String aPath = bFile.getAbsolutePath();
                lmtFunction(aPath);
            }
        }
    }

    /*
    This function is used to get the all files/directories in the root directory and call the above functions based on the command
    line arguments given
     */
    public static void doFindStuff() throws NullPointerException{

        File curDir = new File(startingDir);
        File[] fileArray = curDir.listFiles();
        if (!fileType.equals("")) {
            for (File aFile : fileArray) {
                if (fileType.equals("d")) {
                    if (aFile.isDirectory()) {
                        lofBasedOnType.add(aFile.getName());
                    }
                } else if (fileType.equals("f")) {
                    if (!aFile.isDirectory()) {
                        lofBasedOnType.add(curDir.getName() + "/" + aFile.getName());
                    }
                    if (aFile.isDirectory()) {
                        String aPath = aFile.getAbsolutePath();
                        typeFunction(aPath);
                    }
                }
            }
        }

        if(!fileName.equals("")) {
            for (File aFile : fileArray) {
                if (!aFile.isDirectory()) {
                    if (aFile.getName().matches(fileName)) {
                        lofBasedOnName.add(0, curDir.getName() + "/" + aFile.getName());
                    }
                }
                if (aFile.isDirectory()) {
                    String aPath = aFile.getAbsolutePath();
                    nameFunction(aPath);
                }
            }
        }
        if (!fileLength.equals("")) {
            for (File aFile : fileArray) {
                if (!aFile.isDirectory()) {
                    if (fileLengthSymbol.equals("+")) {
                        if (aFile.length() > Integer.parseInt(fileLength)) {
                            lofBasedOnLength.add(curDir.getName() + "/" + aFile.getName() + " length: " + aFile.length());
                        }
                    } else if (fileLengthSymbol.equals("-")) {
                        if (aFile.length() < Integer.parseInt(fileLength)) {
                            lofBasedOnLength.add(curDir.getName() + "/" + aFile.getName() + " length: " + aFile.length());
                        }
                    }
                }
                if (aFile.isDirectory()) {
                    String dirPth = aFile.getAbsolutePath();
                    lengthFunction(dirPth);
                }
            }
        }
        if (!lastModSymbol.equals("")) {
            for (File aFile : fileArray) {
                if (!aFile.isDirectory()) {
                    if (lastModSymbol.equals("-")) {
                        if ((System.currentTimeMillis() - aFile.lastModified()) / (1000 * 60 * 60 * 24) < Long.parseLong(lastModTime)) {
                            lofBasedOnLMT.add(0, (curDir.getName() + "/" + aFile.getName()) + " - " + sdf.format(aFile.lastModified()));
                        }
                    }
                    if (lastModSymbol.equals("+")) {
                        if ((System.currentTimeMillis() - aFile.lastModified()) / (1000 * 60 * 60 * 24) > Long.parseLong(lastModTime)) {
                            lofBasedOnLMT.add(curDir.getName() + "/" + aFile.getName() + " - " + sdf.format(aFile.lastModified()));
                        }
                    }
                }
                if (aFile.isDirectory()) {
                    String aPath = aFile.getAbsolutePath();
                    lmtFunction(aPath);
                }
            }
        }
    }
    /*
    This function is used to display the output to the user
     */
    public static void findJava() {
        if (!(lastModSymbol.equals("")) && fileType.equals("f")) {
            for (int i = 0; i < lofBasedOnLMT.size(); i++) {
                System.out.println(lofBasedOnLMT.get(i));
            }
            return;
        } else if (!(lastModSymbol.equals("")) && fileType.equals("d")) {
            for (int i = 0; i < lofBasedOnLMT.size(); i++) {
                String aString = lofBasedOnLMT.get(i).substring(0, lofBasedOnLMT.get(i).indexOf("/"));
                hs.add(aString);
            }
            return;
        } else if (!fileLength.equals("") && fileType.equals("f")) {
            for (int i = 0; i < lofBasedOnLength.size(); i++) {
                System.out.println(lofBasedOnLength.get(i));

            }
            return;
        } else if (!fileLength.equals("") && !(fileName.equals(""))) {
            for (int i = 0; i < lofBasedOnLength.size(); i++) {
                if (lofBasedOnLength.get(i).matches(".*"+fileName+".*")) {
                    System.out.println(lofBasedOnLength.get(i));
                }
            }
            return;
        }else if (!fileName.equals("") && !(lastModTime.equals(""))) {
            for (int i = 0; i < lofBasedOnLMT.size(); i++) {
                if (lofBasedOnLMT.get(i).matches(".*" + fileName + ".*")) {
                    System.out.println(lofBasedOnLMT.get(i));
                }
            }
            return;
        }else if (!fileLength.equals("")  && fileType.equals("d")) {
            for (int i = 0; i < lofBasedOnLength.size(); i++) {
                String aString = lofBasedOnLength.get(i).substring(0, lofBasedOnLength.get(i).indexOf("/"));
                hs.add(aString);

            }
            return;
        } else if (!(fileName.equals(""))&& fileType.equals("f")) {
            for (int i = 0; i < lofBasedOnName.size(); i++) {
                System.out.println(lofBasedOnName.get(i));

            }
            return;
        } else if (!(fileName.equals("")) && fileType.equals("d")) {
            for (int i = 0; i < lofBasedOnName.size(); i++) {
                String aString = lofBasedOnName.get(i).substring(0, lofBasedOnName.get(i).indexOf("/"));
                hs.add(aString);
            }
            return;
        }
        else if (!fileName.equals("")){
            for (int i = 0; i < lofBasedOnName.size(); i++) {
                System.out.println(lofBasedOnName.get(i));
            }
            return;
        }else if (!fileType.equals("")){
            for (int i = 0; i < lofBasedOnType.size(); i++) {
                System.out.println(lofBasedOnType.get(i));
            }
            return;
        }else if (!lastModSymbol.equals("")) {
            for (int i = 0; i < lofBasedOnLMT.size(); i++) {
                System.out.println(lofBasedOnLMT.get(i));
            }
            return;
        }else if (!fileLength.equals("")) {
            for (int i = 0; i < lofBasedOnLength.size(); i++) {
                System.out.println(lofBasedOnLength.get(i));
            }
            return;
        }
    }

    /*
    This function is used to get the command Line Arguments and store them in a specified variables.
     */
    public static void main(String[] args) {
        try {
            for (int i=0; i<args.length; i++){
                if(args[i].equals(".")){
                    startingDir = System.getProperty("user.dir");
                }
                else if(args[i].startsWith("-name")){
                    fileName = args[i+1];
                }
                else if (args[i].startsWith("-type")){
                    fileType = args[i+1];
                }
                else if(args[i].startsWith("-date")){
                    lastModTime = args[i+1].substring(1);
                    lastModSymbol = args[i+1].substring(0,1);
                }
                else if (args[i].startsWith("-length")){
                    fileLength = args[i+1].substring(1);
                    fileLengthSymbol = args[i+1].substring(0,1);
                }
                else if (new File(args[i]).isDirectory()){
                    startingDir = args[i];
                }
            }

            if (args.length==1){
                fileType="f";
            }
            doFindStuff();
            findJava();
            if (fileType.equals("d") && hs.size()>0) {
                al.addAll(hs);
                for (int i = 0; i < al.size(); i++) {
                    System.out.println(al.get(i));
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Arguments given are not proper");
        } catch (ArrayIndexOutOfBoundsException ae){
            System.out.println("Arguments given are not proper");
        }


    }
}
