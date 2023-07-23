package com.github.tiy2000.tictactoe.game.player;

import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;
import com.github.tiy2000.tictactoe.ui.Console;

public abstract class AbstractPlayer implements Player {

    protected final String name;
    protected final PlayerSymbol symbol;
    protected final Console console;

    public AbstractPlayer(String name, PlayerSymbol symbol, Console console) {
        this.name = name;
        this.symbol = symbol;
        this.console = console;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PlayerSymbol getSymbol() {
        return symbol;
    }
}
