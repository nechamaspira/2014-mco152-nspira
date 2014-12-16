package spira.scrabble;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ScrabbleDictionary {
	
	private Set<String> words;

	public ScrabbleDictionary() throws FileNotFoundException{
		
		words = new HashSet<String>();
		Scanner inputFile = new Scanner(new File("./OWL.txt"));
		
		while(inputFile.hasNext()){
			words.add(inputFile.next());
			inputFile.nextLine();
		}
		inputFile.close();
	}
	
	public boolean contains(String word ){
		if(word == null){
			return false;
		}
		String upper = word.toUpperCase();
		return words.contains(upper);	
	}
	
}
