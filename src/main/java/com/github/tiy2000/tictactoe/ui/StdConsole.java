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
        try {
            return Integer.parseInt(scanner.next());
        } catch (Exception e) {
            return INVALID_NUMBER_SPECIAL_VALUE;
        }
    }

    @Override
    public String readString() {
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                return line;
            }
        }
        return scanner.nextLine();
    }
}
