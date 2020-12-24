package jyatlon.core;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Utils {

	public static String toLowerFirst(String s) {
		char c[] = s.toCharArray();
		c[0] = Character.toLowerCase(c[0]);
		return new String(c);
	}
	public static String unquote(String s) {
		return s != null ? s.substring(1, s.length()-1) : s;
	}
	public static URL getResourceURL(String res) {
		return Utils.class.getClassLoader().getResource(res);
	}
	public static java.nio.file.Path getPath(String filePath) {
		return Paths.get(filePath);
	}
	public static java.nio.file.Path getPath(File file) {
		return file.toPath();
	}
	public static java.nio.file.Path getPath(URL url) throws URISyntaxException {
		return Paths.get(url.toURI());
	}
	public static String pathToString(java.nio.file.Path path) throws IOException {
		return new String(Files.readAllBytes(path));
	}
	public static boolean isString(String s, String quotes) {
		int len = s != null ? s.length() : -1;
		return len > 1 && quotes.indexOf(s.charAt(0)) == quotes.length() - quotes.lastIndexOf(s.charAt(len-1)) - 1;
	}
	public static boolean isNumber(String s) {
		return s != null && Character.isDigit(s.charAt(0))
				? (s.indexOf(Constant.DOT) >= 0
					? Double.toString(Double.parseDouble(s)).equals(s)
					: Long.toString(Long.parseLong(s)).equals(s))
				: false;
	}
	public static String getClassName(Object o) {
		Object result;
		if (Map.class.isAssignableFrom(o.getClass()) && (result = ((Map<?,?>)o).get(Constant.MAP_KEY_FOR_CLASS)) != null)
			return result.toString();
		return o != null ? o.getClass().getName() : null;
	}
	
//	public static InputStream urlToStream() throws FileNotFoundException {
//		File f = urlToFile(url);
//		return new BufferedInputStream(new FileInputStream(f));
//	}
//	public static ByteArrayInputStream retrieveByteArrayInputStream(String text) {
//		return new ByteArrayInputStream(text.getBytes());
//	}
//	public static ByteArrayInputStream retrieveByteArrayInputStream(File file) throws IOException {
//	    return new ByteArrayInputStream(Files.readAllBytes(file.toPath()));
//	}
//	public static ByteArrayInputStream retrieveByteArrayInputStream(URL url) throws IOException {
//	    return new ByteArrayInputStream(Files.readAllBytes(urlToPath(url)));
//	}
}
