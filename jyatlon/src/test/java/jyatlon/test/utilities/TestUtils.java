package jyatlon.test.utilities;

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
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

	public static String loadFromFile(String filename, Charset encoding) throws IOException {
		
		byte[] encoded = Files.readAllBytes(getResource(filename).toPath());
		return new String(encoded, encoding);
	}
	public static void saveToFile(String testFileFolder, String filename, String text) throws IOException {
		File file = new File(testFileFolder, filename);
		try (PrintStream out = new PrintStream(new FileOutputStream(file))) {
		    out.print(text);
		}
	}
	public static Object jsonToObj(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		if (json.startsWith("{"))
			return mapper.readValue(json, Map.class);
		else if (json.startsWith("["))
			return mapper.readValue(json, ArrayList.class);
		else
			return mapper.readValue(json, Object.class);
	}
	public static String objToString(Object o) {
		return ReflectionToStringBuilder.toString(o, ToStringStyle.MULTI_LINE_STYLE);
	}
	public static File getResource(String filename) {
		ClassLoader classLoader = TestUtils.class.getClassLoader();
		File f = new File(classLoader.getResource(filename).getFile());
		if (!f.canRead())
			System.out.println("Cannot read file: " + f.getAbsolutePath());
		return new File(classLoader.getResource(filename).getFile());
	}
	public static File[] getAllFiles(String folder, String ext) {
		//Creating a File object for directory
		File directoryPath = new File(folder);
		FilenameFilter textFilefilter = new FilenameFilter(){
			public boolean accept(File dir, String name) {
				String[] parts = ext.toLowerCase().split("\\*");
				String lowercaseName = name.toLowerCase();
				return parts.length == 2 
						? lowercaseName.startsWith(parts[0]) && lowercaseName.endsWith(parts[1])
						: (ext.endsWith("*") 
								? lowercaseName.startsWith(parts[0]) 
								: lowercaseName.equals(parts[0]));
			}
		};
		//List of all the text files
		return  directoryPath.listFiles(textFilefilter);
	}
}
/* MAVEN 
<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.9</version>
    <scope>test</scope>
</dependency>
<!-- http://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.8.6/jackson-core-2.8.6.jar -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.8.6</version>
    <scope>test</scope>
</dependency>
<!-- http://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.8.6/jackson-annotations-2.8.6.jar -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
    <version>2.8.6</version>
    <scope>test</scope>
</dependency>
<!-- http://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.8.6/jackson-databind-2.8.6.jar -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.8.6</version>
    <scope>test</scope>
</dependency>
<!-- https://mvnrepository.com/artifact/org.mockito/mockito-all -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-all</artifactId>
    <version>1.10.19</version>
    <scope>test</scope>
</dependency>


*/