package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.services.ShopAvailabilityService;

import static org.junit.jupiter.api.Assertions.*;

class ShopAvailablityServiceTest {
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

        //shopAvailabilityService.resetStore(2);
    }
}
