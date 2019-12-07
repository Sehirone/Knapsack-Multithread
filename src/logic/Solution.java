/**
 * 
 */
package logic;

import java.util.ArrayList;

/** Class containing basic information about solution like it's found weight, value and selected items<br>
 *  Depends on implementation of Item class
 * @author Kajetan Parzyszek
 * @see Item
 */
public class Solution {
	/**
	 * Total weight of solution
	 */
	private int weight;
	/**
	 * Total value of solution
	 */
	private float value;
	/**
	 * Array of selected items
	 */
	private ArrayList<Item> items;
	/**
	 * String representation of selected items
	 */
	private String chosenItems;
	
	/**
	 * Constructs default empty solution
	 */
	public Solution() {
		weight = 0;
		value = 0;
		chosenItems = "";
		items = new ArrayList<Item>();
	}
	
	/**
	 * Gets total weight of Solution
	 * @return <b>int</b> 
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Gets total value of Solution
	 * @return <b>float</b>
	 */
	public float getValue() {
		return value;
	}
	
	/**
	 * Adds Item to items list and increases total weight and value
	 * @param item Item to be added
	 * @param itemIndex index at which Item can be found in Instance
	 * @see Item
	 */
	public void addItem(Item item, int itemIndex) {
		items.add(item);
		weight += item.getWeight();
		value += item.getValue();
		chosenItems += Integer.toString(itemIndex) + " ";
	}
	
	/** 
	 * Removes last Item added to items list <br>
	 * decreases total weight and value
	 * @see Item
	 */
	public void removeLastItem() {
		weight -= items.get(items.size() - 1).getWeight();
		value -= items.get(items.size() - 1).getValue();
		chosenItems = chosenItems.substring(0, chosenItems.length() - 2);
		items.remove(items.size() - 1);
	}
	
	/**
	 * Clears Solutions Class variables, assigns default values 
	 */
	public void clearSolution() {
		items.clear();
		weight = 0;
		value = 0;
		chosenItems = "";
	}
	
	/**
	 * Gets items ids chosen in solution
	 * @return <b>String</b>
	 */
	public String getChosenItems() {
		return chosenItems;
	}
	
}
