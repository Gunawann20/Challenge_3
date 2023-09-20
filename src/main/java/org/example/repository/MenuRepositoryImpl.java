package org.example.repository;

import org.example.entity.Menu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuRepositoryImpl implements MenuRepository{

    private final List<Menu> menuList;
    public MenuRepositoryImpl(){
        menuList = new ArrayList<>(
                Arrays.asList(
                        new Menu(1,"Nasi Goreng  ", 15_000),
                        new Menu(2,"Mie Goreng   ", 13_000),
                        new Menu(3,"Nasi + Ayam  ", 18_000),
                        new Menu(4,"Es Teh Manis ", 3_000),
                        new Menu(5,"Es Jeruk     ", 5_000)
                )
        );
    }
    @Override
    public List<Menu> getAll() {
        return menuList;
    }
}
