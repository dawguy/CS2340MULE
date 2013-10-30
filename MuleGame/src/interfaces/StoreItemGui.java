package interfaces;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * This class represents the buying of a single item. It will have two buttons, a plus and minus, with a number
 * in the middle that represents the number the player has
 * @author antonio, Eric Rabinowitz
 *
 */
public class StoreItemGui {

	private final String TEXTBOX_BACKGROUND = "SettingsScreen/TextboxBackground.jpeg";

	private TextFieldStyle textStyle;
	private TextField amountField;

	private int x;
	private int y;
	
	private StoreItemGui(){
		super();
	}
	
	/**
	 * The position is the bottom left hand corner of the gui.
	 * @param posX
	 * @param posY
	 */
	public StoreItemGui(int posX, int posY){
		this();
		x = posX;
		y = posY;

		createAmountField();
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
		
		// amountField.setTextFieldListener(new MyTextFieldListener());
	}

}
