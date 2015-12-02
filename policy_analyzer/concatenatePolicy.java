package policy_analyzer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import policy_analyzer.PassHtmlUtils;
public class concatenatePolicy {
	// this method is used to generate concatenatedCorpus
  public static void main( String args[])
  {
	concatenateCorpus("corpus");
  }
  
  public static void concatenateCorpus(String path)
  {
	  StanfordLemmatizer sl=new StanfordLemmatizer();
	  List<String> l;
	  File file=new File(path);
	  File[] tempList = file.listFiles();
	  File printFile= new File("corpus.txt");

	FileWriter fw=null;
	try {
		fw = new FileWriter(printFile.getAbsoluteFile());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   BufferedWriter bw = new BufferedWriter(fw);
	   
	   int nSec=0;
	  //int i=0;
	  for(int i=0;i<tempList.length;i++)
	  {
		  System.out.println("this is file: "+ tempList[i].getName());
		  BufferedReader reader = null;
		  FileInputStream fis=null;
		  InputStreamReader isr=null;
			try {
				fis = new FileInputStream(tempList[i]);
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
				int countWords=0;
				String sessionToWrite="";
				while ((tempString = reader.readLine()) != null) {
					String toWrite=PassHtmlUtils.filterHtml(tempString);
					if( tempString.contains("</SECTION>") )
					{
						l= sl.lemmatize(sessionToWrite);
						countWords=l.size();
						//System.out.println("ENDOFSESSION:"+sessionToWrite);
						//System.out.println("Session Words:"+countWords);
						sessionToWrite+= "<endsession>\n ";
						if(countWords<10) {
							countWords = 0;
							sessionToWrite="";
							continue;
						}
						else {
							countWords=0;
							nSec++;
							bw.write(sessionToWrite);
							sessionToWrite="";
						}
					}
					else {
						sessionToWrite+=(toWrite+" ");
					}
				}
				bw.write("<enddoc>\n");
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
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  
  }


}
