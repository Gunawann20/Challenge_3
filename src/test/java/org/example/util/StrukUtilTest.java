package org.example.util;

import org.example.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StrukUtilTest {

    List<Order> dataOrder = new ArrayList<>();

    @BeforeEach
    void setUp() {
        dataOrder.add(new org.example.entity.Order("Nasi Goreng  ", 1, 15000));
    }
    @Test
    void printStruk() {
        try {
            StrukUtil.printStruk(dataOrder);
        }catch (IOException e) {
            fail();
        }
    }

    @Test
    void getFilename() throws InterruptedException {
        String filename1 = StrukUtil.getFilename();
        Thread.sleep(1000);
        String filename2 = StrukUtil.getFilename();
        assertDoesNotThrow(() -> {
            StrukUtil.getFilename();
        });
        assertNotNull(StrukUtil.getFilename());
        assertNotEquals(filename1, filename2);
    }
}