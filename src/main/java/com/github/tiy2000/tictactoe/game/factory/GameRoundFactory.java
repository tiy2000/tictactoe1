package com.github.tiy2000.tictactoe.game.factory;

import com.github.tiy2000.tictactoe.game.Game;

import java.util.Optional;

public interface GameRoundFactory {

    Optional<Game> createGameRound();
}
