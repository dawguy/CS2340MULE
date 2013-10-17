package screens;

import gameObjects.Map;
import renderers.MapRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.me.mygdxgame.Mule;
/**
 * The Screen object for the map.
 * Includes the game loop for the screen object
 * @author David Wright
 *
 */
public class MapScreen implements Screen{
	MapRenderer renderer;
	Map map;
	Mule game;
	
	public MapScreen(Mule mule){
		super();
		map = new Map(false);
		game = mule;
		renderer = new MapRenderer(map);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		handleInput();
		renderer.update(delta);
		renderer.render();
		if(map.changeToTown()){
			//MIGHT Have to do certain thigns to the town game here
			//Because right now all it is doing is straight changing the screen
			//over to the town screen
			map.putBelowTown();
			game.setScreen(game.TOWNSCREEN);
		}
	}
	
	public void setMap(Map m){
		map = m;
	}
	
	private void handleInput(){
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			map.moveRight();
		} else if(Gdx.input.isKeyPressed(Keys.LEFT)){
			map.moveLeft();
		} else if(Gdx.input.isKeyPressed(Keys.UP)){
			map.moveUp();
		} else if(Gdx.input.isKeyPressed(Keys.DOWN)){
			map.moveDown();
		}
	}

	@Override
	public void resize(int width, int height) {
		renderer.setSize(width,height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
