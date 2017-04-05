package cz.muni.tron.trongame;

import java.util.List;

public class LastOneStandingGameType implements GameType {
    
    private final int MINIMUM_PLAYER_LIMIT = 1;
    
    @Override
    public boolean isEndGame(List<Player> players) {
        return players.size() <= MINIMUM_PLAYER_LIMIT;
    }

    @Override
    public String getResult(List<Player> players) {
        if (players.isEmpty()) {
            return "No players";
        }
        if (isEndGame(players)) {
            return players.get(0).toString() + " wins";
        }
        return "Game was not ended";
    }

    @Override
    public void collisionOccured(Collision collision, List<Player> players) {
        players.remove(collision.getCollider());
    }
    
}
