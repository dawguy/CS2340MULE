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
	
	private List<TextField> playerNames;
	
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
		playerNames = new ArrayList<TextField>();
		
		stage = new Stage(width, height, false);
		Gdx.input.setInputProcessor(stage);
		
		textStyle = new TextFieldStyle();
		textStyle.font = new BitmapFont();
		textStyle.fontColor = Color.BLACK;
		
		Sprite spriteTemp = new Sprite(new Texture(TEXTBOX_BACKGROUND));
		SpriteDrawable drawableTemp = new SpriteDrawable(spriteTemp);
		
		textStyle.background = drawableTemp;
		
		for(int i = 0 ; i < MIN_PLAYERS ; i++){
			addPlayer();
		}
	}
	
	public void draw(){
		stage.draw();
	}
	
	public boolean addPlayer(){
		if(playerNames.size() >= MAX_PLAYERS){
			return false;
		}
		
		TextField temp = new TextField("Player #" + (playerNames.size() + 1), textStyle);
		temp.setY(positionY + (BUFFER_Y * playerNames.size()));
		temp.setTextFieldListener(new TextFieldListener(){

			@Override
			public void keyTyped(TextField textField, char key) {
				if((int)key == DELETE_KEY && textField.getText().length() > 0){
					textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
				} else if((int) key == ENTER_KEY){
					
				}
			}
			
		});
		playerNames.add(temp);
		stage.addActor(temp);
		return true;
	}
}