package spira.acm2;

import java.util.ArrayList;
import java.util.Scanner;

public class HappyPrimes {

	public static void main(String[] args) {

		HappyPrimes happiness = new HappyPrimes();
		Scanner keyboard = new Scanner(System.in);
		int dataSets = keyboard.nextInt();
		int[] allDataSets = new int[dataSets];

		StringBuilder info = new StringBuilder();
		for (int i = 0; i < dataSets; i++) {
			keyboard.nextInt();
			int number = keyboard.nextInt();
			allDataSets[i] = number;
		}

		for (int i = 0; i < allDataSets.length; i++) {
			info.append(i + 1);
			info.append(" ");
			info.append(allDataSets[i]);
			info.append(" ");

			boolean prime = happiness.ifPrime(allDataSets[i]);
			boolean happy = happiness.isHappy(allDataSets[i]);

			if (prime && happy ) {

				info.append("YES");

			} else {

				info.append("NO");
			}
			info.append("\n");

		}
		keyboard.close();
		System.out.println(info.toString());
	}

	public boolean ifPrime(int numb) {
		boolean prime = true;
		if (numb == 1) {
			prime = false;
		}
		for (int i = 2; i <= numb / 2; i++) {
			if (numb % i == 0) {
				prime = false;
				break;
			}
		}
		return prime;
	}

	public boolean isHappy(int number) {

		String numb = String.valueOf(number);
	//	int number1 = 0;
		ArrayList<String> all = new ArrayList<String>();

		while (number != 1) {
			if (all.contains(numb)) {
				return false;
			}
			char[] array = numb.toCharArray();
			all.add(numb);
			number =0;
			for (int i = 0; i < array.length; i++) {
				
				number += Integer.parseInt(Character.toString(array[i]))
						* Integer.parseInt(Character.toString(array[i]));
			}

			numb = String.valueOf(number);
		}
		return true;

	}

}
