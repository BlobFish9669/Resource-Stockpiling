package seng201.team0.unittests.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Inventory;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    /*
    private Inventory inventoryTest;

    @BeforeEach
    void setupTest() {
        inventoryTest = new Inventory();
    }

    @Test
    void testDifficultySelection() {
        new ArrayList<>(Arrays.asList("Stone", "Coal", "Copper", "Silver"));
        Tower tempTower = new Tower("Coal", 1, 1.0, 1, 1);
        Upgrade tempUpgrade = new Upgrade("Change Resource Type to Diamond", "Resource Type" , "Diamond", 60, 1.0);
        inventoryTest.setMainTowers(new ArrayList<>(List.of(tempTower, tempTower, tempTower)));
        inventoryTest.addMainTower(tempTower);
        inventoryTest.addReserveTower(tempTower);
        inventoryTest.addUpgrade(tempUpgrade);
        assertEquals(tempTower, inventoryTest.getMainTowers().getLast());
        assertEquals(tempTower, inventoryTest.getReserveTowers().getLast());
        assertEquals(tempUpgrade, inventoryTest.getUpgrades().getLast());
        assertEquals(4, inventoryTest.getMainTowers().size());
        assertEquals(1, inventoryTest.getReserveTowers().size());
        assertEquals(1, inventoryTest.getUpgrades().size());
        inventoryTest.removeMainTower(tempTower);
        inventoryTest.removeReserveTower(tempTower);
        inventoryTest.removeUpgrade(tempUpgrade);
        assertEquals(3, inventoryTest.getMainTowers().size());
        assertEquals(0, inventoryTest.getReserveTowers().size());
        assertEquals(0, inventoryTest.getUpgrades().size());
        inventoryTest.resetInventory();
        assertEquals(0, inventoryTest.getMainTowers().size());
        assertEquals(0, inventoryTest.getReserveTowers().size());
        assertEquals(0, inventoryTest.getUpgrades().size());
    }*/
}
