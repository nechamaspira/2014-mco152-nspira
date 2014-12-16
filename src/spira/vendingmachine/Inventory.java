package spira.vendingmachine;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
	private Map<String, Item> map;
	private ArrayList<Item> inv;

	public Inventory() {
		this.inv = new ArrayList<Item>();
		this.map = new HashMap<String, Item>();

	}

	public void load(String inventoryFilename) throws IOException {
		Scanner inputFile = new Scanner(new File(inventoryFilename));

		while (inputFile.hasNextLine()) {
			String[] i = inputFile.nextLine().split(",");
			double doub = Double.parseDouble(i[2]);
			int numb = Integer.parseInt(i[3]);
			Item value = new Item(i[0], i[1], doub, numb);
			this.inv.add(value);
			String key = i[0];
			this.map.put(key, value);
		}
		inputFile.close();
	}

	/**
	 * 
	 * @param code
	 * @return the item or null if an item with that code doesn't exist
	 */

	public Item get(String code) {
		Item item = null;

		if (this.map.containsKey(code)) {
			item = this.map.get(code);
		}

		return item;
	}

	/**
	 * 
	 * @param item
	 *            to add
	 */
	public void add(Item item) {
		if (!this.map.containsValue(item)) {
			this.map.put(item.getCode(), item);
		}
	}

	/**
	 * Removes one from quantity of the specified item
	 * 
	 * @param code
	 */
	public void removeOne(String code) throws CodeNotFoundException {
		boolean found = false;
		if (this.map.containsKey(code)) {
			found = true;
		}
		if (found) {
			this.map.get(code).setQuantity();
		} else {
			throw new CodeNotFoundException();
		}
	}

	/**
	 * 
	 * @param code
	 * @return true if the Item exists and there is 0 quantity, otherwise false.
	 */
	public boolean isEmpty(String code) {
		boolean there = false;
		if (this.map.containsKey(code) && this.map.get(code).getQuantity() == 0) {
			there = true;
		}

		return there;

	}

	public int getSize() {
		return this.map.size();
	}

	public Map<String, Item> getMap() {
		return this.map;
	}

	/**
	 * Lists the items in the inventory one per line in the format code name @
	 * price x quantity\n
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("$##0.00");

		StringBuilder info = new StringBuilder();
		for (int i = 0; i < inv.size(); i++) {
			info.append(inv.get(i).getCode());
			info.append(" ");
			info.append(inv.get(i).getName());
			info.append(" ");
			info.append("@");
			info.append(" ");
			info.append(df.format(inv.get(i).getPrice()));
			info.append(" ");
			info.append("x");
			info.append(" ");
			info.append(inv.get(i).getQuantity());
			info.append("\n");

		}
		return info.toString();
	}

	public Item get(int i) {
		return this.inv.get(i);
	}

}
