package gameObjects.Buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * An abstract class representing what all buildings must be able to do.
 * @author antonio
 *
 */
public abstract class Building{

	protected Image buildingImage;
	
	public Building(Stage stage, String s){
		super();
		createImage(s);
		stage.addActor(buildingImage);
	}
	
	public Building(Stage stage, String s, int x, int y){
		this(stage, s);
		buildingImage.setX(x);
		buildingImage.setY(y);
	}
	
	public void createImage(String s){
		Texture temp = new Texture(Gdx.files.internal(s));
		Sprite tempSprite = new Sprite(temp);
		SpriteDrawable tempDraw = new SpriteDrawable(tempSprite);
		buildingImage = new Image(tempDraw);
	}

	public float getX(){
		return buildingImage.getX();
	}
	
	public float getY(){
		return buildingImage.getY();
	}
	
	public float getWidth(){
		return buildingImage.getWidth();
	}
	
	public float getHeight(){
		return buildingImage.getHeight();
	}
	
	public void setX(float x){
		buildingImage.setX(x);
	}
	
	public void setY(float y){
		buildingImage.setY(y);
	}
}
