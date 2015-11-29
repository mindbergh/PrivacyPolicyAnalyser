package policy_analyzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;

import javax.swing.text.html.HTMLDocument.Iterator;

public class ConstructVocabulary {
    //not finished
    public static void main(String args[])
    {
        HashMap<String,Integer> voc= new HashMap<String,Integer>();

        StanfordLemmatizer sl=new StanfordLemmatizer();
        //List<String> l= sl.lemmatize(str);

        File file= new File("corpus.txt");
        BufferedReader reader = null;
        FileInputStream fis=null;
        InputStreamReader isr=null;
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
            HashSet<String> docFlag= new HashSet<String> ();
            while ((tempString = reader.readLine()) != null) {
                List<String> l= sl.lemmatize(tempString);
                for (int i=0;i<l.size();i++) {
                    String lowerWord= l.get(i).toLowerCase();
                    if(docFlag.contains(lowerWord)) {
                        //System.out.println(l.get(i)+" show twice in a doc") ;
                    }
                    else{
                            if (voc.containsKey(lowerWord)) {
                                voc.put(lowerWord, voc.get(lowerWord) + 1);
                                docFlag.add(lowerWord );
                              //  System.out.println(l.get(i)+" first show in a doc====not first in the corpus");
                            } else {
                                voc.put(lowerWord, 1);
                                docFlag.add(lowerWord );
                              //  System.out.println(l.get(i)+" first show in the corpus");
                            }
                    }
                }

                if( tempString.contains("<enddoc>") )
                {
                    docFlag.clear();
                }
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
//load stopwords===========================
        File sw= new File("stopwords");
        BufferedReader reader2 = null;
        FileInputStream fis2=null;
        InputStreamReader isr2=null;
        HashSet<String> stopwords= new HashSet<String>();

        try {
            fis2 = new FileInputStream(sw);
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        try {
            isr2 = new InputStreamReader(fis2,"UTF-8");
        } catch (UnsupportedEncodingException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        try {
            reader2 = new BufferedReader(isr2);
            String tempString = null;
            while ((tempString = reader2.readLine()) != null) {
                stopwords.add(tempString);
            }
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//=============finish loading


        File printFile= new File("voc.txt");
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
        int nVoc=0;
        while(it.hasNext())
        {
            Map.Entry<String,Integer> m=(Map.Entry<String,Integer>)it.next();
            try {
                if (m.getValue() > 10 && !stopwords.contains(m.getKey())) {
                    bw.write(m.getKey() + "\n");
                    nVoc++;
                }
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
        System.out.println(nVoc);

    }
}
