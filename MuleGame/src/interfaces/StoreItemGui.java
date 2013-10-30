package interfaces;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * This class represents the buying of a single item. It will have two buttons, a plus and minus, with a number
 * in the middle that represents the number the player has
 * @author antonio, Eric Rabinowitz
 *
 */
public class StoreItemGui {

	private final String TEXTBOX_BACKGROUND = "SettingsScreen/TextboxBackground.jpeg";
	private final int ENTER_KEY = 13;
	private final int DELETE_KEY = 8;

	private TextFieldStyle textStyle;
	private TextField amountField;
	private Stage stage;

	private String name;
	private int price;
	private int owned;
	private int x;
	private int y;
	
	private StoreItemGui(){
		super();
		name = "-";
		price = 0;
		owned = 0;
	}
	
	/**
	 * The position is the bottom left hand corner of the gui.
	 * @param posX
	 * @param posY
	 */
	public StoreItemGui(int posX, int posY, Stage s){
		this();
		x = posX;
		y = posY;

		createAmountField();

		addActorsToStage(s);
		stage = s;
	}

	private void addActorsToStage(Stage s){
		s.addActor(amountField);
	}

	public void resetAmount(){
		amountField.setText("0");
	}

	private void createAmountField(){
		textStyle = new TextFieldStyle();
		textStyle.font = new BitmapFont();
		textStyle.fontColor = Color.BLACK;
		
		Sprite spriteTemp = new Sprite(new Texture(TEXTBOX_BACKGROUND));
		SpriteDrawable drawableTemp = new SpriteDrawable(spriteTemp);
		
		textStyle.background = drawableTemp;
		
		amountField = new TextField("0", textStyle);
		
		amountField.setX(x);
		amountField.setY(y);
		amountField.setWidth(30);
		
		amountField.setTextFieldListener(new MyTextFieldListener());
	}
	public void draw(SpriteBatch batch){
		BitmapFont font = new BitmapFont();
		batch.begin();
		font.draw(batch,name,x-10,y+270);
		font.draw(batch,Integer.toString(price),x,y+130);
		font.draw(batch,Integer.toString(owned),x,y+110);
		batch.end();
	}

	public void setName(String name){
		this.name = name;
	}
	public void setPrice(int price){
		this.price = price;
	}
	public void setOwned(int owned){
		this.owned = owned;
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
