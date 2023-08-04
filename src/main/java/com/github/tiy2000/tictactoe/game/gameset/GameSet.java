package com.github.tiy2000.tictactoe.game.gameset;

import com.github.tiy2000.tictactoe.game.Game;
import com.github.tiy2000.tictactoe.game.board.Board;
import com.github.tiy2000.tictactoe.game.factory.bygametype.GameRoundFactoryByGameType;
import com.github.tiy2000.tictactoe.game.factory.byplayertype.GameRoundFactoryByPlayerType;
import com.github.tiy2000.tictactoe.ui.Console;
import com.github.tiy2000.tictactoe.ui.ConsoleMenu;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GameSet implements Game {

    private static final String DELIMITER_LINE = "-".repeat(40);

    private final Console console;

    @Override
    public void play() {
        Board board = new Board(console);
        console.println("Tic-Tac-Toe game");

        ConsoleMenu menu = new ConsoleMenu(console);
        menu.withTitle("Select game set type")
                .withEnum(GameSetType.class)
                .withItem(makeChangeGameBoardSizeMenuItem(board))
                .withExitItem();

        while (true) {
            int choice = menu.readChoice();
            if (choice == menu.getItemsCount()) {
                break;
            }
            if (choice == menu.getItemsCount() - 1) {
                board = createBoardInteractive();
                menu.updateItem(menu.getItemsCount() - 2, makeChangeGameBoardSizeMenuItem(board));
                continue;
            }
            Optional<Game> maybeGame = switch (GameSetType.values()[choice - 1]) {
                case GAME_SET_WITH_GAME_TYPE -> new GameRoundFactoryByGameType(console, board).createGameRound();
                case GAME_SET_WITH_PLAYER_TYPE -> new GameRoundFactoryByPlayerType(console, board).createGameRound();

            };
            maybeGame.ifPresent(this::playTheGame);
        }

        console.println("By");
    }

    private static String makeChangeGameBoardSizeMenuItem(Board board) {
        return "Change game board size (current size = " + board.getSize() + ")";
    }

    private Board createBoardInteractive() {
        console.print("Enter game border size [" + Board.MIN_SIZE + "-" + Board.MAX_SIZE + "]: ");
        int boardSize = console.readInt(Board.MIN_SIZE, Board.MAX_SIZE);
        return new Board(boardSize, console);
    }

    private void playTheGame(Game game) {
        console.println(DELIMITER_LINE);
        console.println("Game round started");
        game.play();
        console.println("Game round finished");
        console.println(DELIMITER_LINE);
    }
}
