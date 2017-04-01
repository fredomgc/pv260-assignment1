package cz.muni.tron.engine;

import cz.muni.tron.Position;
import java.awt.Color;

/**
 * Represents single frame of the game which consists of matrix of color points.
 */
public interface GameFrame {

    /**
     * @return Resolution of this game frame in points
     */
    public Resolution getResolution();

    /**
     * @param position Position index
     * @return Color at desired point
     * @throws InvalidPositionException Throw exception in case of row and
     * column index pointing out of this frame
     */
    public Color getPoint(Position position) throws InvalidPositionException;
}
