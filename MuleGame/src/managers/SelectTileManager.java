package managers;

import gameObjects.Player;
import gameObjects.Tile;

import com.me.mygdxgame.Mule;

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

	private boolean[] donePlayers;
	
	private int numberOfDone;
	
	private final int FREE_LAND_NUMBER_OF_ROUNDS = 2;
	
	private Mule game;
	
	public SelectTileManager(){
		super();
		currentPlayer = Mule.pm.getCurrentPlayer();
		currentRound = 1;
		playerNumber = 0;
		startOfGame = true;
		donePlayers = new boolean[Mule.pm.getNumberOfPlayers()];
		numberOfDone = 0;
	}
	
	public SelectTileManager(boolean start){
		this();
		startOfGame = start;
	}
	
	public SelectTileManager(Mule g){
		this();
		game = g;
	}
	
	public boolean isDone(){
		return (numberOfDone == Mule.pm.getNumberOfPlayers());
	}
	
	public void setPlayerDone(int i){
		if(donePlayers[i]){
			numberOfDone --;
			donePlayers[i] = false;
		} else{
			numberOfDone++;
			if(numberOfDone == Mule.pm.getNumberOfPlayers()){
				return;
			}
			donePlayers[i] = true;
			if(donePlayers[playerNumber]){
				while(donePlayers[playerNumber]){
					playerNumber++;
					if(playerNumber >= Mule.gm.getNumberOfPlayers()){
						playerNumber = 0;
						currentRound++;
					}
					currentPlayer = Mule.gm.getPlayer(playerNumber);
				}
			}
		}
	}
	
	public boolean getPlayerDone(int i){
		return donePlayers[i];
	}
	
	public int getNumberOfPlayersDone(){
		return numberOfDone;
	}
	
	public boolean tilePicked(Tile t){
		return t.setOwner(currentPlayer);
	}

	/**
	  * Highlights proper tile.
	  * @param x x value inside tile
	  * @param y y value inside tile
	  */
	public void highlightTile(int x, int y){
		// Clear highlight of all tiles
		for(int i = 0 ; i < Mule.gm.getMap().getTiles().length ; i ++){
			for(int j = 0 ; j < Mule.gm.getMap().getTiles()[i].length ; j++){
				Mule.gm.getMap().getTiles()[i][j].setIsHighlighted(false);
			}
		}
		Tile t = Mule.gm.getMap().getMouseClickedTile(x, y);
		if(t == null) {
			return;
		}
		if(t.getTileType() == 2){
			return; // can't highlight town tile
		}
		t.setIsHighlighted(currentPlayer.getColor(), true);
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
			while(donePlayers[playerNumber]){
				playerNumber++;
				if(playerNumber >= Mule.gm.getNumberOfPlayers()){
					playerNumber = 0;
					currentRound++;
				}
			}
			currentPlayer = Mule.gm.getPlayer(playerNumber);
		}
		return tileBought;
	}
}
