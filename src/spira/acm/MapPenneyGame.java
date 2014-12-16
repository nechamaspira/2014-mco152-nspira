package spira.acm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MapPenneyGame {
	Map<String, Integer> map;
	String[] ht;

	public MapPenneyGame() {
		map = new HashMap<String, Integer>();
		ht = new String[8];

		ht[0] = "TTT";
		ht[1] = "TTH";
		ht[2] = "THT";
		ht[3] = "THH";
		ht[4] = "HTT";
		ht[5] = "HTH";
		ht[6] = "HHT";
		ht[7] = "HHH";

	}

	public static void main(String[] args) {

		MapPenneyGame penney = new MapPenneyGame();
		//penney.fillMap();
		StringBuilder info = new StringBuilder();
		Scanner keyboard = new Scanner(System.in);
		char[] headsTails;

		int dataSets = keyboard.nextInt();

		for (int i = 0; i < dataSets; i++) {
			penney.fillMap();

			int dataNumb = keyboard.nextInt();
			keyboard.nextLine();
			String string = keyboard.nextLine();
			headsTails = string.toCharArray();
			penney.theAmount(headsTails);

			info.append(dataNumb);
			
			info.append(penney.toString());
			info.append("\n");

		}
		System.out.println(info.toString());
		keyboard.close();
	}

	public void fillMap() {
		String key;
		Integer value = 0;
		for (int i = 0; i < ht.length; i++) {
			key = ht[i];

			map.put(key, value);
		}

	}

	public void theAmount(char[] headTail) {

		for (int i = 0; i < headTail.length - 2; i++) {
			String chance = String.valueOf(headTail[i])
					+ String.valueOf(headTail[i + 1])
					+ String.valueOf(headTail[i + 2]);
			for (int j = 0; j < ht.length; j++) {
				if (ht[j].equals(chance)) {
					map.put(chance, map.get(chance) + 1);

					
					break;
				}
			}

		}
		// return map;

	}

	public String toString() {
		StringBuilder info = new StringBuilder();
		for (int i = 0; i < ht.length; i++) {
			info.append(" ");
			info.append(map.get(ht[i]));
		}
		return info.toString();

	}
}
