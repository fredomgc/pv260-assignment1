package cz.muni.tron.trongame;

import java.util.List;

public class ColliderLoosesGameType implements GameType {
    
    private Collision collision;

    @Override
    public boolean isEndGame(List<Player> players) {
        return isCollision() || players.isEmpty();
    }

    @Override
    public String getResult(List<Player> players) {
        if (isCollision()) {
            return collision.toString();
        }
        if (players.isEmpty()) {
            return "No players";
        }
        return "Game was not ended";
    }

    @Override
    public void collisionOccured(Collision collision, List<Player> players) {
        if (!isCollision()) {
            this.collision = collision;
        }
    }
    
    private boolean isCollision() {
        return collision != null;
    }
    
}
