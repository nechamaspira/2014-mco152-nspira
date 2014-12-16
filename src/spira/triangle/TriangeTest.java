package spira.triangle;

import org.junit.Assert;
import org.junit.Test;

public class TriangeTest {

	@Test
	public void testToString1() {
		Triangle triangle = new Triangle(3);
		String happy = triangle.toString();

		Assert.assertEquals("  *\n"
				  + " * *\n"
				  + "*****", happy);

	}
	@Test
	public void testToString2() {
		Triangle triangle = new Triangle(5);
		String happy = triangle.toString();

		Assert.assertEquals("    *\n"+
"   * *\n"+
"  *   *\n"+
" *     *\n"+
"*********", happy);

	}
}
