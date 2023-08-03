package com.github.tiy2000.tictactoe.game.factory.byplayertype;

public enum PlayerType {

    HUMAN("Human"),
    COMPUTER_RANDOM("Computer-random-choice");

    public final String description;

    PlayerType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
