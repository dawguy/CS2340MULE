package gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Town {
	private static Texture townTexture;
	int width, height;
	public Town(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Draws the town. Not working properly for resizing
	 */
	public void draw(SpriteBatch batch){
		batch.draw(townTexture, 0,0,width,height);
		

	}
	
	public void loadTextures(){
		townTexture = new Texture("TownScreen/townmap.png");
	}
}
