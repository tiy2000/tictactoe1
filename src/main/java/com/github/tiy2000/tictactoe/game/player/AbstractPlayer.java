package com.github.tiy2000.tictactoe.game.player;

import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;
import com.github.tiy2000.tictactoe.ui.Console;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractPlayer implements Player {

    @Getter
    protected final String name;

    @Getter
    protected final PlayerSymbol symbol;

    protected final Console console;
}
