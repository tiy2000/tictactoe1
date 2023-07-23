package com.github.tiy2000.tictactoe;

import com.github.tiy2000.tictactoe.game.GameLogic;
import com.github.tiy2000.tictactoe.game.board.Board;
import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;
import com.github.tiy2000.tictactoe.game.player.ComputerRandomPlayer;
import com.github.tiy2000.tictactoe.game.player.Player;
import com.github.tiy2000.tictactoe.game.player.UserPlayer;
import com.github.tiy2000.tictactoe.ui.Console;
import com.github.tiy2000.tictactoe.ui.StdConsole;

public class AppRunner {

    public static void main(String[] args) {
        Console console = new StdConsole();
        Board board = new Board(console);

        Player player1 = new UserPlayer("John", PlayerSymbol.X, console);
        Player player2 = new ComputerRandomPlayer(PlayerSymbol.O, console);

        GameLogic gameLogic = new GameLogic(console, board, player1, player2);

        gameLogic.play();
    }
}
