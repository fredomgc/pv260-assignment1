package cz.muni.tron.trongame;

import java.util.List;

public class StandardCollisionDetector implements CollisionDetector {
        
    @Override
    public Collision detectCollision(List<Player> players) {
        for (Player potentialCollider : players) {
            for (Player potentialCollidee : players) {
                Collision collision = detectPlayersCollision(potentialCollider, potentialCollidee);
                if (collision != null) {
                    return collision;
                }
            }
        }
        return null;
    }

    private Collision detectPlayersCollision(Player potentialCollider, Player potentialCollidee) {
        int collisionIndex = potentialCollidee.getPath().indexOf(potentialCollider.getPosition());
        if (isCollision(potentialCollider, potentialCollidee, collisionIndex)) {
            return new Collision(potentialCollider, potentialCollidee, potentialCollider.getPosition());
        }
        return null;
    }

    protected boolean isCollision(Player collider, Player collidee, int collisionIndex) {
        return collisionIndex != -1 &&
                (collider != collidee || collisionIndex != collider.getPath().size() - 1);
    }
}

