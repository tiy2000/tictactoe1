package com.github.tiy2000.tictactoe.game.board;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PlayerSymbol {

    X("X"),
    O("O"),
    BLANK("*");

    public final String value;
}
