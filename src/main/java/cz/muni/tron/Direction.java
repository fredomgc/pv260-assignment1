package cz.muni.tron;

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
    
    private Direction getOpositeDirecton(Direction direction) {
        switch (direction) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                return direction;
        }
    }
    
}
