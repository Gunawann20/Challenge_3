package org.example.service;

import org.example.entity.Menu;
import org.example.entity.Order;
import org.example.repository.MenuRepository;
import org.example.repository.MenuRepositoryImpl;
import org.example.repository.OrderRepository;
import org.example.repository.OrderRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class OrderServiceImpl implements OrderService{

    private final MenuRepository menuRepository = new MenuRepositoryImpl();
    private final OrderRepository orderRepository = new OrderRepositoryImpl();
    @Override
    public void showAllMenu() {
        menuRepository.getAll().forEach(menu -> System.out.println(menu.getId()+". "+menu.getName()+"  | "+menu.getPrice()));
    }

    @Override
    public void showMenu(Integer index) {
        try {
            Menu menu = menuRepository.getAll().get(index);
            System.out.println(menu.getName() + "  | "+ menu.getPrice());
        }catch (IndexOutOfBoundsException e) {
            System.out.println("Data tidak ditemukan.");
        }
    }

    @Override
    public void addOrder(Integer index, Integer quantity) {
        try {
            Menu menu = menuRepository.getAll().get(index);
            Order order = new Order(menu.getName(), quantity, menu.getPrice());
            orderRepository.add(menu.getName(), order);
        }catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Gagal menambahkan data.");
        }
    }

    @Override
    public void getDataOrder() {
        Stream<Order> orderStream = orderRepository.getAll().values().stream();
        orderStream.filter(Objects::nonNull)
                .filter(o -> o.getMenuName() != null || o.getQuantity() != null || o.getPrice() != null)
                .filter(o -> o.getQuantity() != null && o.getQuantity() > 0)
                .forEach(o -> System.out.println(o.getMenuName()+"   "+o.getQuantity()+"  "+(o.getQuantity()*o.getPrice())));
        Integer sumQty = orderRepository.getAll().values().stream()
                .filter(Objects::nonNull)
                .filter(o -> o.getMenuName() != null || o.getQuantity() != null || o.getPrice() != null)
                .filter(o -> o.getQuantity() != null && o.getQuantity() > 0)
                .reduce(0, (v,o) -> v + o.getQuantity(), Integer::sum);
        Integer sumPrice = orderRepository.getAll().values().stream()
                .filter(Objects::nonNull)
                .filter(o -> o.getMenuName() != null || o.getQuantity() != null || o.getPrice() != null)
                .filter(o -> o.getQuantity() != null && o.getQuantity() > 0)
                .reduce(0, (v,o)-> v + (o.getQuantity() * o.getPrice()), Integer::sum);
        System.out.println("--------------------------------+");
        System.out.println("Total           "+ sumQty + "  "+ sumPrice);
    }

    @Override
    public boolean checkDataOrder() {
        return orderRepository.getAll().size() > 0;
    }

    @Override
    public List<Order> mapDataOrder() {
        return new ArrayList<>(orderRepository.getAll().values());
    }
}
