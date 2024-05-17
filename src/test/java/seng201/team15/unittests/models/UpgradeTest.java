package seng201.team15.unittests.models;

import seng201.team15.models.Upgrade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpgradeTest {
    private Upgrade testUpgrade;
    @Test
    void testUpgrades() {
        testUpgrade = new Upgrade("TestUpgrade", "Resource Type", "Stone", 20, 1.0);
        assertEquals("Resource Type", testUpgrade.getUpgradeType());
        assertEquals("Stone", testUpgrade.getResourceType());

        testUpgrade = new Upgrade("TestUpgrade", "Tower Points", 20, 1.0);
        assertEquals("TestUpgrade", testUpgrade.getUpgradeTitle());
        assertNull(testUpgrade.getResourceType());
        assertEquals(20, testUpgrade.getCost());
        assertEquals(1.0, testUpgrade.getUpgradeModifierAmount());
        assertEquals(15, testUpgrade.getSellPrice());
    }
}
