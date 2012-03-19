package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import junit.framework.TestCase;

import org.junit.Before;
import static org.junit.Assert.*;
import keepcalm.Reader;

public class TestReader extends TestCase {
	private Reader r;
	
	@Before
	public void setUp() throws Exception {
		r = new Reader("src/test/testdata.csv");
	}
	
	public void testGetInstance() {
		assertEquals("getInstance(1) returns the wrong size!", 17, r.getInstance(1).size());
		assertEquals("getInstance(60) returns the wrong size!", 17, r.getInstance(60).size());
		assertEquals("getInstance(33) returns the wrong size!", 17, r.getInstance(33).size());
	}
	
	/* had trouble making it work JUnit4-style for some
	 * reason
	 */
	public void testGetInstanceThatDoesNotExist() {
		try {
			r.getInstance(99);
			fail("Didn't throw NoSuchElementException.");
		} catch (IndexOutOfBoundsException e) {
			assertNotNull(e.getMessage());
		}
	}
	
	public void testNoSuchFile() {
		try {
			new Reader("idontexist.csv");
			fail("Didn't throw FileNotFoundException.");
		} catch (FileNotFoundException e) {
			assertNotNull(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			fail("Threw weird exception.");
		}
	}
	
	public void testReadInstance() {
		ArrayList<String> d = r.getInstance(33);
		assertEquals("High", d.get(0));
		assertEquals("Low", d.get(1));
		assertEquals("High", d.get(2));
		assertEquals("Nominal", d.get(3));
		assertEquals("Nominal", d.get(4));
		assertEquals("Low", d.get(5));
		assertEquals("Low", d.get(6));
		assertEquals("Nominal", d.get(7));
		assertEquals("Nominal", d.get(8));
		assertEquals("Nominal", d.get(9));
		assertEquals("Nominal", d.get(10));
		assertEquals("High", d.get(11));
		assertEquals("High", d.get(12));
		assertEquals("Nominal", d.get(13));
		assertEquals("Low", d.get(14));
		assertEquals("66.6", d.get(15));
		assertEquals("352.8", d.get(16));
	}
}
