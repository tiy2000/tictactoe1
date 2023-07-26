package com.github.tiy2000.tictactoe.game.gameset;

import com.github.tiy2000.tictactoe.game.Game;
import com.github.tiy2000.tictactoe.game.board.Board;
import com.github.tiy2000.tictactoe.ui.Console;

import java.util.Arrays;

public class GameSet implements Game {

    private static final int EXIT_MENU_NUMBER = GameType.values().length + 1;
    private final Console console;
    private final GameRoundFactory gameRoundFactory;

    public GameSet(Console console) {
        this.console = console;
        this.gameRoundFactory = new GameRoundFactory(console, new Board(console));
    }

    @Override
    public void play() {
        console.println("Tic-Tac-Toe game");
        while (true) {
            printMenu();
            int choice = inputUserChoice();

            if (choice == EXIT_MENU_NUMBER) break;

            Game gameRound = gameRoundFactory.makeGameRound(GameType.values()[choice - 1]);
            gameRound.play();
        }
        console.println("By");
    }

    private void printMenu() {
        console.println("Select game round type or exit the game:");
        Arrays.stream(GameType.values())
                .forEach(gameType -> console.println(gameType.ordinal() + 1 + ". " + gameType.description));
        console.println(EXIT_MENU_NUMBER + ". Exit");
    }

    private int inputUserChoice() {
        int choice;
        while (true) {
            console.print("Make your choice: ");
            choice = console.readInt();
            if (choice < 1 || choice > EXIT_MENU_NUMBER) {
                console.println("Invalid input! Type a number between 1 and " + EXIT_MENU_NUMBER);
            } else {
                break;
            }
        }
        return choice;
    }
}
