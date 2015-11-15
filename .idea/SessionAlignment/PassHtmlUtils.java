package SessionAlignment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassHtmlUtils {
	private final static String regxpForHtml = "<([^>]*)>"; // ����������<��ͷ��>��β�ı�ǩ

	private final static String regxpForImgTag = "<]*)\\s<\\s*img\\s+([^>]*)\\s*>"; // �ҳ�IMG��ǩ

	private final static String regxpForImaTagSrcAttrib = "src=\"([^\"]+)\""; // �ҳ�IMG��ǩ��SRC����
	/**
	 * 
	 * �������ܣ��滻�����������ʾ
	 * <p>
	 * 
	 * @param input
	 * @return String
	 */
	public String replaceTag(String input) {
		if (!hasSpecialChars(input)) {
			return input;
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i <= input.length() - 1; i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("<");
				break;
			case '>':
				filtered.append(">");
				break;
			case '"':
				filtered.append("\"");
				break;
			case '&':
				filtered.append("&");
				break;
			default:
				filtered.append(c);
			}

		}
		return (filtered.toString());
	}

	/**
	 * 
	 * �������ܣ��жϱ���Ƿ����
	 * <p>
	 * 
	 * @param input
	 * @return boolean
	 */
	public boolean hasSpecialChars(String input) {
		boolean flag = false;
		if ((input != null) && (input.length() > 0)) {
			char c;
			for (int i = 0; i <= input.length() - 1; i++) {
				c = input.charAt(i);
				switch (c) {
				case '>':
					flag = true;
					break;
				case '<':
					flag = true;
					break;
				case '"':
					flag = true;
					break;
				case '&':
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 
	 * �������ܣ�����������"<"��ͷ��">"��β�ı�ǩ
	 * <p>
	 * 
	 * @param str
	 * @return String
	 */
	public static String filterHtml(String str) {
		Pattern pattern = Pattern.compile(regxpForHtml);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 
	 * �������ܣ�����ָ����ǩ
	 * <p>
	 * 
	 * @param str
	 * @param tag
	 *            ָ����ǩ
	 * @return String
	 */
	public static String filterHtmlTag(String str, String tag) {
		String regxp = "<\\s*" + tag + "]*)\\s<\\s+([^>]*)\\s*>";
		Pattern pattern = Pattern.compile(regxp);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 
	 * �������ܣ��滻ָ���ı�ǩ
	 * <p>
	 * 
	 * @param str
	 * @param beforeTag
	 *            Ҫ�滻�ı�ǩ
	 * @param tagAttrib
	 *            Ҫ�滻�ı�ǩ����ֵ
	 * @param startTag
	 *            �±�ǩ��ʼ���
	 * @param endTag
	 *            �±�ǩ�������
	 * @return String
	 * @�磺�滻img��ǩ��src����ֵΪ[img]����ֵ[/img]
	 */
	public static String replaceHtmlTag(String str, String beforeTag,
			String tagAttrib, String startTag, String endTag) {
		String regxpForTag = "<\\s*" + beforeTag + "]*)\\s<\\s+([^>]*)\\s*>";
		String regxpForTagAttrib = tagAttrib + "=\"([^\"]+)\"";
		Pattern patternForTag = Pattern.compile(regxpForTag);
		Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
		Matcher matcherForTag = patternForTag.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result = matcherForTag.find();
		while (result) {
			StringBuffer sbreplace = new StringBuffer();
			Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag
					.group(1));
			if (matcherForAttrib.find()) {
				matcherForAttrib.appendReplacement(sbreplace, startTag
						+ matcherForAttrib.group(1) + endTag);
			}
			matcherForTag.appendReplacement(sb, sbreplace.toString());
			result = matcherForTag.find();
		}
		matcherForTag.appendTail(sb);
		return sb.toString();
	}


	public static void main(String[] args) {
		//System.out.println(filterHtml("<p style='TEXT-JUSTIFY: inter-ideograph; TEXT-ALIGN: left; LINE-HEIGHT: 150%; MARGIN: 6pt 0cm; FONT-FAMILY: 'Calibri','sans-serif'; FONT-SIZE: 10.5pt' align='left'></p><p>���ڰ�׿4.0ϵͳ��ʹ�õ������ƴ���С�����������ɿ��Ƶ�ǰ���õĹ��ܣ��Ӷ��ӳ��ֻ����ʹ��ʱ�䡣Ĭ������£��������С������ʾ������������Ļ�С����ô���С��������ʾ���������ò��ŵĹ���ʱ���Ϳ��Թر���Щ���ܣ��Ӷ��ӳ����ʹ��ʱ�䡣<br>�����ǰ����������Ļ�ж�û�е������ƴ���С��������ִ�����²�����Ӹô���С������<br>1��ת��������Ӧ�á���Ļ������������С��������ǩ�� </p><p>2��������ָ�������С�����������ҳ����������ơ���Ȼ��������ס�ô���С������</p><p></p><p style='TEXT-ALIGN: center'><span style='LINE-HEIGHT: 150%; FONT-FAMILY: ����; COLOR: black'><img src='E:/resource/images/uploadfile/20130516133022937001.gif' border='0'></span></p>"));
		//System.out.println( fiterHtmlTag("<p style='TEXT-JUSTIFY: inter-ideograph; TEXT-ALIGN: left; LINE-HEIGHT: 150%; MARGIN: 6pt 0cm; FONT-FAMILY: 'Calibri','sans-serif'; FONT-SIZE: 10.5pt' align='left'></p><p>���ڰ�׿4.0ϵͳ��ʹ�õ������ƴ���С�����������ɿ��Ƶ�ǰ���õĹ��ܣ��Ӷ��ӳ��ֻ����ʹ��ʱ�䡣Ĭ������£��������С������ʾ������������Ļ�С����ô���С��������ʾ���������ò��ŵĹ���ʱ���Ϳ��Թر���Щ���ܣ��Ӷ��ӳ����ʹ��ʱ�䡣<br>�����ǰ����������Ļ�ж�û�е������ƴ���С��������ִ�����²�����Ӹô���С������<br>1��ת��������Ӧ�á���Ļ������������С��������ǩ�� </p><p>2��������ָ�������С�����������ҳ����������ơ���Ȼ��������ס�ô���С������</p><p></p><p style='TEXT-ALIGN: center'><span style='LINE-HEIGHT: 150%; FONT-FAMILY: ����; COLOR: black'><img src='E:/resource/images/uploadfile/20130516133022937001.gif' border='0'></span></p>","p") );
		System.out.println(filterHtmlTag(" <SECTION> <SUBTITLE>Visitors from outside the United States</SUBTITLE><SUBTEXT>The Site's offices are located in the United States. If you visit the site from another country, please be aware that information you provide to us or that we obtain as a result of your use of the Site may be processed in and transferred to the United States and will be subject to U.S. law. U.S. privacy and data protection laws may not be equivalent to the laws in your country of residence. By using the Site or providing us with your information, you consent to the collection, transfer, storage, and processing of information to and in the United States. European Union Residents: AOL adheres to the EU-US Safe Harbor Privacy Principles of Notice, Choice, Onward Transfer, Security, Data Integrity, Access and Enforcement, and is registered with the U.S. Department of Commerce's Safe Harbor Program.</SUBTEXT></SECTION>","" ) );

	}

}