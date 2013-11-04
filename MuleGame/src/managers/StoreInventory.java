package managers;

import gameObjects.Player;
import gameObjects.Resource;

/**
 * This class keeps track of the stock of the store
 * @author antonio
 *
 */
public class StoreInventory {
	
	private int mules;
	
	private int food;
	private int energy;
	private int ore;
	
	private final int BEGINNER_FOOD = 16;
	private final int BEGINNER_ENERGY = 16;
	private final int BEGINNER_MULE = 25;
	private final int BEGINNER_ORE = 0;
	
	public StoreInventory(){
		food = BEGINNER_FOOD;
		energy = BEGINNER_ENERGY;
		ore = BEGINNER_ORE;
		mules = BEGINNER_MULE;
	}
	
	public int getFood(){
		return food;
	}
	
	public int getEnergy(){
		return energy;
	}
	
	public int getOre(){
		return ore;
	}
	
	public int getMules(){
		return mules;
	}
	
	public boolean buyMule(Player p){
		if(mules <= 0 || p.getMoney() < Resource.MULE_PRICE){
			return false;
		}
		mules--;
		p.incrementMoney(-1 * Resource.MULE_PRICE);
		return true;
	}
	
	public boolean buyFood(Player p){
		if(food <= 0 || p.getMoney() < Resource.FOOD_PRICE){
			return false;
		}
		food--;
		p.incrementMoney(-1 * Resource.FOOD_PRICE);
		p.gainResources(Resource.RESOURCE_FOOD, 1);
		return true;
	}
	
	public boolean buyEnergy(Player p){
		if(energy <= 0 || p.getMoney() < Resource.ENERGY_PRICE){
			return false;
		}
		energy--;
		p.incrementMoney(-1 * Resource.ENERGY_PRICE);
		p.gainResources(Resource.RESOURCE_ENERGY, 1);
		return true;
	}
	
	public boolean buyOre(Player p){
		if(ore <= 0 || p.getMoney() < Resource.ORE_PRICE){
			return false;
		}
		ore--;
		p.incrementMoney(-1 * Resource.ORE_PRICE);
		p.gainResources(Resource.RESOURCE_ORE, 1);
		return true;
	}
	
	public void sellOre(Player p){
		if(p.getNumberOfResource(Resource.RESOURCE_ORE) <= 0){
			return;
		}
		p.incrementMoney(Resource.ORE_PRICE);
		p.loseResources(Resource.RESOURCE_ORE, 1);
		ore++;
	}
	
	public void sellEnergy(Player p){
		if(p.getNumberOfResource(Resource.RESOURCE_ENERGY) <= 0){
			return;
		}
		p.incrementMoney(Resource.ENERGY_PRICE);
		p.loseResources(Resource.RESOURCE_ENERGY, 1);
		energy++;
	}
	
	public void sellFood(Player p){
		if(p.getNumberOfResource(Resource.RESOURCE_FOOD) <= 0){
			return;
		}
		p.incrementMoney(Resource.FOOD_PRICE);
		p.loseResources(Resource.RESOURCE_FOOD, 1);
		food++;
	}
	
	public void sellMule(Player p){
		mules++;
	}
}
