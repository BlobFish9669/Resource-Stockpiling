package seng201.team15.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team15.services.ShopAvailabilityService;

import static org.junit.jupiter.api.Assertions.*;

class ShopAvailabilityServiceTest {
    ShopAvailabilityService shopAvailabilityServiceTest;

    @BeforeEach
    void setUp() {
        shopAvailabilityServiceTest = new ShopAvailabilityService();
    }

    @Test
    void testingTowersBuyAndSell() {
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

    @Test
    void testingUpgradesBuyAndSell() {
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
