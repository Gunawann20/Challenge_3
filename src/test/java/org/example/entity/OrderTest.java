package org.example.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;
    @BeforeEach
    void setUp(){
        order = new Order("Nasi Goreng", 2, 15000);
    }
    @Test
    void getMenuName() {
        assertNotNull(order.getMenuName());
        assertEquals("Nasi Goreng", order.getMenuName());
    }

    @Test
    void getQuantity() {
        assertNotNull(order.getQuantity());
        assertEquals(2, order.getQuantity());
    }

    @Test
    void getPrice() {
        assertNotNull(order.getPrice());
        assertEquals(15000, order.getPrice());
    }
}