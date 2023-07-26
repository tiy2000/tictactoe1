package com.github.tiy2000.tictactoe;

import com.github.tiy2000.tictactoe.game.GameRound;
import com.github.tiy2000.tictactoe.game.board.Board;
import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;
import com.github.tiy2000.tictactoe.game.player.computer.random.ComputerRandomPlayer;
import com.github.tiy2000.tictactoe.game.player.Player;
import com.github.tiy2000.tictactoe.game.player.human.HumanPlayer;
import com.github.tiy2000.tictactoe.ui.Console;
import com.github.tiy2000.tictactoe.ui.StdConsole;

public class AppRunner {

    public static void main(String[] args) {
        Console console = new StdConsole();
        Board board = new Board(console);

        Player player1 = new HumanPlayer("John", PlayerSymbol.X, console);
        Player player2 = new ComputerRandomPlayer(PlayerSymbol.O, console);

        GameRound gameRound = new GameRound(console, board, player1, player2);

        gameRound.play();
    }
}