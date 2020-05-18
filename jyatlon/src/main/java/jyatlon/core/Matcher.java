package jyatlon.core;

public class Matcher {
	public static boolean isSameObject(Object o1, Object o2) {
		if (o1 == o2)
			return true;
		if (o1 == null || o2 == null)
			return false;
		return o1.toString().equals(o2.toString());
		// Collections... FIXME
	}
}
