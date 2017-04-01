package cz.muni.tron.trongame;

import cz.muni.tron.Position;
import cz.muni.tron.controls.Direction;

public interface Grid {

    /**
     * @return Width of grid
     */
    public int getWidth();

    /**
     * @return Height of grid
     */
    public int getHeight();

    /**
     * Makes move from position in given direction
     *
     * @return New position after move
     */
    public Position move(Position position, Direction direction);
}
