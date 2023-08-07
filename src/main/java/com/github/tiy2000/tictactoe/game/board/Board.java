package com.github.tiy2000.tictactoe.game.board;

import com.github.tiy2000.tictactoe.game.exception.InvalidCellPositionException;
import com.github.tiy2000.tictactoe.ui.Console;
import lombok.Getter;

import java.util.Arrays;

public class Board {

    public static final int MIN_SIZE = 3;
    public static final int MAX_SIZE = 5;
    public static final int DEFAULT_SIZE = MIN_SIZE;
    @Getter
    private final int size;
    private final PlayerSymbol[][] board;
    private final WinnerChecker winnerChecker;
    private final CellPositionChecker cellPositionChecker;

    private final Console console;

    public Board(Console console) {
        this(console, DEFAULT_SIZE);
    }

    public Board(Console console, int size) {
        if (size < MIN_SIZE || size > MAX_SIZE) {
            throw new IllegalArgumentException("Board size should be between " + MIN_SIZE + " and " + MAX_SIZE);
        }
        this.size = size;
        this.console = console;

        board = new PlayerSymbol[size][size];
        reset();

        winnerChecker = new WinnerChecker(board);
        cellPositionChecker = new CellPositionChecker(board);
    }

    public void reset() {
        for (PlayerSymbol[] playerSymbols : board) {
            Arrays.fill(playerSymbols, PlayerSymbol.BLANK);
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

    public boolean isBlank() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] != PlayerSymbol.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isRowBlank(int row) {
        return winnerChecker.isRowFilled(row - 1, PlayerSymbol.BLANK);
    }

    public boolean isColBlank(int col) {
        return winnerChecker.isColFilled(col - 1, PlayerSymbol.BLANK);
    }

    public boolean isCellBlank(Position position) {
        return isCellBlank(position.getRow(), position.getCol());
    }

    public boolean isCellBlank(int row, int col) {
        return getCellSymbol(row, col) == PlayerSymbol.BLANK;
    }

    public PlayerSymbol getCellSymbol(Position position) {
        return getCellSymbol(position.getRow(), position.getCol());
    }

    public PlayerSymbol getCellSymbol(int row, int col) {
        cellPositionChecker.checkCellPositions(row, col);
        return board[row - 1][col - 1];
    }

    public void setCellSymbol(Position position, PlayerSymbol symbol) {
        setCellSymbol(position.getRow(), position.getCol(), symbol);
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
}
