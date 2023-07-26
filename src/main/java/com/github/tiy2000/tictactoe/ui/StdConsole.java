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

    @Override
    public int readInt(int min, int max) {
        while (true) {
            int number = readInt();
            if (number < min || number > max) {
                print("Invalid input! Type a number between " + min + " and " + max + ": ");
            } else {
                return number;
            }
        }
    }
}
