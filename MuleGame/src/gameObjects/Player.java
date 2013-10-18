package gameObjects;

import com.badlogic.gdx.graphics.Color;

/**
 * Represents a single player in the MULE game. It will keep track of its own data and allow
 * for the modification of its data through methods.
 * 
 * @author antonio
 * @version 1.0
 *
 */
public class Player {
	
	private static enum Race {
		HUMAN, FLAPPER
	};
	
	private Race race;
	private String name;
	private Color color;
	private Resource resources;
	
	private int money;
	
	public Player(){
		super();
		name = "";
		color = null;
		race = Race.HUMAN;
		setMoney();
		resources = new Resource();
	}
	
	public Player(String n, Color c){
		this();
		name = n;
		color = c;
	}
	
	public Player(String name, String race, Color c){
		this(name, c);
		this.setRace(race);
		setMoney();
	}
	
	private void setMoney(){
		if(race.equals(Race.FLAPPER)){
			money = 1600;
		} else if(race.equals(Race.HUMAN)){
			money = 600;
		} else{
			money = 1000;
		}
	}
	
	public int getMoney(){
		return money;
	}
	
	/**
	 * This method returns the current score, and currently that is just money
	 * @return the current score.
	 */
	public int getScore(){
		return money;
	}
	
	public void incrementMoney(int i){
		money += i;
	}
	
	public void setMoney(int i){
		money = i;
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
	
	public void spendResources(int resourceType, int amount){
		resources.spendResource(resourceType, amount);
	}
	
	public void gainResources(int resourceType, int amount){
		resources.gainResource(resourceType, amount);
	}
	
	public int getNumberOfResource(int resource){
		return resources.getResource(resource);
	}
	
	public boolean canBuy(Tile t){
		if(t.getCost() <= money){
			return true;
		}
		return false;
	}
}
