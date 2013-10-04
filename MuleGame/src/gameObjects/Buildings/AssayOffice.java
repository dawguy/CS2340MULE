package gameObjects.Buildings;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * This class represents the assay office in the game.
 * @author antonio
 *
 */
public class AssayOffice extends Building{

	private final static String IMAGE_LOCATION = "TownScreen/AssayOffice.jpeg";
	
	public AssayOffice(Stage s){
		super(s, IMAGE_LOCATION);
	}
	
	public AssayOffice(Stage s, int posX, int posY){
		super(s, IMAGE_LOCATION, posX, posY);
	}	
}
