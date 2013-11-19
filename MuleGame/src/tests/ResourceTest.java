package tests;

import static org.junit.Assert.*;
import gameObjects.Resource;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Eric Rabinowitz
 * tests methods in the Resource class
 */
public class ResourceTest{
	private Resource testResource;
	
	@Before
	public void setUp(){
		testResource = new Resource();
	}

	@Test
	public void startingValuesTest(){
		assertEquals("Should start with 0 money", testResource.getResource(0), 0);
		assertEquals("Should start with 4 food", testResource.getResource(1), 4);
		assertEquals("Should start with 2 energy", testResource.getResource(2), 2);
		assertEquals("Should start with 0 ore", testResource.getResource(3), 0);
	}

	@Test
	public void gainResourceTest(){
		testResource.gainResource(0, 500); //gain 500 money
		assertEquals("After gaining 500 money", testResource.getResource(0), 500);
		testResource.gainResource(2, 6); //gain 6 energy
		assertEquals("After gaining 6 energy", testResource.getResource(2), 8);
		testResource.gainResource(5, 1); //gain 1 ore mule
		assertEquals("After gaining 1 ore mule", testResource.getResource(5), 1);
	}

	@Test
	public void spendResourceTest(){
		int startMoney = testResource.getResource(0);
		int return1 = testResource.spendResource(0, 1000);
		assertEquals("After attempting to spend more money than had", return1, 0);
		assertEquals("Shouldn't lose any money", testResource.getResource(0), startMoney);

		testResource.gainResource(0, 500); //gain 500 money
		int money2 = testResource.getResource(0);
		int return2 = testResource.spendResource(0, 250);
		assertEquals("After spending money that was had", return2, 250);
		assertEquals("Should now have 250 less money", testResource.getResource(0), money2-250);
	}

	@Test
	public void loseResourceTest(){
		int food1 = testResource.getResource(1);
		int return1 = testResource.loseResource(1, 1000);
		int diff = 1000 - food1;
		assertEquals("Amount lost when not enough", return1, diff);
		assertEquals("Losing more food than had", testResource.getResource(1), 0);

		testResource.gainResource(1, 500); //gain 500 food
		int food2 = testResource.getResource(1);
		int return2 = testResource.loseResource(1, 250);
		assertEquals("After losing less food than had", return2, 250);
		assertEquals("Should now have 250 less food", testResource.getResource(1), food2-250);
	}

	@Test(expected = NullPointerException.class)
	public void invalidSpendResourceTest(){
		testResource.spendResource(10,1);
	}

	@Test(expected = NullPointerException.class)
	public void invalidLoseResourceTest(){
		testResource.loseResource(10,1);
	}
}