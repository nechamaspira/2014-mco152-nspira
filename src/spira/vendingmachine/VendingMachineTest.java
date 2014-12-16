package spira.vendingmachine;

import java.io.IOException;
import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {

	Inventory invent = new Inventory();
	Money bank = new Money(2, 2, 2, 2);
	Money additional = new Money(1, 1, 1, 1);
	VendingMachine vm;
	DecimalFormat df = new DecimalFormat("$##0.00");

	@Test
	public void testPay() throws IOException {
		// Inventory invent = new Inventory();
		invent.load("./inventory.txt");
		// Money bank = new Money(2,2,2,2);
		// Money additional = new Money(1,1,1,1);
		this.vm = new VendingMachine(invent, bank);
		Assert.assertEquals(1.40, this.vm.pay(additional), .00);

	}

	@Test
	public void testBuy() throws IOException, CodeNotFoundException,
			NotEnoughPaidException, NotEnoughChangeException {
		// Inventory invent = new Inventory();
		invent.load("./inventory.txt");
		// Money bank = new Money(2,2,2,2);
		// Money additional = new Money(1,1,1,1);
		this.vm = new VendingMachine(invent, bank);
		vm.pay(additional);

		Money change = vm.buy("B02");
		int quantity = invent.get("B02").getQuantity();
		Assert.assertEquals(.10, change.getTotal(), .00);
		Assert.assertEquals(2, quantity);
		Assert.assertEquals(0, vm.getPaid().getTotal(), .00);
		
		Assert.assertEquals(4.10, vm.getBank().getTotal(), .00);


	}

	@Test
	public void testBuy2() throws IOException, CodeNotFoundException,
			NotEnoughPaidException, NotEnoughChangeException {
		invent.load("./inventory.txt");
		Money bank = new Money(0, 0, 0, 60);
		Money additional = new Money(2, 0, 0, 0);
		this.vm = new VendingMachine(invent, bank);
		this.vm.pay(additional);

		Money change = vm.buy("B02");
		int quantity = invent.get("B02").getQuantity();
		Assert.assertEquals(.70, Math.round(change.getTotal() * 100.0) / 100.0,
				.00);
		Assert.assertEquals(2, quantity);
		Assert.assertEquals(14, change.getNumNickles());

		Assert.assertEquals(0, vm.getPaid().getTotal(), .00);

	}

	@Test
	public void testBuyThrowsCodeNotFoundException() throws IOException,
			NotEnoughPaidException, NotEnoughChangeException {
		invent.load("./inventory.txt");
		this.vm = new VendingMachine(invent, bank);
		try {
			vm.buy("B09");
			Assert.fail("CodeNotFoundException should  be thrown here");
		} catch (CodeNotFoundException e) {
			// it should go here
		}

	}

	@Test
	public void testBuyThrowsNotEnoughPaidException() throws IOException,
			CodeNotFoundException, NotEnoughChangeException {
		invent.load("./inventory.txt");
		this.vm = new VendingMachine(invent, bank);
		vm.pay(additional);
		try {
			vm.buy("A01");
			Assert.fail("NotEnoughPaidException should  be thrown here");
		} catch (NotEnoughPaidException e) {
			// it should go here
		}

	}

	@Test
	public void testBuyThrowsNotEnoughChangeException() throws IOException,
			CodeNotFoundException, NotEnoughPaidException {
		invent.load("./inventory.txt");
		Money bank2 = new Money(1, 4, 3, 0);
		Money additional2 = new Money(1, 0, 3, 0);

		this.vm = new VendingMachine(invent, bank2);
		vm.pay(additional2);
		try {
			vm.buy("C03");
			Assert.fail("NotEnoughChangeException should  be thrown here");
		} catch (NotEnoughChangeException e) {
			// it should go here
		}

	}

	@Test
	public void testBuyThrowsNotEnoughChangeException2() throws IOException,
			CodeNotFoundException, NotEnoughPaidException {
		invent.load("./inventory.txt");
		Money bank2 = new Money(7, 0, 0, 0);
		Money additional2 = new Money(2, 0, 0, 0);

		this.vm = new VendingMachine(invent, bank2);
		vm.pay(additional2);
		try {
			vm.buy("A01");
			Assert.fail("NotEnoughChangeException should  be thrown here");
		} catch (NotEnoughChangeException e) {
			// it should go here
		}

	}

}
