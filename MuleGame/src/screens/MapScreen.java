package screens;

import gameObjects.Map;
import renderers.MapRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
		map = new Map();
		game = mule;
		renderer = new MapRenderer(map);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		renderer.update(delta);
		renderer.render();
		
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
