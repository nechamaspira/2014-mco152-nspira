package spira.vendingmachine;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class RunVendingMachine {

	// print inventory
	// make selection
	// input money
	// input selection
	// output change
	public static void main(String[] args) {
		boolean finished = false;
		Scanner keyboard = new Scanner(System.in);

		try {
			DecimalFormat df = new DecimalFormat("$##0.00");

			Inventory invent = new Inventory();
			invent.load("./inventory.txt");
			Money bank = new Money(10, 10, 10, 10);
			VendingMachine vm = new VendingMachine(invent, bank);

			String lowerCaseOption;
			String option;
			Money user;
			double balance = 0.0;

			System.out.println(invent.toString());

			System.out.println("Add Money/Make Selection?");
			System.out.println("1. Dollar");
			System.out.println("2. Quarter");
			System.out.println("3. Dime");
			System.out.println("4. Nickle");
			System.out.println("or enter in the Item Code ");

			do {

				System.out.println("Balance " + (df.format(balance)));

				lowerCaseOption = keyboard.nextLine();
				option = lowerCaseOption.toUpperCase();
				switch (option) {
				case "1":// add a dollar
					user = new Money(1, 0, 0, 0);
					balance = vm.pay(user);
					//vm.getBank().add(user);
					break;
				case "2":// add a quarter
					user = new Money(0, 1, 0, 0);
					balance = vm.pay(user);
					//vm.getBank().add(user);
					break;
				case "3":// add a dime
					// wanna put bank not paid
					user = new Money(0, 0, 1, 0);
					balance = vm.pay(user);
					//vm.getBank().add(user);
					break;
				case "4":// add a nickel
					user = new Money(0, 0, 0, 1);
					balance = vm.pay(user);
					//vm.getBank().add(user);
					break;
				default:
					try {
						Money change = vm.buy(option);
						if(change.getTotal()==0){
							System.out.println("there is no more of that item");
							break;
						}
						System.out.println("Dispensing "
								+ invent.get(option).getName());
						System.out.println("Change  "
								+ df.format(change.getTotal()));
						/*
						 * System.out.println(vm.getBank().getNumDollars());
						 * System.out.println(vm.getBank().getNumQuarters());
						 * System.out.println(vm.getBank().getNumDimes());
						 * System.out.println(vm.getBank().getNumNickles());
						 * System.out.println(invent.get(option).getQuantity());
						 */
						finished = true;

					} catch (NotEnoughPaidException e) {
						System.out.println(e.getMessage());
					} catch (CodeNotFoundException e1) {
						System.out.println(e1.getMessage());
					} catch (NotEnoughChangeException e2) {
						System.out.println(e2.getMessage());
					}
					break;
				}
			} while (!finished);
			keyboard.close();

		} catch (IOException e4) {
			e4.getMessage();
		}

	}

}
