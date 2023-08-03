package com.github.tiy2000.tictactoe.game.gameset;

public enum GameSetType {

    GAME_SET_WITH_GAME_TYPE("Set with game type"),
    GAME_SET_WITH_PLAYER_TYPE("Set with player type");

    public final String description;

    GameSetType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
