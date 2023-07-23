package com.github.tiy2000.tictactoe.game.board;

public enum PlayerSymbol {

    X("X"),
    O("O"),
    BLANK("*");

    public final String value;

    PlayerSymbol(String value) {
        this.value = value;
    }
}
