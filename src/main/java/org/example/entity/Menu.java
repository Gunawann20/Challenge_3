package org.example.entity;

import lombok.Getter;

@Getter
public class Menu {

    private final int id;
    private final String name;
    private final Integer price;

    public Menu(int id, String name, Integer price){
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
