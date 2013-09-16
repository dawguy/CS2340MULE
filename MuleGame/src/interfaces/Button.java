package interfaces;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A class that represents a button in the game. It will draw itself and allow the user to click.
 * @author antonio
 * @since 9/16/13
 *
 */
public class Button {

	private Texture image;
	private Rectangle rect;
	
	public Button(Texture t){
		super();
		image = t;
		rect = new Rectangle(0, 0, image.getWidth(), image.getHeight());
	}
	
	public Button(Texture t, int x, int y){
		this(t);
		rect.x = x;
		rect.y = y;
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
		batch.draw(image, rect.x, rect.y);
	}
	
	public boolean isClicked(InputHandler input){
		return rect.contains(input.getMousePoint()) && input.mouseClicked();
	}
}
