package policy_analyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassHtmlUtils {
	private final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签

	private final static String regxpForImgTag = "<]*)\\s<\\s*img\\s+([^>]*)\\s*>"; // 找出IMG标签

	private final static String regxpForImaTagSrcAttrib = "src=\"([^\"]+)\""; // 找出IMG标签的SRC属性
	/**
	 * 
	 * 基本功能：替换标记以正常显示
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
	 * 基本功能：判断标记是否存在
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
	 * 基本功能：过滤所有以"<"开头以">"结尾的标签
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
	 * 基本功能：过滤指定标签
	 * <p>
	 * 
	 * @param str
	 * @param tag
	 *            指定标签
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
	 * 基本功能：替换指定的标签
	 * <p>
	 * 
	 * @param str
	 * @param beforeTag
	 *            要替换的标签
	 * @param tagAttrib
	 *            要替换的标签属性值
	 * @param startTag
	 *            新标签开始标记
	 * @param endTag
	 *            新标签结束标记
	 * @return String
	 * @如：替换img标签的src属性值为[img]属性值[/img]
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
		//System.out.println(filterHtml("<p style='TEXT-JUSTIFY: inter-ideograph; TEXT-ALIGN: left; LINE-HEIGHT: 150%; MARGIN: 6pt 0cm; FONT-FAMILY: 'Calibri','sans-serif'; FONT-SIZE: 10.5pt' align='left'></p><p>对于安卓4.0系统，使用电量控制窗口小部件可以轻松控制当前不用的功能，从而延长手机电池使用时间。默认情况下，这个窗口小部件显示在最左侧的主屏幕中。当该窗口小部件中显示了您觉得用不着的功能时，就可以关闭这些功能，从而延长电池使用时间。<br>如果当前的所有主屏幕中都没有电量控制窗口小部件，请执行以下步骤添加该窗口小部件：<br>1．转至【所有应用】屏幕，触摸【窗口小部件】标签。 </p><p>2．滑动手指浏览窗口小部件，从中找出【电量控制】，然后触摸并按住该窗口小部件。</p><p></p><p style='TEXT-ALIGN: center'><span style='LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; COLOR: black'><img src='E:/resource/images/uploadfile/20130516133022937001.gif' border='0'></span></p>"));
		//System.out.println( fiterHtmlTag("<p style='TEXT-JUSTIFY: inter-ideograph; TEXT-ALIGN: left; LINE-HEIGHT: 150%; MARGIN: 6pt 0cm; FONT-FAMILY: 'Calibri','sans-serif'; FONT-SIZE: 10.5pt' align='left'></p><p>对于安卓4.0系统，使用电量控制窗口小部件可以轻松控制当前不用的功能，从而延长手机电池使用时间。默认情况下，这个窗口小部件显示在最左侧的主屏幕中。当该窗口小部件中显示了您觉得用不着的功能时，就可以关闭这些功能，从而延长电池使用时间。<br>如果当前的所有主屏幕中都没有电量控制窗口小部件，请执行以下步骤添加该窗口小部件：<br>1．转至【所有应用】屏幕，触摸【窗口小部件】标签。 </p><p>2．滑动手指浏览窗口小部件，从中找出【电量控制】，然后触摸并按住该窗口小部件。</p><p></p><p style='TEXT-ALIGN: center'><span style='LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; COLOR: black'><img src='E:/resource/images/uploadfile/20130516133022937001.gif' border='0'></span></p>","p") );
		System.out.println(filterHtmlTag(" <SECTION> <SUBTITLE>Visitors from outside the United States</SUBTITLE><SUBTEXT>The Site's offices are located in the United States. If you visit the site from another country, please be aware that information you provide to us or that we obtain as a result of your use of the Site may be processed in and transferred to the United States and will be subject to U.S. law. U.S. privacy and data protection laws may not be equivalent to the laws in your country of residence. By using the Site or providing us with your information, you consent to the collection, transfer, storage, and processing of information to and in the United States. European Union Residents: AOL adheres to the EU-US Safe Harbor Privacy Principles of Notice, Choice, Onward Transfer, Security, Data Integrity, Access and Enforcement, and is registered with the U.S. Department of Commerce's Safe Harbor Program.</SUBTEXT></SECTION>","" ) );

	}

}