package com.github.tiy2000.tictactoe.game.factory.bygametype;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GameType {

    HUMAN_VS_HUMAN("Human [X] vs human [O]"),
    HUMAN_VS_COMPUTER("Human [X] vs computer-random [O]"),
    COMPUTER_VS_HUMAN("Computer-random [X] vs human [O]"),
    COMPUTER_VS_COMPUTER("Computer-random [X] vs computer-random [O]");

    public final String description;

    @Override
    public String toString() {
        return description;
    }
}
