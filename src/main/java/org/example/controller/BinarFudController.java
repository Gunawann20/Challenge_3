package org.example.controller;

import org.example.service.OrderService;
import org.example.service.OrderServiceImpl;
import org.example.util.InputUtil;
import org.example.util.StrukUtil;

import java.io.IOException;
import java.util.InputMismatchException;

public class BinarFudController {

    private final OrderService orderService = new OrderServiceImpl();

    /**
     * Method untuk menampilkan daftar menu yang tersedia
     */
    public void showMenuView() {
        System.out.println("============================");
        System.out.println(" Selamat datang di BinarFud");
        System.out.println("============================");
        System.out.println();
        System.out.println("Silahkan pilih makanan :");
        orderService.showAllMenu();
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0.  Keluar aplikasi");
    }

    /**
     * Method untuk menanpilkan menu yang telah dipilih
     * @param index menu
     */
    public void addorderMenuView(Integer index) {
        System.out.println("==========================");
        System.out.println("Berapa pesanan anda");
        System.out.println("==========================");
        orderService.showMenu(index);
        System.out.println("(input 0 untuk kembali)");
    }

    /**
     * Method untuk menambahkan order menu
     * @param index menu
     * @param quantity menu yang diorder
     */
    public void addOrder(Integer index, Integer quantity) {
        if (quantity > 0) {
            orderService.addOrder(index, quantity);
        }
    }

    /**
     * Menampilkan data menu yang telah diorder, jika data order kosong akan menampilkan Silahkan order terlebih dahulu!
     */
    public void dataOrderView() {
        if (orderService.checkDataOrder()){
            System.out.println("================================");
            System.out.println("Konfirmasi dan Pembayaran");
            System.out.println("================================");
            System.out.println();
            orderService.getDataOrder();
            System.out.println();
            System.out.println("1. Konfirmasi dan Bayar");
            System.out.println("2. Kembali ke menu utama");
            System.out.println("0. Keluar aplikasi");
        }else {
            System.out.println("Silahkan order terlebih dahulu!");
        }
    }

    public void confirmAndPay() {
        dataOrderView();
        if (orderService.checkDataOrder()){
            int inputUser;
            do {
                try {
                    System.out.print("=> ");
                    inputUser = InputUtil.inputUser();
                }catch (InputMismatchException e) {
                    System.out.println("Pilihan tidak tersedia.");
                    inputUser = -999;
                }
            }while (inputUser == -999);
            switch (inputUser){
                case 1:
                    try {
                        StrukUtil.printStruk(orderService.mapDataOrder());
                    } catch (IOException e) {
                        System.out.println("Terjadi kesalahan. Gagal cetak struk pembayaran");
                    }
                    System.exit(0);
                    break;
                case 2:
                    System.out.println("Kembali ke menu utama..");
                    break;
                case 0:
                    System.out.println("Keluar aplikasi..");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia. Kembali ke menu utama");
                    break;
            }
        }
    }

}
