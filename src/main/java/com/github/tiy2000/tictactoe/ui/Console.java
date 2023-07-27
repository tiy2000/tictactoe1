package com.github.tiy2000.tictactoe.ui;

public interface Console {

    int INVALID_NUMBER_SPECIAL_VALUE = -1;

    void print(String text);

    default void println(String text) {
        print(text + "\n");
    }

    default void println() {
        print("\n");
    }

    int readInt();

    default int readInt(int min, int max) {
        while (true) {
            int number = readInt();
            if (number < min || number > max) {
                print("Invalid input! Type a number between " + min + " and " + max + ": ");
            } else {
                return number;
            }
        }
    }

    String readString();

    default String readString(String prompt) {
        print(prompt);
        if (!prompt.endsWith(": ")) print(": ");
        return readString();
    }

    default String readString(String prompt, String defaultString) {
        String readString = readString(prompt + " [" + defaultString + "]: ");
        return readString.isEmpty() ? defaultString : readString;
    }
}
