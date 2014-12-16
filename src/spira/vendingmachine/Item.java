package spira.vendingmachine;

public class Item {

	private String code;
	private String name;
	private double price;
	private int quantity;
	
	public Item(String code, String name, double price, int quantity) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	public String getCode() {
		return this.code;
	}
	public String getName() {
		return this.name;
	}
	public double getPrice() {
		return this.price;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public void setQuantity() {
		this.quantity = this.quantity - 1;
	}
}
