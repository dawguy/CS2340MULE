package interfaces;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * A class that will contain all of the ui for the player creations screen.
 * @author antonio
 * @since 9/17/13
 */
public class PlayerCreationInput {

	private final int MAX_PLAYERS = 4;
	private final int MIN_PLAYERS = 2;
	private final int ENTER_KEY = 13;
	private final int DELETE_KEY = 8;
	
	private List<PlayerVariableInputs> players;
	
	
	private int positionX;
	private int positionY;
	
	private int width;
	private int height;
	
	private final int BUFFER_Y = 50;
	
	private final String TEXTBOX_BACKGROUND = "SettingsScreen/TextboxBackground.jpeg";
	
	private Stage stage;
	
	private TextFieldStyle textStyle;  
	
	private PlayerCreationInput(){}
	
	public PlayerCreationInput(int posX, int posY, int w, int h){
		this();
		positionX = posX;
		positionY = posY;
		width = w;
		height = h;
		
		players = new ArrayList<PlayerVariableInputs>();
		
		stage = new Stage(width, height, false);
		Gdx.input.setInputProcessor(stage);
			
		for(int i = 0 ; i < MIN_PLAYERS ; i++){
			addPlayer();
		}
	}
	
	public void draw(){
		stage.draw();
	}
	
	public boolean addPlayer(){
		if(players.size() >= MAX_PLAYERS){
			return false;
		}
		PlayerVariableInputs temp = new PlayerVariableInputs(positionX, 
					positionY - (BUFFER_Y * players.size()), stage);
		players.add(temp);
		return true;
	}
}
