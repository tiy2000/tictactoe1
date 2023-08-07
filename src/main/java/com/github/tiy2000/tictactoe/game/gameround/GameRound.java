package com.github.tiy2000.tictactoe.game.gameround;

import com.github.tiy2000.tictactoe.game.Game;
import com.github.tiy2000.tictactoe.game.board.Board;
import com.github.tiy2000.tictactoe.game.player.Player;
import com.github.tiy2000.tictactoe.ui.Console;
import com.github.tiy2000.tictactoe.ui.ConsoleMenu;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameRound implements Game {

    private static final String DELIMITER_LINE = "-".repeat(40);

    private final Console console;
    private final Board board;
    private final Player playerX;
    private final Player playerO;

    @Override
    public void play() {
        do {
            console.println(DELIMITER_LINE);
            console.println("Game round started");

            playerX.init(board);
            playerO.init(board);

            board.reset();
            board.print();

            while (true) {
                if (playerMove(console, board, playerX)) break;
                if (playerMove(console, board, playerO)) break;
            }

            console.println("Game round finished");
            console.println(DELIMITER_LINE);

        } while (askRoundToBeContinued());
    }

    private boolean playerMove(Console console, Board board, Player player) {
        console.print(player.getDescription() + " move. Enter row and column, divided by space: ");
        player.move();
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

    private boolean askRoundToBeContinued() {
        ConsoleMenu menu = new ConsoleMenu(console);
        int choice = menu
                .withTitle("Do you want to continue?")
                .withItem("Continue the round")
                .withItem("Break the round")
                .readChoice();
        return choice == 1;
    }
}
