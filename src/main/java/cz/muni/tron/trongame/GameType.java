package cz.muni.tron.trongame;

import java.util.List;

public interface GameType {
    
    public boolean isEndGame(List<Player> players);
    
    public String getResult(List<Player> players);
    
    public void collisionOccured(Collision collision, List<Player> players);
    
}
