package managers;

import gameObjects.Player;
import gameObjects.Resource;

/**
 * This class keeps track of the stock of the store
 * @author antonio, Eric Rabinowitz
 *
 */
public class StoreInventory {
	
	public static final int ORE_MULE = 0;
	public static final int FOOD_MULE = 1;
	public static final int ENERGY_MULE = 2;	
	public static final int FOOD_INDEX = 3;
	public static final int ENERGY_INDEX = 4;
	public static final int ORE_INDEX = 5;

	private int[] resources = new int[6];
	private int[] prices = new int[6];
	
	private static final int BEGINNER_FOOD = 16;
	private static final int BEGINNER_ENERGY = 16;
	private static final int BEGINNER_MULE = 25;
	private static final int BEGINNER_ORE = 0;
	private static final int MULE_PRICE = 100;
	private static final int FOOD_PRICE = 30;
	private static final int ENERGY_PRICE = 25;
	private static final int ORE_PRICE = 50;
	
	public StoreInventory(){
		resources[ORE_MULE] = BEGINNER_MULE;
		prices[ORE_MULE] = MULE_PRICE;
		resources[FOOD_MULE] = BEGINNER_MULE;
		prices[FOOD_MULE] = MULE_PRICE;
		resources[ENERGY_MULE] = BEGINNER_MULE;
		prices[ENERGY_MULE] = MULE_PRICE;
		
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
		//Handling mules differently
		//0 = Ore Mule
		//1 = Food Mule
		//2 = Energy Mule
		if(i < 3) {
			if(p.getMule() != -1) return false;
			p.setMule(i);
			//5 is the # for the MULE_ORE in resource tab
			p.incrementMoney(-1 * prices[i]);
			p.gainResources(getMatchingPlayerResourceIndex(i), 1);
			return true;
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
		//Handling seperately for mules
		if(i < 3) {
			if(p.getMule() == -1) return;
			p.setMule(-1);
			//5 is the # for the MULE_ORE in resource tab
			p.incrementMoney(prices[i]);
			p.loseResources(getMatchingPlayerResourceIndex(i), 1);
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
			case 0: resource_index =  Resource.RESOURCE_MULE_ORE;
					break;
			case 1: resource_index =  Resource.RESOURCE_MULE_FOOD;
				break;
			case 2: resource_index =  Resource.RESOURCE_MULE_ENERGY;
				break;			
			case 3: resource_index =  Resource.RESOURCE_FOOD;
					break;
			case 4: resource_index =  Resource.RESOURCE_ENERGY;
					break;
			case 5: resource_index =  Resource.RESOURCE_ORE;
					break;
		}
		return resource_index;
	}
}
