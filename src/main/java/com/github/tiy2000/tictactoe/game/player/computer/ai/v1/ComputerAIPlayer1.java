package com.github.tiy2000.tictactoe.game.player.computer.ai.v1;

import com.github.tiy2000.tictactoe.game.board.Board;
import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;
import com.github.tiy2000.tictactoe.game.board.Position;
import com.github.tiy2000.tictactoe.game.player.computer.AbstractComputerPlayer;
import com.github.tiy2000.tictactoe.ui.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ComputerAIPlayer1 extends AbstractComputerPlayer {

    private boolean isJustBegan = true;

    public ComputerAIPlayer1(String name, PlayerSymbol symbol, Console console) {
        super(name, symbol, console);
    }

    @Override
    public void init(Board board) {
        super.init(board);
        isJustBegan = true;
    }

    @Override
    protected Position getPosition() {
        Position position;
//        if (isJustBegan && symbol == PlayerSymbol.X) {
        if (isJustBegan && board.isCellBlank(createCenterPosition())) {
            position = createCenterPosition();
            isJustBegan = false;
        } else {
            position = createBestPosition();
        }
        return position;
    }


    private Position createBestPosition() {
        List<Position> positions = getAlmostWinPositions(symbol);
        if (positions.isEmpty()) {
            for (int i = 1; i < board.getSize(); i++) {
                positions = getAlmostWinPositions(getRivalSymbol(), i);
                if (!positions.isEmpty()) {
                    break;
                }
            }
        }
        if (positions.isEmpty()) {
            console.println("\n*********** JUST FOR DEBUG: RANDOM POSITION **********");
            return createRandomPosition();
        }
        return positions.get(0);
    }


    private List<Position> getAlmostWinPositions(PlayerSymbol symbol) {
        return getAlmostWinPositions(symbol, 1);
    }

    private List<Position> getAlmostWinPositions(PlayerSymbol symbol, int freeCellNumber) {
        List<Position> positions = new ArrayList<>();

        for (int row = 1; row <= board.getSize(); row++) {
            checkAlmostWinLinePosition(row, 0, 1, 1, symbol, freeCellNumber, positions);
        }

        for (int col = 1; col <= board.getSize(); col++) {
            checkAlmostWinLinePosition(1, 1, col, 0, symbol, freeCellNumber, positions);
        }

        checkAlmostWinLinePosition(1, 1, 1, 1, symbol, freeCellNumber, positions);
        checkAlmostWinLinePosition(1, 1, board.getSize(), -1, symbol, freeCellNumber, positions);

        System.out.println();
        positions.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .forEach((key, value) -> System.out.println(key + " : " + value));

        positions = positions.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .toList();

        return positions;
    }

    private void checkAlmostWinLinePosition(int rowBegin, int rowStep,
                                            int colBegin, int colStep,
                                            PlayerSymbol symbol,
                                            int freeCellNumber,
                                            List<Position> positions) {
        List<Position> positionsAcc = new ArrayList<>();
        int mySymbolsCounter = 0;
        int freeCellsCounter = 0;
        for (int i = 1, row = rowBegin, col = colBegin; i <= board.getSize(); i++, row += rowStep, col += colStep) {
            if (board.getCellSymbol(row, col) == symbol) {
                mySymbolsCounter++;
            } else if (board.getCellSymbol(row, col) == PlayerSymbol.BLANK) {
                freeCellsCounter++;
                positionsAcc.add(new Position(row, col));
            }
        }
        if (mySymbolsCounter == (board.getSize() - freeCellNumber) && freeCellsCounter == freeCellNumber) {
            positions.addAll(positionsAcc);
        }
    }
}
