package logic;

import java.util.ArrayList;
import java.util.List;

public class DynamicThreads extends Algorithm{
	private int threads = 5;

	/**
	 * Default constructor of BruteForceThreads, sets description of Algorithm
	 * @see Algorithm
	 */
	// A utility function that returns maximum of two integers 
	public DynamicThreads() {
		setAlgorithmDescription("Dynamic Programming Threads\n");
	}

	public void setThreads(int threadsCount) {
		this.threads = threadsCount;
	}

	@Override
	public boolean solve() {
		float bestSolution = Integer.MAX_VALUE;
		if(instance.isEmpty()) {
			return false;
		}
		
		solution.clearSolution();
		if(threads==1) {
			Thread thread;
			DynamicCalculation dynamicCalculation = new DynamicCalculation(instance, 0, 0);
			thread = new Thread(dynamicCalculation);
			thread.start();
			try {
				thread.join();
				bestSolution = dynamicCalculation.getBestSolution();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if(threads<4) {
			List<Item> items = new ArrayList<Item>(instance.getItems());
			int lightestItem = 0;
			int lightestItemWeight = Integer.MAX_VALUE;
			for(int i = 0; i<items.size(); i++) {
				if(items.get(i).getWeight()<lightestItemWeight) {
					lightestItemWeight = items.get(i).getWeight();
					lightestItem = i;
				}
			}
			float lightestItemValue = items.get(lightestItem).getValue();
			items.remove(lightestItem);
			Instance newInstance = new Instance((ArrayList<Item>) items);
			newInstance.setMaximumWeight(instance.getMaximumWeight());
			DynamicCalculation dynamicCalculation1 = new DynamicCalculation(newInstance, 0, 0);
			Thread thread1 = new Thread(dynamicCalculation1);
			thread1.start();
			DynamicCalculation dynamicCalculation2 = new DynamicCalculation(newInstance, lightestItemValue, lightestItemWeight);
			Thread thread2 = new Thread(dynamicCalculation2);
			thread2.start();
			try {
				thread1.join();
				thread2.join();
				bestSolution = Float.max(dynamicCalculation1.getBestSolution(), dynamicCalculation2.getBestSolution());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			List<Item> items = new ArrayList<Item>(instance.getItems());
			int lightestItem = 0;
			int lightestItemWeight = Integer.MAX_VALUE;
			int secondLightestItem = 0;
			int secondLightestItemWeight = Integer.MAX_VALUE;
			for(int i = 0; i<items.size(); i++) {
				if(items.get(i).getWeight()<lightestItemWeight) {
					secondLightestItemWeight = lightestItemWeight;
					secondLightestItem = lightestItem;
					lightestItemWeight = items.get(i).getWeight();
					lightestItem = i;

				}
				else if(items.get(i).getWeight()<secondLightestItemWeight) {
					secondLightestItemWeight = items.get(i).getWeight();
					secondLightestItem = i;
				}
			}
			float lightestItemValue = items.get(lightestItem).getValue();
			float secondLightestItemValue = items.get(lightestItem).getValue();
			if(lightestItem>secondLightestItem) {
				items.remove(lightestItem);
				items.remove(secondLightestItem);
			}
			else {
				items.remove(secondLightestItem);
				items.remove(lightestItem);
			}
			
			Instance newInstance = new Instance((ArrayList<Item>) items);
			newInstance.setMaximumWeight(instance.getMaximumWeight());
			DynamicCalculation dynamicCalculation00 = new DynamicCalculation(newInstance, 0, 0);
			Thread thread00 = new Thread(dynamicCalculation00);
			thread00.start();
			
			DynamicCalculation dynamicCalculation01 = new DynamicCalculation(newInstance, lightestItemValue, lightestItemWeight);
			Thread thread01 = new Thread(dynamicCalculation01);
			thread01.start();
			
			DynamicCalculation dynamicCalculation10 = new DynamicCalculation(newInstance, secondLightestItemValue, secondLightestItemWeight);
			Thread thread10 = new Thread(dynamicCalculation10);
			thread10.start();
			
			DynamicCalculation dynamicCalculation11 = new DynamicCalculation(newInstance, 
					secondLightestItemValue + lightestItemValue, secondLightestItemWeight + lightestItemWeight);
			Thread thread11 = new Thread(dynamicCalculation11);
			thread11.start();
			
			try {
				thread00.join();
				thread01.join();
				thread10.join();
				thread11.join();
				bestSolution = Float.max(Float.max(dynamicCalculation00.getBestSolution(), dynamicCalculation01.getBestSolution()),
						Float.max(dynamicCalculation10.getBestSolution(), dynamicCalculation11.getBestSolution()));
				System.out.println("bestSolution:" + bestSolution);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		//System.out.println(bestSolution);
		return true;
	}
}

class DynamicCalculation implements Runnable{
	float bestSolution;
	float valueOfItemsAlreadyIncluded;
	int weightOfItemsAlreadyIncluded;
	
	Instance instance;
	public DynamicCalculation (Instance instance, float valueOfItemsAlreadyIncluded, int weightOfItemsAlreadyIncluded) {
		this.instance = instance;
		this.valueOfItemsAlreadyIncluded = valueOfItemsAlreadyIncluded;
		this.weightOfItemsAlreadyIncluded = weightOfItemsAlreadyIncluded;
	}
	public void run() {
		bestSolution = knapSack(instance.getMaximumWeight() - weightOfItemsAlreadyIncluded, 
				instance.getItemWeights(), instance.getItemValues(),instance.getItemsCount());
		//System.out.println(bestSolution); 
	}
	
	public float getBestSolution() {
		return bestSolution + valueOfItemsAlreadyIncluded;
	}
	static float max(float a, float b) { return (a > b)? a : b; } 
	  
	// Returns the maximum value that can be put in a knapsack of capacity W 
	
	static float knapSack(int W, int wt[], float val[], int n) 
	{ 
	   // Base Case 
	   if (n == 0 || W == 0) 
	       return 0; 
	      
	   // If weight of the nth item is more than Knapsack capacity W, then 
	   // this item cannot be included in the optimal solution 
	   if (wt[n-1] > W) 
	      return knapSack(W, wt, val, n-1); 
	      
	   // Return the maximum of two cases:  
	   // (1) nth item included  
	   // (2) not included 
	   else return max( val[n-1] + knapSack(W-wt[n-1], wt, val, n-1), knapSack(W, wt, val, n-1) ); 
	}
}

