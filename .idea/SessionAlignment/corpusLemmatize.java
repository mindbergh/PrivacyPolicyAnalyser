package SessionAlignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;
import SessionAlignment.StanfordLemmatizer;
public class corpusLemmatize {
//not finished
	public static void main(String args[])
	{
		HashSet<String> voc= new HashSet<String>();
		
		StanfordLemmatizer sl=new StanfordLemmatizer();
		//List<String> l= sl.lemmatize(str);
		
		  File file= new File("C:\\Users\\venceWEN\\Desktop\\corpus.txt");
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
				while ((tempString = reader.readLine()) != null) {
					List<String> l= sl.lemmatize(tempString);
					for (int i=0;i<l.size();i++)
						voc.add(l.get(i));
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
			
			File printFile= new File("C:\\Users\\venceWEN\\Desktop","voc.txt");
			FileWriter fw=null;
			try {
				fw = new FileWriter(printFile.getAbsoluteFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedWriter bw = new BufferedWriter(fw);
			java.util.Iterator<String> it= voc.iterator();
			while(it.hasNext())
		       {
		           String o=it.next();
		           
		           try {
					bw.write(o+"\n");
		           } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		           }
		           System.out.println(o);
		       }
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(voc.size());
			
	}
}
