package logic;

/**
 * Brute Force Threads implementation of Algorithm
 * @author Kajetan Parzyszek
 * @see Algorithm
 */
public class BruteForceThreads extends Algorithm{
	private int threads = 5;

	/**
	 * Default constructor of BruteForceThreads, sets description of Algorithm
	 * @see Algorithm
	 */
	public BruteForceThreads() {
		setAlgorithmDescription("Brute Force Threads\n");
	}

	public void setThreads(int threadsCount) {
		this.threads = threadsCount;
	}

	@Override
	public boolean solve() {
		if(instance.isEmpty()) {
			return false;
		}
		solution.clearSolution();

		double maxValue = Math.pow(2, instance.getItemsCount());
		int range = (int) maxValue/threads;
		Thread[] threadsArray = new Thread[threads];
		BFCalculation[] calcArray = new BFCalculation[threads];

		for(int i = 0 ; i < threads ; i++) {
			calcArray[i] = new BFCalculation(i * range, ((i + 1) * range) - 1, instance);
			threadsArray[i] = new Thread(calcArray[i]);
			threadsArray[i].start();
		}

		for(int i = 0 ; i < threads ; i++) {
			try {
				threadsArray[i].join();
				if(calcArray[i].getBestSolution().getValue() > solution.getValue()) {
					solution = calcArray[i].getBestSolution();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		
		return true;
	}
}

class BFCalculation implements Runnable{

	int beg;
	int end;
	Instance instance;
	Solution bestSolution = new Solution();

	public BFCalculation(int beg, int end, Instance instance) {
		this.beg = beg;
		this.end = end;
		this.instance = instance;
	}

	public void run() {
		System.out.println("test5");
		for(int i = beg ; i < end ; i++) {
			Solution tempSolution = new Solution();
			String currentSolutionArray = Integer.toBinaryString(i);

			// for every item check if its taken and add to tempSolution
			for(int k = 0 ; k < instance.getItemsCount() ; k++) {
				if(k >= currentSolutionArray.length()) {
					break;
				}
				if(currentSolutionArray.charAt(k) == '1') {
					tempSolution.addItem(instance.getItem(k), k);
				}
			}

			// check if new solution is viable and if its better choose it as new solution
			if(isViableSolution(tempSolution) && tempSolution.getValue() > bestSolution.getValue()) {
				bestSolution = tempSolution;
			}
		}
	}

	public Solution getBestSolution() {
		return bestSolution;
	}

	private boolean isViableSolution(Solution solution) {
		return  solution.getWeight() <= instance.getMaximumWeight();
	}
}
