package renderers;


import gameObjects.Map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Renders the map.
 * @author DavidW
 */
public class MapRenderer {
	private static final float CAMERA_WIDTH = 9f; //9 because there are 9 collums tiles on the screen
	private static final float CAMERA_HEIGHT = 5f; //5 because there are 5 rows of tiles on the scren
	private Map map;
	private OrthographicCamera cam;
	
	private int width;	//Width of the screen
	private int height;	//Height of the screen
	public static float ppuX;	//Pixels per unit X
	public static float ppuY; //Pixels per unit Y
	
	SpriteBatch spriteBatch;
	
	public void setSize(int w, int h){
		this.width = w;
		this.height = h;
		ppuX = (float)width / CAMERA_WIDTH;
		ppuY = (float)height / CAMERA_HEIGHT;
		map.setPPU(ppuX,ppuY);
	}
	
	public MapRenderer(Map map){
		this.map = map;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2, 0);
		this.cam.update();
		spriteBatch = new SpriteBatch();
		this.map.loadTextures();
	}
	
	public void update(float delta){
		map.update(delta);
	}
	
	public void render(){
		spriteBatch.begin();
			map.draw(spriteBatch);
		spriteBatch.end();
	}
}
