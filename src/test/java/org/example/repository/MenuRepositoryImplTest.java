package org.example.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuRepositoryImplTest {

    @Test
    void getAll() {
        MenuRepository menuRepository = new MenuRepositoryImpl();

        assertNotNull(menuRepository.getAll());
        assertEquals(5, menuRepository.getAll().size());
        assertEquals("Nasi Goreng", menuRepository.getAll().get(0).getName().trim());
        assertEquals(15000, menuRepository.getAll().get(0).getPrice());
    }
}