package logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Random Search implementation of Algorithm
 * Value to modify algorithm is number of repetitions, 0 means default which is (numberOfItems^2)*10
 * @author Kajetan Parzyszek
 * @see Algorithm
 */
public class RandomSearch extends Algorithm{
	
	/**
	 * Random number generator
	 */
	private Random rng;
	/** 
	 * Integer repetitions of main loop
	 */
	private int repetitions;
	
	/**
	 * Default constructor of RandomSearch, sets description of Algorithm, number of repetitions to default
	 * @see Algorithm
	 */
	public RandomSearch() {
		rng = new Random();
		repetitions = 0;
		setAlgorithmDescription("Random Search(Repetitions = " + (repetitions == 0 ? "default" : Integer.toString(repetitions)) + ")\n");
	}

	/**
	 * Implementation of abstract solve function from Algorithm
	 * @see Algorithm
	 */
	@Override
	public boolean solve() {
		if(instance.isEmpty()) {
			return false;
		}
		solution.clearSolution();
		if(repetitions == 0) {
			repetitions = instance.getItemsCount() * instance.getItemsCount() * 10;
		}
		
		for(int i = 0 ; i < repetitions ; i++) {
			ArrayList<Integer> leftToChoose = new ArrayList<Integer>();
			for(int j = 0 ; j < instance.getItemsCount() ; j++) {
				leftToChoose.add(j);
			}
			
			Solution tempSolution = new Solution();
			while(!leftToChoose.isEmpty()) {
				int randomPick = rng.nextInt(leftToChoose.size());
				tempSolution.addItem(instance.getItem(leftToChoose.get(randomPick)), leftToChoose.get(randomPick));
				leftToChoose.remove(randomPick);
				if(!isViableSolution(tempSolution)) {
					tempSolution.removeLastItem();
				}
			}
			
			if(tempSolution.getValue() > solution.getValue()) {
				solution = tempSolution;
			}
		}
		
		return true;
	}
	
	/**
	 * Sets number of repetitions of algorithms main loop
	 * @param number 0 - default
	 * @return <b>boolean</b> true if success 
	 */
	public boolean setRepetitions(int number) {
		if(isValidRepetitions(number)) {
			repetitions = number;
			setAlgorithmDescription("Random Search(Repetitions = " + (repetitions == 0 ? "default" : Integer.toString(repetitions)) + ")\n");
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks whether given number is a valid number of repetitions
	 * @param number number of repetitions
	 * @return <b>boolean</b> true if valid
	 */
	private boolean isValidRepetitions(int number) {
		if(number > 0) {
			return true;
		} else {
			return false;
		}
	}
}
