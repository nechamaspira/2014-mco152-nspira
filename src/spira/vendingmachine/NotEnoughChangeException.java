package spira.vendingmachine;

public class NotEnoughChangeException extends Exception {

	public NotEnoughChangeException(){
		super("the transaction cannot be completed because there isn't enough money in the vending machine for the change");
	}
	private static final long serialVersionUID = 1L;

}
