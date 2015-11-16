package policy_analyzer;

import java.io.*;

/**
 * Created by wenvence on 11/15/15.
 */
public class EliminateWords {
    public static void main(String args[])
    {
        File file= new File("voc.txt");
        BufferedReader reader = null;
        FileInputStream fis=null;
        InputStreamReader isr=null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch blockvoc
            e2.printStackTrace();
        }

        try {
            isr = new InputStreamReader(fis,"UTF-8");
        } catch (UnsupportedEncodingException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        try {
            reader = new BufferedReader(isr);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                System.out.print(tempString);
               // ifDFLessThan10(tmpString);
            }
        }catch (IOException e){
                e.printStackTrace();
            }
    }

}
