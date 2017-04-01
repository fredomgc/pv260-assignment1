package cz.muni.tron;

import cz.muni.tron.controls.TurnListenerWithDirection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Player implements TurnListenerWithDirection {

    private final PlayerDirection playerDirection;
    private final Color color;
    private final List<Position> path = new ArrayList();

    public Player(Position position, Direction currentDirection, Color color) {
        this.playerDirection = new PlayerDirection(currentDirection, position);
        this.color = color;
        this.path.add(position);
    }

    public Position getPosition() {
        return path.get(path.size() - 1);
    }

    public Color getColor() {
        return color;
    }

    public List<Position> getPath() {
        return path;
    }

    public void move(Grid inGrid) {
        path.add(
                inGrid.move(getPosition(), getDirection())
        );
    }

    @Override
    public String toString() {
        return color.toString();
    }

    @Override
    public Direction getDirection() {
        return playerDirection.getDirection();
    }

    @Override
    public void turn(Turn turn) {
        playerDirection.turn(turn);
    }
    
    private class PlayerDirection {
        private Direction currentDirection;
        private Position lastDirectionChange;

        public PlayerDirection(Direction initialDirection, Position initialPosition) {
            this.currentDirection = initialDirection;
            this.lastDirectionChange = initialPosition;
        }
        
        public Direction getDirection() {
            return currentDirection;
        }
        
        public void turn(Turn turn) {
            if (canChangeDirection()) {
                doTurn(turn);
            }
        }
        
        private boolean canChangeDirection() {
            return !lastDirectionChange.equals(getPosition());
        }
        
        private void doTurn(Turn turn) {
            currentDirection = currentDirection.turn(turn);
            lastDirectionChange = getPosition();
        }
        
    }
    
}
