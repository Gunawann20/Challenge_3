package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    private OrderService orderService;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl();
        orderService.addOrder(0, 3);
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void showAllMenu() {
        orderService.showAllMenu();

        String expected =
                "1. Nasi Goreng    | 15000\r\n" +
                "2. Mie Goreng     | 13000\r\n" +
                "3. Nasi + Ayam    | 18000\r\n" +
                "4. Es Teh Manis   | 3000\r\n" +
                "5. Es Jeruk       | 5000";
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void showMenu() {
        orderService.showMenu(0);

        String expected =
                "Nasi Goreng    | 15000";
        assertEquals(expected, outputStream.toString().trim());
    }
    @Test
    void showMenu_with_index_out_of_bound() {
        orderService.showMenu(5);

        String expected =
                "Data tidak ditemukan.";
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void addOrder() {
        orderService.addOrder(2,3);
        orderService.getDataOrder();
        String expected =
                "Nasi + Ayam     3  54000\r\n" +
                "Nasi Goreng     3  45000\r\n" +
                "--------------------------------+\r\n" +
                "Total           6  99000";
        assertEquals(expected, outputStream.toString().trim());
    }
    @Test
    void addOrder_with_index_out_of_bound() {
        orderService.addOrder(6,3);
        String expected =
                "Gagal menambahkan data.";
        assertEquals(expected, outputStream.toString().trim());
    }
    @Test
    void addOrder_with_null_value() {
        orderService.addOrder(null,null);
        String expected =
                "Gagal menambahkan data.";
        assertEquals(expected, outputStream.toString().trim());
    }
    @Test
    void getDataOrder() {
        orderService.getDataOrder();
        String expected =
                "Nasi Goreng     3  45000\r\n" +
                "--------------------------------+\r\n" +
                "Total           3  45000";
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void getDataOrder_with_no_data_order() {

        OrderService orderService1 = new OrderServiceImpl();
        orderService1.getDataOrder();
        String expected =
                "--------------------------------+\r\n" +
                "Total           0  0";
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void checkDataOrder() {
        assertTrue(orderService.checkDataOrder());
    }

    @Test
    void mapDataOrder() {
        orderService.addOrder(2,3);

        assertEquals(2, orderService.mapDataOrder().size());
    }
}
