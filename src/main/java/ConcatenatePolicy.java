import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ConcatenatePolicy {

  public static void main(String args[]) {


    concatenateCorpus("C:\\Users\\venceWEN\\Desktop\\corpus");
  }

  public static void concatenateCorpus(String path) {
    StanfordLemmatizer sl = new StanfordLemmatizer();
    // List<String> l= sl.lemmatize(str);
    File file = new File(path);
    File[] tempList = file.listFiles();
    File printFile = new File("C:\\Users\\venceWEN\\Desktop", "corpus.txt");

    FileWriter fw = null;
    try {
      fw = new FileWriter(printFile.getAbsoluteFile());
    } catch (IOException e) {
      e.printStackTrace();
    }
    BufferedWriter bw = new BufferedWriter(fw);

    int nSec = 0;
    for (int i = 0; i < tempList.length; i++) {
      System.out.println("this is file: " + tempList[i].getName());
      BufferedReader reader = null;
      FileInputStream fis = null;
      InputStreamReader isr = null;
      try {
        fis = new FileInputStream(tempList[i]);
      } catch (FileNotFoundException e2) {
        // TODO Auto-generated catch block
        e2.printStackTrace();
      }

      try {
        isr = new InputStreamReader(fis, "UTF-8");
      } catch (UnsupportedEncodingException e2) {
        // TODO Auto-generated catch block
        e2.printStackTrace();
      }

      try {
        reader = new BufferedReader(isr);
        String tempString = null;
        //	int line = 1;
        //	int start=1;
        //	int stop=0;
        while ((tempString = reader.readLine()) != null) {
          //System.out.println("line " + line + ": " + tempString);
          //line++;
          String toWrite = PassHtmlUtils.filterHtml(tempString);

          if (tempString.contains("</SECTION>")) {
            nSec++;
            toWrite += "<xp>\n ";
          } else
            toWrite += " ";
          bw.write(toWrite);
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
    }

    System.out.println(nSec);
    try {
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


}
