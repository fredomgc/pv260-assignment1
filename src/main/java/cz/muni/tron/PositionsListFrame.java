package cz.muni.tron;

import cz.muni.tron.engine.GameFrame;
import cz.muni.tron.engine.InvalidPositionException;
import java.awt.Color;
import java.util.List;


public class PositionsListFrame implements GameFrame {
    private final Color[][] frame;

    public PositionsListFrame(int width, int height) {
        this.frame = new Color[height][width];
    }
    
    @Override
    public int getWidth() {
        return frame[0].length;
    }

    @Override
    public int getHeight() {
        return frame.length;
    }

    @Override
    public Color getPoint(Position position) throws InvalidPositionException {
        checkPositionIsValid(position);
        return frame[position.getColumn()][position.getRow()];
    }
    
    public void addList(List<Position> positions, Color color) throws InvalidPositionException {
        for (Position position : positions) {
            addPosition(position, color);
        }
    }
    
    private void addPosition(Position position, Color color) throws InvalidPositionException {
        checkPositionIsValid(position);
        frame[position.getColumn()][position.getRow()] = color;
    }
    
    private void checkPositionIsValid(Position position) throws InvalidPositionException {
        if (!isPositionValid(position)) {
            throw new InvalidPositionException("Position " + position + " is out of the frame bound " + getFrameBounds());
        }
    }
    
    private boolean isPositionValid(Position position) {
        return position.getRow() >= 0 && position.getRow() < frame.length &&
                position.getColumn() >= 0 && position.getColumn() < frame[0].length;
    }
    
    private String getFrameBounds() {
        return "[" + frame.length + "; " + frame[0].length + "]";
    }

}
