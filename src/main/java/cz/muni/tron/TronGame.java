package cz.muni.tron;

import cz.muni.tron.engine.Game;
import cz.muni.tron.engine.GameFrame;
import java.util.ArrayList;
import java.util.List;

public class TronGame implements Game {

    private static final int GAME_SPEED = 20;

    private final List<Player> players = new ArrayList<>();
    private final Grid grid;
    private final CollisionDetector collisionDetector;
    private Collision collisition;

    public TronGame(Grid grid) {
        this.grid = grid;
        collisionDetector = new CollisionDetector();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public void tick() {
        if (isCollision()) {
            return;
        }
        movePlayers();
        detectCollision();
    }

    @Override
    public GameFrame getCurrentFrame() {
        PositionsListFrame frame = new PositionsListFrame(grid.getWidth(), grid.getHeight());
        for (Player player : players) {
            frame.addList(player.getPath(), player.getColor());
        }
        return frame;
    }

    @Override
    public int getGameSpeed() {
        return GAME_SPEED;
    }

    @Override
    public boolean isEndGame() {
        return isCollision();
    }

    @Override
    public String getResult() {
        return isCollision()
                ? collisition.toString()
                : "Game was not ended";
    }

    private boolean isCollision() {
        return collisition != null;
    }

    private void movePlayers() {
        for (Player player : players) {
            player.move(grid);
        }
    }
    
    private void detectCollision() {
        collisition = collisionDetector.detectCollision(players);
    }
    
    private class CollisionDetector {
        
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
            if (!areSamePlayers(potentialCollider, potentialCollidee) &&
                    isPositionInList(potentialCollider.getPosition(), potentialCollidee.getPath())) {
                return new Collision(potentialCollider, potentialCollidee, potentialCollider.getPosition());
            }
            return null;
        }
        
        private boolean areSamePlayers(Player first, Player second) {
            return first == second;
        }
        
        private boolean isPositionInList(Position position, List<Position> list) {
            return list.contains(position);
        }
    }

    private class Collision {

        private final Player collider;
        private final Player collidee;
        private final Position position;

        public Collision(Player collider, Player collidee, Position position) {
            this.collider = collider;
            this.collidee = collidee;
            this.position = position;
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
            return collider + " crashed into " + collidee +
                    " on position " + position;
        }
        
    }

}
