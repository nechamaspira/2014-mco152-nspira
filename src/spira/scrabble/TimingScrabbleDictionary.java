package spira.scrabble;

import java.io.FileNotFoundException;

public class TimingScrabbleDictionary {

	public static void main(String[] args) throws FileNotFoundException {
		ScrabbleDictionary d = new ScrabbleDictionary();
		
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < 1000000; i++){
			d.contains("hello");
			
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);

	}

}
