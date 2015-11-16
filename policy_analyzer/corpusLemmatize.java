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
import java.util.HashSet;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

public class corpusLemmatize {
//not finished
	public static void main(String args[])
	{
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

		java.util.Iterator<String> it=stopwords.iterator();
		while(it.hasNext())
		{
			String s= it.next();
			System.out.println(s);
		}
	}
}
