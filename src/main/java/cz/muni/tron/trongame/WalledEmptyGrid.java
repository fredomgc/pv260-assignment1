package cz.muni.tron.trongame;

import cz.muni.tron.Position;
import java.util.ArrayList;
import java.util.List;

public class WalledEmptyGrid extends EmptyGrid {
    
    private final int rows;
    private final int columns;
    
    public WalledEmptyGrid(int rows, int columns) {
        super(new WallGridAxis(rows), new WallGridAxis(columns));
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public List<Position> getWalls() {
        List<Position> walls = new ArrayList<>();
        addHorizontalWalls(walls);
        addVericalWalls(walls);
        return walls;
    }
    
    private void addHorizontalWalls(List<Position> walls) {
        addHorizontalWall(0, walls);
        addHorizontalWall(rows - 1, walls);
    }
    
    private void addVericalWalls(List<Position> walls) {
        addVerticalWall(0, walls);
        addVerticalWall(columns - 1, walls);
    }
    
    private void addHorizontalWall(int row, List<Position> walls) {
        for (int column = 0; column < columns; column++) {
            walls.add(new Position(row, column));
        }
    }
    
    private void addVerticalWall(int column, List<Position> walls) {
        for (int row = 0; row < columns; row++) {
            walls.add(new Position(row, column));
        }
    }
    
}
class WallGridAxis extends GridAxis {

    public WallGridAxis(int positionLimit) {
        super(positionLimit);
    }

    @Override
    public int normalizePosition(int position) throws NomralizationException {
        if (isPositionOutOfBounds(position)) {
            throw new NomralizationException();
        }
        return position;
    }
    
    private boolean isPositionOutOfBounds(int position) {
        return position <= 1 ||
                position >= (getPositionLimit() - 1);
    }

}