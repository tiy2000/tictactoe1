package com.github.tiy2000.tictactoe.game.gameset;

import com.github.tiy2000.tictactoe.game.board.Board;
import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;
import com.github.tiy2000.tictactoe.game.gameround.GameRound;
import com.github.tiy2000.tictactoe.game.player.Player;
import com.github.tiy2000.tictactoe.game.player.computer.random.ComputerRandomPlayer;
import com.github.tiy2000.tictactoe.game.player.human.HumanPlayer;
import com.github.tiy2000.tictactoe.ui.Console;

public class GameRoundFactory {

    private final Console console;
    private final Board board;

    public GameRoundFactory(Console console, Board board) {
        this.console = console;
        this.board = board;
    }

    public GameRound makeGameRound(GameType gameType) {
        Player playerX = null;
        Player playerO = null;
        switch (gameType) {
            case HUMAN_VS_HUMAN -> {
                // TODO: Enter player's name from console
                playerX = new HumanPlayer("Human 1", PlayerSymbol.X, console);
                playerO = new HumanPlayer("Human 2", PlayerSymbol.O, console);
            }
            case HUMAN_VS_COMPUTER -> {
                playerX = new HumanPlayer("Human", PlayerSymbol.X, console);
                playerO = new ComputerRandomPlayer(PlayerSymbol.O, console);
            }
            case COMPUTER_VS_HUMAN -> {
                playerX = new ComputerRandomPlayer(PlayerSymbol.X, console);
                playerO = new HumanPlayer("Human", PlayerSymbol.O, console);
            }
            case COMPUTER_VS_COMPUTER -> {
                playerX = new ComputerRandomPlayer("Computer 1", PlayerSymbol.X, console);
                playerO = new ComputerRandomPlayer("Computer 2", PlayerSymbol.O, console);
            }
        }
        return new GameRound(console, board, playerX, playerO);
    }
}
