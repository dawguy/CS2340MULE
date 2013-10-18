package managers;

import com.me.mygdxgame.Mule;

import gameObjects.Map;
import gameObjects.Player;
import gameObjects.Tile;

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
	
	private final int GUI_HEIGHT = 100;
	
	private final float MAP_PPU_X = Mule.WIDTH / 9;
	private final float MAP_PPU_Y = (Mule.HEIGHT - GUI_HEIGHT) / 5;
	
	public GameManager(){
		super();
		players = new PlayerManager();
	}
	
	public GameManager(PlayerManager pm){
		this();
		players = pm;
	}
	
	public int getNumberOfPlayers(){
		return players.getNumberOfPlayers();
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
			map = new Map(false);
		}
	}
	
	public void setPPU(){
		map.setPPU(MAP_PPU_X, MAP_PPU_Y);
	}
	
	public void setMap(Map m){
		map = m;
	}
	
	public Map getMap(){
		return map;
	}
	
	public String toString(){
		String s = "";
		s += "DIFFICULTY : " + difficulty + "\n";
		s += "MAP : \n" + map.toString() + "\n";
		s += players.toString();
		return s;
	}
	
	public boolean buyTile(int x, int y, Player p){
		Tile t = map.getMouseClickedTile(x, y);
		if(t == null){
			return false;
		}
		if(t.isOwned()){
			return false;
		}
		t.setOwner(p);
		return true;
	}
	
	/**
	 * This method buys a tile. (NOTE NOT A FREE TILE)
	 * @param x mouse click x pos
	 * @param y mouse click y pos
	 * @param p the player buying
	 * @param subtractMoney whether the player needs the money for it
	 * @return
	 */
	public boolean buyTile(int x, int y, Player p, boolean subtractMoney){
		Tile t = map.getMouseClickedTile(x, y);
		if(t == null){
			return false;
		}
		if(t.isOwned()){
			return false;
		}
		if( !p.canBuy(t)){
			return false;
		}
		t.setOwner(p, subtractMoney);
		return true;
	}
	
	public Player getPlayer(int i){
		return players.getPlayer(i);
	}
}
