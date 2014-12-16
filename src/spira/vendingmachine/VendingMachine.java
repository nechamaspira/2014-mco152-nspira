package spira.vendingmachine;

public class VendingMachine {

	private Inventory inventory;
	private Money bank;

	/**
	 * The amount of money the person has put into the Vending Machine so far
	 */
	private Money paid;

	public VendingMachine(Inventory inventory, Money bank) {
		this.inventory = inventory;
		this.bank = bank;
		paid = new Money();
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Money getBank() {
		return bank;
	}

	public Money getPaid() {
		return paid;
	}

	/**
	 * Add additional Money to the machine
	 * 
	 * @param additional
	 * @return the amount that the person has put into the machine
	 */
	public double pay(Money additional) {
		paid.add(additional);
		return paid.getTotal();
	}

	/**
	 * 
	 * @param code
	 * @return the amount of change as a Money object
	 * @throws CodeNotFoundException
	 *             if there is no item with that code
	 * @throws NotEnoughPaidException
	 *             if paid is not enough to buy the item
	 * @throws NotEnoughChangeException
	 *             if the transaction cannot be completed because there isn't
	 *             enough money in the vending machine for the change
	 */
	public Money buy(String code) throws CodeNotFoundException,
			NotEnoughPaidException, NotEnoughChangeException {

		Money change;
		Money nothingThere = new Money(0,0,0,0);

		if (this.inventory.get(code)==null) {
			throw new CodeNotFoundException();
		}

		double cost = this.inventory.get(code).getPrice();

		if (this.paid.getTotal() < cost) {
			throw new NotEnoughPaidException();
		} else {
			this.bank.add(this.paid);
			if(this.inventory.get(code).getQuantity() == 0){
				return nothingThere;
			}
			double give = Math.round((this.paid.getTotal() - cost) * 100.0) / 100.0;

			change = this.bank.remove(give);
			
			this.inventory.get(code).setQuantity();
			this.paid.setNumDimes(0);
			this.paid.setNumNickles(0);
			this.paid.setNumQuarters(0);
			this.paid.setNumDollars(0);

		}

		return change;
	}

}
