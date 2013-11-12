package screens;

import interfaces.ResourceTracker;
import gameObjects.Map;
import gameObjects.PlayerToken;
import managers.GameManager;
import managers.XMLManager;
import renderers.MapRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.me.mygdxgame.Mule;
import com.sun.org.apache.bcel.internal.classfile.PMGClass;
/**
 * The Screen object for the map.
 * Includes the game loop for the screen object
 * @author David Wright
 *
 */
public class MapScreen implements Screen{
	MapRenderer renderer;
	Map map;
	GameManager gm;
	Mule game;
	
	public MapScreen(Mule g){
		super();
		gm = Mule.gm;
		map = gm.getMap();
		game = g;
		renderer = new MapRenderer(map);
	}
	
	public MapScreen(Mule g, Map m){
		super();
		gm = Mule.gm;
		map = m;
		game = g;
		renderer = new MapRenderer(map);
	}
	

	
	@Override
	public void render(float delta) {
		if (gm.getRoundTime()<gm.getCurrentPlayerTime())
			gm.nextPlayer();
		Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		handleInput();
		game.gm.incrementCurrentPlayerTime(delta);
		renderer.update(delta);
		renderer.render();
		if(map.changeToTown()){
			//MIGHT Have to do certain thigns to the town game here
			//Because right now all it is doing is straight changing the screen
			//over to the town screen
			map.putBelowTown();
			game.setScreen(Mule.TOWNSCREEN);
		}
	}
	
	public void setMap(Map m){
		map = m;
	}
	
	private boolean spacePressed = false;
	private void handleInput(){
		if(Gdx.input.isKeyPressed(Keys.RIGHT)||Gdx.input.isKeyPressed(Keys.D)){
			map.moveRight();
		} else if(Gdx.input.isKeyPressed(Keys.LEFT)||Gdx.input.isKeyPressed(Keys.A)){
			map.moveLeft();
		} else if(Gdx.input.isKeyPressed(Keys.UP)||Gdx.input.isKeyPressed(Keys.W)){
			map.moveUp();
		} else if(Gdx.input.isKeyPressed(Keys.DOWN)||Gdx.input.isKeyPressed(Keys.S)){
			map.moveDown();
		} 
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			if(!spacePressed)map.spacePressed();
			spacePressed = true;
		} else {
			spacePressed = false;
		}
		if(Gdx.input.isKeyPressed(Keys.Q)){
			XMLManager.SaveGame();
			Gdx.app.exit();
		}
		map.checkDialogClosed();
	}

	@Override
	public void resize(int width, int height) {
		renderer.setSize(width,height);
	}

	/**
	 * Handles what happens when the game switches to the map
	 */
	@Override
	public void show() {
		map.drawPlayer = true;
		map.playerT = new PlayerToken(Mule.pm.getCurrentPlayer(),0,0);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
