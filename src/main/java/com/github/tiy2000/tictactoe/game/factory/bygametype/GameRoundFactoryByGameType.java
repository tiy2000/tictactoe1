package com.github.tiy2000.tictactoe.game.factory.bygametype;

import com.github.tiy2000.tictactoe.game.Game;
import com.github.tiy2000.tictactoe.game.board.Board;
import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;
import com.github.tiy2000.tictactoe.game.factory.GameRoundFactory;
import com.github.tiy2000.tictactoe.game.gameround.GameRound;
import com.github.tiy2000.tictactoe.game.player.Player;
import com.github.tiy2000.tictactoe.game.player.computer.random.ComputerRandomPlayer;
import com.github.tiy2000.tictactoe.game.player.human.HumanPlayer;
import com.github.tiy2000.tictactoe.ui.Console;
import com.github.tiy2000.tictactoe.ui.ConsoleMenu;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GameRoundFactoryByGameType implements GameRoundFactory {

    private final Console console;

    @Override
    public Optional<Game> createGameRound() {
        ConsoleMenu menu = new ConsoleMenu(console)
                .withTitle("Select game type")
                .withEnum(GameType.class)
                .withExitItem();
        int choice = menu.readChoice();

        if (choice == menu.getItemsCount()) {
            return Optional.empty();
        }

        Player playerX = null;
        Player playerO = null;
        switch (GameType.values()[choice - 1]) {
            case HUMAN_VS_HUMAN -> {
                playerX = new HumanPlayer("Human X", PlayerSymbol.X, console);
                playerO = new HumanPlayer("Human O", PlayerSymbol.O, console);
            }
            case COMPUTER_VS_HUMAN -> {
                playerX = new ComputerRandomPlayer(PlayerSymbol.X, console);
                playerO = new HumanPlayer(PlayerSymbol.O, console);
            }
            case HUMAN_VS_COMPUTER -> {
                playerX = new HumanPlayer(PlayerSymbol.X, console);
                playerO = new ComputerRandomPlayer(PlayerSymbol.O, console);
            }
            case COMPUTER_VS_COMPUTER -> {
                playerX = new ComputerRandomPlayer(PlayerSymbol.X, console);
                playerO = new ComputerRandomPlayer(PlayerSymbol.O, console);
            }
        }
        return Optional.of(new GameRound(console, new Board(console), playerX, playerO));
    }
}
