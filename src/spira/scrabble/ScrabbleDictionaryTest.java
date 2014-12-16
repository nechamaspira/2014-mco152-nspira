package spira.scrabble;


import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

public class ScrabbleDictionaryTest {

	@Test
	public void testContainsTrue() throws FileNotFoundException{
		ScrabbleDictionary dictionary = new ScrabbleDictionary();
		
		Assert.assertTrue(dictionary.contains("HELLO"));
		Assert.assertTrue(dictionary.contains("HeLLO"));

	}
	
	@Test
	public void testContainsFalse() throws FileNotFoundException{
		ScrabbleDictionary dictionary = new ScrabbleDictionary();
		
		Assert.assertFalse(dictionary.contains("DGASNBUABOBG"));
		Assert.assertFalse(dictionary.contains("bthnbxkhbfwaptpe"));

	}
	@Test
	public void testContainsNull() throws FileNotFoundException{
		ScrabbleDictionary dictionary = new ScrabbleDictionary();
		
		Assert.assertFalse(dictionary.contains(null));

	}
		
}
