package seng201.team15.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team15.services.ShopAvailabilityService;

import static org.junit.jupiter.api.Assertions.*;

class ShopAvailabilityServiceTest {
    ShopAvailabilityService shopAvailabilityServiceTest;

    /**
     * Initialise a new ShopAvailabilityService class before each test.
     */
    @BeforeEach
    void setUp() {
        shopAvailabilityServiceTest = new ShopAvailabilityService();
    }

    /**
     * Test for buying towers.
     * After initialisation, the test checks that the following 4 attributes have been constructed correctly:
     * The test checks that the number of towers available to purchase is between 3 and 5 inclusive,
     * It checks that the list of available towers has the same size as the number of available towers,
     * It also checks that the purchased tower list (a list of boolean values indicated whether a specified tower has been purchased) has
     * the same size as the number of towers available,
     * It then checks that there are no towers that have been purchased.
     * The test then sets the first 2 towers as purchased, and checks that the total number of purchased towers is only 2.
     * It lastly checks that the first 2 towers have been acknowledged as being purchased in the purchased towers list.
     */
    @Test
    void testingTowersBuy() {
        shopAvailabilityServiceTest.setTotalRounds(5);
        shopAvailabilityServiceTest.resetStore(1);

        assertTrue(shopAvailabilityServiceTest.getNumberTowersAvailable() >= 3 && shopAvailabilityServiceTest.getNumberTowersAvailable() <= 5);
        assertEquals(shopAvailabilityServiceTest.getAvailableTowers().size(), shopAvailabilityServiceTest.getNumberTowersAvailable());
        assertEquals(shopAvailabilityServiceTest.getPurchasedTowers().size(), shopAvailabilityServiceTest.getNumberTowersAvailable());
        assertFalse(shopAvailabilityServiceTest.getPurchasedTowers().contains(true));

        shopAvailabilityServiceTest.setTowerPurchased(0);
        shopAvailabilityServiceTest.setTowerPurchased(1);

        int tempOccurrences = 0;
        for (Boolean bool: shopAvailabilityServiceTest.getPurchasedTowers()) {
            if (bool) {
                tempOccurrences++;
            }
        }
        assertEquals(2, tempOccurrences);
        assertTrue(shopAvailabilityServiceTest.getPurchasedTowers().get(0) && shopAvailabilityServiceTest.getPurchasedTowers().get(1));
    }

    /**
     * Test for resetting the shop.
     * The shop is initially reset, then the test checks that the following 4 attributes have been reset correctly:
     * The test checks that the number of towers available to purchase is between 3 and 5 inclusive,
     * It checks that the list of available towers has the same size as the number of available towers,
     * It also checks that the purchased tower list (a list of boolean values indicated whether a specified tower has been purchased) has
     * the same size as the number of towers available,
     * It then checks that there are no towers that have been purchased.
     * The test then indicates the first 2 towers as purchased and ensures that they are updated correctly.
     */
    @Test
    void testingTowerShopReset() {
        shopAvailabilityServiceTest.setTotalRounds(5);
        shopAvailabilityServiceTest.resetStore(5);

        assertTrue(shopAvailabilityServiceTest.getNumberTowersAvailable() >= 3 && shopAvailabilityServiceTest.getNumberTowersAvailable() <= 5);
        assertEquals(shopAvailabilityServiceTest.getAvailableTowers().size(), shopAvailabilityServiceTest.getNumberTowersAvailable());
        assertEquals(shopAvailabilityServiceTest.getPurchasedTowers().size(), shopAvailabilityServiceTest.getNumberTowersAvailable());
        assertFalse(shopAvailabilityServiceTest.getPurchasedTowers().contains(true));

        shopAvailabilityServiceTest.setTowerPurchased(0);
        shopAvailabilityServiceTest.setTowerPurchased(1);

        int tempOccurrences = 0;
        for (Boolean bool: shopAvailabilityServiceTest.getPurchasedTowers()) {
            if (bool) {
                tempOccurrences++;
            }
        }
        assertEquals(2, tempOccurrences);
        assertTrue(shopAvailabilityServiceTest.getPurchasedTowers().get(0) && shopAvailabilityServiceTest.getPurchasedTowers().get(1));
    }

