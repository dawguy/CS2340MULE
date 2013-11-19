import static org.junit.Assert.*;
import gameObjects.Player;
import managers.StoreInventory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author andrew
 *
 */
public class StoreInventoryTest {
    /**
     * The test fixture
     */
	StoreInventory inventory;

    /**
     * This method is run before each test
     * @throws java.lang.Exception
     */
	@Before
	public void setUp() throws Exception {
		inventory=new StoreInventory();
	}

    /**
     * Test method for {@link managers.StoreInventory#getResourceAmount()}.
     */
    @Test
    public void testGetResourceAmount() {
       Assert.assertEquals("Initial Resource Food Incorrect", 16, inventory.getResourceAmount(3));
       Assert.assertEquals("Initial Resource Energy Incorrect", 16, inventory.getResourceAmount(4));
       Assert.assertEquals("Initial Resource Mule Incorrect", 25, inventory.getResourceAmount(0));
       Assert.assertEquals("Initial Resource Ore Incorrect", 0, inventory.getResourceAmount(5));
    }
    
    /**
     * Test method for {@link managers.StoreInventory#getResourcePrice()}.
     */
    @Test
    public void testGetResourcePrice() {
       Assert.assertEquals("Initial Price Food incorrect", 30, inventory.getResourcePrice(3));
       Assert.assertEquals("Initial Price Energy incorrect", 25, inventory.getResourcePrice(4));
       Assert.assertEquals("Initial Price Mule incorrect",100, inventory.getResourcePrice(0));
       Assert.assertEquals("Initial Price Ore incorrect", 50, inventory.getResourcePrice(5));
    }

    /**
     * Test method for {@link managers.StoreInventory#setResourceAmount()}.
     */
    @Test
    public void testSetResourceAmount() {
    	inventory.setResourceAmount(0,200);	
       Assert.assertEquals("New Resource Food Incorrect", 200, inventory.getResourceAmount(0));
       inventory.setResourceAmount(3,64);	
       Assert.assertEquals("New Resource Energy Incorrect", 64, inventory.getResourceAmount(3));
    }
    
    /**
     * Test method for {@link managers.StoreInventory#sellResource()}.
     */
    @Test
    public void testSellResource() {
    	Player p = new Player(1);
    	inventory.sellResource(p,3);
        Assert.assertEquals("New Resource Food Incorrect", 17, inventory.getResourceAmount(3));

    }
}
