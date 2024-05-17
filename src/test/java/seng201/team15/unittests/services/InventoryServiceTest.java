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

    @BeforeEach
    void setupTest() {
        inventoryServiceTest = new InventoryService();
    }

    @Test
    void testDifficultySelection() {
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