package org.example.util;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;
import static org.junit.jupiter.api.Assertions.*;

class InputUtilTest {

    @Test
    void inputUser() {
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        try {
            Integer inputUser = InputUtil.inputUser();
            assertEquals(2, inputUser);
        }catch (InputMismatchException e) {
            fail();
        }
    }
    @Test
    void inputUser_with_wrong_data() {
        String input = "Y\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThrows(InputMismatchException.class, InputUtil::inputUser);
    }
}