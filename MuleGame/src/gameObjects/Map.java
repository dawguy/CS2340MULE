package gameObjects;

import java.awt.Point;
import java.util.Random;
import java.util.Stack;

import interfaces.DialogWindow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.me.mygdxgame.Mule;
import com.sun.org.apache.bcel.internal.classfile.PMGClass;

/**
 * The Map which holds the tile elements.
 * @author DavidW
 *
 */
public class Map {
	
	private final int TOWN_X = 4;
	private final int TOWN_Y = 2;
	
	Tile[][] tiles;
	/*
	 * The tile list starts at 0,0 at the bottom left hand corner
	 *
	 *	0,3		1,3		2,3		3,3
	 *	0,2		1,2		2,2		3,2
	 *	0,1		1,1		2,1		3,1
	 *	0,0		1,0		2,0		3,0
	 *
	 *	This formating takes the column number as the x and the row number as the y
	 */
	
	public PlayerToken playerT;
	
	public boolean drawPlayer;

	private boolean drawDialog = false;
	private DialogWindow dialog;
	private float ppuX, ppuY;
	private Random r = new Random();
	private boolean randomOn = false; //Change this based on whether or not we want a random map
	private boolean highlighting = true;
	
	Direction direction = Direction.UP;
	enum Direction{
		UP,RIGHT,LEFT,DOWN;
	}
	
	/**
	 * This constructor allows for a player to pick whether or not it should be a random map
	 * @param randomOn
	 */
	public Map(boolean randomOn){
		this.randomOn = randomOn;
		if(!randomOn){
			defaultMap();
		} else {
			generateRandomMap();
		}
		loadTextures();
		drawPlayer = true;
	}
	
	/**
	 * Similar to the other constructor this one can create random maps and takes a parameter
	 * to decide whether or not to draw the player token
	 * @param randomOn
	 * @param drawP
	 */
	public Map(boolean randomOn, boolean drawP){
		this(randomOn);
		drawPlayer = drawP;
	}
	
	/**
	 * Basic constructor that takes a parameter, which is the actual tiles of the map
	 * @param tiles
	 */
	public Map(Tile[][] tiles){
		this.tiles = tiles;
	}
	
	/**
	 * This method instantiates the map to the default
	 */
	public void defaultMap(){
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
	}
	
