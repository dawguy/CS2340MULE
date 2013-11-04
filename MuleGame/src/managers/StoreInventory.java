package managers;

import gameObjects.Player;
import gameObjects.Resource;

/**
 * This class keeps track of the stock of the store
 * @author antonio, Eric Rabinowitz
 *
 */
public class StoreInventory {
	
	public static final int MULES_INDEX = 0;
	public static final int FOOD_INDEX = 1;
	public static final int ENERGY_INDEX = 2;
	public static final int ORE_INDEX = 3;

	private int[] resources = new int[4];
	private int[] prices = new int[4];
	
	private static final int BEGINNER_FOOD = 16;
	private static final int BEGINNER_ENERGY = 16;
	private static final int BEGINNER_MULE = 25;
	private static final int BEGINNER_ORE = 0;
	private static final int MULE_PRICE = 100;
	private static final int FOOD_PRICE = 30;
	private static final int ENERGY_PRICE = 25;
	private static final int ORE_PRICE = 50;
	
	public StoreInventory(){
		resources[MULES_INDEX] = BEGINNER_MULE;
		prices[MULES_INDEX] = MULE_PRICE;
		resources[FOOD_INDEX] = BEGINNER_FOOD;
		prices[FOOD_INDEX] = FOOD_PRICE;
		resources[ENERGY_INDEX] = BEGINNER_ENERGY;
		prices[ENERGY_INDEX] = ENERGY_PRICE;
		resources[ORE_INDEX] = BEGINNER_ORE;
		prices[ORE_INDEX] = ORE_PRICE;
	}
	
	public int getResourceAmount(int i){
		return resources[i];
	}

	public int getResourcePrice(int i){
		return prices[i];
	}
	
	public boolean buyResource(Player p, int i) {
		if(resources[i] <= 0 || p.getMoney() < prices[i]){
			return false;
		}
		resources[i]--;
		p.incrementMoney(-1 * prices[i]);
		p.gainResources(getMatchingPlayerResourceIndex(i), 1);
		return true;
	}

	public void sellResource(Player p, int i) {
		if(p.getNumberOfResource(getMatchingPlayerResourceIndex(i)) <= 0){
			return;
		}
		p.incrementMoney(prices[i]);
		p.loseResources(getMatchingPlayerResourceIndex(i), 1);
		resources[i]++;
	}

	/**
	 * Get Resource index for matching inventory index.
	 * @param storeIndex the resource index in StoreInventory
	 * @return the corresponding Resoure index
	 */
	private int getMatchingPlayerResourceIndex(int storeIndex) {
		int resource_index = 0;
		switch(storeIndex){
			case 0: resource_index =  0; //still need to figure out player MULES
					break;
			case 1: resource_index =  Resource.RESOURCE_FOOD;
					break;
			case 2: resource_index =  Resource.RESOURCE_ENERGY;
					break;
			case 3: resource_index =  Resource.RESOURCE_ORE;
					break;
		}
		return resource_index;
	}
}
