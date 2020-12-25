package jyatlon.dev;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lintense
 * 
 * Use this class to call a class that is generating a java class file.
 * - Once ready, use "Run As/Maven build" to generate your class.
 * 
 * --- ANT SETUP ---
 * 1 - Copy the following task into your build.xml file
 * 2 - Update the <arg value="..."/> to match the main parameters.
 * 3 - Create as many target as you need to generate more classes.
 * 
 <target name="maven_gen_struct" description="Generate Struct.java">
	<echo level="info" message="Begin generation of Struct..."/>
	<echo level="info" message="project_bin=${project_bin}"/>
	<echo level="info" message="compile_classpath=${compile_classpath}"/>
	<java fork="true" failonerror="true" classname="jyatlon.dev.GenFile">
		<classpath>
			<pathelement location="${project_bin}"/>
			<path refid="maven.compile.classpath"/>
		</classpath>
		<arg value="${project_src}"/>
		<arg value="jyatlon.dev.StructGen"/>
		<arg value="jyatlon.core.Struct"/>
		<jvmarg value="-showversion"/>
	</java>
	<echo level="info" message="Done generating Struct..."/>
 </target>
 *
 * --- MAVEN SETUP ---
 * 1 - Copy the following sections into your pom.xml
 * 2 - Update the <target name="..." /> to match your build.xml ant target name. 
 * 3 - Call many ant task to generate more files.
 * 
  <dependency>
     <groupId>org.apache.maven.plugins</groupId>
     <artifactId>maven-antrun-plugin</artifactId>
     <version>3.0.0</version>
     <type>maven-plugin</type>
  </dependency>
 ...
 <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-antrun-plugin</artifactId>
    <version>3.0.0</version>
    <executions>
       <execution>
          <phase>generate-sources</phase>
          <configuration>
             <target>
                <property name="compile_classpath" refid="maven.compile.classpath" />
                <property name="project_src" value="${project.basedir}\src\main\java" />
                <property name="project_bin" value="${project.build.directory}\classes" />
                <echo level="info" message="Calling: maven_gen_struct" />
                <ant antfile="${basedir}\build.xml">
                   <target name="maven_gen_struct" />
                   <reference torefid="maven.compile.classpath" refid="maven.compile.classpath" />
                </ant>
             </target>
          </configuration>
          <goals>
             <goal>run</goal>
          </goals>
       </execution>
    </executions>
 </plugin>
 *
 */
public class GenJavaFile {

	private static final String JAVA_EXT = ".java";
	/**
	 * @param parms
	 * parms[0] = Java src folder to use for generating the class
	 * parms[1] = Java Generator class to call the main method (always without parms)
	 * parms[2] = Java Generated class fully qualified name
	 */
	public static void main(String[] parms) {
		
		if (parms.length == 0) {
			parms = new String[] {System.getProperty("user.dir"), jyatlon.dev.StructGen.class.getName(), jyatlon.core.Struct.class.getName() + JAVA_EXT};
		}
		
		String src = parms[0].trim();
		String generatorClass = parms[1].trim();
		String generatedClass = parms[2].trim();
		
		src = src.endsWith("\\") || src.endsWith("/")
				? src.substring(0, src.length()-1)
				: src;
		String generatedClassNoExt = generatedClass.toLowerCase().endsWith(JAVA_EXT)
				? generatedClass.substring(0, generatedClass.length() - JAVA_EXT.length())
				: generatedClass;
		String generatedClassname = generatedClassNoExt.substring(generatedClassNoExt.lastIndexOf('.')+1);
		String generatedClassPackage = generatedClassNoExt.substring(0, generatedClassNoExt.length()-generatedClassname.length()-1);

		
		File generatedFolder = new File(src + File.separator + generatedClassPackage.replace(".", File.separator));
		generatedFolder.mkdirs();
		File generatedFile = new File(generatedFolder, generatedClassname + JAVA_EXT);
		try(FileOutputStream os = new FileOutputStream(generatedFile)) {

			// Redirect System out to new file
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			PrintStream(stream, autoFlush, encoding)
			
			System.setOut(new PrintStream(baos));
			
			// Call Generator class
			
			Class<?> c = Class.forName(generatorClass);
			Method main = c.getMethod("main", String[].class);
			main.invoke(null, (Object)new String[] {});
			
			System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
			
			os.write(baos.toByteArray());
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
