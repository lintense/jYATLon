package jyatlon.core;

public class Utils {

	public static String toLowerFirst(String s) {
		char c[] = s.toCharArray();
		c[0] = Character.toLowerCase(c[0]);
		return new String(c);
	}
	public static String unquote(String quotedString) {
		return quotedString.charAt(0) == quotedString.charAt(quotedString.length()-1)
			? quotedString.substring(1, quotedString.lastIndexOf(quotedString.charAt(0)))
			: quotedString;
	}
}
