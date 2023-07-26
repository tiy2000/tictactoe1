package com.github.tiy2000.tictactoe.ui;

public interface Console {

    void print(String text);

    default void println(String text) {
        print(text + "\n");
    }

    default void println() {
        print("\n");
    }

    int readInt();

    int readInt(int min, int max);

}
