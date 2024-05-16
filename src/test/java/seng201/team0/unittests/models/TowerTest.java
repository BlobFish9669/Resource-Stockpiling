package seng201.team0.unittests.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;

import static org.junit.jupiter.api.Assertions.*;

public class TowerTest {
    private Tower testTower;
    private Upgrade testUpgrade;
    @BeforeEach
    void setupTest() {
        testTower = new Tower("Stone", 1, 1, 1, 30);
    }
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
    }
    @Test
    void testUpgradeChangeResource() {
        testUpgrade = new Upgrade("TestUpgrade", "Resource Type", "Gold", 20, 1.0);
        assertEquals("Stone", testTower.getResourceType());
        testTower.applyUpgrade(testUpgrade);
        assertEquals("Gold", testTower.getResourceType());
    }
    @Test
    void testUpgradeAddPoints() {
        testUpgrade = new Upgrade("TestUpgrade", "Tower Points", 20, 150.0);
        assertEquals(0, testTower.getTowerPoints());
        testTower.applyUpgrade(testUpgrade);
        assertEquals(50, testTower.getTowerPoints());
        assertEquals(2, testTower.getLevel());
    }
}
