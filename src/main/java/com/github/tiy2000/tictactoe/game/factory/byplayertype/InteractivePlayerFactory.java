package com.github.tiy2000.tictactoe.game.factory.byplayertype;

import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;
import com.github.tiy2000.tictactoe.game.player.Player;
import com.github.tiy2000.tictactoe.game.player.computer.random.ComputerRandomPlayer;
import com.github.tiy2000.tictactoe.game.player.human.HumanPlayer;
import com.github.tiy2000.tictactoe.ui.Console;
import com.github.tiy2000.tictactoe.ui.ConsoleMenu;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class InteractivePlayerFactory {

    private final Console console;

    public Optional<Player> createPlayer(PlayerSymbol symbol) {
        ConsoleMenu menu = new ConsoleMenu(console)
                .withTitle("Select player type")
                .withPrompt("Select player [" + symbol.value + "]: ")
                .withEnum(PlayerType.class)
                .withExitItem();
        int choice = menu.readChoice();
        if (choice == menu.getItemsCount()) {
            return Optional.empty();
        }
        PlayerType playerType = PlayerType.values()[choice - 1];
        String playerName = inputPlayerName(playerType);
        return Optional.of(makePlayer(playerType, playerName, symbol));
    }

    private String inputPlayerName(PlayerType playerType) {
        return console.readString("Enter player's name", playerType.description);
    }

    private Player makePlayer(PlayerType playerType, String playerName, PlayerSymbol symbol) {
        return switch (playerType) {
            case HUMAN -> new HumanPlayer(playerName, symbol, console);
            case COMPUTER_RANDOM -> new ComputerRandomPlayer(playerName, symbol, console);
        };
    }
}
