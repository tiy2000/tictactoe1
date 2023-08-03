package com.github.tiy2000.tictactoe.game.factory.byplayertype;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PlayerType {

    HUMAN("Human"),
    COMPUTER_RANDOM("Computer-random-choice");

    public final String description;

    @Override
    public String toString() {
        return description;
    }
}
