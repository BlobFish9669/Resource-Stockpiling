package seng201.team15.unittests.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import seng201.team15.models.Tower;
import seng201.team15.models.Upgrade;

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

        testTower.gainTowerPoints(270);
        assertEquals(5, testTower.getLevel());
        assertEquals(0, testTower.getTowerPoints());
        assertEquals(70, testTower.getCost());
        assertEquals(52, testTower.getSellPrice());
        assertEquals(21, testTower.getResourceAmount());
        assertEquals(0.1, testTower.getReloadSpeed());
        assertEquals(210, testTower.getFillRate());
    }
    @Test
    void testRoundsUsed() {
        assertEquals(0, testTower.getRoundsUsed());
        testTower.addRoundUsed();
        testTower.addRoundUsed();
        assertEquals(2, testTower.getRoundsUsed());
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
    @Test
    void testUpgradeResourceAmount() {
        testUpgrade = new Upgrade("TestUpgrade", "Resource Amount", 20, 2.0);
        assertEquals(1, testTower.getResourceAmount());
        testTower.applyUpgrade(testUpgrade);
        assertEquals(2, testTower.getResourceAmount());
        testTower.applyUpgrade(testUpgrade);
        assertEquals(4, testTower.getResourceAmount());
    }
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
