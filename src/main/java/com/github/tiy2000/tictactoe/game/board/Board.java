package com.github.tiy2000.tictactoe.game.board;

import com.github.tiy2000.tictactoe.exception.InvalidCellPositionException;
import com.github.tiy2000.tictactoe.ui.Console;

public class Board {

    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 5;
    private static final int DEFAULT_SIZE = MIN_SIZE;
    private final int size;
    private final PlayerSymbol[][] board;
    private final WinnerChecker winnerChecker = new WinnerChecker();
    private final CellPositionChecker cellPositionChecker = new CellPositionChecker();
    private final Console console;

    public Board(Console console) {
        this(DEFAULT_SIZE, console);
    }

    public Board(int size, Console console) {
        if (size < MIN_SIZE || size > MAX_SIZE) {
            throw new IllegalArgumentException("Board size should be between " + MIN_SIZE + " and " + MAX_SIZE);
        }
        this.size = size;
        board = new PlayerSymbol[size][size];
        this.console = console;
        reset();
    }

    public int getSize() {
        return size;
    }

    public void reset() {
        for (PlayerSymbol[] playerSymbols : board) {
            for (int i = 0; i < size; i++) {
                playerSymbols[i] = PlayerSymbol.BLANK;
            }
        }
    }

    public boolean isFilled() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == PlayerSymbol.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }

    public PlayerSymbol getCellSymbol(int row, int col) {
        cellPositionChecker.checkCellPositions(row, col);
        return board[row - 1][col - 1];
    }

    public void setCellSymbol(int row, int col, PlayerSymbol symbol) {
        cellPositionChecker.checkCellPositions(row, col);
        if (board[row - 1][col - 1] != PlayerSymbol.BLANK) {
            throw new InvalidCellPositionException("Cell is busy");
        }
        board[row - 1][col - 1] = symbol;
    }

    public void print() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                console.print((board[row][col] == PlayerSymbol.BLANK ? "_" : board[row][col].value) + " ");
            }
            console.println();
        }
    }

    public boolean isWinner(PlayerSymbol symbol) {
        return winnerChecker.isWinner(symbol);
    }


    private class CellPositionChecker {
        public void checkCellPositions(int row, int col) {
            checkPosition(row, "Row");
            checkPosition(col, "Column");
        }

        private void checkPosition(int position, String target) throws IllegalArgumentException {
            if (position < 1 || position > size) {
                throw new InvalidCellPositionException(makeErrorMsg(target));
            }
        }

        private String makeErrorMsg(String target) {
            return target + " should be a number between 1 and " + size;
        }
    }


    private class WinnerChecker {
        private boolean isWinner(PlayerSymbol symbol) {
            for (int i = 0; i < size; i++) {
                if (isRowFilled(i, symbol) || isColFilled(i, symbol)) {
                    return true;
                }
            }
            return isLineFilled(0, 1, 0, 1, symbol) || isLineFilled(0, 1, size - 1, -1, symbol);
        }

        private boolean isRowFilled(int row, PlayerSymbol symbol) {
            return isLineFilled(row, 0, 0, 1, symbol);
        }

        private boolean isColFilled(int col, PlayerSymbol symbol) {
            return isLineFilled(0, 1, col, 0, symbol);
        }

        private boolean isLineFilled(int rowBegin, int rowStep, int colBegin, int colStep, PlayerSymbol symbol) {
            for (int i = 0, row = rowBegin, col = colBegin; i < size; i++, row += rowStep, col += colStep) {
                if (board[row][col] != symbol) {
                    return false;
                }
            }
            return true;
        }
    }
}
