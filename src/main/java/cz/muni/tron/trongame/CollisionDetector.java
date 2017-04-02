package cz.muni.tron.trongame;

import java.util.List;

public interface CollisionDetector {
    
    public Collision detectCollision(List<Player> players);
    
}
