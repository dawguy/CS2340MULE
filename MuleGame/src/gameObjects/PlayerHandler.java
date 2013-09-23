package gameObjects;

/**
 * A class that will maintain all of the players and their actions.
 * @author antonio
 * @since 9/17/2013
 */
public class PlayerHandler {

	private Player[] players;
	
	public PlayerHandler(int numOfPlayers){
		players = new Player[numOfPlayers];
	}
	
	public PlayerHandler(){
		players = null;
	}
	
	/**
	 * Sets the number of players as well as recreates the array
	 * @param n the number of players
	 */
	public void setNumberOfPlayers(int n){
		players = new Player[n];
	}
	
	/**
	 * Adds a new player to the list and puts it in the first available
	 * spot. If there is no available spot return false.
	 * @param p the player being added
	 * @return
	 */
	public boolean addPlayer(Player p){
		if(players == null){
			return false;
		}
		for(int i = 0 ; players.length > i ; i ++){
			if(players[i] == null){
				players[i] = p;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds a player to a specific spot. If the index isn't within
	 * the array return false. If we are able to add the player, return true
	 * @param p the player being added to the handler
	 * @param n the index of the player being added (0 <= n < players.length)
	 * @return
	 */
	public boolean addPlayer(Player p, int n){
		if(n < 0 || n >= players.length){
			return false;
		}
		players[n] = p;
		return true;
	}
	
	/**
	 * Gets the backing array of all the players
	 * @return
	 */
	public Player[] getPlayers(){
		return players;
	}
	
	/**
	 * Gets the specific player at that index.
	 * @param n the index the user wants
	 * @return
	 */
	public Player getPlayer(int n){
		return players[n];
	}
}
