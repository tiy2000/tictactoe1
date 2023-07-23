package com.github.tiy2000.tictactoe.ui;

import java.util.Scanner;

public class StdConsole implements Console {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String text) {
        System.out.print(text);
    }

    @Override
    public int readInt() {
        int value;
        try {
            value = Integer.parseInt(scanner.next());
        } catch (Exception e) {
            value = -1;
        }
        return value;
    }
}
