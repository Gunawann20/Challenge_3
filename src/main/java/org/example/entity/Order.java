package org.example.entity;

import lombok.Getter;

@Getter
public class Order {

    private final String menuName;
    private final Integer quantity;
    private final Integer price;

    public Order(String menuName, Integer quantity, Integer price){
        this.menuName = menuName;
        this.price = price;
        this.quantity = quantity;
    }
}
