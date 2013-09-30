package interfaces;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * A class that represents a button in the game. It will draw itself and allow the user to click.
 * @author antonio
 * @since 9/16/13
 *
 */
public class Button extends Actor{

	private Texture image;
	private Rectangle rect;
	
	public Button(Texture t){
		super();
		image = t;
		rect = new Rectangle(0, 0, image.getWidth(), image.getHeight());
		super.setX(0);
		super.setY(0);
		super.setWidth(image.getWidth());
		super.setHeight(image.getHeight());
	}
	
	public Button(Texture t, int x, int y){
		this(t);
		rect.x = x;
		rect.y = y;
		super.setX(x);
		super.setY(y);
	}
	
	public Texture getTexture(){
		return image;
	}
	
	public void setTexture(Texture t){
		image = t;
		rect = new Rectangle(rect.x, rect.y, image.getHeight(), image.getWidth());
	}
	
	public boolean isClicked(int x, int y){
		return rect.contains(x, y);
	}
	
	public void draw(SpriteBatch batch){
		batch.setColor(Color.WHITE);
		batch.draw(image, rect.x, rect.y);
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		this.draw(batch);
	}
	
	public boolean isClicked(InputHandler input){
		return rect.contains(input.getMousePoint()) && input.mouseClicked();
	}
}
