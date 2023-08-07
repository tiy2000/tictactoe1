package com.github.tiy2000.tictactoe.game.board;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Position {

    private final int row;
    private final int col;
}
