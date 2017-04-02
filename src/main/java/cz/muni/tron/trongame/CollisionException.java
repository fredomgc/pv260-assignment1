package cz.muni.tron.trongame;

import cz.muni.tron.Position;

public class CollisionException extends Exception {
    
    private final Position position;

    public CollisionException(Position position) {
        super();
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
    
}
