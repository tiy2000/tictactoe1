package com.github.tiy2000.tictactoe.game.gameset;

import com.github.tiy2000.tictactoe.game.Game;
import com.github.tiy2000.tictactoe.game.factory.bygametype.GameRoundFactoryByGameType;
import com.github.tiy2000.tictactoe.game.factory.byplayertype.GameRoundFactoryByPlayerType;
import com.github.tiy2000.tictactoe.ui.Console;
import com.github.tiy2000.tictactoe.ui.ConsoleMenu;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GameSet implements Game {

    private final Console console;

    @Override
    public void play() {
        console.println("Tic-Tac-Toe game");

        ConsoleMenu menu = new ConsoleMenu(console);
        menu.withTitle("Select game set type")
                .withEnum(GameSetType.class)
                .withExitItem();

        while (true) {
            int choice = menu.readChoice();
            if (choice == menu.getItemsCount()) {
                break;
            }
            Optional<Game> maybeGame = switch (GameSetType.values()[choice - 1]) {
                case GAME_SET_WITH_GAME_TYPE -> new GameRoundFactoryByGameType(console).createGameRound();
                case GAME_SET_WITH_PLAYER_TYPE -> new GameRoundFactoryByPlayerType(console).createGameRound();

            };
            maybeGame.ifPresent(Game::play);
        }

        console.println("By");
    }
}
