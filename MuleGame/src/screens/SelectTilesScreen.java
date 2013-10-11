package screens;

import managers.GameManager;
import managers.SelectTileManager;
import gameObjects.Map;
import renderers.MapRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.me.mygdxgame.Mule;

/**
 * This screen will handle the displaying of the entire map and allow players to pick a tile for various purposes
 * including buying tiles.
 * @author antonio
 *
 */
public class SelectTilesScreen implements Screen{

	private MapRenderer renderer;
	
	private Mule game;
	
	private Map map;
	
	private SelectTileManager manager;
	
	public SelectTilesScreen(Mule mule){
		super();
		map = game.gm.getMap();
		renderer = new MapRenderer(map);
		renderer.setSize(Mule.WIDTH, Mule.HEIGHT);
		map.setDrawPlayer(false);
		manager = new SelectTileManager();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		update(delta);
		
		renderer.update(delta);
		renderer.render();
	}
	
	private void update(float delta){
		getInput();
	}
	
	private void getInput(){
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			manager.tilePicked(map.getMouseClickedTile(Gdx.input.getX(), Gdx.input.getY()));
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
