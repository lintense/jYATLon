package jyatlon.core;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

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
