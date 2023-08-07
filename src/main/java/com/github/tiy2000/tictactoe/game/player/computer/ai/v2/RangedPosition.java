package com.github.tiy2000.tictactoe.game.player.computer.ai.v2;

import com.github.tiy2000.tictactoe.game.board.Position;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Value
public class RangedPosition extends Position {

    int range;

    public RangedPosition(Position position, int range) {
        super(position.getRow(), position.getCol());
        this.range = range;
    }
}
