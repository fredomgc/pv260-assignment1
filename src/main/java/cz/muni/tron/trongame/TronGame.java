package cz.muni.tron.trongame;

import cz.muni.tron.PositionsListFrame;
import cz.muni.tron.engine.Game;
import cz.muni.tron.engine.GameFrame;
import cz.muni.tron.events.EventNotifier;
import cz.muni.tron.events.EventSubscriber;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class TronGame implements Game {

    private static final int GAME_SPEED = 20;

    private final List<Player> players = new ArrayList<>();
    private final Grid grid;
    private final CollisionDetector collisionDetector = new CollisionDetector();
    private Collision collisition;
    private final EventController eventController = new EventController();
    private final FrameGenerator frameGenerator = new FrameGenerator();

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
        if (isEndGame()) {
            return;
        }
        movePlayers();
        detectCollision();
    }

    @Override
    public GameFrame getCurrentFrame() {
        return frameGenerator.generateFrame(players, grid);
    }

    @Override
    public int getGameSpeed() {
        return GAME_SPEED;
    }

    @Override
    public boolean isEndGame() {
        return isCollision() || players.isEmpty();
    }

    @Override
    public String getResult() {
        if (isCollision()) {
            return collisition.toString();
        }
        if (players.isEmpty()) {
            return "No players";
        }
        return "Game was not ended";
    }

    private boolean isCollision() {
        return collisition != null;
    }

    private void movePlayers() {
        for (Player player : players) {
            try {
                player.move(grid);
            } catch (CollisionException ex) {
                collisition = new Collision(player, ex.getPosition());
                return;
            }
        }
    }
    
    private void detectCollision() {
        if (collisition == null) {
            collisition = collisionDetector.detectCollision(players);
        }
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
    
    private class EventController {
        
        private final List<EventSubscriber> eventSubscribers = new ArrayList<>();
        
        public void addSubscriber(EventSubscriber subscriber) {
            eventSubscribers.add(subscriber);
        }
        
        public void hookEvents(EventNotifier dispatcher) {
            eventSubscribers.stream().forEach(subscriber -> subscriber.subscribe(dispatcher));
        }
    }
    
    private class FrameGenerator {
        
        private final Color WALL_COLOR = new Color(139, 69, 19);
        
        public GameFrame generateFrame(List<Player> players, Grid grid) {
            
            PositionsListFrame frame = new PositionsListFrame(grid.getResolution());
            renderPlayers(frame, players);
            renderWalls(frame, grid);
            return frame;
        }
        
        private void renderPlayers(PositionsListFrame frame, List<Player> players) {
            for (Player player : players) {
                frame.addList(player.getPath(), player.getColor());
                frame.addPosition(player.getPosition(), player.getColor().darker());
            }
        }
        
        private void renderWalls(PositionsListFrame frame, Grid grid) {
            frame.addList(grid.getWalls(), WALL_COLOR);
        }
    }

}
