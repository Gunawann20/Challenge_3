package org.example;


import org.example.controller.BinarFudController;
import org.example.util.InputUtil;

import java.io.IOException;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        BinarFudController binarFudController = new BinarFudController();

        while (true) {
            binarFudController.showMenuView();
            int pilihan;
            do {
                try {
                    System.out.print("=> ");
                    pilihan = InputUtil.inputUser();
                }catch (InputMismatchException e){
                    System.out.println("Input invalid. Silahkan masukan pilihan yang tersedia!");
                    pilihan = -999;
                }
            }while (pilihan == -999);

            switch (pilihan) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    binarFudController.addorderMenuView(pilihan - 1);
                    int inputQty;
                    do {
                        try {
                            System.out.print("QTY => ");
                            inputQty = InputUtil.inputUser();
                        }catch (InputMismatchException e) {
                            System.out.println("Input invalid. SIlahkan masukan jumlah pesanan dengan benar!");
                            inputQty = -999;
                        }
                    }while (inputQty == -999);
                    binarFudController.addOrder(pilihan - 1, inputQty);
                    break;
                case 0:
                    return;
                case 99:
                    binarFudController.confirmAndPay();
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia. Silahkan masukan pilihan yang tersedia!");
                    break;
            }
        }
    }
}