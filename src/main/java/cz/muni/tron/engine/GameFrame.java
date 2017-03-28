package cz.muni.tron.engine;

import java.awt.Color;

/**
 * Represents single frame of the game which consists of matrix of color points.
 */
public interface GameFrame {

    /**
     * @return Width of this game frame in points
     */
    public int getWidth();

    /**
     * @return Height of this game frame in points
     */
    public int getHeight();

    /**
     * @param row Row index
     * @param column Column index
     * @return Color at desired point
     * @throws InvalidPositionException Throw exception in case of row and
     * column index pointing out of this frame
     */
    public Color getPoint(int row, int column) throws InvalidPositionException;
}
