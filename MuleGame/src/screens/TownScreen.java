package screens;


import gameObjects.Player;
import gameObjects.PlayerToken;
import gameObjects.Buildings.AssayOffice;
import gameObjects.Buildings.LandOffice;
import gameObjects.Buildings.Pub;
import gameObjects.Buildings.Store;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.me.mygdxgame.Mule;

/**
 * This class will represent the town to the player. It has four locations that the player could visit.
 * @author antonio
 *
 */
public class TownScreen implements Screen{

	public final String BACKGROUND_LOCATION = "TownScreen/Background.jpg";
	
	private Stage stage;
	
	private Mule game;
	
	private Store store;
	private Pub pub;
	private LandOffice landOffice;
	private AssayOffice assayOffice;
	
	private PlayerToken token;
	
	public TownScreen(Mule g){
		super();
		game = g;
		stage = new Stage();
		
		setBackground();
		setBuildings();
		setPlayer();
	}
	
	private void setPlayer(){
		Player p = new Player("TEST", Color.BLUE);
		token = new PlayerToken(p, Mule.WIDTH / 2, Mule.HEIGHT / 2);
		//stage.addActor(token);
	}
	
	private void setBuildings(){
		int buffer_x = 25;
		int buffer_y = 25;
		store = new Store(stage, buffer_x, buffer_y);
		pub = new Pub(stage);
		landOffice = new LandOffice(stage);
		assayOffice = new AssayOffice(stage);
		
		pub.setX(Mule.WIDTH - buffer_x - pub.getWidth());
		pub.setY(buffer_y);
		
		landOffice.setX(buffer_x);
		landOffice.setY(Mule.HEIGHT - buffer_y - landOffice.getHeight());
		
		assayOffice.setX(Mule.WIDTH - buffer_x - assayOffice.getWidth());
		assayOffice.setY(Mule.HEIGHT - buffer_y - assayOffice.getHeight());
	}
	
	private void setBackground(){
		Group background = new Group();
		background.setBounds(0, 0, Mule.WIDTH, Mule.HEIGHT);
		
		Texture backgroundTexture = new Texture(Gdx.files.internal(BACKGROUND_LOCATION));
		Sprite backgroundSprite = new Sprite(backgroundTexture);
		SpriteDrawable backgroundDrawable = new SpriteDrawable(backgroundSprite);
		Image backgroundImage = new Image(backgroundDrawable);
		
		background.addActor(backgroundImage);
		
		stage.addActor(background);
	}
	
	@Override
	public void render(float delta) {
		stage.draw();	
		SpriteBatch sb = new SpriteBatch();
		sb.begin();
		token.draw(sb, 1);
		sb.end();
		sb.dispose();
		handleInput();
		
		checkLocation();
	}
	
	private void handleInput(){
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			token.moveRight();
		} else if(Gdx.input.isKeyPressed(Keys.LEFT)){
			token.moveLeft();
		} else if(Gdx.input.isKeyPressed(Keys.UP)){
			token.moveUp();
		} else if(Gdx.input.isKeyPressed(Keys.DOWN)){
			token.moveDown();
		}
	}
	
	private void checkLocation(){
		if(token.getX() < 0 || token.getX() + token.getWidth() > Mule.WIDTH ||
				token.getY() < 0 || token.getY() + token.getHeight() > Mule.HEIGHT){
			token.setX(Mule.WIDTH / 2);
			token.setY(Mule.HEIGHT / 2);
			game.setScreen(Mule.MAPSCREEN);
		}
		
		checkBuildingCollision();
	}
	
	private void checkBuildingCollision(){
		Rectangle tokenRect = token.getRect();
		Rectangle pubRect = pub.getRect();
		if(pubRect.contains(tokenRect)){
			game.gm.endTurnPub();
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
