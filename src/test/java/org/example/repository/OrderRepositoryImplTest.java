package org.example.repository;

import org.example.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryImplTest {

    private OrderRepository orderRepository;
    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepositoryImpl();
        orderRepository.add("Nasi Goreng", new Order("Nasi Goreng", 2, 15000));
    }

    @Test
    void getAll() {

        assertEquals(1, orderRepository.getAll().size());
        assertEquals("Nasi Goreng", orderRepository.getAll().get("Nasi Goreng").getMenuName());
        assertEquals(2, orderRepository.getAll().get("Nasi Goreng").getQuantity());
        assertEquals(15000, orderRepository.getAll().get("Nasi Goreng").getPrice());
    }

    @Test
    void add() {

        orderRepository.add("Es Teh Manis", new Order("Es Teh Manis", 3, 3000));

        assertEquals(2, orderRepository.getAll().size());
        assertEquals("Es Teh Manis", orderRepository.getAll().get("Es Teh Manis").getMenuName());
        assertEquals(3, orderRepository.getAll().get("Es Teh Manis").getQuantity());
        assertEquals(3000, orderRepository.getAll().get("Es Teh Manis").getPrice());
    }

    @Test
    void add_with_null_data() {

        orderRepository.add("Es Teh Manis", new Order(null, null, null));

        assertEquals(2, orderRepository.getAll().size());
    }
}
