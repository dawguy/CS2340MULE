package gameObjects;

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
	private final int VELOCITY_X = 5;
	private final int VELOCITY_Y = 5;
	private Texture pTexture;
	private int x = 0;
	private int y = 0;
	private final int WIDTH = 50;
	private final int HEIGHT = 50;
	
	private Rectangle rect;
	
	public PlayerToken(Player p){
		super();
		owner = p;
		color = p.getColor();
		// pTexture = new Texture("MapScreen/humanP.png");
	}
	
	public PlayerToken(Player p, int x, int y){
		this(p);
		setX(x);
		setY(y);
		rect = new Rectangle(getX(), getY(), WIDTH, HEIGHT);
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		batch.end();
		ShapeRenderer sr = new ShapeRenderer();
		sr.begin(ShapeType.Filled);
		sr.setColor(color);
		sr.rect(x, y, WIDTH, HEIGHT);
		sr.end();
		batch.begin();
	}
	
	public void moveLeft(){
		setX(getX() - VELOCITY_X);
		rect.setX(getX() - VELOCITY_X);
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return WIDTH;
	}
	
	public int getHeight(){
		return HEIGHT;
	}
	
	public void moveRight(){
		setX(x + VELOCITY_X);
		rect.setX(x + VELOCITY_X);
	}
	
	public void moveDown(){
		setY(getY() - VELOCITY_Y);
		rect.setY(getY() - VELOCITY_Y);
	}
	
	public void moveUp(){
		setY(getY() + VELOCITY_Y);
		rect.setY(getY() + VELOCITY_Y);
	}
	
	public Rectangle getRect(){
		return rect;
	}
	
	public boolean compareTo(Player play){
		if(play == owner) return true;
		return false;
	}
}
