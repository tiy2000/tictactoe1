package com.github.tiy2000.tictactoe.game.board;

import com.github.tiy2000.tictactoe.game.exception.InvalidCellPositionException;

class CellPositionChecker {
    private final PlayerSymbol[][] board;

    CellPositionChecker(PlayerSymbol[][] board) {
        this.board = board;
    }

    void checkCellPositions(int row, int col) {
        checkPosition(row, "Row");
        checkPosition(col, "Column");
    }

    private void checkPosition(int position, String target) throws IllegalArgumentException {
        if (position < 1 || position > board.length) {
            throw new InvalidCellPositionException(makeErrorMsg(target, position));
        }
    }

    private String makeErrorMsg(String target, int givenValue) {
        return target + " should be a number between 1 and " + board.length + ". Given " + givenValue;
    }
}
