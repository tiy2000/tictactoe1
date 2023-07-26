package com.github.tiy2000.tictactoe.game.gameround;

import com.github.tiy2000.tictactoe.game.Game;
import com.github.tiy2000.tictactoe.game.board.Board;
import com.github.tiy2000.tictactoe.game.player.Player;
import com.github.tiy2000.tictactoe.ui.Console;

public class GameRound implements Game {

    private final Console console;
    private final Board board;
    private final Player playerX;
    private final Player playerO;

    public GameRound(Console console, Board board, Player playerX, Player playerO) {
        this.console = console;
        this.board = board;
        this.playerX = playerX;
        this.playerO = playerO;
    }

    @Override
    public void play() {
        board.reset();
        board.print();
        while (true) {
            if (playerMove(console, board, playerX)) break;
            if (playerMove(console, board, playerO)) break;
        }
    }

    private boolean playerMove(Console console, Board board, Player player) {
        console.print(player.getDescription() + " move. Enter row and column, divided by space: ");
        player.move(board);
        board.print();
        if (board.isWinner(player.getSymbol())) {
            console.println(player.getDescription() + " won!");
            return true;
        }
        if (board.isFilled()) {
            console.println("Dead heat!");
            return true;
        }
        return false;
    }
}
