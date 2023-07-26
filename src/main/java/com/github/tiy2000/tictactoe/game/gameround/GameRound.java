package com.github.tiy2000.tictactoe.game.gameround;

import com.github.tiy2000.tictactoe.game.Game;
import com.github.tiy2000.tictactoe.game.board.Board;
import com.github.tiy2000.tictactoe.game.player.Player;
import com.github.tiy2000.tictactoe.ui.Console;

public class GameRound implements Game {

    private final Console console;
    private final Board board;
    private final Player player1;
    private final Player player2;

    public GameRound(Console console, Board board, Player player1, Player player2) {
        this.console = console;
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void play() {
        console.println("Game is started");
        board.reset();
        board.print();
        while (true) {
            if (playerMove(console, board, player1)) break;
            if (playerMove(console, board, player2)) break;
        }
        console.println("Game is finished");
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
