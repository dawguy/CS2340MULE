package interfaces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * A class that will represent all of the text input for each player. This 
 * includes the player's name, race, and color. Race and color will need 
 * to be drop down boxes.
 * @author antonio
 *
 */
public class PlayerVariableInputs {

	private final String TEXTBOX_BACKGROUND = "SettingsScreen/TextboxBackground.jpeg";
	private final int ENTER_KEY = 13;
	private final int DELETE_KEY = 8;
	private final String[] RACES = {"HUMAN", "TEST1", "TEST2"};
	private final int BUFFER_Y = 25;
	private final int BUFFER_X = 25;
	
	private TextField playerName;
	private DropMenu playerRace;
	
	private TextFieldStyle textStyle;
	
	private int positionX;
	private int positionY;
	
	private Stage stage;
	
	
	public PlayerVariableInputs(int x, int y, Stage s){
		positionX = x;
		positionY = y;
		
		createPlayerText();
		createRaceBox();
		addActorsToStage(s);
		stage = s;
	}
	
	public boolean removeActorsFromStage(Stage s){
		s.getActors().removeValue(playerName, true);
		s.getActors().removeValue(playerRace, true);
		return true;
	}
	
	private void addActorsToStage(Stage s){
		s.addActor(playerName);
		s.addActor(playerRace);
	}
	
	private void createPlayerText(){
		textStyle = new TextFieldStyle();
		textStyle.font = new BitmapFont();
		textStyle.fontColor = Color.BLACK;
		
		Sprite spriteTemp = new Sprite(new Texture(TEXTBOX_BACKGROUND));
		SpriteDrawable drawableTemp = new SpriteDrawable(spriteTemp);
		
		textStyle.background = drawableTemp;
		
		playerName = new TextField("PLAYER NAME", textStyle);
		
		playerName.setX(positionX);
		playerName.setY(positionY);
		
		playerName.setTextFieldListener(new MyTextFieldListener());
	}
	
	private void createRaceBox(){
		playerRace = new DropMenu(RACES,(int) (positionX + playerName.getWidth() + BUFFER_X), positionY);
	}
	
	private class MyTextFieldListener implements TextFieldListener{

		@Override
		public void keyTyped(TextField textField, char key) {
			if((int)key == DELETE_KEY && textField.getText().length() > 0){
				textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
			} else if((int) key == ENTER_KEY){
				
			}
		}
		
	}
}
