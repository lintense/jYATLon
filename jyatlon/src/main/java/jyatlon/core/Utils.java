package jyatlon.core;

public class Utils {

	public static String toLowerFirst(String string) {
		char c[] = string.toCharArray();
		c[0] = Character.toLowerCase(c[0]);
		return new String(c);
	}
}
