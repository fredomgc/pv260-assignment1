package cz.muni.tron.trongame;

import cz.muni.tron.Position;
import cz.muni.tron.controls.Direction;
import cz.muni.tron.engine.Resolution;
import java.util.List;


public abstract class EmptyGrid implements Grid {

    private final GridAxis rowAxis;
    private final GridAxis columnAxis;

    public EmptyGrid(GridAxis rowAxis, GridAxis columnAxis) {
        this.rowAxis = rowAxis;
        this.columnAxis = columnAxis;
    }

    @Override
    public Resolution getResolution() {
        return new Resolution(rowAxis.getPositionLimit(), columnAxis.getPositionLimit());
    }
    
    @Override
    public Position move(Position position, Direction direction) throws CollisionException {
        try {
            return doMove(position, direction);
        } catch (GridAxis.NomralizationException ex) {
            throw new CollisionException(position);
        }
    }
    
    private Position doMove(Position position, Direction direction) throws GridAxis.NomralizationException {
        switch (direction) {
            case UP:
                return makeNormalizedPosition(position.getRow() - 1, position.getColumn());
            case DOWN:
                return makeNormalizedPosition(position.getRow() + 1, position.getColumn());
            case LEFT:
                return makeNormalizedPosition(position.getRow(), position.getColumn() - 1);
            case RIGHT:
                return makeNormalizedPosition(position.getRow(), position.getColumn() + 1);
        }
        return position;
    }
    
    @Override
    public abstract List<Position> getWalls();
    
    private Position makeNormalizedPosition(int row, int column) throws GridAxis.NomralizationException {
        return new Position(rowAxis.normalizePosition(row), columnAxis.normalizePosition(column));
    }

}
