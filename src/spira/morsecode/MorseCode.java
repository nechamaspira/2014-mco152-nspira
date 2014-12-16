package spira.morsecode;

import java.util.HashMap;
import java.util.Map;




public class MorseCode {
	Map<String,String> map;

	public MorseCode(){

	
	String [][] morse= {
			{"A",".-"},{"B","-..."},{"C","-.-."},{"D","-.."},{"E","."},{"F","..-."},{"G","--."},
			{"H","...."},{"I",".."},{"J",".---"},{"K","-.-"},{"L",".-.."},{"M","--"},{"N","-."},
			{"O","---"},{"P",".--."},{"Q","--.-"},{"R",".-."},{"S","..."},{"T","-"},{"U","..-"},
			{"V","...-"},{"W",".--"},{"X","-..-"},{"Y","-.--"},{"Z","--.."},{"0","-----"},{"1",".----"},
			{"2","..---"},{"3","...--"},{"4","....-"},{"5","....."},{"6","-...."},{"7","--..."},
			{"8","---.."},{"9","----."},{" ","/"}};
	map = new HashMap<String,String>();
	
	for(int i = 0; i < morse.length; i++){
		String key = morse[i][0];
		String value = morse[i][1];
		
		map.put(key, value);
		map.put(value, key);
	}
	}
	
	/*public static void main(String[] args){
		MorseCode morseCode = new MorseCode();
		System.out.println(morseCode.toMorseCode("SOS"));
	   System.out.println(morseCode.toPlainText("... - .. --- / ... --- ..."));

	}
	*/
	//public MorseCode() throws FileNotFoundException{
		/*morse = new String[40][2];
		Scanner inputFile = new Scanner(new File("./morseCode.txt"));
		int i=0;
		while(inputFile.hasNextLine()){
			this.morse[i][0] =inputFile.nextLine();
			this.morse[i][1] =inputFile.nextLine();
			this.numbFilled++;
			i++; 
		}*/
	//}

	public String toMorseCode(String plainText){
		
		StringBuilder info = new StringBuilder();
		String upperPlainText = plainText.toUpperCase();
		
		String [] c = upperPlainText.split("");

		for(int i =0; i <c.length; i++){
			//for(int j=0;j<morse.length;j++){
			//	if(c[i].equals(morse[j][0])){
					if(i==c.length-1){
						//info.append(morse[j][1]);
						info.append(map.get(c[i]));

					}
					else{
						info.append(map.get(c[i])+" ");

						//info.append(morse[j][1]+" ");
					}
				}

			//}*/
		//}

		return info.toString();
	}

	public String toPlainText(String morseCode){

		StringBuilder info = new StringBuilder();
		String [] c = morseCode.split(" ");


		for(int i =0; i <c.length; i++){
			info.append(map.get(c[i]));

			/*for(int j=0;j<morse.length;j++){
				if(c[i].equals(morse[j][1])){
						info.append(morse[j][0]) ;
					
				}

			}*/
		}

		return info.toString();
	}
}
