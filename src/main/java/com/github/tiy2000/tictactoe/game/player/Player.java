package com.github.tiy2000.tictactoe.game.player;

import com.github.tiy2000.tictactoe.game.board.Board;
import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;

public interface Player {

    void move(Board board);

    String getName();

    PlayerSymbol getSymbol();

    default String getDescription() {
        return "Player " + getName() + " [" + getSymbol().value + "]";
    }
}
