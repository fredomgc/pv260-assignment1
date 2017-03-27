package cz.muni.tron;


public class SingleStepRotaryGrid implements Grid {

    private final Axis rowAxis;
    private final Axis columnAxis;

    public SingleStepRotaryGrid(int rows, int columns) {
        this.rowAxis = new Axis(rows);
        this.columnAxis = new Axis(columns);
    }

    public Position move(Position position, Direction direction) {
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
    
    private Position makeNormalizedPosition(int row, int column) {
        return new Position(rowAxis.wrapAround(row), columnAxis.wrapAround(column));
    }

    private class Axis {

        private final int positionLimit;

        public Axis(int maxValue) {
            this.positionLimit = maxValue + 1;
        }

        public int wrapAround(int position) {
            return (position + positionLimit) % positionLimit;
        }

    }
}
