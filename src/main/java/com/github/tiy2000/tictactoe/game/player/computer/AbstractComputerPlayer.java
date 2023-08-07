package com.github.tiy2000.tictactoe.game.player.computer;

import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;
import com.github.tiy2000.tictactoe.game.board.Position;
import com.github.tiy2000.tictactoe.game.player.AbstractPlayer;
import com.github.tiy2000.tictactoe.ui.Console;

import java.util.Random;

public abstract class AbstractComputerPlayer extends AbstractPlayer {

    protected final Random random = new Random();

    public AbstractComputerPlayer(String name, PlayerSymbol symbol, Console console) {
        super(name, symbol, console);
    }

    @Override
    public void move() {
        Position position = getPosition();
        board.setCellSymbol(position, symbol);
        console.println(position.getRow() + " " + position.getCol());
    }

    protected abstract Position getPosition();


    protected Position createCenterPosition() {
        int pos = (board.getSize() / 2) + 1;
        return new Position(pos, pos);
    }

    protected Position createRandomPosition() {
        Position position;
        do {
            int row = random.nextInt(board.getSize()) + 1;
            int col = random.nextInt(board.getSize()) + 1;
            position = new Position(row, col);
        } while (!board.isCellBlank(position));
        return position;
    }

    protected PlayerSymbol getRivalSymbol() {
        return getRivalSymbol(symbol);
    }

    protected PlayerSymbol getRivalSymbol(PlayerSymbol symbol) {
        return symbol == PlayerSymbol.X ? PlayerSymbol.O : PlayerSymbol.X;
    }
}
