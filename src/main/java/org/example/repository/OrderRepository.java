package org.example.repository;

import org.example.entity.Order;

import java.util.Map;

public interface OrderRepository {
    Map<String, Order> getAll();
    void add(String menuName, Order order);
}
