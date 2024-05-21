package seng201.team15.unittests.models;

import org.junit.jupiter.api.Test;

import seng201.team15.models.Cart;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart testCart;

    /**
     * Test to fill the cart with resources. Checks that filling the cart updates the filled size correctly,
     * and checks that the cart is not overfilled when the filled size reaches the size of the cart.
     */
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

    /**
     * Test to check the overflow handling of the cart, to ensure it is only filled to its max size even if more resources are added.
     */
    @Test
    void fill_overflow() {
        testCart = new Cart(3, null, 0);
        assertEquals(0, testCart.getFilledSize());
        testCart.fill(4);
        assertEquals(3, testCart.getFilledSize());
    }
}