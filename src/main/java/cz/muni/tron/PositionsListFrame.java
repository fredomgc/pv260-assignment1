package cz.muni.tron;

import cz.muni.tron.engine.GameFrame;
import cz.muni.tron.engine.InvalidPositionException;
import cz.muni.tron.engine.Resolution;
import java.awt.Color;
import java.util.List;

public class PositionsListFrame implements GameFrame {

    private final Resolution resolution;
    private final Color[][] frame;

    public PositionsListFrame(Resolution resolution, Color defaultColor) {
        this.resolution = resolution;
        this.frame = new Color[resolution.getHeight()][resolution.getWidth()];
        fillFrame(defaultColor);
    }
    
    public PositionsListFrame(Resolution resolution) {
        this(resolution, Color.BLACK);
    }

    @Override
    public Resolution getResolution() {
        return resolution;
    }

    @Override
    public Color getPoint(Position position) throws InvalidPositionException {
        checkPositionIsValid(position);
        return frame[position.getRow()][position.getColumn()];
    }

    public void addList(List<Position> positions, Color color) throws InvalidPositionException {
        for (Position position : positions) {
            addPosition(position, color);
        }
    }

    public void addPosition(Position position, Color color) throws InvalidPositionException {
        checkPositionIsValid(position);
        frame[position.getRow()][position.getColumn()] = color;
    }

    private void checkPositionIsValid(Position position) throws InvalidPositionException {
        if (!isPositionValid(position)) {
            throw new InvalidPositionException("Position " + position + " is out of the frame bound " + getFrameBounds());
        }
    }

    private boolean isPositionValid(Position position) {
        return position.getRow() >= 0 && position.getRow() < frame.length
                && position.getColumn() >= 0 && position.getColumn() < frame[0].length;
    }

    private String getFrameBounds() {
        return "[" + frame.length + "; " + frame[0].length + "]";
    }
    
    private void fillFrame(Color color) {
        for (int row = 0; row < resolution.getHeight(); row++) {
            for (int column = 0; column < resolution.getWidth(); column++) {
                addPosition(new Position(row, column), color);
            }
        }
    }

}
