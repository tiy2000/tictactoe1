package com.github.tiy2000.tictactoe.game.gameset;

public enum GameType {

    HUMAN_VS_HUMAN("Human [X] vs human [O]"),
    HUMAN_VS_COMPUTER("Human [X] vs computer [O]"),
    COMPUTER_VS_HUMAN("Computer [X] vs human [O]"),
    COMPUTER_VS_COMPUTER("Computer [X] vs computer [O]");

    public final String description;

    GameType(String description) {
        this.description = description;
    }
}
