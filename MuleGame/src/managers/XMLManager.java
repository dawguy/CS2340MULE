package managers;

import gameObjects.Player;
import gameObjects.Tile;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.me.mygdxgame.Mule;

/**
 * This class will load and save a game from an XML document in the assets/SavedFiles directory
 * @author antonio
 *
 */
public class XMLManager {

	private final static String FILE_PATH = "Game.xml";
	
	
	/**
	 * This class creates the document that will hold our tree structure for the game.
	 * @return the document that was created using this method
	 * @throws ParserConfigurationException
	 */
	private static Document getDocument() throws ParserConfigurationException{
		DocumentBuilderFactory buildFactory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder builder = buildFactory.newDocumentBuilder();
		
		Document document = builder.newDocument();
		
		return document;
	}
	
	/**
	 * This method takes the document created by the other methods and saves it to the correct file.
	 * @param document the document we are saving.
	 * @throws TransformerException
	 */
	private static void writeToFile(Document document) throws TransformerException{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		
        Transformer transformer = transformerFactory.newTransformer();
        
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        
        DOMSource source = new DOMSource(document);
        
        File outFile = new File(FILE_PATH);
        
        StreamResult result =  new StreamResult(outFile);
        
        transformer.transform(source, result);
	}
	
	/**
	 * This method will save the various objects that we need to save.
	 */
	public static void SaveGame(){
		
		try {
			
			Document document = getDocument();
			
			saveData(document);			
			
			writeToFile(document);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void saveData(Document document){
		Element gameInstanceElement = document.createElement("GameInstance");
		
		Element gameManagerElement = document.createElement("GameManager");
		
		Element playerManagerElement = document.createElement("PlayerManager");
		
		gameInstanceElement.appendChild(gameManagerElement);
				
		gameInstanceElement.appendChild(playerManagerElement);
		
		//Saving the game manager's data
		Element difficultyElement = document.createElement("Difficulty");
		difficultyElement.appendChild(document.createTextNode(Mule.gm.getDifficulty()));
		gameManagerElement.appendChild(difficultyElement);

		
		Element roundElement = document.createElement("CurrentRound");
		roundElement.appendChild(document.createTextNode(Integer.toString(Mule.gm.getCurrentRound())));
		gameManagerElement.appendChild(roundElement);
		
		saveMap(document, gameManagerElement);
		
		document.appendChild(gameInstanceElement);
	}
	
	private static void saveMap(Document document, Element gameManager){
		Element mapElement = document.createElement("Map");
		
		Tile[][] tiles = Mule.gm.getMap().getTiles();
		
		for(int i = 0 ; i < tiles.length ; i ++){
			for(int j = 0 ; j < tiles[i].length ; j ++){
				Element tileElement = getTileElement(document, tiles[i][j]);
				
				mapElement.appendChild(tileElement);
			}
		}
		
		gameManager.appendChild(mapElement);
	}
	
	private static Element getTileElement(Document document, Tile t){
		Element tileElement = document.createElement("Tile");
		
		Element posX = document.createElement("PositionX");
		posX.appendChild(document.createTextNode(Integer.toString(t.getX())));
		tileElement.appendChild(posX);
		
		Element posY = document.createElement("PositionY");
		posY.appendChild(document.createTextNode(Integer.toString(t.getY())));
		tileElement.appendChild(posY);
		
		Element tileType = document.createElement("TileType");
		tileType.appendChild(document.createTextNode(Integer.toString(t.getTileType())));
		tileElement.appendChild(tileType);
		
		Player p = t.getOwner();
		
		String owner = "";
		
		if(p != null){
			owner = p.getName();
		}
		
		Element ownerElement = document.createElement("Owner");
		ownerElement.appendChild(document.createTextNode(owner));
		tileElement.appendChild(ownerElement);
		
		
		return tileElement;
	}
	
	public static void main(String[] args){
		SaveGame();
	}
}
