/*
@author-name:Rishab Katta
@author-name:Akhil karrothu
 */

import java.io.*;
import java.util.ArrayList;
import java.util.regex.*;
import java.util.stream.Collectors;


public class Grep {
    static ArrayList<Character> commandArray = new ArrayList<Character>();
    static String pattern = new String();
    static ArrayList<String> fileNameArray = new ArrayList<String>();
    static int count =0;
    static ArrayList<Integer> countArray = new ArrayList<Integer>();


    /*
    Lfunc returns the filename when it encounters a first match of a line in the file with pattern
     */
    public static String lfunc(String filename, String pt1) throws IOException{

        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader br  = new BufferedReader(fr);
        String line=null;
        String ptfinal = ".*" + pt1 + ".*";

//        while ((line = br.readLine()) != null){
//            if(Pattern.matches(ptfinal, line)){
//                return filename;
//            }
//        }
        boolean bool = br.lines().anyMatch(str -> str.matches(ptfinal));
        if(bool==true){
            return filename;
        }
        return "";
    }
    /*
    cfunc returns the counts of pattern matches in all the lines of a file
     */
    public static int cfunc(String filename, String pt1) throws IOException{

        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader br  = new BufferedReader(fr);
        String line=null;
        String ptfinal = ".*" + pt1 + ".*";
        count=0;

//        while ((line = br.readLine()) != null){
//            if(Pattern.matches(ptfinal, line)){
//                count +=1;
//            }
//        }

        count = (int)br.lines().filter(str -> str.matches(ptfinal)).count();
        return count;
    }
    /*
    wfunc returns lines with whole word matches in the file passed as the argument
     */
    public static ArrayList<String> wfunc(String filename, String pt1) throws IOException{

        ArrayList<String> matchedlines = new ArrayList<>();
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader br  = new BufferedReader(fr);
        String line=null;
        String ptfinal = "(.)*\\b" + pt1 + "\\b(.)*";
        count=0;

//        while ((line = br.readLine()) != null){
//            if(Pattern.matches(ptfinal, line)){
//                count +=1;
//                matchedlines.add(line);
//            }
//        }
        matchedlines = br.lines().filter(str -> str.matches(ptfinal)).collect(Collectors.toCollection(ArrayList::new));
        count = matchedlines.size();
        countArray.add(count);
        return matchedlines;
    }
    /*
    qfunc returns nothing and exits with 0 if a match is found else exits with the exitcode 1.
     */
    public static void qfunc(String filename, String pt1) throws IOException{

        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader br  = new BufferedReader(fr);
        String line=null;
        String ptfinal = ".*" + pt1 + ".*";


//        while ((line = br.readLine()) != null){
//            if(Pattern.matches(ptfinal, line)){
//                System.exit(0);
//            }
//        }
//        System.exit(1);
        boolean bool = br.lines().anyMatch(str -> str.matches(ptfinal));
        if (bool==true){
            System.exit(0);
        }
        System.exit(1);

    }

    /*
    globalRegExPrint function is used to used to call all the functions like cfunc, wfunc etc based on the commands
    given in the command line arguments. It stores the results in the ArrayLists and prints them accordingly.
     */
    public static void globalRegExPrint() throws IOException{
        ArrayList<String> resultfilearray = new ArrayList<String>();
        ArrayList<String> resultcountarray = new ArrayList<String>();
        ArrayList<String> resultlinearray = new ArrayList<String>();


        for(int i=0; i<fileNameArray.size(); i++){
            for (int j=0; j<commandArray.size(); j++){
                if (commandArray.get(j)=='c'){
                    int c = cfunc(fileNameArray.get(i),pattern);
                    int li = fileNameArray.get(i).lastIndexOf("/");
                    resultcountarray.add(fileNameArray.get(i).substring(li+1) + ":" + c);
                }
                else if (commandArray.get(j)=='l'){
                    String resString = lfunc(fileNameArray.get(i),pattern);
                    int li = resString.lastIndexOf("/");
                    resultfilearray.add(resString.substring(li+1));
                }
                else if (commandArray.get(j)=='w'){
                    ArrayList<String> resArraylist = wfunc(fileNameArray.get(i),pattern);
                    for (int p=0; p<resArraylist.size(); p++){
                        int li = fileNameArray.get(i).lastIndexOf("/");
                        resultlinearray.add(fileNameArray.get(i).substring(li+1) + ":" + resArraylist.get(p));
                    }
                }
                else if (commandArray.get(j)=='q'){
                    qfunc(fileNameArray.get(i),pattern);
                }
            }
        }
        if (commandArray.size()==1 && commandArray.get(0)=='c'){
            for(int i =0; i<resultcountarray.size(); i++){
                System.out.println(resultcountarray.get(i));
            }
        }
        if(commandArray.size()==1 && commandArray.get(0)=='w') {
            for (int i = 0; i < resultlinearray.size(); i++) {
                System.out.println(resultlinearray.get(i));
            }
        }
        if(commandArray.size()==1 && commandArray.get(0)=='l') {
            for (int i = 0; i < resultfilearray.size(); i++) {
                System.out.println(resultfilearray.get(i));
            }
        }
        if(commandArray.size()==2 && (commandArray.get(0)=='w'|| commandArray.get(0)=='c')
                && (commandArray.get(1)=='w'|| commandArray.get(1)=='c')) {
            for (int i = 0; i < countArray.size(); i++) {
                System.out.println(fileNameArray.get(i).substring(fileNameArray.get(i).lastIndexOf("/")+1) + ":" + countArray.get(i));
            }
        }
        if(commandArray.size()==2 && (commandArray.get(0)=='w'|| commandArray.get(0)=='c')
                && (commandArray.get(1)=='l')) {
            for (int i = 0; i < countArray.size(); i++) {
                System.out.println(resultfilearray.get(i));
            }
        }
        if(commandArray.size()==2 && (commandArray.get(0)=='l')
                && (commandArray.get(1)=='w'|| commandArray.get(1)=='c')) {
            for (int i = 0; i < countArray.size(); i++) {
                System.out.println(resultfilearray.get(i));
            }
        }
        if(commandArray.size()==3 && (commandArray.get(0)=='w'|| commandArray.get(0)=='c' || commandArray.get(0)=='l')
                && (commandArray.get(1)=='w'|| commandArray.get(1)=='c' || commandArray.get(1)=='l') &&
                (commandArray.get(2)=='w'|| commandArray.get(2)=='c' || commandArray.get(2)=='l') ) {
            for (int i = 0; i < countArray.size(); i++) {
                System.out.println(resultfilearray.get(i));
            }
        }

    }

    /*
    Main is used to take the command line arguments and put them in seperate arrays for making operations on them.
     */
    public static void main(String[] args) throws IOException {
        for (int i=0; i<args.length; i++){
            if (args[i].startsWith("-")){
                commandArray.add(args[i].charAt(1));
            }
            else if(args[i].endsWith(".txt")){
                fileNameArray.add(args[i]);
            }
            else {
                pattern = args[i];
            }
        }
        globalRegExPrint();

    }
}
