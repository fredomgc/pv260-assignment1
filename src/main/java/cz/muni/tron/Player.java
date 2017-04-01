package cz.muni.tron;

import cz.muni.tron.controls.TurnListenerWithDirection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Player implements TurnListenerWithDirection {

    private Direction currentDirection;
    private final Color color;

    private final List<Position> path = new ArrayList();

    public Player(Position position, Direction currentDirection, Color color) {
        this.currentDirection = currentDirection;
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
                inGrid.move(getPosition(), currentDirection)
        );
    }

    @Override
    public String toString() {
        return color.toString();
    }

    @Override
    public Direction getDirection() {
        return currentDirection;
    }

    @Override
    public void turn(Turn turn) {
        currentDirection = currentDirection.turn(turn);
    }
}
