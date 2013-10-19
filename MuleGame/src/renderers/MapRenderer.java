package renderers;


import gameObjects.Map;
import gameObjects.PlayerToken;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.Mule;

/**
 * In charge of rendering the map screen including all parts of the map screen.
 * @author DavidW
 */
public class MapRenderer {
	private static final float CAMERA_WIDTH = 9f; //9 because there are 9 columns tiles on the screen
	private static final float CAMERA_HEIGHT = 5f; //5 because there are 5 rows of tiles on the screen
	private Map map;
	private OrthographicCamera cam;
	
	private int width;	//Width of the screen
	private int height;	//Height of the screen
	private int realHeight; //Used to save room for the GUI
	public static float ppuX;	//Pixels per unit X
	public static float ppuY; //Pixels per unit Y
	
	SpriteBatch spriteBatch;
	boolean ppuSet = false;
	
	/**
	 * Changes the size of the screen. libGDX auto handles screen size changing so updating the ppuX and ppuY after the first call is not needed.
	 * @param w
	 * @param h
	 */
	public void setSize(int w, int h){
		this.width = w;
		this.realHeight = h;
		this.height = h - 20;
		if(!ppuSet){
			ppuX = (float)w / CAMERA_WIDTH;
			ppuY = (float)height / CAMERA_HEIGHT;
			ppuSet = true;
			map.setPPU(ppuX,ppuY);
		}
	}
	
	/**
	 * Sets up the renderer
	 * @param map
	 */
	public MapRenderer(Map map){
		this.map = map;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2, 0);
		this.cam.update();
		spriteBatch = new SpriteBatch();
		this.map.loadTextures();
		
	}
	
	public static void setPPU(float x, float y){
		ppuX = x;
		ppuY = y;
	}
	
	/**
	 * Used for testing purposes. Likely will not need an update method in the finished renderer
	 * @param delta time difference in fractions of seconds
	 */
	public void update(float delta){
		Mule.gm.setMap(map);
		Mule.MAPSCREEN.setMap(map);
		map.update(delta);
	}
	
	/**
	 * Passes in the spriteBatch that wants to be rendered to the map object which will in turn ask each portition of the map to render itself
	 */
	public void render(){
		spriteBatch.begin();
			System.out.println("Map draw");
			map.draw(spriteBatch);
		spriteBatch.end();
	}
}
