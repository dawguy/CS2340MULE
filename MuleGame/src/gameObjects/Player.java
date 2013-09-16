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
		HUMAN
	};
	
	private Race race;
	private String name;
	private Color color;
	
	public Player(){
		name = "";
		color = null;
		race = Race.HUMAN;
	}
	
	public Player(String n, Color c){
		name = n;
		color = c;
	}
}