	/**
	 * This method generates a random map
	 */
	private void generateRandomMap(){
		Stack<Integer> tileTypes = new Stack<Integer>();
		tiles = new Tile[9][5];
		//Using set number of tiles here. For bigger maps this will have to be changed
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
	 * @param delta
	 */
	private float count = 0;
	public void update(float delta){
		if(playerT == null){
			this.playerT = new PlayerToken(Mule.pm.getCurrentPlayer(),0,0);
			drawPlayer = true;
		}
	}
	
	/**
	 * Loads all of the tile textures necessary.
	 */
	public void loadTextures(){
		Tile.loadTextures();
	}

	/**
	 * Creates a dialog window with the message
	 * @param message
	 */
	public void createDialog(String message){
		dialog = new DialogWindow(message, 20, 200);
		drawDialog = true;
	}

	/**
	 * Checks to see if a dialog window is closed
	 */
	public void checkDialogClosed(){
		if(drawDialog){
			if(dialog.isClosed()){
				drawDialog = false;
			}
		}
	}
	
	/**
	 * Draws the entire map
	 * @param sprites
	 */
	public void draw(SpriteBatch sprites){
		ShapeRenderer sr = new ShapeRenderer();
		
		/*
		 * Drawing black grid along with tiles
		 */
		sr.begin(ShapeRenderer.ShapeType.Line);
		sr.setColor(Color.BLACK);
		for(int i = 0; i < tiles.length; i++){
			sr.line(i * ppuX, 0, i * ppuX, 5 * ppuY);
			for(int c = 0; c < tiles[i].length; c++){
				tiles[i][c].draw(sprites);
			}
		}

		for(int i = 0; i < tiles[i].length; i++){
			sr.line(0, i * ppuY, 9 * ppuX, i * ppuY);
		}
		sr.end();
		
		
		/*
		 * Draw ALL COLORS for ownership
		 */
		for(int i = 0; i < tiles.length; i++){
			for(int c = 0; c < tiles[i].length; c++){
				tiles[i][c].drawOwner(sprites);
				if(!drawPlayer) tiles[i][c].drawHighlight(sprites);
			}
		}

		/*
		 * Player has to be drawn over the sprites so he doens't "walk" under them.
		 */
		if(this.playerT != null && drawPlayer){
			this.playerT.draw(sprites, 1);
		}
		if(drawPlayer && Mule.pm.getCurrentPlayer().getMule() != -1){
			sprites.end();
			sr = new ShapeRenderer();
			sr.begin(ShapeRenderer.ShapeType.Filled);
			sr.setColor(Mule.pm.getCurrentPlayer().getColor());
			int x = playerT.getX() + playerT.getWidth() / 2;
			int y = playerT.getY() + playerT.getHeight() / 2;
			Texture muleTexture= new Texture(Gdx.files.internal("MapScreen/muleSheet.gif"));
			int offSet = 10;
			sprites.begin();
			sprites.setColor(Mule.pm.getCurrentPlayer().getColor());
			if(direction.equals(Direction.DOWN)){
				x -= offSet;
				y += 30;
				//sr.rect(x, y, 20, 20);
				sprites.draw(muleTexture,x,y,10,20,20,20,1,1,90,0,0,20,20,true,false);			} else if (direction.equals(Direction.RIGHT)){
				x -= 50;
				y -= offSet;
				//sr.rect(x, y, 20, 20);
				sprites.draw(muleTexture,x,y);
			} else if (direction.equals(Direction.UP)){
				x -= offSet;
				y -= 50;
				//sr.rect(x, y, 20, 20);
				sprites.draw(muleTexture,x,y,10,0,20,20,1,1,270,0,0,20,20,true,false);
			} else if (direction.equals(Direction.LEFT)){
				x += 30;
				y -= offSet;
				//sr.rect(x, y, 20, 20);
				sprites.draw(muleTexture,x,y,20,20,0,0,20,20,true,false);
			}
			sr.end();
			sprites.setColor(1,1,1,1);
			sprites.end();
			sprites.begin();
		}
		sprites.end();
		if(drawDialog){
			dialog.draw(sprites);
		}
		sprites.begin();
	}
	 
	/**
	 * Sets the boolean flag for drawing the player token
	 * @param b
	 */
	public void setDrawPlayer(boolean b){
		drawPlayer = b;
	}
	
	/**
	 * Gets the integer indexes for the mouse click
	 * @param x the x mouse position
	 * @param y the y mouse position
	 * @return the point representing the index of the mouse click
	 */
	public Point getMouseClickPoint(int x, int y){
		int a = (int) (x / ppuX);
		int b = (int) ((Mule.HEIGHT - y) / ppuY);
		if(tiles[a].length <= b){
			return null;
		}
		return new Point(a, b);
	}
	
	/**
	 * Gets the tile at the given mouse click
	 * @param x the x mouse position
	 * @param y the y mouse position
	 * @return the tile at the given position
	 */
	public Tile getMouseClickedTile(int x, int y){
		int a = (int) (x / ppuX);
		int b = (int) ((Mule.HEIGHT - y) / ppuY);
		if(tiles[a].length <= b){
			return null;
		}
		return tiles[a][b];
	}
	
	/**
	 * Handles mule swapping and placing
	 */
	public void spacePressed(){
		Player player = Mule.pm.getCurrentPlayer();
		if(player.getMule() == -1){
			
		} else {
			swapMules(player);
		}
	}
	
	private void swapMules(Player owner){
		int x = playerT.getX() + 25;
		int y = playerT.getY() + 25;
		int a = (int) (x / ppuX);
		int b = (int) ((Mule.HEIGHT - y - 80) / ppuY);
		if(tiles[a].length <= b){
			return;
		}
		//Centering a and b on the tile
		a = (int) ((a + .5) * ppuX);
		b = (int) (((4 - b) + .5) * ppuY);
		//System.out.println(a + " , " + b);
		//System.out.println(x + " , " + y);
		int dX = x - a;
		int dY = y - b;
		int distance = (int)Math.sqrt(dX * dX + dY * dY);
		System.out.println(distance);
		//20 distance seems like a good cut off for missing the center of the tile
		if(distance > 20){
			loseMule(owner);
		} else {
			a = (int) (x / ppuX);
			b = (int) (y / ppuY);
			Tile tileOn = tiles[a][b];
			System.out.println(a + "," + b + ": " + tileOn.getCords());
			if(owner.equals(tileOn.getOwner())){
				int tempMuleVal = tileOn.getMule();
				tileOn.setMule(owner.getMule());
				owner.setMule(tempMuleVal);
				if(owner.getMule() == -1) loseMule(owner);
			} else {
				loseMule(owner);
			}
		}
	}
	
	private void loseMule(Player owner){
		owner.setMule(-1);	//The player just lost his mule
		owner.loseResources(5,1);
		owner.loseResources(6,1);
		owner.loseResources(7,1);	
	}
	
	/**	
	 * Moves the player up
	 */
	public void moveUp(){
		direction = Direction.UP;
		if(playerT.getY()<=Mule.HEIGHT-135)
		this.playerT.moveUp();
	}

	/**
	 * Moves the player right
	 */
	public void moveRight(){
		direction = Direction.RIGHT;
		if(playerT.getX()<=Mule.WIDTH-40)
		this.playerT.moveRight();
	}
	
	/**
	 * Moves the player down
	 */
	public void moveDown(){
		direction = Direction.DOWN;
		if(playerT.getY()>=5)
		this.playerT.moveDown();
	}
	
	/**
	 * Moves the player left
	 */
	public void moveLeft(){
		direction = Direction.LEFT;
		if(playerT.getX()>=-10)
		this.playerT.moveLeft();
	}
	
	/**
	 * True if the playerToken and Town tile are overlapping
	 */
	public boolean changeToTown(){
		if(this.playerT == null){
			this.playerT = new PlayerToken(Mule.pm.getCurrentPlayer(), 0, 0);
			System.out.println("MAKING NEW PLAYER TOKEN");
		} else if (!this.playerT.compareTo(Mule.pm.getCurrentPlayer())){
			playerT = new PlayerToken(Mule.pm.getCurrentPlayer(), 0, 0);
			System.out.println("MAKING NEW PLAYER TOKEN");
		}
		float tX = 4.5f * ppuX;	//In order to get center of tile use .5 more than tile number
		float tY = 2.5f * ppuY;
		float pX = playerT.getX() + 25;	//Center of player
		float pY = playerT.getY() + 25;
		
		float a = (tX - pX) * (tX - pX);
		float b = (tY - pY) * (tY - pY);
		float c = (float)Math.sqrt((a * a) + (b * b));
		if(c < 1000) return true;
		return false;
	}

	/**
	 * Sets the pixels per unit
	 * @param x the pixel per units in the x
	 * @param y the pixel per units in the y
	 */
	public void setPPU(float x, float y){
		ppuX = x;
		ppuY = y;
	}
	
	/**
	 * Converts the map to a string representation
	 */
	public String toString(){
		String s = "";
		s += "RANDOM : " + randomOn;
		s += "\n";
		for(int i = 0 ; i < tiles.length ; i ++){
			for(int j = 0 ; j < tiles[i].length ; j++){
				s += tiles[i][j];
			}
			s += "\n";
		}
		return s;
	}
	
	/**
	 * Puts the player token below the town
	 */
	public void putBelowTown(){
		playerT.setX((int)ppuX * TOWN_X);
		playerT.setY((int)ppuY * (TOWN_Y - 1));
	}
	
	/**
	 * Get the array representation of the tiles
	 * @return the tile array
	 */
	public Tile[][] getTiles(){
		return tiles;
	}

	/**
	 * Get the number of tiles in the x
	 * @return
	 */
	public int getXSize(){
		return tiles.length;
	}

	/**
	 * Get the number of tiles in the y
	 * @return
	 */
	public int getYSize(){
		return tiles[0].length;
	}
	
	/**
	 * Get the player token
	 * @return
	 */
	public PlayerToken getToken(){
		return playerT;
	}

	/**
	 * Calculate the production of a given tiles
	 * @param i the x position of the tile
	 * @param j the y position of the tile
	 */
	public void produce(int i, int j){
		tiles[i][j].produce();
	}
}
