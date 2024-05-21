package seng201.team15.unittests.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import seng201.team15.models.Tower;
import seng201.team15.models.Upgrade;

import static org.junit.jupiter.api.Assertions.*;

public class TowerTest {
    private Tower testTower;
    private Upgrade testUpgrade;

    /**
     * Initialise a new Tower object before each test.
     */
    @BeforeEach
    void setupTest() {
        testTower = new Tower("Stone", 1, 1, 1, 30);
    }

    /**
     * Test for updating tower points. Tests ensure that when tower points are gained, the level is updated correctly,
     * along with the stats of the tower. The different blocks of code indicate different instances of updating tower points,
     * with increments that cause zero, one, or more than one level increase at a time. The test also ensures that attributes of the tower,
     * such as the reload speed, do not fall below their specified threshold.
     */
    @Test
    void testTowerPoints() {
        assertEquals(0, testTower.getTowerPoints());
        testTower.gainTowerPoints(50);
        assertEquals(50, testTower.getTowerPoints());
        testTower.gainTowerPoints(80);
        assertEquals(2, testTower.getLevel());
        assertEquals(30, testTower.getTowerPoints());
        assertEquals(40, testTower.getCost());
        assertEquals(30, testTower.getSellPrice());
        assertEquals(6, testTower.getResourceAmount());
        assertEquals(0.75, testTower.getReloadSpeed());
        assertEquals(8, testTower.getFillRate());

        testTower.gainTowerPoints(600);
        assertEquals(4, testTower.getLevel());
        assertEquals(130, testTower.getTowerPoints());
        assertEquals(60, testTower.getCost());
        assertEquals(45, testTower.getSellPrice());
        assertEquals(16, testTower.getResourceAmount());
        assertEquals(0.25, testTower.getReloadSpeed());
        assertEquals(64, testTower.getFillRate());

        testTower.gainTowerPoints(270);
        assertEquals(5, testTower.getLevel());
        assertEquals(0, testTower.getTowerPoints());
        assertEquals(70, testTower.getCost());
        assertEquals(52, testTower.getSellPrice());
        assertEquals(21, testTower.getResourceAmount());
        assertEquals(0.1, testTower.getReloadSpeed());
        assertEquals(210, testTower.getFillRate());
    }

    /**
     * Test to ensure that when a tower is used, the rounds used property is increased to indicate that the tower was used in that round.
     */
    @Test
    void testRoundsUsed() {
        assertEquals(0, testTower.getRoundsUsed());
        testTower.addRoundUsed();
        testTower.addRoundUsed();
        assertEquals(2, testTower.getRoundsUsed());
    }

    /**
     * Test for applying a change resource type upgrade to a tower. The test ensures that the resource type is changed according to the resource type
     * specified by the upgrade that is applied.
     */
    @Test
    void testUpgradeChangeResource() {
        testUpgrade = new Upgrade("TestUpgrade", "Resource Type", "Gold", 20, 1.0);
        assertEquals("Stone", testTower.getResourceType());
        testTower.applyUpgrade(testUpgrade);
        assertEquals("Gold", testTower.getResourceType());
    }

    /**
     * Test for applying an upgrade that adds points to the tower. The test ensures that points are added correctly, and the tower
     * level is updated accordingly.
     */
    @Test
    void testUpgradeAddPoints() {
        testUpgrade = new Upgrade("TestUpgrade", "Tower Points", 20, 150.0);
        assertEquals(0, testTower.getTowerPoints());
        testTower.applyUpgrade(testUpgrade);
        assertEquals(50, testTower.getTowerPoints());
        assertEquals(2, testTower.getLevel());
    }

    /**
     * Test for applying an upgrade that increases the resource amount of a tower.
     */
    @Test
    void testUpgradeResourceAmount() {
        testUpgrade = new Upgrade("TestUpgrade", "Resource Amount", 20, 2.0);
        assertEquals(1, testTower.getResourceAmount());
        testTower.applyUpgrade(testUpgrade);
        assertEquals(2, testTower.getResourceAmount());
        testTower.applyUpgrade(testUpgrade);
        assertEquals(4, testTower.getResourceAmount());
    }

    /**
     * Test for upgrade that reduces the reload speed of the tower. The reload speed modifier is applied multiplicatively, and
     * the test ensures that the reload speed does not fall below the specified threshold.
     */
    @Test
    void testUpgradeReloadSpeed() {
        testUpgrade = new Upgrade("TestUpgrade", "Reload Speed", 20, 0.5);
        assertEquals(1, testTower.getReloadSpeed());
        testTower.applyUpgrade(testUpgrade);
        assertEquals(0.5, testTower.getReloadSpeed());
        testTower.applyUpgrade(testUpgrade);
        assertEquals(0.25, testTower.getReloadSpeed());
        testTower.applyUpgrade(testUpgrade);
        testTower.applyUpgrade(testUpgrade);
        assertEquals(0.1, testTower.getReloadSpeed());
    }

    /**
     * Test for appling a price reduction upgrade. Also checks that the sell price is updated appropriately when the cost price is changed.
     */
    @Test
    void testUpgradePrice() {
        testUpgrade = new Upgrade("TestUpgrade", "Price", 20, 0.5);
        testTower.setCost(40);
        assertEquals(40, testTower.getCost());
        assertEquals(30, testTower.getSellPrice());
        testTower.applyUpgrade(testUpgrade);
        assertEquals(20, testTower.getCost());
        assertEquals(15, testTower.getSellPrice());
    }
}
