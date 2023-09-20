package org.example.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinarFudControllerTest {

    private BinarFudController binarFudController;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp(){
        binarFudController = new BinarFudController();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void showMenuView() {
        binarFudController.showMenuView();
        String expected =
                "============================\r\n" +
                " Selamat datang di BinarFud\r\n" +
                "============================\r\n" +
                "\r\n" +
                "Silahkan pilih makanan :\r\n" +
                "1. Nasi Goreng    | 15000\r\n" +
                "2. Mie Goreng     | 13000\r\n" +
                "3. Nasi + Ayam    | 18000\r\n" +
                "4. Es Teh Manis   | 3000\r\n" +
                "5. Es Jeruk       | 5000\r\n" +
                "99. Pesan dan Bayar\r\n" +
                "0.  Keluar aplikasi";

        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void addorderMenuView() {
        binarFudController.addorderMenuView(1);

        String expected =
                "==========================\r\n" +
                "Berapa pesanan anda\r\n" +
                "==========================\r\n" +
                "Mie Goreng     | 13000\r\n" +
                "(input 0 untuk kembali)";

        assertEquals(expected, outputStream.toString().trim());
    }
    @Test
    void addorderMenuView_with_index_out_of_bound() {
        binarFudController.addorderMenuView(6);

        String expected =
                "==========================\r\n" +
                "Berapa pesanan anda\r\n" +
                "==========================\r\n" +
                "Data tidak ditemukan.\r\n" +
                "(input 0 untuk kembali)";

        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void addOrder() {
        binarFudController.addOrder(0, 3);
        binarFudController.dataOrderView();

        String expected =
                "================================\r\n" +
                "Konfirmasi dan Pembayaran\r\n" +
                "================================\r\n" +
                "\r\n" +
                "Nasi Goreng     3  45000\r\n" +
                "--------------------------------+\r\n" +
                "Total           3  45000\r\n" +
                "\r\n" +
                "1. Konfirmasi dan Bayar\r\n" +
                "2. Kembali ke menu utama\r\n" +
                "0. Keluar aplikasi";

        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void addOrder_with_index_out_of_bound() {
        binarFudController.addOrder(7, 3);

        String expected =
                "Gagal menambahkan data.";

        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void dataOrderView() {
        binarFudController.addOrder(0, 3);
        binarFudController.addOrder(2, 3);
        binarFudController.addOrder(1, 3);
        binarFudController.addOrder(4, 3);
        binarFudController.dataOrderView();

        String expected =
                "================================\r\n" +
                "Konfirmasi dan Pembayaran\r\n" +
                "================================\r\n" +
                "\r\n" +
                "Nasi + Ayam     3  54000\r\n" +
                "Es Jeruk        3  15000\r\n" +
                "Nasi Goreng     3  45000\r\n" +
                "Mie Goreng      3  39000\r\n" +
                "--------------------------------+\r\n" +
                "Total           12  153000\r\n" +
                "\r\n" +
                "1. Konfirmasi dan Bayar\r\n" +
                "2. Kembali ke menu utama\r\n" +
                "0. Keluar aplikasi";

        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void dataOrderView_if_no_data() {
        binarFudController.dataOrderView();

        String expected =
                "Silahkan order terlebih dahulu!";

        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void confirmAndPay_2() {
        binarFudController.addOrder(0,1);
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertDoesNotThrow(()-> binarFudController.confirmAndPay());
    }

    @Test
    void confirmAndPay_1() {
        binarFudController.addOrder(0,1);
        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertDoesNotThrow(()-> binarFudController.confirmAndPay());
    }

    @Test
    void confirmAndPay_0() {
        binarFudController.addOrder(0,1);
        String input = "0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertDoesNotThrow(()-> binarFudController.confirmAndPay());
    }

    @Test
    void confirmAndPay_with_no_data_order(){
        binarFudController.confirmAndPay();
        String expected =
                "Silahkan order terlebih dahulu!";

        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void confirmAndPay_with_wrong_data(){
        binarFudController.addOrder(0,1);
        String input = "6\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertDoesNotThrow(()-> binarFudController.confirmAndPay());
        String expected =
                "================================\r\n" +
                        "Konfirmasi dan Pembayaran\r\n" +
                        "================================\r\n" +
                        "\r\n" +
                        "Nasi Goreng     1  15000\r\n" +
                        "--------------------------------+\r\n" +
                        "Total           1  15000\r\n" +
                        "\r\n" +
                        "1. Konfirmasi dan Bayar\r\n" +
                        "2. Kembali ke menu utama\r\n" +
                        "0. Keluar aplikasi\r\n" +
                        "=> Pilihan tidak tersedia. Kembali ke menu utama";

        assertEquals(expected, outputStream.toString().trim());
    }
    @Test
    void confirmAndPay_with_data_non_integer(){
        binarFudController.addOrder(0,1);
        String input = "Y";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThrows(NoSuchElementException.class, () -> {
           binarFudController.confirmAndPay();
        });
    }
}