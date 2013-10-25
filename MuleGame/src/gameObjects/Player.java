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
	
	public Player(){
		super();
		name = "";
		color = null;
		race = Race.HUMAN;
		resources = new Resource();
		setMoney();
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
		if(t.getCost() <= resources.getResource(0)){
			return true;
		}
		return false;
	}
}
