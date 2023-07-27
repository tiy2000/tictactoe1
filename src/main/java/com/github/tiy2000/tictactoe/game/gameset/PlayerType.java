package com.github.tiy2000.tictactoe.game.gameset;

public enum PlayerType {

    HUMAN("Human"),
    COMPUTER_RANDOM("Computer-random-choice");

    public final String description;

    PlayerType(String description) {
        this.description = description;
    }
}
