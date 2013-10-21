package gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Town {
	private static Texture townTexture;
	int width, height, realHeight;
	
	/**
	 * Pass in the actual height of the frame. The method will cause the height too become realHeight - 30
	 * @param width
	 * @param height
	 */
	public Town(int width, int height){
		this.width = width;
		this.height = height - 30;
		this.realHeight = height;
	}
	

	public void draw(SpriteBatch batch){
		batch.draw(townTexture, 0,0,width,height);
	}
	
	public void loadTextures(){
		townTexture = new Texture("TownScreen/townmap.png");
	}
}
