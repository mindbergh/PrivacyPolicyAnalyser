package policy_analyzer;

import java.io.*;
import java.util.*;

/**
 * Created by wenvence on 11/19/15.
 */
public class GenTrainSet {
    // used to generate the TrainSet
    // format:
    public static void main(String args[])
    {
        StanfordLemmatizer sl=new StanfordLemmatizer();
        //List<String> l= sl.lemmatize(str);
        File printFile= new File("XTrain");
        FileWriter fw=null;
        try {
            fw = new FileWriter(printFile.getAbsoluteFile());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        File file= new File("corpus.txt");
        BufferedReader reader = null;
        FileInputStream fis=null;
        InputStreamReader isr=null;
        HashMap<String,Integer> voc=getVocReader();
        WriteLabeledVoc(voc);

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
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
                List<String> l= sl.lemmatize(tempString);
                for (int i=0;i<l.size();i++) {
                    String lowerWord= l.get(i).toLowerCase();
                    if( voc.containsKey(lowerWord) ) {
                        Integer num=voc.get(lowerWord);
                        num+=1;
                        String toWrite=  num.toString();
                        bw.write(toWrite+" ");
                    }
                }
                if( tempString.contains("<endsession>") )
                {
                    bw.write('\n');
                }
                if( tempString.contains("<enddoc>") )
                {
                    bw.write("<enddoc>"+'\n');
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    bw.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static void WriteLabeledVoc(HashMap<String,Integer> voc)
    {
        File printFile= new File("LabeledVoc.txt");
        FileWriter fw=null;
        try {
            fw = new FileWriter(printFile.getAbsoluteFile());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        Set s=voc.entrySet();
        java.util.Iterator it=s.iterator();
        while(it.hasNext())
        {
            Map.Entry<String,Integer> m=(Map.Entry<String,Integer>)it.next();
            try {
                    bw.write(m.getKey() + " "+ (m.getValue()+1)+"\n");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // System.out.println(m.getKey());
            // System.out.println(m.getValue());
        }

        try {
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static HashMap<String,Integer> getVocReader() {
        File file = new File("voc.txt");
        BufferedReader reader = null;
        FileInputStream fis = null;
        InputStreamReader isr = null;
        HashMap<String,Integer> voc= new HashMap<String,Integer>();
        int count=0;

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
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
                String word= tempString;
                voc.put(word,count);
                count++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        reader = new BufferedReader(isr);

        return voc;
    }

}
