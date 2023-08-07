package com.github.tiy2000.tictactoe.game.factory.byplayertype;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PlayerType {

    HUMAN("Human"),
    COMPUTER_RANDOM("Computer-random"),
    COMPUTER_AI_1("Computer-AI-v1"),
    COMPUTER_AI_2("Computer-AI-v2");

    public final String description;

    @Override
    public String toString() {
        return description;
    }
}
