package spira.acm;

import java.util.Scanner;

public class RepeatingCharacters {

	public static void main(String[] args) {
		
			StringBuilder builder = new StringBuilder();

			RepeatingCharacters rc = new RepeatingCharacters();
			Scanner keyboard = new Scanner(System.in);
			int dataSets = keyboard.nextInt();
			
			keyboard.nextLine();
			for (int i = 0; i < dataSets; i++) {

				int dataSetNumb = keyboard.nextInt();
				int amntTime = keyboard.nextInt();

				String str = keyboard.nextLine();
				String s = rc.toString(str.trim(), amntTime);

				builder.append(dataSetNumb + " " + s);
				builder.append("\n");

			}

			System.out.println(builder.toString());
			keyboard.close();

			
		}
	

	public String toString(String s, int times) {
		StringBuilder info = new StringBuilder();

		char[] st = s.toCharArray();

		for (int i = 0; i < st.length; i++) {
			for (int j = 0; j < times; j++) {
				info.append(st[i]);
			}
		}
		return info.toString();

	}
}
