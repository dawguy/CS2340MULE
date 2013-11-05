package managers;

import gameObjects.Player;
import gameObjects.Resource;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	/**
	 * Gets the next player in line.
	 */
	public void nextPlayer(){
		currentPlayer = (currentPlayer + 1) % players.size();	//Shouldn't worry about player score. That will be handled by gameManager
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
	
	public int getNumberOfPlayers(){
		return players.size();
	}
	
	public Player getPlayer(int i){
		return players.get(i);
	}
	
	/**
	 * This gets the current player order for the turn. It returns an array where player[0] is the first
	 * player, and player[player.length - 1] is the last player to go.
	 * @return an array containing the players order
	 */
	public Player[] updatePlayerOrder(){
		Player[] order = new Player[players.size()];
		for(int i = 0 ; i < players.size() ; i ++){
			order[i] = players.get(i);
		}
		
		Arrays.sort(order);
		
		for(int i = 0 ; i < order.length ; i ++){
			players.set(i, order[i]);
		}
		
		return order;
	}
}
