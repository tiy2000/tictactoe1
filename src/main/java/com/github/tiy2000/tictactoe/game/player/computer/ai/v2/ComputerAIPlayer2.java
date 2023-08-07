package com.github.tiy2000.tictactoe.game.player.computer.ai.v2;

import com.github.tiy2000.tictactoe.game.board.Board;
import com.github.tiy2000.tictactoe.game.board.PlayerSymbol;
import com.github.tiy2000.tictactoe.game.board.Position;
import com.github.tiy2000.tictactoe.game.player.computer.AbstractComputerPlayer;
import com.github.tiy2000.tictactoe.ui.Console;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ComputerAIPlayer2 extends AbstractComputerPlayer {

    public ComputerAIPlayer2(String name, PlayerSymbol symbol, Console console) {
        super(name, symbol, console);
    }

    @Override
    public void init(Board board) {
        super.init(board);
    }

    @Override
    protected Position getPosition() {
        return createBestPosition();
    }

    private Position createBestPosition() {
        Position centerPosition = createCenterPosition();
        if (board.isCellBlank(centerPosition)) {
            return centerPosition;
        }
        List<RangedPosition> winPositions;
        for (int i = 1; i <= board.getSize(); i++) {
            winPositions = getRivalWinPositions(i);
            if (winPositions.get(0).getRange() > 0) {
                return getRandomizedPositionWithTopRange(winPositions);
            }
            winPositions = getMyWinPositions(i);
            if (winPositions.get(0).getRange() > 0) {
                return getRandomizedPositionWithTopRange(winPositions);
            }
        }
        return createRandomPosition();
    }

    private RangedPosition getRandomizedPositionWithTopRange(List<RangedPosition> positions) {
        int randomPositionIndex = 0;
        int range = positions.get(0).getRange();
        List<RangedPosition> rangedPositions = positions.stream()
                .filter(rp -> rp.getRange() == range)
                .toList();
        if (rangedPositions.size() > 1) {
            randomPositionIndex = random.nextInt(rangedPositions.size());
        }
        return rangedPositions.get(randomPositionIndex);
    }

    private List<RangedPosition> getMyWinPositions(int stepsToWin) {
        return getWinPositions(symbol, stepsToWin);
    }

    private List<RangedPosition> getRivalWinPositions(int stepsToWin) {
        return getWinPositions(getRivalSymbol(), stepsToWin);
    }

    private List<RangedPosition> getWinPositions(PlayerSymbol symbol, int stepsToWin) {
        Map<Position, Integer> map = new LinkedHashMap<>();
        for (int row = 1; row <= board.getSize(); row++) {
            for (int col = 1; col <= board.getSize(); col++) {
                if (board.isCellBlank(row, col)) {
                    int winVariantsNumber = calcWinVariants(row, col, symbol, stepsToWin);
                    Position position = new Position(row, col);
                    map.put(position, winVariantsNumber);
                }
            }
        }
        return map.entrySet()
                .stream()
                .map(e -> new RangedPosition(e.getKey(), e.getValue()))
                .sorted(Comparator.comparing(RangedPosition::getRange).reversed())
                .toList();
    }

    private int calcWinVariants(int row, int col, PlayerSymbol symbol, int stepsToWin) {
        int counter = 0;
        counter += calcWinVariantsInRow(row, col, symbol, stepsToWin);
        counter += calcWinVariantsInCol(row, col, symbol, stepsToWin);
        if (row == col) {
            counter += calcWinVariantsInLeftDiagonal(row, col, symbol, stepsToWin);
        }
        if (row == board.getSize() - col + 1) {
            counter += calcWinVariantsInRightDiagonal(row, col, symbol, stepsToWin);
        }
        return counter;
    }

    private int calcWinVariantsInRow(int row, int col, PlayerSymbol symbol, int stepsToWin) {
        return checkAlmostWinLinePosition(row, col, row, 0, board.getSize(), -1, symbol, stepsToWin);
    }

    private int calcWinVariantsInCol(int row, int col, PlayerSymbol symbol, int stepsToWin) {
        return checkAlmostWinLinePosition(row, col, 1, 1, col, 0, symbol, stepsToWin);
    }

    private int calcWinVariantsInLeftDiagonal(int row, int col, PlayerSymbol symbol, int stepsToWin) {
        return checkAlmostWinLinePosition(row, col, 1, 1, 1, 1, symbol, stepsToWin);
    }

    private int calcWinVariantsInRightDiagonal(int row, int col, PlayerSymbol symbol, int stepsToWin) {
        return checkAlmostWinLinePosition(row, col, 1, 1, board.getSize(), -1, symbol, stepsToWin);
    }

    private int checkAlmostWinLinePosition(int row2Check, int col2Check,
                                           int rowBegin, int rowStep,
                                           int colBegin, int colStep,
                                           PlayerSymbol symbol,
                                           int stepsToWin) {
        int occupiedBySymbolCounter = 0;
        for (int i = 1, row = rowBegin, col = colBegin; i <= board.getSize(); i++, row += rowStep, col += colStep) {
            if (row == row2Check && col == col2Check) {
                continue;
            }
            PlayerSymbol cellSymbol = board.getCellSymbol(row, col);
            if (cellSymbol == getRivalSymbol(symbol)) {
                return 0;
            }
            if (cellSymbol == symbol) {
                occupiedBySymbolCounter++;
            }
        }
        return (board.getSize() - occupiedBySymbolCounter) == stepsToWin ? 1 : 0;
    }
}
