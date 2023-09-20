package org.example.service;

import org.example.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void showAllMenu();
    void showMenu(Integer index);
    void addOrder(Integer index, Integer quantity);
    void getDataOrder();
    boolean checkDataOrder();
    List<Order> mapDataOrder();
}
