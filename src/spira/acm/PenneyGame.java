package spira.acm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PenneyGame {

	public static void main(String[] args) {
		PenneyGame penney = new PenneyGame();
		StringBuilder info = new StringBuilder();
		Scanner keyboard = new Scanner(System.in);
		int dataSets = keyboard.nextInt();
		char[] tH;
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for (int i = 0; i < dataSets; i++) {
			int dataNumb = keyboard.nextInt();
			keyboard.nextLine();
			String string = keyboard.nextLine();
			tH = string.toCharArray();

			int[] amount = penney.theAmount(tH);

			info.append(dataNumb);
			for (int j = 0; j < amount.length; j++) {
				info.append(" ");
				info.append(amount[j]);
			}
			info.append("\n");

		}
		System.out.println(info.toString());
		keyboard.close();
	}

	public int[] theAmount(char[] tH) {

		int[] amount = new int[8];
		for (int i = 0; i < amount.length; i++) {
			amount[i] = 0;
		}
		for (int i = 0; i < tH.length - 2; i++) {
			String chance = String.valueOf(tH[i] )+  String.valueOf(tH[i + 1]) +  String.valueOf(tH[i + 2]);
			if ("TTT".equals(chance)) {
				amount[0]++;
			}
			if ("TTH".equals(chance)) {
				amount[1]++;
			}
			if ("THT".equals(chance)) {
				amount[2]++;
			}
			if ("THH".equals(chance)) {
				amount[3]++;
			}
			if ("HTT".equals(chance)) {
				amount[4]++;
			}
			if ("HTH".equals(chance)) {
				amount[5]++;
			}
			if ("HHT".equals(chance)) {
				amount[6]++;
			}
			if ("HHH".equals(chance)) {
				amount[7]++;
			}
		}

		return amount;

	}

}
