package managers;

import gameObjects.Map;
import gameObjects.Player;
import gameObjects.Tile;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.w3c.dom.Document;

import screens.MapScreen;
import screens.SelectTilesScreen;
import screens.TownScreen;

import com.me.mygdxgame.Mule;

public class XMLReader{
	private final static String FILE_PATH = "Game.xml";
	Document dom;
	Mule game;
	public XMLReader(Mule game){
		this.game = game;
	}
	
	public static void main(String[] args){
		XMLReader xRdr = new XMLReader(new Mule());
		xRdr.run();
	}
	
	public void run(){
		loadGame();
	}
	
	public void loadGame(){
		File file = new File("Game.xml");
		try {
			Scanner s = new Scanner(file);
			Path p = Paths.get("Game.xml").toAbsolutePath();
			int oreMule = nextInt(s);
			int foodMule = nextInt(s);
			int energyMule = nextInt(s);
			int foodR = nextInt(s);
			int energyR = nextInt(s);
			int oreR = nextInt(s);
			
			//Setting up round
			int roundNumber = nextInt(s);
			
			//Loading all tiles
			int[] tileValues = new int[5 * 45];
			for(int i = 0; i < tileValues.length; i++){
				tileValues[i] = nextInt(s);
			}
			Map map = generateMap(tileValues);
			
			//Loading Player Values
			ArrayList<Player> players = new ArrayList<Player>();
			while(s.hasNextLine()){
				boolean nameSearch = true;
				boolean raceSearch = true;
				String name = "Backwards";
				String race = "ERROR";
				while(s.hasNextLine() && nameSearch){
					String line = s.nextLine();
					if(line.contains("Name")){
						line = line.substring(18);
						line = line.substring(0, line.length() -7);	//Should now have only the name
						name = line;
						nameSearch = false;
					}
				}
				while(s.hasNextLine() && raceSearch){
					String line = s.nextLine();
					if(line.contains("Race")){
						line = line.substring(18);
						line = line.substring(0, line.length() -7);	//Should now have only the name
						race = line;
						raceSearch = false;
					}
				}
				int playerNum = nextInt(s);
				int money = nextInt(s);
				float red = nextFloat(s);
				float green = nextFloat(s);
				float blue = nextFloat(s);
				int food = nextInt(s);
				int energy = nextInt(s);
				int ore = nextInt(s);
				int crystite = nextInt(s);
				Player play = new Player(name,race,red,green,blue,food,energy,ore,crystite,playerNum,money);
				if(!race.equals("ERROR"))players.add(play);
			}
			PlayerManager pm = new PlayerManager(players);
			GameManager gm = new GameManager(game,pm,roundNumber,map,"Standard");
			Mule.pm = pm;
			Mule.gm = gm;
			Mule.MAPSCREEN = new MapScreen(game,map);
			Mule.SELECTTILESSCREEN = new SelectTilesScreen(game);
			Mule.TOWNSCREEN = new TownScreen(game);
			StoreInventory si = new StoreInventory();
			si.setResourceAmount(si.ENERGY_MULE, energyMule);
			si.setResourceAmount(si.FOOD_MULE, foodMule);
			si.setResourceAmount(si.ORE_MULE, oreMule);
			si.setResourceAmount(si.ORE_INDEX, oreR);
			si.setResourceAmount(si.ENERGY_INDEX, energyR);
			si.setResourceAmount(si.FOOD_INDEX, foodR);
			gm.setStoreInventory(si);
			Mule.gm = gm;
			Mule.TOWNSCREEN.setTownScreenStoreInventory(si);
			System.out.println(Mule.TOWNSCREEN.getStoreInventory().getResourceAmount(StoreInventory.FOOD_INDEX));
			game.setScreen(Mule.MAPSCREEN);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private int nextInt(Scanner s){
		int returnVal =-5;
		while(s.hasNextLine()){
			String testVa = s.next();
			if(isInteger(testVa)){
				return Integer.parseInt(testVa);
			}
		}
		return returnVal;
	}
	
	private boolean isInteger( String input ) {
	    try {
	        Integer.parseInt( input );
	        return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
	}
	
	private float nextFloat(Scanner s){
		float returnVal =-5;
		while(s.hasNext()){
			String testVa = s.next();
			if(isFloat(testVa)){
				return Float.parseFloat(testVa);
			}
		}
		return returnVal;
	}

	private boolean isFloat( String input ) {
	    try {
	        Float.parseFloat( input );
	        return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
	}	
	
	private Map generateMap(int[] tiles){
		Tile[][] mapTiles = new Tile[9][5];
		int count = 0;
		for(int i = 0; i < tiles.length; i+=5){
			int x = tiles[i];
			int y = tiles[i + 1];
			int type = tiles[i + 2];
			int owner = tiles[i + 3];
			int mule = tiles[i + 4];
			Tile t = new Tile(x,y,type,owner,mule);
			mapTiles[x][y] = t;
			count ++;			
		}
		Map map = new Map(mapTiles);
		return map;
	}
}