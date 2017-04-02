package cz.muni.tron.trongame;

import cz.muni.tron.Position;
import cz.muni.tron.controls.Direction;
import cz.muni.tron.engine.Resolution;
import java.util.List;

public interface Grid {
    
    /**
     * @return Resolution of the grid
     */
    public Resolution getResolution();

    /**
     * Makes move from position in given direction
     *
     * @return New position after move
     */
    public Position move(Position position, Direction direction) throws CollisionException;
    
    public List<Position> getWalls();
    
}
