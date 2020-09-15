package jyatlon.test.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

	public static String loadFromFile(String filename, Charset encoding) throws IOException {
		
		byte[] encoded = Files.readAllBytes(getResource(filename).toPath());
		return new String(encoded, encoding);
	}
	public static void saveToFile(String filename, String text) throws IOException {
		File file = new File("src/test/resources", filename);
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