package tests.Managers;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import gameObjects.Player;
import gameObjects.Tile;
import managers.PlayerManager;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Color;
import com.me.mygdxgame.Mule;

public class TileProductionTest {
	private PlayerManager manager;
	private Player p1;
	private Player p2;

	@Before
	public void setUpOwners(){
		Mule mule = new Mule();
		manager = new PlayerManager();
		mule.pm = manager;
		p1 = new Player("David", Color.RED, 1);
		p2 = new Player("Wright", Color.BLUE, 2);
		manager.addPlayer(p1);
		manager.addPlayer(p2);
	}
	
	@Test
	public void TileCreation(){
		Tile t = new Tile(1,3,true);
	}
	
	@Test
	public void TileOwnerWorks(){
		Tile t= new Tile(2,3,true);
		t.setOwner(p1);
		assertSame("Both should be same", p1,t.getOwner());
		assertNotSame("Should not be same", p2,t.getOwner());
	}
	
	@Test
	public void OreProductionWorks(){
		Tile t= new Tile(4,3,true);
		t.setOwner(p1);
		t.setMule(0);		//Ore Mule
		t.setType(3);		//1 Mountain
		int playerResources = p1.getNumberOfResource(3);	//Ore resource
		t.produce();
		assertEquals("After production:", playerResources + 2, p1.getNumberOfResource(3));
	}
	
	@Test
	public void FoodProductionWorks(){
		Tile t= new Tile(4,3,true);
		t.setOwner(p1);
		t.setMule(1);		//Food Mule
		t.setType(5);		//2 Mountain
		int playerResources = p1.getNumberOfResource(1);	//Food resource
		t.produce();
		assertEquals("After production:", playerResources + 1, p1.getNumberOfResource(1));
	}
	
	@Test
	public void EnergyProductionWorks(){
		Tile t= new Tile(4,3,true);
		t.setOwner(p1);
		t.setMule(2);		//Energy Mule
		t.setType(0);		//Plains
		int playerResources = p1.getNumberOfResource(2);	//Energy resource
		t.produce();
		assertEquals("After production:", playerResources + 3, p1.getNumberOfResource(2));
	}
	
	@Test
	public void NoMuleProductionWorks(){
		Tile t= new Tile(4,3,true);
		t.setOwner(p1);
		t.setMule(-1);		//Energy Mule
		t.setType(0);		//Plains
		int playerResources = p1.getNumberOfResource(2);	//Energy resource
		t.produce();
		assertEquals("After production:", playerResources + 0, p1.getNumberOfResource(2));
	}
	
	@Test
	public void NonExistantResourceProductionWorks(){
		Tile t= new Tile(3,5,true);
		t.setOwner(p1);
		t.setMule(2);		//Energy Mule
		t.setType(7);		//Plains
		int playerResources = p1.getNumberOfResource(2);	//Energy resource
		t.produce();
		assertEquals("After production:", playerResources + 0, p1.getNumberOfResource(2));
	}
	
}
