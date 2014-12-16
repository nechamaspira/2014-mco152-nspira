package spira.vendingmachine;

public class Money {

	private int numDollars;
	private int numQuarters;
	private int numDimes;
	private int numNickles;

	public Money() {

	}

	public Money(int numDollars, int numQuarters, int numDimes, int numNickles) {
		this.numDollars = numDollars;
		this.numQuarters = numQuarters;
		this.numDimes = numDimes;
		this.numNickles = numNickles;
	}

	public void add(Money money) {
		this.numDimes += money.getNumDimes();
		this.numDollars += money.getNumDollars();
		this.numNickles += money.getNumNickles();
		this.numQuarters += money.getNumQuarters();
	}

	public Money remove(double amount) throws NotEnoughChangeException {
		int dollarAmount = 0;
		int quarterAmount = 0;
		int dimeAmount = 0;
		int nickelAmount = 0;
		while (amount >= 1 && this.numDollars >= 1) {
			dollarAmount++;
			amount = Math.round((amount - 1) * 100.0) / 100.0;
		}
		while (amount >= .25 && this.numQuarters >= 1) {
			quarterAmount++;
			amount = Math.round((amount - .25) * 100.0) / 100.0;
		}
		while (amount >= .10 && this.numDimes >= 1) {
			dimeAmount++;
			amount = Math.round((amount - .10) * 100.0) / 100.0;
		}
		while (amount >= .05 && this.numNickles >= 1) {
			nickelAmount++;
			amount = Math.round((amount - .05) * 100.0) / 100.0;
		}
		if (amount != 0) {
			throw new NotEnoughChangeException();
		}
		Money back = new Money(dollarAmount, quarterAmount, dimeAmount,
				nickelAmount);
		this.numDollars = this.numDollars - back.getNumDollars();
		this.numQuarters = this.numQuarters - back.getNumQuarters();
		this.numDimes = this.numDimes - back.getNumDimes();
		this.numNickles = this.numNickles - back.getNumNickles();

		return back;

	}

	public double getTotal() {
		return Math.round(((this.numDimes * .10) + (this.numNickles * .05)
				+ (this.numQuarters * .25) + (this.numDollars * 1))*100.0)/100.0;

	}

	public int getNumDollars() {
		return numDollars;
	}

	public void setNumDollars(int numDollars) {
		this.numDollars = numDollars;
	}

	public int getNumQuarters() {
		return numQuarters;
	}

	public void setNumQuarters(int numQuarters) {
		this.numQuarters = numQuarters;
	}

	public int getNumNickles() {
		return numNickles;
	}

	public void setNumNickles(int numNickles) {
		this.numNickles = numNickles;
	}

	public int getNumDimes() {
		return numDimes;
	}

	public void setNumDimes(int numDimes) {
		this.numDimes = numDimes;
	}

}
