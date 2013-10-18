package managers;

import gameObjects.Player;
import gameObjects.Resource;

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
	public Player[] getPlayerOrder(){
		Player[] order = new Player[players.size()];
		for(int i = 0 ; i < players.size() ; i ++){
			order[i] = players.get(i);
		}
		
		Player temp = null;
		
		//Sort the array
		for(int i = 0 ; i < players.size() ; i ++){
			for(int j = i ; j < players.size() ; j++){
				int s1 = order[i].getScore();
				int s2 = order[j].getScore();
				if(s1 > s2){
					//swap the players
					temp = order[i];
					order[i] = order[j];
					order[j] = temp;
				}
			}
		}
		return order;
	}
	
	
	public int getCurrentPlayerTime(int requirements){
		Player p = players.get(currentPlayer);
		int time = 50;
		int food = p.getNumberOfResource(Resource.RESOURCE_FOOD);
		if(food == 0){
			time = 5;
		} else if(food < requirements){
			time = 30;
		}
		return time;
	}
}
