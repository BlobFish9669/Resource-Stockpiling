package seng201.team15.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team15.services.MoneyBalanceService;

import static org.junit.jupiter.api.Assertions.*;


class MoneyBalanceServiceTest {
    MoneyBalanceService moneyBalanceServiceTest;

    @BeforeEach
    void setUp() {
        moneyBalanceServiceTest = new MoneyBalanceService();
    }

    @Test
    void testBalance() {
        assertEquals(0, moneyBalanceServiceTest.getCurrentBalance());
        moneyBalanceServiceTest.setNewBalance(100);
        moneyBalanceServiceTest.setNewBalance(moneyBalanceServiceTest.getCurrentBalance() + 50);
        assertEquals(150, moneyBalanceServiceTest.getCurrentBalance());
    }
}