    /**
     * Test for buying upgrades.
     * The shop is initially reset, then the test checks that the following 4 attributes have been reset correctly:
     * The test checks that the number of upgrades available to purchase is between 3 and 6 inclusive,
     * It checks that the list of available upgrades has the same size as the number of available upgrades,
     * It also checks that the purchased upgrade list (a list of boolean values indicated whether a specified upgrade has been purchased) has
     * the same size as the number of upgrades available,
     * It then checks that there are no upgrades that have been purchased.
     * The shop then sets the first 2 upgrades as purchased.
     * The test iterates through the purchased upgrade list, and checks that only 2 upgrades are marked as purchased.
     * It also checks that the first and second upgrades have been indicated as purchased.
     */
    @Test
    void testingUpgradesBuy() {
        shopAvailabilityServiceTest.setTotalRounds(5);
        shopAvailabilityServiceTest.resetStore(1);

        assertTrue(shopAvailabilityServiceTest.getNumberUpgradesAvailable() >= 3 && shopAvailabilityServiceTest.getNumberUpgradesAvailable() <= 6);
        assertEquals(shopAvailabilityServiceTest.getAvailableUpgrades().size(), shopAvailabilityServiceTest.getNumberUpgradesAvailable());
        assertEquals(shopAvailabilityServiceTest.getPurchasedUpgrades().size(), shopAvailabilityServiceTest.getNumberUpgradesAvailable());
        assertFalse(shopAvailabilityServiceTest.getPurchasedUpgrades().contains(true));

        shopAvailabilityServiceTest.setUpgradePurchased(0);
        shopAvailabilityServiceTest.setUpgradePurchased(1);

        int tempOccurrences = 0;
        for (Boolean bool: shopAvailabilityServiceTest.getPurchasedUpgrades()) {
            if (bool) {
                tempOccurrences++;
            }
        }
        assertEquals(2, tempOccurrences);
        assertTrue(shopAvailabilityServiceTest.getPurchasedUpgrades().get(0) && shopAvailabilityServiceTest.getPurchasedUpgrades().get(1));
    }

    /**
     * Test for resetting the upgrades portion of the shop.
     * The shop is initially reset, then the test checks that the following 4 attributes have been reset correctly:
     * The test checks that the number of upgrades available to purchase is between 3 and 6 inclusive,
     * It checks that the list of available upgrades has the same size as the number of available upgrades,
     * It also checks that the purchased upgrade list (a list of boolean values indicated whether a specified upgrade has been purchased) has
     * the same size as the number of upgrades available,
     * It then checks that there are no upgrades that have been purchased.
     * The shop then sets the first 2 upgrades as purchased.
     * The test iterates through the purchased upgrade list, and checks that only 2 upgrades are marked as purchased.
     * It also checks that the first and second upgrades have been indicated as purchased.
     */
    @Test
    void testingUpgradesShopReset() {
        shopAvailabilityServiceTest.setTotalRounds(5);
        shopAvailabilityServiceTest.resetStore(5);

        assertTrue(shopAvailabilityServiceTest.getNumberUpgradesAvailable() >= 3 && shopAvailabilityServiceTest.getNumberUpgradesAvailable() <= 6);
        assertEquals(shopAvailabilityServiceTest.getAvailableUpgrades().size(), shopAvailabilityServiceTest.getNumberUpgradesAvailable());
        assertEquals(shopAvailabilityServiceTest.getPurchasedUpgrades().size(), shopAvailabilityServiceTest.getNumberUpgradesAvailable());
        assertFalse(shopAvailabilityServiceTest.getPurchasedUpgrades().contains(true));

        shopAvailabilityServiceTest.setUpgradePurchased(0);
        shopAvailabilityServiceTest.setUpgradePurchased(1);

        int tempOccurrences = 0;
        for (Boolean bool: shopAvailabilityServiceTest.getPurchasedUpgrades()) {
            if (bool) {
                tempOccurrences++;
            }
        }
        assertEquals(2, tempOccurrences);
        assertTrue(shopAvailabilityServiceTest.getPurchasedUpgrades().get(0) && shopAvailabilityServiceTest.getPurchasedUpgrades().get(1));
    }
}
