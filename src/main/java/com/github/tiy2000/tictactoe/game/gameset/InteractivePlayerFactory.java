package com.github.tiy2000.tictactoe.game.gameset;

import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;
import com.github.tiy2000.tictactoe.game.player.Player;
import com.github.tiy2000.tictactoe.game.player.computer.random.ComputerRandomPlayer;
import com.github.tiy2000.tictactoe.game.player.human.HumanPlayer;
import com.github.tiy2000.tictactoe.ui.Console;

import java.util.Arrays;

public class InteractivePlayerFactory {

    private final Console console;

    public InteractivePlayerFactory(Console console) {
        this.console = console;
    }

    public Player makePlayerInteractive(PlayerSymbol symbol) {
        printPlayerTypeMenu();
        PlayerType playerType = inputPlayerType(symbol);
        String playerName = inputPlayerName(playerType);

        return makePlayer(playerType, playerName, symbol);
    }

    private void printPlayerTypeMenu() {
        Arrays.stream(PlayerType.values())
                .forEach(pt -> console.println(pt.ordinal() + 1 + ". " + pt.description));
    }

    private PlayerType inputPlayerType(PlayerSymbol symbol) {
        console.print("Select player [" + symbol.value + "] type: ");
        int playerTypeNumber = console.readInt(1, PlayerType.values().length);
        return PlayerType.values()[playerTypeNumber - 1];
    }

    private String inputPlayerName(PlayerType playerType) {

        console.readString();   // This is a BIG CRUTCH!!! Very BAD!!!

        String playerName = console.readString("Enter player's name", playerType.description);
        System.out.println("[" + playerName + "]");
        return playerName;
    }

    private Player makePlayer(PlayerType playerType, String playerName, PlayerSymbol symbol) {
        return switch (playerType) {
            case HUMAN -> new HumanPlayer(playerName, symbol, console);
            case COMPUTER_RANDOM -> new ComputerRandomPlayer(playerName, symbol, console);
        };
    }
}
