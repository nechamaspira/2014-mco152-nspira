package spira.acm2;

import java.util.Scanner;

public class MaxSum {

	public static void main(String[] args) {

		MaxSum game = new MaxSum();
		Scanner keyboard = new Scanner(System.in);
		int size = keyboard.nextInt();

		int[][] array = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				array[i][j] = keyboard.nextInt();
			}
		}
		int largest = game.getLargest(array);
		System.out.println(largest);

		keyboard.close();
	}

	public int getLargest(int[][] array) {

		int[] temp;
		int col = array.length;
		int row = array[0].length;
		int maxSum = Integer.MIN_VALUE;
		int largest;
		int middle;
		for (int i = 0; i < col; i++) {
			temp = new int[row];
			middle = 0;
			largest = Integer.MIN_VALUE;
			for (int j = i; j < col; j++) {
				middle = 0;
				for (int k = 0; k < row; k++) {
					temp[k] = temp[k]+ array[j][k];
					middle = middle+temp[k];

					if (largest < middle) {
						largest = middle;
					}
					if (middle < 0 ) {
						middle = 0;
					}

				}
				
				if (largest > maxSum) {
					maxSum = largest;
				}

			}
		}

		return maxSum;
	}

	

}
