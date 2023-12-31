package com.github.tiy2000.tictactoe.game.player.human;

import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;
import com.github.tiy2000.tictactoe.game.exception.InvalidCellPositionException;
import com.github.tiy2000.tictactoe.game.player.AbstractPlayer;
import com.github.tiy2000.tictactoe.ui.Console;

public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(PlayerSymbol symbol, Console console) {
        this("Human", symbol, console);
    }

    public HumanPlayer(String name, PlayerSymbol symbol, Console console) {
        super(name, symbol, console);
    }

    @Override
    public void move() {
        while (true) {
            int row = console.readInt();
            int col = console.readInt();
            try {
                board.setCellSymbol(row, col, symbol);
                return;
            } catch (InvalidCellPositionException e) {
                console.print(e.getMessage() + ", try again: ");
            }
        }
    }
}
