package com.github.tiy2000.tictactoe.game.player.computer.random;

import com.github.tiy2000.tictactoe.game.board.Board;
import com.github.tiy2000.tictactoe.game.exception.InvalidCellPositionException;
import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;
import com.github.tiy2000.tictactoe.game.player.AbstractPlayer;
import com.github.tiy2000.tictactoe.ui.Console;

import java.util.Random;

public class ComputerRandomPlayer extends AbstractPlayer {

    private final Random random = new Random();

    public ComputerRandomPlayer(PlayerSymbol symbol, Console console) {
        this("Computer", symbol, console);
    }

    public ComputerRandomPlayer(String name, PlayerSymbol symbol, Console console) {
        super(name, symbol, console);
    }

    @Override
    public void move(Board board) {
        while (true) {
            int row = random.nextInt(board.getSize()) + 1;
            int col = random.nextInt(board.getSize()) + 1;
            try {
                board.setCellSymbol(row, col, symbol);
                console.println(row + " " + col);
                return;
            } catch (InvalidCellPositionException e) {
                // Just repeat the loop
            }
        }
    }
}
