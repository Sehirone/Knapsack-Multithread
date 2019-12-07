/**
 * 
 */
package logic;

import java.util.Random;

/**
 * Abstract class to be used as an interface for implementation of algorithms solving Knapsack Problem<br>
 * Depends on classes Instance and Solution<br>
 * Contains basic functions to be used by implemented algorithm like data loading and checking Solution<br>
 * Child classes need to implement function solve()
 * @author Kajetan Parzyszek
 * @see Instance
 * @see Solution
 *
 */
public abstract class Algorithm {
	
	/**
	 * Instance of problem to be solved
	 */
	protected Instance instance;
	/**
	 * Solution of solved problem
	 */
	protected Solution solution;
	/**
	 * String description of Algorithm
	 */
	protected String algorithmDescription;
	
	/**
	 * Creates basic instance of Algorithm with initialized Instance and Solution
	 */
	public Algorithm() {
		instance = new Instance();
		solution = new Solution();
	}
	
	/**
	 * Abstract function to be implemented in algorithms to be implemented using Algorithm class
	 * @return <b>boolean</b> saying whether solving succeeded
	 */
	abstract public boolean solve();
	
	/**
	 * Basic data loading function for all algorithms
	 * @param dataString format is "max_weight weight1 value1 weight2 value2 ... weight(n) value(n)"<br>
	 * @return <b>boolean</b> saying whether data loading succeeded
	 */
	public boolean loadData(String dataString) {
		instance.clearItems();
		instance.setMaximumWeight(-1);
		
		String[] splittedData = dataString.split(" ");
		if(splittedData.length % 2 != 1) {
			return false;
		}
		
		try {
			instance.setMaximumWeight(Integer.parseInt(splittedData[0]));
		} catch (NumberFormatException e) {
			return false;
		}

		int weight;
		float value;
		for(int i = 1 ; i < splittedData.length - 1 ; i+=2) {
			try {
				weight = Integer.parseInt(splittedData[i]);
				value = Float.parseFloat(splittedData[i+1]);
			} catch (NumberFormatException e) {
				return false;
			}
			instance.addItem(new Item(weight, value));
		}
		
		return true;
	}

	public boolean loadRandomData(int itemsCount) {
		Random r = new Random();
		for(int i = 0 ; i < itemsCount  ; i++) {
			int weight = r.nextInt(100) + 1;
			int value = r.nextInt(100) + 1;
			instance.addItem(new Item(weight, value));
		}
		instance.setMaximumWeight(r.nextInt(500) + 100);
		return true;
	}
	
	/**
	 * Function returning found solutions total value
	 * @return <b>float</b>
	 */
	public float getSolutionsValue() {
		return solution.getValue();
	}
	
	/**
	 * Function returning found solutions total weight
	 * @return <b>int</b>
	 */
	public int getSolutionsWeight() {
		return solution.getWeight();
	}
	
	/**
	 * Function returning found solutions ids of chosen items
	 * @return <b>String</b>
	 */
	public String getSolutionsChosenItems() {
		return solution.getChosenItems();
	}
	
	/** 
	 * Function returning description of working algorithm
	 * @return <b>String</b> should contain algorithms name and working mode
	 */
	public String getAlgorithmDescription() {
		return algorithmDescription;
	}
	
	/**
	 * Sets description of working algorithm, to be used in constructor of class implementing Algorithm
	 * @param desc should contain algorithms name and working mode
	 */
	protected void setAlgorithmDescription(String desc) {
		algorithmDescription = desc;
	}
	
	/** 
	 * Checks whether given solution is a viable one based on it's found weight and maximum weight
	 * @param solution solution to be checked whether is viable
	 * @return <b>boolean</b> saying whether solution is viable
	 * @see Solution
	 */
	protected boolean isViableSolution(Solution solution) {
		return  solution.getWeight() <= instance.getMaximumWeight();
	}
	
}
