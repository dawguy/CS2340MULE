package gameObjects.Buildings;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * This class represents the land office in the game
 * @author antonio
 *
 */
public class LandOffice extends Building{

	private final static String IMAGE_LOCATION = "TownScreen/LandOffice.jpeg";
	
	public LandOffice(Stage s){
		super(s, IMAGE_LOCATION);
	}
	
	public LandOffice(Stage s, int posX, int posY){
		super(s, IMAGE_LOCATION, posX, posY);
	}
}