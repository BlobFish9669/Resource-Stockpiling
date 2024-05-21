package seng201.team15.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team15.models.Tower;
import seng201.team15.models.Upgrade;
import seng201.team15.services.InventoryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {
    private InventoryService inventoryServiceTest;

    /**
     * Initialise an InventoryService object before each test.
     */
    @BeforeEach
    void setupTest() {
        inventoryServiceTest = new InventoryService();
    }

    /**
     * Test for performing inventory tasks involving the main and reserve set of towers, along with upgrades.
     * The test checks that the following tasks are executed correctly:
     * Setting a list of towers as the main tower selection,
     * Adding towers to the reserve tower selection,
     * Adding upgrades to the user's upgrades,
     * Adding towers to the main tower selection,
     * Removing a main tower, reserve tower, and upgrade,
     * And resetting the inventory.
     */
    @Test
    void testInventoryService() {
        new ArrayList<>(Arrays.asList("Stone", "Coal", "Copper", "Silver"));
        Tower tempTower = new Tower("Coal", 1, 1.0, 1, 1);
        Upgrade tempUpgrade = new Upgrade("Change Resource Type to Diamond", "Resource Type" , "Diamond", 60, 1.0);
        inventoryServiceTest.setMainTowerSelection(new ArrayList<>(List.of(tempTower, tempTower, tempTower)));
        inventoryServiceTest.addToMainTowerSelection(tempTower);
        inventoryServiceTest.addToReserveTowerSelection(tempTower);
        inventoryServiceTest.addUserUpgrade(tempUpgrade);
        assertEquals(tempTower, inventoryServiceTest.getMainTowerSelection().getLast());
        assertEquals(tempTower, inventoryServiceTest.getReserveTowerSelection().getLast());
        assertEquals(tempUpgrade, inventoryServiceTest.getUserUpgrades().getLast());
        assertEquals(4, inventoryServiceTest.getMainTowerSelection().size());
        assertEquals(1, inventoryServiceTest.getReserveTowerSelection().size());
        assertEquals(1, inventoryServiceTest.getUserUpgrades().size());
        inventoryServiceTest.removeMainTower(tempTower);
        inventoryServiceTest.removeReserveTower(tempTower);
        inventoryServiceTest.removeUserUpgrade(tempUpgrade);
        assertEquals(3, inventoryServiceTest.getMainTowerSelection().size());
        assertEquals(0, inventoryServiceTest.getReserveTowerSelection().size());
        assertEquals(0, inventoryServiceTest.getUserUpgrades().size());
        inventoryServiceTest.resetInventory();
        assertEquals(0, inventoryServiceTest.getMainTowerSelection().size());
        assertEquals(0, inventoryServiceTest.getReserveTowerSelection().size());
        assertEquals(0, inventoryServiceTest.getUserUpgrades().size());
    }
}