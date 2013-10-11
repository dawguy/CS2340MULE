package screens;

import gameObjects.Map;
import gameObjects.Tile;
import renderers.MapRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
	
	private Tile[][] tiles;
	
	float ppuX, ppuY;
	
	int selectionBoxX = 3;
	int selectionBoxY = 3;
	
	public SelectTilesScreen(Mule mule){
		super();
		map = game.gm.getMap();
		tiles = map.getTiles();
		renderer = new MapRenderer(map);
	}
	
	@Override
	public void render(float delta) {
		if(ppuX < 1f) ppuX = renderer.ppuX;
		if(ppuY < 1f) ppuY = renderer.ppuY;
		Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		SpriteBatch sprite = new SpriteBatch();
		sprite.begin();
		for(int i = 0; i < tiles.length; i++){
			for(int c = 0; c <  tiles[i].length; c++){
				tiles[i][c].draw(sprite);
			}
		}
		ShapeRenderer sr = new ShapeRenderer();
		System.out.println(ppuX + ", " + ppuY);
		sr.begin(ShapeRenderer.ShapeType.Line);
		sr.setColor(Color.BLACK);
		for(int i = 0; i < tiles.length; i++){
			sr.line(i * ppuX, 0, i * ppuX, 5 * ppuY);
		}
		for(int i = 0; i < tiles[i].length; i++){
			sr.line(0, i * ppuY, 9 * ppuX, i * ppuY);
		}
		sr.setColor(Color.RED);
			//Horizontal lines
			sr.line(selectionBoxX * ppuX, selectionBoxY * ppuY, ppuX * (selectionBoxX + 1), selectionBoxY * ppuY );
			sr.line(selectionBoxX * ppuX, (selectionBoxY + 1) * ppuY, ppuX * (selectionBoxX + 1), (selectionBoxY + 1) * ppuY );	
			//Vertical lines
			sr.line(selectionBoxX * ppuX, selectionBoxY * ppuY, ppuX * (selectionBoxX), (selectionBoxY + 1) * ppuY );
			sr.line((selectionBoxX + 1) * ppuX, selectionBoxY * ppuY, ppuX * (selectionBoxX + 1), (selectionBoxY + 1) * ppuY );
			sr.end();
		sprite.end();
	}
	
	public void update(float delta){
		
	}

		
	@Override
	public void resize(int width, int height) {
		
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
