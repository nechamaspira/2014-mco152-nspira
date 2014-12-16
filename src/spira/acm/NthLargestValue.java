package spira.acm;

import java.util.Scanner;

public class NthLargestValue {

	public static void main(String[] args) {
		NthLargestValue trying = new NthLargestValue();
		StringBuilder info = new StringBuilder();

		Scanner keyboard = new Scanner(System.in);
		int dataSets = keyboard.nextInt();
		int[] numbers = new int[10];

		for (int i = 0; i < dataSets; i++) {
			int dataNumb = keyboard.nextInt();
			for (int j = 0; j < 10; j++) {
				numbers[j] = keyboard.nextInt();
			}
			info.append(dataNumb);
			info.append(" ");
			info.append(trying.theOne(numbers));
			info.append("\n");

		}
		System.out.println(info.toString());
		keyboard.close();
	}

	public int[] sort(int[] numb) {
		int temp;
		boolean swapped;

		do {
			swapped = false;
			for (int i = 0; i < numb.length - 1; i++) {
				if (numb[i] > numb[i + 1]) {
					temp = numb[i];
					numb[i] = numb[i + 1];
					numb[i + 1] = temp;
					swapped = true;
				}

			}

		} while (swapped);
		return numb;

	}

	public int theOne(int[] list) {
		int[] numbers = sort(list);
		int numb = numbers[7];

		return numb;

	}

}
