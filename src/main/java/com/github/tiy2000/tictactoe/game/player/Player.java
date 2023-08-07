package com.github.tiy2000.tictactoe.game.player;

import com.github.tiy2000.tictactoe.game.board.Board;
import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;

public interface Player {

    default void init(Board board) {
    }

    void move();

    String getName();

    PlayerSymbol getSymbol();

    default String getDescription() {
        return "Player [" + getSymbol().value + "] (" + getName() + ")";
    }
}
