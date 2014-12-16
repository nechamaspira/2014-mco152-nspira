package spira.acm2;

import java.util.Scanner;

public class RascalTriangle {

	public static void main(String[] args) {
		RascalTriangle tri = new RascalTriangle();
		Scanner keyboard = new Scanner(System.in);
		int dataSets = keyboard.nextInt();
		int[] allDataSets = new int[dataSets];

		StringBuilder info = new StringBuilder();
		for (int i = 0; i < dataSets; i++) {
			 keyboard.nextInt();
			int n = keyboard.nextInt();
			int m = keyboard.nextInt();

			int number = tri.number(n, m);
			allDataSets[i] = number;
		}

		for (int i = 0; i < allDataSets.length; i++) {
			info.append(i + 1);
			info.append(" ");
			info.append(allDataSets[i]);
			info.append("\n");
		}
		keyboard.close();
		System.out.println(info.toString());

		 
	}

	public int number(int n, int m) {
		int theNumb = m * (n - m) + 1;
		return theNumb;
	}

}
