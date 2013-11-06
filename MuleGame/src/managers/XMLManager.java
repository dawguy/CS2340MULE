package managers;

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
			
			Element rootElement = document.createElement("RootElement");
			
			document.appendChild(rootElement);
			
			Element secondElement = document.createElement("SecondElement");
			
			rootElement.appendChild(secondElement);
			
			Attr testAttr = document.createAttribute("TestAttribute");
			testAttr.setValue("TestValue");
			secondElement.setAttributeNode(testAttr);
			
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
	
	public static void main(String[] args){
		SaveGame();
	}
}
