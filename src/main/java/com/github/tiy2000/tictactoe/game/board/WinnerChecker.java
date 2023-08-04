package com.github.tiy2000.tictactoe.game.board;

class WinnerChecker {
    private final PlayerSymbol[][] board;

    public WinnerChecker(PlayerSymbol[][] board) {
        this.board = board;
    }

    public boolean isWinner(PlayerSymbol symbol) {
        for (int i = 0; i < board.length; i++) {
            if (isRowFilled(i, symbol) || isColFilled(i, symbol)) {
                return true;
            }
        }
        return isLineFilled(0, 1, 0, 1, symbol) || isLineFilled(0, 1, board.length - 1, -1, symbol);
    }

    private boolean isRowFilled(int row, PlayerSymbol symbol) {
        return isLineFilled(row, 0, 0, 1, symbol);
    }

    private boolean isColFilled(int col, PlayerSymbol symbol) {
        return isLineFilled(0, 1, col, 0, symbol);
    }

    private boolean isLineFilled(int rowBegin, int rowStep, int colBegin, int colStep, PlayerSymbol symbol) {
        for (int i = 0, row = rowBegin, col = colBegin; i < board.length; i++, row += rowStep, col += colStep) {
            if (board[row][col] != symbol) {
                return false;
            }
        }
        return true;
    }
}
