package org.example.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private Menu menu;
    @BeforeEach
    void setUp(){
        menu = new Menu(1,"Nasi Goreng", 15000);
    }
    @Test
    void getName() {
        assertNotNull(menu.getName());
        assertEquals("Nasi Goreng", menu.getName());
    }

    @Test
    void getPrice() {
        assertNotNull(menu.getPrice());
        assertEquals(15000, menu.getPrice());
    }
}