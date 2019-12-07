/**
 * 
 */
package logic;

/** Container class for basic information about item like it's weight and value
 * @author Kajetan Parzyszek
 */
public class Item {
	/**
	 * Integer weight of item
	 */
	private int weight;
	/**
	 * Float value of item
	 */
	private float value;
	
	/**
	 * Constructor of Item that sets its basic information
	 * @param weight weight of item
	 * @param value value of item
	 */
	public Item(int weight, float value) {
		this.weight = weight;
		this.value = value;
	}

	/**
	 * Returns weight of Item
	 * @return <b>int</b> 
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Returns value of Item
	 * @return <b>float</b>
	 */
	public float getValue() {
		return value;
	}
}
