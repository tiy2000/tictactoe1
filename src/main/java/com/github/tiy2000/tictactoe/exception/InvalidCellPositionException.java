package com.github.tiy2000.tictactoe.exception;

public class InvalidCellPositionException extends RuntimeException {

    public InvalidCellPositionException(String message) {
        super(message);
    }
}
