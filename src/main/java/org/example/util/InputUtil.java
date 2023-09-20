package org.example.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {

    public static Integer inputUser() throws InputMismatchException{
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
