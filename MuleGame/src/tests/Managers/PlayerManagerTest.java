package tests.Managers;

import static org.junit.Assert.*;
import gameObjects.Player;

import managers.PlayerManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Color;

/**
 * This JUnit class will test methods in PlayerManager
 * @author antonio
 *
 */
public class PlayerManagerTest {

	private PlayerManager manager;
	private Player p1;
	private Player p2;
	private Player p3;
	private Player p4;
	
	@Before
	public void setUp() throws Exception {
		manager = new PlayerManager();
		p1 = new Player("Test1", Color.BLACK, 1);
		p2 = new Player("Test2", Color.RED, 2);
		p3 = new Player("Test3", Color.BLUE, 3);
		p4 = new Player("Test4", Color.GREEN, 4);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTwoPlayersOrdering(){
		p1.setMoney(2000);
		p2.setMoney(0);
		
		manager.addPlayer(p1);
		manager.addPlayer(p2);
		
		Player order[] = manager.updatePlayerOrder();
		
		Player correctOrder[] = new Player[2];
		correctOrder[0] = p2;
		correctOrder[1] = p1;
		
		boolean correct = true;
		
		for(int i = 0 ; i < order.length ; i ++){
			if(!order[i].getName().equals(correctOrder[i].getName())){
				correct = false;
			}
		}
		
		assertEquals(true, correct);
	}

	@Test
	public void testThreePlayersOrdering(){
		p1.setMoney(100);
		p2.setMoney(20);
		p3.setMoney(100);
		
		manager.addPlayer(p1);
		manager.addPlayer(p2);
		manager.addPlayer(p3);
		
		Player order[] = manager.updatePlayerOrder();
		
		Player correctOrder[] = new Player[3];
		correctOrder[0] = p2;
		correctOrder[1] = p1;
		correctOrder[2] = p3;
		
		boolean correct = true;
		
		for(int i = 0 ; i < order.length ; i ++){
			if(!order[i].getName().equals(correctOrder[i].getName())){
				correct = false;
			}
		}
		
		assertEquals(true, correct);
	}
	
	@Test
	public void testFourPlayersOrdering(){
		p1.setMoney(100);
		p2.setMoney(20);
		p3.setMoney(100);
		p4.setMoney(0);
		
		manager.addPlayer(p1);
		manager.addPlayer(p2);
		manager.addPlayer(p3);
		manager.addPlayer(p4);
		
		Player order[] = manager.updatePlayerOrder();
		
		Player correctOrder[] = new Player[4];
		correctOrder[0] = p4;
		correctOrder[1] = p2;
		correctOrder[2] = p1;
		correctOrder[3] = p3;
		
		boolean correct = true;
		
		for(int i = 0 ; i < order.length ; i ++){
			if(!order[i].getName().equals(correctOrder[i].getName())){
				correct = false;
			}
		}
		
		assertEquals(true, correct);
	}
}
