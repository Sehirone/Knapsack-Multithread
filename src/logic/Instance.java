/**
 * 
 */
package logic;

import java.util.ArrayList;

/**
 * Instance class meant to contain basic information about Knapsack Problem. <br>
 * Stored are <b>Item ArrayList</b> and <b>maximum weight</b> that is allowed.
 * @author Kajetan Parzyszek
 * @see Item
 */
public class Instance {
	/**
	 * Array of items that represent Instance of knapsack problem
	 */
	private ArrayList<Item> items;
	/**
	 * Integer maximum weight allowed to be carried in Solution
	 */
	private int maximumWeight;
	
	/**
	 * Constructs default empty Instance object
	 */
	public Instance() {
		items = new ArrayList<Item>();
		maximumWeight = 0;
	}
	
	/**
	 * Returns Item contained within Instance at given index
	 * @param index requested index
	 * @return <b>Item</b> requested Item
	 * @see Item
	 */
	public Item getItem(int index) {
		return items.get(index);
	}
	
	/**
	 * Returns number of items contained within Instance
	 * @return <b>int</b> number of items contained within Instance
	 */
	public int getItemsCount() {
		return items.size();
	}

	/**
	 * Adds item to list of items contained within Instance
	 * 
	 * @param item to be added to Instance
	 * @see Item
	 */
	public void addItem(Item item) {
		items.add(item);
	}
	
	/**
	 * Clears items contained in instance
	 */
	public void clearItems() {
		items.clear();
	}

	/**
	 * Gets maximum allowed weight for given Instance
	 * @return <b>int</b> maximum allowed weight for given Instance
	 * 
	 */
	public int getMaximumWeight() {
		return maximumWeight;
	}

	/**
	 * Sets maximum allowed weight for given Instance
	 * @param maximumWeight maximum allowed weight for given Instance
	 */
	public void setMaximumWeight(int maximumWeight) {
		this.maximumWeight = maximumWeight;
	}
	
	/**
	 * Checks whether list Instance is empty
	 * @return <b>True</b> if list is empty <br> 
	 * 		   <b>False</b> if list is populated
	 *
	 */
	public boolean isEmpty() {
		return items.size() == 0;
	}
}
