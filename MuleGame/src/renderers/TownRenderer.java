package renderers;


import gameObjects.Town;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * In charge of rendering the map screen including all parts of the map screen.
 * @author DavidW
 */
public class TownRenderer {
	private OrthographicCamera cam;
	Town town;
	private int width;	//Width of the screen
	private int height;	//Height of the screen

	SpriteBatch spriteBatch;

	/**
	 * Changes the size of the screen.
	 * @param w
	 * @param h
	 */
	public void setSize(int w, int h){
		this.width = w;
		this.height = h;
	}
	
	/**
	 * Sets up the renderer
	 * @param map
	 */
	public TownRenderer(Town town){
		this.town = town;
		spriteBatch = new SpriteBatch();
		this.town.loadTextures();
	}
	
	/**
	 * No use yet
	 * @param delta time difference in fractions of seconds
	 */
	public void update(float delta){
		
	}
	
	/**
	 * Passes in the spriteBatch that wants to be rendered to the map object which will in turn ask each portition of the map to render itself
	 */
	public void render(){
		spriteBatch.begin();
			town.draw(spriteBatch);
		spriteBatch.end();
	}
}
