package cz.muni.tron.trongame;

import cz.muni.tron.Position;
import java.util.ArrayList;
import java.util.List;

public class RotaryEmptyGrid extends EmptyGrid {
    
    public RotaryEmptyGrid(int rows, int columns) {
        super(new RotaryGridAxis(rows), new RotaryGridAxis(columns));
    }

    @Override
    public List<Position> getWalls() {
        return new ArrayList<>();
    }
    
}

class RotaryGridAxis extends GridAxis {

    public RotaryGridAxis(int positionLimit) {
        super(positionLimit);
    }

    @Override
    public int normalizePosition(int position) {
        return (position + getPositionLimit()) % getPositionLimit();
    }

}