package org.example.repository;

import org.example.entity.Menu;
import java.util.List;

public interface MenuRepository {
    List<Menu> getAll();
}
