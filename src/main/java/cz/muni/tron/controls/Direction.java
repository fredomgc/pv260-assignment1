package cz.muni.tron.controls;

/**
 *
 * @author Doumr
 */
public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;
    
    public boolean isOpositeTo(Direction otherDirecton) {
        return getOpositeDirecton(this) == otherDirecton;
    }
    
    public Direction turn(Turn turn) {
        if (turn == Turn.LEFT) {
            return turnLeft();
        }
        return turnRight();
    }
    
    public Turn toTurn(Direction otherDirection) {
        if (turnLeft() == otherDirection) {
            return Turn.LEFT;
        }
        if (turnRight() == otherDirection) {
            return Turn.RIGHT;
        }
        return null;
    }
    
    private Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
            default:
                return this;
        }
    }
    
    private Direction turnRight() {
        return turnLeft().turnLeft().turnLeft();
    }
    
    private Direction getOpositeDirecton(Direction direction) {
        return direction.turnLeft().turnLeft();
    }
    
}
