package org.example.util;

import org.example.entity.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class StrukUtil {

    public static void printStruk(List<Order> dataOrder) throws IOException {
        File file = new File(getFilename());
        if (file.createNewFile()) {
            System.out.println("Mencetak struk..");
            System.out.println("Struk berhasil dicetak.");
        }

        try(FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bwr = new BufferedWriter(fileWriter)) {

            bwr.write("===========================================\n");
            bwr.write("BinarFud\n");
            bwr.write("===========================================\n\n");
            bwr.write("Terima kasih sudah memesan di BinarFud\n\n");
            bwr.write("Dibawah ini adalah pesanan anda\n\n");

            Stream<Order> orderStream = dataOrder.stream();
            orderStream.filter(Objects::nonNull)
                    .filter(o -> o.getMenuName() != null || o.getQuantity() != null || o.getPrice() != null)
                    .filter(o -> o.getQuantity() != null && o.getQuantity() > 0)
                    .forEach(o -> {
                        try {
                            bwr.write(o.getMenuName()+"   "+o.getQuantity()+"  "+(o.getQuantity()*o.getPrice())+"\n");
                        } catch (IOException e) {
                            System.out.println();
                        }
                    });
            Integer sumQty = dataOrder.stream()
                    .filter(Objects::nonNull)
                    .filter(o -> o.getMenuName() != null || o.getQuantity() != null || o.getPrice() != null)
                    .filter(o -> o.getQuantity() != null && o.getQuantity() > 0)
                    .reduce(0, (v,o) -> v + o.getQuantity(), Integer::sum);
            Integer sumPrice = dataOrder.stream()
                    .filter(Objects::nonNull)
                    .filter(o -> o.getMenuName() != null || o.getQuantity() != null || o.getPrice() != null)
                    .filter(o -> o.getQuantity() != null && o.getQuantity() > 0)
                    .reduce(0, (v,o)-> v + (o.getQuantity() * o.getPrice()), Integer::sum);
            bwr.write("----------------------------------------+\n");
            bwr.write("Total"+"           "+sumQty+"  "+sumPrice+"\n\n\n");
            bwr.write("Pembayaran: BinarCash\n\n");
            bwr.write("===========================================\n");
            bwr.write("Simpan struk ini sebagai bukti pembayaran\n");
            bwr.write("===========================================");
            bwr.flush();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan. gagal mencetak struk.");
        }
    }

    public static String getFilename() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatter);

        return "/home/"+formattedDateTime + " - Struk pembayaran.txt";
    }
}
