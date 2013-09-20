package gameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The Map which holds the tile elements
 * @author DavidW
 *
 */
public class Map {
	Tile[][] tiles;
	float ppuX, ppuY;
	
	public Map(){
		tiles = new Tile[9][5];
		for(int i = 0; i < tiles.length; i++){
			for(int c = 0; c < tiles[i].length; i++){
				tiles[i][c] = new Tile(c,i);
			}
		}
		loadTextures();
	}
	
	public void loadTextures(){
		Tile.loadTextures();
	}
	
	public void draw(SpriteBatch sprites){
		for(int i = 0; i < tiles.length; i++){
			for(int c = 0; c < tiles[i].length; i++){
				tiles[i][c].draw(sprites);
			}
		}
	}
	
	public void setPPU(float x, float y){
		ppuX = x;
		ppuY = y;
	}
}
