package com.github.tiy2000.tictactoe;

import com.github.tiy2000.tictactoe.game.Game;
import com.github.tiy2000.tictactoe.game.gameset.GameSet;
import com.github.tiy2000.tictactoe.ui.StdConsole;

public class AppRunner {

    public static void main(String[] args) {
        Game game = new GameSet(new StdConsole());
        game.play();
    }
}
