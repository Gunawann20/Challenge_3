package org.example.repository;

import org.example.entity.Order;
import java.util.HashMap;
import java.util.Map;

public class OrderRepositoryImpl implements OrderRepository{

    private final Map<String, Order> dataOrder;

    public OrderRepositoryImpl(){
        dataOrder = new HashMap<>();
    }
    @Override
    public Map<String, Order> getAll() {
        return dataOrder;
    }

    @Override
    public void add(String menuName, Order order) {
        dataOrder.put(menuName, order);
    }
}
