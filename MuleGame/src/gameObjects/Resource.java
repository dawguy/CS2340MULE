package gameObjects;
/**
 * Keeps track of resources of an individual player.
 * @author David Wright
 *
 */
public class Resource {
	int[] resources = new int[6];
	
	public static final int RESOURCE_MONEY = 0;
	public static final int RESOURCE_FOOD = 1;
	public static final int RESOURCE_ENERGY = 2;
	public static final int RESOURCE_ORE = 3;
	public static final int RESOURCE_CRYSTITE = 4;
	public static final int RESOURCE_MULE = 5;
	
	public Resource(){
		for(int i = 0; i < resources.length; i++){
			resources[i] = 0;
		}
		resources[1] = 3;
	}
	
	/**
	 * There must be enough resources available in order spend them
	 * @param resourceType
	 * @param amount
	 * @return amount of resources lost
	 */
	public int spendResource(int resourceType, int amount){
		if(resourceType > resources.length) throw new NullPointerException();
		if(resources[resourceType] - amount < 0){
			System.out.println("NOT ENOUGH RESOURCES");
			return 0;
		} else {
			resources[resourceType] -= amount;
			return amount;
		}
	}
	
	/**
	 * If the player does not have enough resources they will lose them all.
	 * @param resourceType
	 * @param amount
	 * @return amount of resources lost
	 */
	public int loseResource(int resourceType, int amount){
		if(resourceType > resources.length) throw new NullPointerException();
		if(resources[resourceType] - amount < 0){
			int amountLost = amount - resources[resourceType];
			resources[resourceType] = 0;
			return amountLost;
		} else {
			resources[resourceType] -= amount;
			return amount;
		}
	}
	
	/**
	 * Gains resources
	 * @param resourceType
	 * @param amount
	 * @return
	 */
	public int gainResource(int resourceType, int amount){
		resources[resourceType] += amount;
		return amount;
	}
	
	/**
	 * Checks amount of resource
	 * @param resourceType
	 * @return amount of resource
	 */
	public int getResource(int resourceType){
		return resources[resourceType];
	}
}
