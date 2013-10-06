package gameObjects;

import renderers.MapRenderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * This class represents a tile in the map. Possible states are
 * 0-	Plains
 * 1-	River
 * 2-	Town
 * 3-	1 Ridge Mountain
 * 4-	2 Ridge Mountain
 * 5-	3 Ridge Mountain
 * @author DavidW
 */
public class Tile {
	private static Texture[] textures = new Texture[7];
	private static final float SIZE = 1f;
	
	private int tileType;
	private int x,y;
	
	private Rectangle rect;
	
	/**
	 * Creates a Tile with only its position in the Grid
	 * @param x
	 * @param y
	 */
	public Tile(int x, int y){
		this.x = x;
		this.y = y;
		rect = new Rectangle(x * MapRenderer.ppuX, MapRenderer.ppuY * y, MapRenderer.ppuX * SIZE, MapRenderer.ppuY * SIZE);
	}
	
	/**
	 * Draws the tile to the SpriteBatch of the map
	 */
	public void draw(SpriteBatch batch){
		batch.draw(textures[tileType], x * MapRenderer.ppuX, MapRenderer.ppuY * y, MapRenderer.ppuX * SIZE, MapRenderer.ppuY * SIZE);
	}
	
	public void setType(int newType){
		tileType = newType;
	}
	
	public int getTileType(){
		return tileType;
	}
	
	
	public static void loadTextures(){
		textures[0] = new Texture("MapScreen/plains.jpg");
		textures[1] = new Texture("MapScreen/river.png");
		textures[2] = new Texture("MapScreen/town.png");
		textures[3] = new Texture("MapScreen/oneRidge.png");
		textures[4] = new Texture("MapScreen/twoRidge.png");
		textures[5] = new Texture("MapScreen/threeRidge.png");
	}
	
	public Rectangle getRect(){
		return rect;
	}
	
	public String toString(){
		String s = "";
		if(tileType == 0){
			s += "P";
		} else if(tileType == 1){
			s += "R";
		} else if(tileType == 2){
			s += "T";
		} else{
			s += "M";
		}
		return s;
	}
}
