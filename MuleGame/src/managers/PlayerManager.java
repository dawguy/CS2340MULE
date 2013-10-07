package managers;

import gameObjects.Player;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

/**
 * This class will handle all of the players including the creation of them.
 * @author antonio
 *
 */
public class PlayerManager {

	private List<Player> players;
	private int currentPlayer = 0;
	
	public PlayerManager(){
		super();
		players = new ArrayList<Player>();
	}
	
	public PlayerManager(int size){
		this();
		for(int i = 0 ; i < size ; i++){
			players.add(new Player());
		}
	}
	
	public boolean addPlayer(Player p){
		players.add(p);
		return true;
	}
	
	public boolean addPlayer(String name, String race, Color c){
		players.add(new Player(name, race, c));
		return true;
	}
	
	public void nextPlayer(){
		currentPlayer = (currentPlayer + 1) % players.size();
	}
	
	public Player getCurrentPlayer(){
		return players.get(currentPlayer);
	}
	
	public int getCurrentPlayerNumber(){
		return currentPlayer;
	}
	
	public String toString(){
		String s = "";
		for(int i = 0 ; i < players.size() ; i ++){
			s += players.get(i).toString();
		}
		
		return s;
	}
}
