package cz.muni.tron.trongame;

import cz.muni.tron.Position;

public class Collision {

    private final Player collider;
    private final Player collidee;
    private final Position position;

    public Collision(Player collider, Player collidee, Position position) {
        this.collider = collider;
        this.collidee = collidee;
        this.position = position;
    }
    
    public Collision(Player collider, Position position) {
        this(collider, null, position);
    }

    public Player getCollider() {
        return collider;
    }

    public Player getCollidee() {
        return collidee;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return collider + " crashed" +
                (collidee != null ? " into " + collidee : "") +
                " on position " + position;
    }

}