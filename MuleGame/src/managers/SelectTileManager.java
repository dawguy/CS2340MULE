package managers;

import com.me.mygdxgame.Mule;

import gameObjects.Player;
import gameObjects.Tile;

/**
 * This class will handle the game logic for when the players get to buy tiles.
 * @author antonio
 *
 */
public class SelectTileManager {

	private Player currentPlayer;
	
	private int currentRound;
	private int playerNumber;
	
	private boolean startOfGame;
	
	private final int FREE_LAND_NUMBER_OF_ROUNDS = 2;
	
	public SelectTileManager(){
		super();
		currentPlayer = Mule.pm.getCurrentPlayer();
		currentRound = 1;
		playerNumber = 0;
		startOfGame = true;
	}
	
	public SelectTileManager(boolean start){
		this();
		startOfGame = start;
	}
	
	public boolean tilePicked(Tile t){
		return t.setOwner(currentPlayer);
	}
	
	public boolean buyTile(int x, int y){
		boolean tileBought;
		if(currentRound <= FREE_LAND_NUMBER_OF_ROUNDS){
			tileBought = Mule.gm.buyTile(x, y, currentPlayer);
		} else{
			tileBought = Mule.gm.buyTile(x, y, currentPlayer, true);
		}
		if(tileBought){
			playerNumber++;
			if(playerNumber >= Mule.gm.getNumberOfPlayers()){
				playerNumber = 0;
				currentRound++;
			}
			currentPlayer = Mule.gm.getPlayer(playerNumber);
		}
		return tileBought;
	}
}
