package gameObjects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * This class is the graphical representation of the player that is currently playing
 * @author antonio
 *
 */
public class PlayerToken extends Image{

	private Color color;
	
	private final int VELOCITY_X = 5;
	private final int VELOCITY_Y = 5;
	
	private final int WIDTH = 50;
	private final int HEIGHT = 50;
	
	public PlayerToken(Player p){
		super();
		color = p.getColor();
	}
	
	public PlayerToken(Player p, int x, int y){
		this(p);
		super.setX(x);
		super.setY(y);
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
		ShapeRenderer sr = new ShapeRenderer();
		sr.begin(ShapeType.Filled);
		sr.setColor(color);
		sr.rect(getX(), getY(), WIDTH, HEIGHT);
		sr.end();
	}
	
	public void moveLeft(){
		setX(getX() - VELOCITY_X);
	}
	
	public void moveRight(){
		setX(getX() + VELOCITY_X);
	}
	
	public void moveDown(){
		setY(getY() - VELOCITY_Y);
	}
	
	public void moveUp(){
		setY(getY() + VELOCITY_Y);
	}
}
