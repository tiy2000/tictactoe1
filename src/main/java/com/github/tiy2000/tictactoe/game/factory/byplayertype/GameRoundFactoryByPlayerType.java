package com.github.tiy2000.tictactoe.game.factory.byplayertype;

import com.github.tiy2000.tictactoe.game.Game;
import com.github.tiy2000.tictactoe.game.board.Board;
import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;
import com.github.tiy2000.tictactoe.game.factory.GameRoundFactory;
import com.github.tiy2000.tictactoe.game.gameround.GameRound;
import com.github.tiy2000.tictactoe.game.player.Player;
import com.github.tiy2000.tictactoe.ui.Console;

import java.util.Optional;

public class GameRoundFactoryByPlayerType implements GameRoundFactory {

    private final Console console;
    private final InteractivePlayerFactory playerFactory;

    public GameRoundFactoryByPlayerType(Console console) {
        this.console = console;
        this.playerFactory = new InteractivePlayerFactory(console);
    }

    @Override
    public Optional<Game> createGameRound() {
        Optional<Player> playerX = playerFactory.createPlayer(PlayerSymbol.X);
        if (playerX.isEmpty()) return Optional.empty();

        Optional<Player> playerO = playerFactory.createPlayer(PlayerSymbol.O);
        return playerO.map(player -> new GameRound(console, new Board(console), playerX.get(), player));

    }
}
