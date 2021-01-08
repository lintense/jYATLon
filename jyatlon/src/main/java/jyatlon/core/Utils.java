package jyatlon.core;

/*
 * BSD 3-Clause Clear License
 * 
 * Copyright (c) 2019 Sylvain Nadeau
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted (subject to the limitations in the disclaimer 
 * below) provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in the 
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of [Owner Organization] nor the names of its contributors 
 *    may be used to endorse or promote products derived from this software 
 *    without specific prior written permission.
 * 
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY 
 * THIS LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT 
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A 
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER 
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */

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
		if (o != null && Map.class.isAssignableFrom(o.getClass()) && (result = ((Map<?,?>)o).get(Constant.MAP_KEY_FOR_CLASS)) != null)
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
