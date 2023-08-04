package com.github.tiy2000.tictactoe.game.board;

import com.github.tiy2000.tictactoe.game.exception.InvalidCellPositionException;

class CellPositionChecker {
    private final PlayerSymbol[][] board;

    public CellPositionChecker(PlayerSymbol[][] board) {
        this.board = board;
    }

    public void checkCellPositions(int row, int col) {
        checkPosition(row, "Row");
        checkPosition(col, "Column");
    }

    private void checkPosition(int position, String target) throws IllegalArgumentException {
        if (position < 1 || position > board.length) {
            throw new InvalidCellPositionException(makeErrorMsg(target));
        }
    }

    private String makeErrorMsg(String target) {
        return target + " should be a number between 1 and " + board.length;
    }
}
