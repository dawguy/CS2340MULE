package managers;

import gameObjects.Map;

/**
 * This class will manage the game logic as well as contain the PlayerManager for the entire game.
 * @author antonio
 *
 */
public class GameManager {
	
	public static enum Difficulty {
		STANDARD, TOURNAMENT 	
	};
	
	private final Difficulty DEFAULT_DIFFICULTY= Difficulty.STANDARD;
	
	private PlayerManager players;
	
	private Difficulty difficulty;
	
	private Map map;
	
	public GameManager(){
		super();
		players = new PlayerManager();
	}
	
	public GameManager(PlayerManager pm){
		this();
		players = pm;
	}
	
	public void setDifficulty(String s){
		if(s.toUpperCase().equals(Difficulty.TOURNAMENT)){
			difficulty = Difficulty.TOURNAMENT;
		} else{
			difficulty = DEFAULT_DIFFICULTY;
		}
	}
	
	public void setMap(String s){
		if(s.toLowerCase().equals("random")){
			map = new Map(true);
		} else{
			map = new Map();
		}
	}
	
	public String toString(){
		String s = "";
		s += "DIFFICULTY : " + difficulty + "\n";
		s += "MAP : \n" + map.toString() + "\n";
		s += players.toString();
		return s;
	}
}
