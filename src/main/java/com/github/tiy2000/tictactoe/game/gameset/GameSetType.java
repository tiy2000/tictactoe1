package com.github.tiy2000.tictactoe.game.gameset;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GameSetType {

    GAME_SET_WITH_GAME_TYPE("Set with game type - factory v1"),
    GAME_SET_WITH_PLAYER_TYPE("Set with player type - factory v2");

    public final String description;

    @Override
    public String toString() {
        return description;
    }
}
