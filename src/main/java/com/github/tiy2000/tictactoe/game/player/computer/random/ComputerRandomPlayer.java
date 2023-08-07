package com.github.tiy2000.tictactoe.game.player.computer.random;

import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;
import com.github.tiy2000.tictactoe.game.board.Position;
import com.github.tiy2000.tictactoe.game.player.computer.AbstractComputerPlayer;
import com.github.tiy2000.tictactoe.ui.Console;

import java.util.Random;

public class ComputerRandomPlayer extends AbstractComputerPlayer {

//    private final Random random = new Random();

    public ComputerRandomPlayer(PlayerSymbol symbol, Console console) {
        this("Computer", symbol, console);
    }

    public ComputerRandomPlayer(String name, PlayerSymbol symbol, Console console) {
        super(name, symbol, console);
    }

    @Override
    protected Position getPosition() {
        return createRandomPosition();
//        while (true) {
//            int row = random.nextInt(board.getSize()) + 1;
//            int col = random.nextInt(board.getSize()) + 1;
//            if (board.isCellBlank(row, col)) {
//                return new Position(row, col);
//            }
//        }
    }
}
