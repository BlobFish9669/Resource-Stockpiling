package seng201.team15.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team15.services.MoneyBalanceService;

import static org.junit.jupiter.api.Assertions.*;


class MoneyBalanceServiceTest {
    MoneyBalanceService moneyBalanceServiceTest;

    /**
     * Initialise a new MoneyBalanceService object before each test.
     */
    @BeforeEach
    void setUp() {
        moneyBalanceServiceTest = new MoneyBalanceService();
    }

    /**
     * Test for setting a new balance for the user, and adding money to the balance through the getters and setters.
     */
    @Test
    void testBalance() {
        assertEquals(0, moneyBalanceServiceTest.getCurrentBalance());
        moneyBalanceServiceTest.setNewBalance(100);
        moneyBalanceServiceTest.setNewBalance(moneyBalanceServiceTest.getCurrentBalance() + 50);
        assertEquals(150, moneyBalanceServiceTest.getCurrentBalance());
    }
}
