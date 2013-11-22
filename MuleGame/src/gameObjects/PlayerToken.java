package gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * This class is the graphical representation of the player that is currently playing
 * @author antonio
 *
 */
public class PlayerToken{

	private Color color;
	private Player owner;
	private String race;
	private final int VELOCITY_X = 5;
	private final int VELOCITY_Y = 5;
	private Texture pTexture;
	private boolean isLeft=false;
	private int x = 0;
	private int y = 0;
	private final int WIDTH = 50;
	private final int HEIGHT = 50;
	private int moveCount=0;
	
	private Rectangle rect;
	
	public PlayerToken(Player p){
		super();
		owner = p;
		color = p.getColor();
		race=p.getRace();
		pTexture = setTexture(race);
	}
	
	public PlayerToken(Player p, int x, int y){
		this(p);
		setX(x);
		setY(y);
		rect = new Rectangle(getX(), getY(), WIDTH, HEIGHT);
	}
	
	/**
	 * Helper method that sets texture file bzsed on race
	 * @param race
	 * @return
	 */
	private Texture setTexture(String race){
		if(race.equals("Human")){
			return new Texture(Gdx.files.internal("MapScreen/playerSheet.gif"));
		}
		else if(race.equals("Ugaite")){
			return new Texture(Gdx.files.internal("MapScreen/playerSheet.gif"));
		}
		else if(race.equals("Buzzite")){
			return new Texture(Gdx.files.internal("MapScreen/playerSheet.gif"));
		}
		else if(race.equals("Flapper")){
			return new Texture(Gdx.files.internal("MapScreen/playerSheet.gif"));
		}
		else{ //default
			return new Texture(Gdx.files.internal("MapScreen/playerSheet.gif"));
		}
	}
	/**
	 * Draws the playerToken on the screen through the sprite batch
	 * @param batch
	 * @param parentAlpha
	 */
	public void draw(SpriteBatch batch, float parentAlpha){
		batch.end();
		ShapeRenderer sr = new ShapeRenderer();
		sr.begin(ShapeType.Filled);
		sr.setColor(color);
		sr.end();
		batch.begin();
		drawPlayer(batch);
	}

	/**
	 * Draws the running animation of the player object
	 * @param batch
	 */
	
	private void drawPlayer(SpriteBatch batch){
		batch.end();
		batch.begin();
		batch.setColor(color);
		moveCount=moveCount%20;
		if(moveCount<6)
			batch.draw(pTexture,x,y,WIDTH,HEIGHT,0,0,WIDTH,HEIGHT,isLeft,false);
		if(moveCount>5 && moveCount <11)
			batch.draw(pTexture,x,y,WIDTH,HEIGHT,WIDTH,0,WIDTH,HEIGHT,isLeft,false);
		if(moveCount>10 && moveCount <16)
			batch.draw(pTexture,x,y,WIDTH,HEIGHT,0,HEIGHT,WIDTH,HEIGHT,isLeft,false);
		if(moveCount>15)
			batch.draw(pTexture,x,y,WIDTH,HEIGHT,WIDTH,HEIGHT,WIDTH,HEIGHT,isLeft,false);
		batch.setColor(1,1,1,1);
	}
	
	/**
	 * Sets the playerToken's X value
	 * @param x
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Sets the playerToken's Y value
	 * @param y
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * Returns the playerToken's X value
	 * @return x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Returns the playerToken's Y value
	 * @return y
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Returns the playerToken's Width
	 * @return WIDTH
	 */
	public int getWidth(){
		return WIDTH;
	}
	
	/**
	 * Returns the playerToken's Height
	 * @return HEIGHT
	 */
	public int getHeight(){
		return HEIGHT;
	}
	
	/**
	 * Decrements the playerToken's X value, moving it left
	 */
	public void moveLeft(){
		moveCount++;
		isLeft=true;
		setX(getX() - VELOCITY_X);
		rect.setX(getX() - VELOCITY_X);
	}
	
	/**
	 * Increments the playerToken's X value, moving it right
	 */
	public void moveRight(){
		moveCount++;
		isLeft=false;
		setX(x + VELOCITY_X);
		rect.setX(x + VELOCITY_X);
	}
	
	/**
	 * Increments the playerToken's Y value, moving it down
	 */
	public void moveDown(){
		moveCount++;
		setY(getY() - VELOCITY_Y);
		rect.setY(getY() - VELOCITY_Y);
	}
	
	/**
	 * Decrements the playerToken's Y value, moving it up
	 */
	public void moveUp(){
		moveCount++;
		setY(getY() + VELOCITY_Y);
		rect.setY(getY() + VELOCITY_Y);
	}
	/**
	 * Returns the rectangle 
	 * @return
	 */
	public Rectangle getRect(){
		return rect;
	}
	/**
	 * Compares whether the comparing player is associated with the current playerToken
	 * @param play
	 * @return
	 */
	public boolean compareTo(Player play){
		if(play == owner) return true;
		return false;
	}
}
