package seng201.team0.unittests.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seng201.team0.models.Cart;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart testCart;

    @Test
    void fill() {
        testCart = new Cart(5, "Stone", 0);
        assertEquals(5, testCart.getSize());
        assertEquals("Stone", testCart.getResourceType());
        assertEquals(0, testCart.getSpeed());
        assertEquals(0, testCart.getFilledSize());
        testCart.fill(2);
        assertEquals(2, testCart.getFilledSize());
        testCart.fill(6);
        assertEquals(5, testCart.getFilledSize());
    }
    @Test
    void fill_overflow() {
        testCart = new Cart(3, null, 0);
        assertEquals(0, testCart.getFilledSize());
        testCart.fill(4);
        assertEquals(3, testCart.getFilledSize());
    }
}