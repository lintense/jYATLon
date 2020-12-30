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
class YATLTest {
	private static final String TEST_SCRIPT_EXTENSION = "*.script.txt";
	
	private static final String TEST_SCRIPT_ROOT = "ROOT";
	private static final String TEST_SCRIPT_TEMPLATE = "TEMPLATE";
	private static final String TEST_SCRIPT_EXPECTED = "EXPECTED";
	
	private static final String AUTOMATED_TESTING_FOLDER = "src/test/resources";
	private static final String MANUAL_TESTING_FOLDER = "src/test/resources/DEBUG";

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
		Template t = YATL.parseTemplate(s);
		String obtained = getStructAsString(testName, t);
		assertTrue(CompareString.compareFileToString(TestUtils.getResource(testName + ".txt"), obtained));
		
	}
	@Test
	void testSectionLines() throws IOException {
		String testName = showTestHeader("testSectionLines");
		Template t = YATL.parseTemplate(" === String === \n\n === Object ===");
		String obtained = getStructAsString(testName, t);
		assertTrue(CompareString.compareFileToString(TestUtils.getResource(testName + ".txt"), obtained));
	}
	@Test
	void testInitControls() throws IOException {
		String testName = showTestHeader("testInitControls");
		Template t = YATL.parseTemplate("{begin X}{before X}{between X}{after X}{end X}");
		String obtained = getStructAsString(testName, t);
		assertTrue(CompareString.compareFileToString(TestUtils.getResource(testName + ".txt"), obtained));
	}
	@Test
	void testAliases() throws IOException {
		String testName = showTestHeader("testAliases");
		Template t = YATL.parseTemplate("abc{{$:Root.toString:Value.toString:String}}def\nghi{{$:Root.toString:Value.toString:String}}jkl");
		String obtained = getStructAsString(testName, t);
		assertTrue(CompareString.compareFileToString(TestUtils.getResource(testName + ".txt"), obtained));
	}
	@Test
	public void testAllScripts() throws IOException {
		System.out.println("List of the text files in the specified directory: " + AUTOMATED_TESTING_FOLDER);
		File[] files = TestUtils.getAllFiles(AUTOMATED_TESTING_FOLDER, TEST_SCRIPT_EXTENSION);
		for(File file : files)
			testScriptFile(file);
	}
	public static void main(String[] args) throws IOException {

		System.out.println("List of the text files in the specified directory: " + MANUAL_TESTING_FOLDER);
		File[] files = TestUtils.getAllFiles(MANUAL_TESTING_FOLDER, TEST_SCRIPT_EXTENSION);
		for(File file : files)
			testScriptFile(file);
	}
	
	private static void testScriptFile(File file) throws IOException {
		System.out.println((">>> Testing script file : " + file.getAbsolutePath()).toUpperCase());
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
	private static Map<String,String> extractTestMap(List<String> lines){
		Map<String, String> result = new HashMap<>();
		String command = null;
		StringBuilder sb = new StringBuilder();
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
		System.out.println(">>> Testing script file : " + (new File(AUTOMATED_TESTING_FOLDER, testName + ".txt")).getAbsolutePath().toUpperCase());
		return testName;
	}
	private static String getStructAsString(String filename, Struct t) throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		ObjectTree.dumpObject(t, ps);
		String obtained = baos.toString();
		TestUtils.saveToFile(AUTOMATED_TESTING_FOLDER, filename + ".last.txt", obtained);
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
