package jyatlon.core;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
//import org.mockito.runners.MockitoJUnitRunner;

import jyatlon.test.utilities.CompareString;
import jyatlon.test.utilities.ObjectTree;
import jyatlon.test.utilities.TestUtils;

@RunWith(JUnitPlatform.class)
class AppTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	@Test
	void testParsing1() throws IOException {
		System.out.println("### testParsing1()");
		String t = " === String ===\n\n\n" +
				" a b### {}[begin]~c {{if ((($.name) == $.val.val2(1,2.3).val2) || $.name() == $.val || $.name == $._val(1)) call .../String ($:ROOT.name(('test')):ALIAS)}}x{begin X}yz\n";
		
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		ObjectTree.dumpObject(App.getTemplate(t), ps);
		String obtained = baos.toString();
		TestUtils.saveToFile("testParsing1.last.txt", obtained);
		assertTrue(CompareString.compareFileToString(TestUtils.getResource("testParsing1.txt"), obtained));
	}
	@Test
	void testSectionLines() throws IOException {
		System.out.println("### testSectionLines()");
		String t = " === String === \n\n === Object ===";
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		ObjectTree.dumpObject(App.getTemplate(t), ps);
		String obtained = baos.toString();
		TestUtils.saveToFile("testSectionLines.last.txt", obtained);
		assertTrue(CompareString.compareFileToString(TestUtils.getResource("testSectionLines.txt"), obtained));
	}
	@Test
	void testInitControls() throws IOException {
		System.out.println("### testInitControls()");
		String t = "{begin X}{before X}{between X}{after X}{end X}";
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		ObjectTree.dumpObject(App.getTemplate(t), ps);
		String obtained = baos.toString();
		TestUtils.saveToFile("testInitControls.last.txt", obtained);
		assertTrue(CompareString.compareFileToString(TestUtils.getResource("testInitControls.txt"), obtained));
	}
	@Test
	void testAliases() throws IOException {
		System.out.println("### testAliases()");
		String t = "abc{{$:Root.toString:Value.toString:String}}def\nghi{{$:Root.toString:Value.toString:String}}jkl";
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		ObjectTree.dumpObject(App.getTemplate(t), ps);
		String obtained = baos.toString();
		TestUtils.saveToFile("testAliases.last.txt", obtained);
	}
}
