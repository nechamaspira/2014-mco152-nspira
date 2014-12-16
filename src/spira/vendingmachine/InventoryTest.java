package spira.vendingmachine;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class InventoryTest {
	Inventory inventory = new Inventory();

	@Test
	public void testLoad() throws IOException {
		inventory.load("./inventory.txt");
		int invSize = inventory.getSize();
		Assert.assertEquals(5, invSize);
	}

	@Test
	public void testGet() throws NotEnoughChangeException, IOException {

		inventory.load("./inventory.txt");

		Item item = inventory.get("B03");
		Assert.assertEquals(null, item);
		Item item2 = inventory.get("B02");
		Item item3 = inventory.get(1);
		Assert.assertEquals(item3, item2);
	}

	@Test
	public void testAdd() throws DuplicateDataException, IOException {
		inventory.load("./inventory.txt");

		Item item = new Item("b08", "nosh", 1.05, 2);
		inventory.add(item);
		int newSize = inventory.getSize();
		Assert.assertEquals(6, newSize);

	}

	@Test
	public void testRemoveOne() throws CodeNotFoundException, IOException {
		inventory.load("./inventory.txt");

		inventory.removeOne("A01");
		int quantity = inventory.get("A01").getQuantity();
		Assert.assertEquals(4, quantity);
	}

	@Test
	public void testIsEmpty() throws CodeNotFoundException, IOException {
		inventory.load("./inventory.txt");

		boolean isThere = inventory.isEmpty("A01");
		Assert.assertFalse(isThere);
		boolean there = inventory.isEmpty("A07");
		Assert.assertFalse(there);
		inventory.get("C03").setQuantity();
		boolean isTrue = inventory.isEmpty("C03");
		Assert.assertTrue(isTrue);

	}

	@Test
	public void testToString() throws CodeNotFoundException, IOException {
		inventory.load("./inventory.txt");
		Assert.assertEquals(
				"A01 Candy Bar @ $1.55 x 5\nB02 Chips @ $1.30 x 3\nC03 Pretzels @ $1.00 x 1\nD04 Nuts @ $2.25 x 10\nE05 Gum @ $1.75 x 20\n",
				inventory.toString());
	}

}
