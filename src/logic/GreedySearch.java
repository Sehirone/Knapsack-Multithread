/**
 * 
 */
package logic;

import java.util.ArrayList;

/**
 * Greedy Search implementation of Algorithm
 * It operates in 3 different modes:
 * 1 - Items with Highest Value are chosen first
 * 2 - Items with Lowest Weight are chosen first
 * 3 - Items with Best Value/Weight Ratio are chosen first
 * @author Kajetan Parzyszek
 * @see Algorithm
 * @see Item
 */
public class GreedySearch extends Algorithm{
	/**
	 * Integer information of current working mode of GreedySearch
	 */
	private int mode;
	
	/**
	 * Default constructor of GreedySearch, sets description of Algorithm, mode to default
	 * @see Algorithm
	 */
	public GreedySearch() {
		mode = 0;
		setAlgorithmDescription("Greedy Search(Mode = " + modeNumberToString(mode) + ")\n");
	}
	
	/**
	 * Implementation of abstract solve function from Algorithm
	 * @see Algorithm
	 */
	@Override
	public boolean solve() {
		if(instance.isEmpty() || !isValidMode(mode)) {
			return false;
		}
		solution.clearSolution();
		
		ArrayList<Integer> leftToChoose = new ArrayList<Integer>();
		for(int j = 0 ; j < instance.getItemsCount() ; j++) {
			leftToChoose.add(j);
		}
		
		while(!leftToChoose.isEmpty()) {
			int bestPick = -1;
			switch(mode) {
				case 1:
					float highestValue = -1f;
					for(int i = 0 ; i < leftToChoose.size() ; i++) {
						if((instance.getItem(leftToChoose.get(i)).getValue() > highestValue)) {
							bestPick = i;
							highestValue = instance.getItem(leftToChoose.get(i)).getValue();
						}
					}
					break;
				case 2:
					int lowestWeight = Integer.MAX_VALUE;
					for(int i = 0 ; i < leftToChoose.size() ; i++) {
						if((instance.getItem(leftToChoose.get(i)).getWeight() < lowestWeight)) {
							bestPick = i;
							lowestWeight = instance.getItem(leftToChoose.get(i)).getWeight();
						}
					}
					break;
				case 3:
					float highestValueToWeight = -1f;
					for(int i = 0 ; i < leftToChoose.size() ; i++) {
						if(((instance.getItem(leftToChoose.get(i)).getValue()/instance.getItem(leftToChoose.get(i)).getWeight()) > highestValueToWeight)) {
							bestPick = i;
							highestValueToWeight = (instance.getItem(leftToChoose.get(i)).getValue()/instance.getItem(leftToChoose.get(i)).getWeight());
						}
					}
					break;
			}
			solution.addItem(instance.getItem(leftToChoose.get(bestPick)), leftToChoose.get(bestPick));
			leftToChoose.remove(bestPick);
			
			if(!isViableSolution(solution)) {
				solution.removeLastItem();
			}
		}	
		
		return true;
	}
	
	/**
	 * Sets working mode of GreedySearch
	 * @param mode should be value between 1 and 3 inclusive 
	 * @return <b>boolean</b> true if success
	 */
	public boolean setMode(int mode) {
		if(!isValidMode(mode)) {
			return false;
		} else {
			this.mode = mode;
			setAlgorithmDescription("Greedy Search(Mode = " + modeNumberToString(mode) + ")\n");
			return true;
		}
	}
	
	/** 
	 * Checks whether given mode is valid
	 * @param mode value to be checked for validity
	 * @return <b>boolean</b> true if valid
	 */
	private boolean isValidMode(int mode) {
		if(mode > 3 || mode < 1) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Converts given mode number to String description
	 * @param mode value to be converted
	 * @return <b>String</b>
	 */
	private String modeNumberToString(int mode) {
		if(!isValidMode(mode)) {
			return "undefined";
		}
		
		switch(mode) {
		case 1:
			return "Highest Value";
		case 2:
			return "Lowest Weight";
		case 3:
			return "Best Value/Weight Ratio";
		}
		
		return "Something's terribly wrong :(";
	}
}
