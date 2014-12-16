package spira.vendingmachine;

import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Test;

public class MoneyTest {

	Money moneyTest = new Money(10, 10, 10, 10);
	DecimalFormat df = new DecimalFormat("$##0.00");

	@Test
	public void testAdd() {
		Money money = new Money(5, 5, 5, 5);
		money.add(moneyTest);
		String regMoney = df.format(money.getTotal());

		Assert.assertEquals("$21.00", regMoney);

	}

	@Test
	public void testRemove() throws NotEnoughChangeException {
		Money money = moneyTest.remove(.25);
		String total = df.format(money.getTotal());
		String totalBank = df.format(moneyTest.getTotal());
		Assert.assertEquals("$0.25", total);
		Assert.assertEquals("$13.75", totalBank);

	}

	@Test
	public void testTotal() {

		String total = df.format(moneyTest.getTotal());
		Assert.assertEquals("$14.00", total);

	}

	@Test
	public void testRemoveThrowsNotEnoughChangeException() {
		Money money2 = new Money(0, 0, 0, 0);
		try {
			money2.remove(1.00);
			Assert.fail("NotEnoughChangeException should  be thrown here");
		} catch (NotEnoughChangeException e) {
			// it should go here

		}
	}
}
