package jyatlon.core;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
//import org.mockito.runners.MockitoJUnitRunner;

import jyatlon.core.Struct.Template;
import jyatlon.test.utilities.CompareString;
import jyatlon.test.utilities.ObjectTree;
import jyatlon.test.utilities.TestUtils;

@RunWith(JUnitPlatform.class)
class AppTest {
	
	private static final String TEST_SCRIPT_EXTENSION = "*.script.txt";
	private static final String DEBUG_SCRIPT_EXTENSION = "*.script.txt.tmp.txt";
	private static final String TEST_SCRIPT_ROOT = "ROOT";
	private static final String TEST_SCRIPT_TEMPLATE = "TEMPLATE";
	private static final String TEST_SCRIPT_EXPECTED = "EXPECTED";

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	@Test
	void testBeginEndControls() throws IOException {
		String test = "{begin ALIAS}Hello {{$:ALIAS}}!{end ALIAS}";
		String obtained = process(test, "World");
		assertEquals("Hello World!", obtained);
	}
	@Test
	void testParsing1() throws IOException {
		String s = " === String ===\n\n\n" +
				" a b%%% {}[begin]~c {{if ((($.name) == $.val.val2(1,2.3).val2) || $.name() == $.val || $.name == $._val(1)) call .../String ($:ROOT.name(('test')):ALIAS)}}x{begin X}yz\n";
		String testName = showTestHeader("testParsing1");
		Template t = App.getTemplate(s);
		String obtained = getStructAsString(testName, t);
		assertTrue(CompareString.compareFileToString(TestUtils.getResource(testName + ".txt"), obtained));
		
	}
	@Test
	void testSectionLines() throws IOException {
		String testName = showTestHeader("testSectionLines");
		Template t = App.getTemplate(" === String === \n\n === Object ===");
		String obtained = getStructAsString(testName, t);
		assertTrue(CompareString.compareFileToString(TestUtils.getResource(testName + ".txt"), obtained));
	}
	@Test
	void testInitControls() throws IOException {
		String testName = showTestHeader("testInitControls");
		Template t = App.getTemplate("{begin X}{before X}{between X}{after X}{end X}");
		String obtained = getStructAsString(testName, t);
		assertTrue(CompareString.compareFileToString(TestUtils.getResource(testName + ".txt"), obtained));
	}
	@Test
	void testAliases() throws IOException {
		String testName = showTestHeader("testAliases");
		Template t = App.getTemplate("abc{{$:Root.toString:Value.toString:String}}def\nghi{{$:Root.toString:Value.toString:String}}jkl");
		String obtained = getStructAsString(testName, t);
		assertTrue(CompareString.compareFileToString(TestUtils.getResource(testName + ".txt"), obtained));
	}
	@Test
	public void testAllScripts() throws IOException {

		String resourceFolder = "src/test/resources";
		System.out.println("List of the text files in the specified directory: " + resourceFolder);
		File[] files = TestUtils.getAllFiles(resourceFolder, TEST_SCRIPT_EXTENSION);
		for(File file : files) {
			System.out.println((">>> Testing file : " + file.getAbsolutePath()).toUpperCase());
			List<String> testScriptLines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			Map<String,String> testMap = extractTestMap(testScriptLines);
			Object testRoot = TestUtils.jsonToObj(testMap.get(TEST_SCRIPT_ROOT));	
			String testTemplate = testMap.get(TEST_SCRIPT_TEMPLATE);	
			String expectedResult = testMap.get(TEST_SCRIPT_EXPECTED);
			String actualResult;
			try {
				actualResult = process(testTemplate, testRoot).trim();
			} catch (Exception e) {
				actualResult = e.getClass().getName() + ": " + e.getMessage();
			}
			assertEquals(expectedResult, actualResult, "Error testing file: " + file.getName());
		}
	}
	public static void main(String[] args) throws IOException {

		String resourceFolder = "src/test/resources";
		System.out.println("List of the text files in the specified directory: " + resourceFolder);
		File[] files = TestUtils.getAllFiles(resourceFolder, DEBUG_SCRIPT_EXTENSION);
		for(File file : files) {
			System.out.println((">>> Testing file : " + file.getAbsolutePath()).toUpperCase());
			List<String> testScriptLines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			Map<String,String> testMap = extractTestMap(testScriptLines);
			Object testRoot = TestUtils.jsonToObj(testMap.get(TEST_SCRIPT_ROOT));	
			String testTemplate = testMap.get(TEST_SCRIPT_TEMPLATE);	
			String expectedResult = testMap.get(TEST_SCRIPT_EXPECTED);
			String actualResult;
			try {
				actualResult = process(testTemplate, testRoot).trim();
			} catch (Exception e) {
				actualResult = e.getClass().getName() + ": " + e.getMessage();
			}
			assertEquals(expectedResult, actualResult, "Error testing file: " + file.getName());
		}
	}
	
	private static Map<String,String> extractTestMap(List<String> lines){
		Map<String, String> result = new HashMap<>();
		String command = null;
		StringBuffer sb = new StringBuffer();
		for (String l : lines) {
			if (l.startsWith(">>>")) {
				if (command != null)
					result.put(command, sb.toString().trim());
				command = l.substring(3, l.indexOf(':')).trim();
				sb.setLength(0);
				sb.append(l.substring(l.indexOf(':') + 1).trim());
			} else {
				sb.append(l).append(System.lineSeparator());
			}
		}
		if (command != null)
			result.put(command, sb.toString().trim());
		return result;
	}
	private static String showTestHeader(String testName) {
		System.out.println("### " + testName + " ###");
		return testName;
	}
	private static String getStructAsString(String filename, Struct t) throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		ObjectTree.dumpObject(t, ps);
		String obtained = baos.toString();
		TestUtils.saveToFile(filename + ".last.txt", obtained);
		return obtained;
	}
	private static String process(String templateContent, Object root) throws IOException {
		StringWriter w = new StringWriter();
		YATL.fromString(templateContent).merge(root, w);
		return w.toString();
	}

	
	
	
	
/* Stuff to be tested - someday
 * 
 * 

 */
}
