package gameObjects;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The Map which holds the tile elements.
 * @author DavidW
 *
 */
public class Map {
	Tile[][] tiles;
	/*
	 * The tile list starts at 0,0 at the top right hand corner
	 *
	 *	0,0		1,0		2,0		3,0
	 *	0,1		1,1		2,1		3,1
	 *	0,2		1,2		2,2		3,2
	 *	0,3		1,3		2,3		3,3
	 *
	 *	This formating takes the column number as the x and the row number as the y
	 */
	
	
	float ppuX, ppuY;
	Random r = new Random();
	boolean randomOn = false; //Change this based on whether or not we want a random map
	
	public Map(){
		if(!randomOn){
			tiles = new Tile[9][5];
			
			//Creates Tiles and defaults them to Plains
			for(int i = 0; i < tiles.length; i++){
				for(int c = 0; c < tiles[i].length; c++){
					tiles[i][c] = new Tile(i,c);
				}
			}
			//River
			for(int i = 0; i < tiles[0].length; i ++){
				tiles[4][i].setType(1);
			}
			//Town
			tiles[4][2].setType(2);
			
			//One Ridge Mountains
			tiles[2][0].setType(3);
			tiles[2][3].setType(3);
			tiles[7][1].setType(3);
			
			//Two Ridge Mountains
			tiles[3][2].setType(4);
			tiles[6][2].setType(4);
			tiles[8][4].setType(4);
			tiles[0][4].setType(4);
			
			//Three Ridge Mountains
			tiles[2][1].setType(5);
			tiles[5][0].setType(5);
			tiles[5][3].setType(5);
		} else {
			generateRandomMap();
		}
		loadTextures();
	}
	
	private void generateRandomMap(){
		Stack<Integer> tileTypes = new Stack<Integer>();
		tiles = new Tile[9][5];
		//Using set number of tiles here. For bigger maps this will hae to be changed
		for(int i = 0; i < 45; i++){
			double roll = r.nextDouble() * 100;
			if(roll > 30.){
				tileTypes.add(0);
			} else if( 30. >= roll && roll > 20.){
				tileTypes.add(3);
			} else if( 20. >= roll && roll > 10.){
				tileTypes.add(4);
			} else if( 10. >= roll && roll >= 0.){
				tileTypes.add(5);
			}
		}
		
		tiles = new Tile[9][5];
		for(int i = 0; i < tiles.length; i++){
			for(int c = 0; c < tiles[i].length; c++){
				tiles[i][c] = new Tile(i,c);
				tiles[i][c].setType(tileTypes.pop());
			}
		}
		
		//River
		for(int i = 0; i < tiles[0].length; i ++){
			tiles[4][i].setType(1);
		}
		//Town
		tiles[4][2].setType(2);
	}
	
	/**
	 * Updates both the map and any potential objects inside the map
	 * @param detla
	 */
	private float count = 0;
	public void update(float delta){
		if(randomOn){
			count += delta;
			if(count > 1){
				generateRandomMap();
				count = 0;
			}
		}
	}
	
	public void loadTextures(){
		Tile.loadTextures();
	}
	
	public void draw(SpriteBatch sprites){
		for(int i = 0; i < tiles.length; i++){
			for(int c = 0; c < tiles[i].length; c++){
				tiles[i][c].draw(sprites);
			}
		}
	}
	
	public void setPPU(float x, float y){
		ppuX = x;
		ppuY = y;
	}
}
