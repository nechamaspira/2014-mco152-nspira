package spira.morsecode;

import java.io.FileNotFoundException;



import org.junit.Assert;
import org.junit.Test;

public class MorseCodeTest {

	@Test
	public void testToMorseCode() throws FileNotFoundException{
		MorseCode morse = new MorseCode();
	
	String encoded = morse.toMorseCode("sos");

	Assert.assertEquals("... --- ...", encoded);

}
	

	@Test
	public void testToPlainText() throws FileNotFoundException{
		MorseCode morse = new MorseCode();
	
	String encoded = morse.toPlainText(".... ..");

	Assert.assertEquals("HI", encoded);

}
}
