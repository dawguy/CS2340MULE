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
	
	public SelectTileManager(){
		super();
		currentPlayer = Mule.pm.getCurrentPlayer();
	}
	
	public boolean tilePicked(Tile t){
		return t.setOwner(currentPlayer);
	}
}
