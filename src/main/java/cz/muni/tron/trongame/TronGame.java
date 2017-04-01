package cz.muni.tron.trongame;

import cz.muni.tron.Position;
import cz.muni.tron.PositionsListFrame;
import cz.muni.tron.engine.Game;
import cz.muni.tron.engine.GameFrame;
import cz.muni.tron.events.EventNotifier;
import cz.muni.tron.events.EventSubscriber;
import java.util.ArrayList;
import java.util.List;

public class TronGame implements Game {

    private static final int GAME_SPEED = 20;

    private final List<Player> players = new ArrayList<>();
    private final Grid grid;
    private final CollisionDetector collisionDetector = new CollisionDetector();
    private Collision collisition;
    private final EventController eventController = new EventController();

    public TronGame(Grid grid) {
        this.grid = grid;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
    
    public void addController(EventSubscriber controller) {
        eventController.addSubscriber(controller);
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
            frame.addPosition(player.getPosition(), player.getColor().darker());
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

    @Override
    public void initialize(EventNotifier eventNotifier) {
        eventController.hookEvents(eventNotifier);
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
            int collisionIndex = potentialCollidee.getPath().indexOf(potentialCollider.getPosition());
            if (isCollision(potentialCollider, potentialCollidee, collisionIndex)) {
                return new Collision(potentialCollider, potentialCollidee, potentialCollider.getPosition());
            }
            return null;
        }
        
        private boolean isCollision(Player collider, Player collidee, int collisionIndex) {
            return collisionIndex != -1 &&
                    (collider != collidee || collisionIndex != collider.getPath().size() - 1);
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
    
    private class EventController {
        
        private final List<EventSubscriber> eventSubscribers = new ArrayList<>();
        
        public void addSubscriber(EventSubscriber subscriber) {
            eventSubscribers.add(subscriber);
        }
        
        public void hookEvents(EventNotifier dispatcher) {
            eventSubscribers.stream().forEach(subscriber -> subscriber.subscribe(dispatcher));
        }
    }

}
