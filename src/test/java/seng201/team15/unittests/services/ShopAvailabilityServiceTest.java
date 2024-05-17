package seng201.team15.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team15.services.ShopAvailabilityService;

import static org.junit.jupiter.api.Assertions.*;

class ShopAvailabilityServiceTest {
    ShopAvailabilityService shopAvailabilityService;

    @BeforeEach
    void setUp() {
        shopAvailabilityService = new ShopAvailabilityService();
    }

    @Test
    void testingTowersBuyAndSell() {
        shopAvailabilityService.setTotalRounds(5);
        assertTrue(shopAvailabilityService.getNumberTowersAvailable() >= 3 && shopAvailabilityService.getNumberTowersAvailable() <= 5);
        assertEquals(shopAvailabilityService.getAvailableTowers().size(), shopAvailabilityService.getNumberTowersAvailable());
        assertEquals(shopAvailabilityService.getPurchasedTowers().size(), shopAvailabilityService.getNumberTowersAvailable());
        assertFalse(shopAvailabilityService.getPurchasedTowers().contains(true));

        shopAvailabilityService.setTowerPurchased(0);
        shopAvailabilityService.setTowerPurchased(1);

        int tempOccurrences = 0;
        for (Boolean bool: shopAvailabilityService.getPurchasedTowers()) {
            if (bool) {
                tempOccurrences++;
            }
        }
        assertEquals(2, tempOccurrences);
        assertTrue(shopAvailabilityService.getPurchasedTowers().get(0) && shopAvailabilityService.getPurchasedTowers().get(1));
    }

    @Test
    void testingTowerShopReset() {
        shopAvailabilityService.setTotalRounds(5);
        shopAvailabilityService.resetStore(5);

        assertTrue(shopAvailabilityService.getNumberTowersAvailable() >= 3 && shopAvailabilityService.getNumberTowersAvailable() <= 5);
        assertEquals(shopAvailabilityService.getAvailableTowers().size(), shopAvailabilityService.getNumberTowersAvailable());
        assertEquals(shopAvailabilityService.getPurchasedTowers().size(), shopAvailabilityService.getNumberTowersAvailable());
        assertFalse(shopAvailabilityService.getPurchasedTowers().contains(true));

        shopAvailabilityService.setTowerPurchased(0);
        shopAvailabilityService.setTowerPurchased(1);

        int tempOccurrences = 0;
        for (Boolean bool: shopAvailabilityService.getPurchasedTowers()) {
            if (bool) {
                tempOccurrences++;
            }
        }
        assertEquals(2, tempOccurrences);
        assertTrue(shopAvailabilityService.getPurchasedTowers().get(0) && shopAvailabilityService.getPurchasedTowers().get(1));
    }

    @Test
    void testingUpgradesBuyAndSell() {
        shopAvailabilityService.setTotalRounds(5);

        assertTrue(shopAvailabilityService.getNumberUpgradesAvailable() >= 3 && shopAvailabilityService.getNumberUpgradesAvailable() <= 6);
        assertEquals(shopAvailabilityService.getAvailableUpgrades().size(), shopAvailabilityService.getNumberUpgradesAvailable());
        assertEquals(shopAvailabilityService.getPurchasedUpgrades().size(), shopAvailabilityService.getNumberUpgradesAvailable());
        assertFalse(shopAvailabilityService.getPurchasedUpgrades().contains(true));

        shopAvailabilityService.setUpgradePurchased(0);
        shopAvailabilityService.setUpgradePurchased(1);

        int tempOccurrences = 0;
        for (Boolean bool: shopAvailabilityService.getPurchasedUpgrades()) {
            if (bool) {
                tempOccurrences++;
            }
        }
        assertEquals(2, tempOccurrences);
        assertTrue(shopAvailabilityService.getPurchasedUpgrades().get(0) && shopAvailabilityService.getPurchasedUpgrades().get(1));
    }

    @Test
    void testingUpgradesShopReset() {
        shopAvailabilityService.setTotalRounds(5);
        shopAvailabilityService.resetStore(5);

        assertTrue(shopAvailabilityService.getNumberUpgradesAvailable() >= 3 && shopAvailabilityService.getNumberUpgradesAvailable() <= 6);
        assertEquals(shopAvailabilityService.getAvailableUpgrades().size(), shopAvailabilityService.getNumberUpgradesAvailable());
        assertEquals(shopAvailabilityService.getPurchasedUpgrades().size(), shopAvailabilityService.getNumberUpgradesAvailable());
        assertFalse(shopAvailabilityService.getPurchasedUpgrades().contains(true));

        shopAvailabilityService.setUpgradePurchased(0);
        shopAvailabilityService.setUpgradePurchased(1);

        int tempOccurrences = 0;
        for (Boolean bool: shopAvailabilityService.getPurchasedUpgrades()) {
            if (bool) {
                tempOccurrences++;
            }
        }
        assertEquals(2, tempOccurrences);
        assertTrue(shopAvailabilityService.getPurchasedUpgrades().get(0) && shopAvailabilityService.getPurchasedUpgrades().get(1));
    }
}
