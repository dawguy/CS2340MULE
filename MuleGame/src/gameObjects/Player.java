package gameObjects;

import java.util.Arrays;

import com.badlogic.gdx.graphics.Color;

/**
 * Represents a single player in the MULE game. It will keep track of its own data and allow
 * for the modification of its data through methods.
 * 
 * @author antonio
 * @version 1.0
 *
 */
public class Player implements Comparable{
	
	private static enum Race {
		HUMAN, FLAPPER
	};
	
	private Race race;
	private String name;
	private Color color;
	private Resource resources;
	private int muleType = -1;
	private int playerNumber = -1;
	
	public Player(int playerNumber){
		super();
		name = "";
		color = null;
		race = Race.HUMAN;
		resources = new Resource();
		setMoney();
		this.playerNumber = playerNumber;
	}
	
	public Player(String n, Color c, int playerNumber){
		this(playerNumber);
		name = n;
		color = c;
	}
	
	public Player(String name, String race, Color c, int playerNumber){
		this(name, c,playerNumber);
		this.setRace(race);
		setMoney();
	}
	
	public Player(String name, String race, float R, float G, float B, int foo, int enr,int ore,int crys,int pNum, int mons){
		this(name,race,new Color(R,G,B, (float) 1.0),pNum);
		resources.gainResource(0,mons);
		resources.gainResource(1, foo);
		resources.gainResource(2,enr);
		resources.gainResource(3, ore);
		resources.gainResource(4,crys);
	}
	
	private void setMoney(){
		if(race.equals(Race.FLAPPER)){
			resources.gainResource(0,1600);
		} else if(race.equals(Race.HUMAN)){
			resources.gainResource(0, 600);
		} else{
			resources.gainResource(0, 1000);
		}
	}
	
	public int getMoney(){
		return resources.getResource(0);
	}
	
	/**
	 * This method returns the current score, and currently that is just resources.gainResource(0,
	 * @return the current score.
	 */
	public int getScore(){
		return resources.getResource(0);
	}
	
	public void incrementMoney(int i){
		resources.gainResource(0, i);
	}
	
	public void setMoney(int i){
		resources.gainResource(0, i);
	}
	
	public boolean setRace(String s){
		if(s.toLowerCase().equals("human")){
			race = Race.HUMAN;
			return true;
		} else if(s.toLowerCase().equals("flapper")){
			race = Race.FLAPPER;
			return true;
		}
		return false;
	}
	
	public void setName(String s){
		name = s;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		String s = "";
		s += "NAME : " + name;
		s += "\t RACE : " + race;
		s += "\t COLOR : " + color;
		s += "\n";
		return s;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void loseResources(int resourceType, int amount){
		resources.loseResource(resourceType, amount);
	}
	
	public int spendResources(int resourceType, int amount){
		return resources.spendResource(resourceType, amount);
	}
	
	public void gainResources(int resourceType, int amount){
		resources.gainResource(resourceType, amount);
	}
	
	public void buyResources(int resourceType){
		
	}
	
	public int getNumberOfResource(int resource){
		return resources.getResource(resource);
	}
	
	public boolean canBuy(Tile t){
		if(t.getCost() <= resources.getResource(0)){
			return true;
		}
		return false;
	}
	
	public int getMule(){
		return muleType;
	}
	
	public void setMule(int i){
		muleType = i;
	}

	@Override
	public int compareTo(Object player2) {
		if(player2 instanceof Player){
			int player1Money = resources.getResource(Resource.RESOURCE_MONEY);
			int player2Money = ((Player) player2).getMoney();
			if(player1Money == player2Money){
				return 0;
			} else if(player1Money > player2Money){
				return 1;
			} else{
				return -1;
			}
		}
		return 0;
	}
	
	public String getRace(){
		if(race.equals(Race.HUMAN)){
			return "Human";
		} else if(race.equals(Race.FLAPPER)){
			return "Flapper";
		}
		return "Human";
	}
	
	public int getPlayerNumber(){
		return playerNumber;
	}
}
