package com.github.tiy2000.tictactoe.game.board;

public class InvalidCellPositionException extends RuntimeException {

    public InvalidCellPositionException(String message) {
        super(message);
    }
}
