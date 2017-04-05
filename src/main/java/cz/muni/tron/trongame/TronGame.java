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
    private final GameType gameType;
    private final CollisionDetector collisionDetector;
    private final EventController eventController = new EventController();
    private final FrameGenerator frameGenerator = new FrameGenerator();

    public TronGame(Grid grid, GameType gameType, CollisionDetector collisionDetector) {
        this.grid = grid;
        this.gameType = gameType;
        this.collisionDetector = collisionDetector;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
    
    public void addController(EventSubscriber controller) {
        eventController.addSubscriber(controller);
    }
    
    @Override
    public void initialize(EventNotifier eventNotifier) {
        eventController.hookEvents(eventNotifier);
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
        return gameType.isEndGame(players);
    }

    @Override
    public String getResult() {
        return gameType.getResult(players);
    }

    private void movePlayers() {
        for (Player player : players) {
            movePlayer(player);
        }
    }
    
    private void movePlayer(Player player) {
        try {
            player.move(grid);
        } catch (CollisionException ex) {
            gameType.collisionOccured(new Collision(player, ex.getPosition()), players);
        }
    }
    
    private void detectCollision() {
        Collision collisition = collisionDetector.detectCollision(players);
        if (collisition != null) {
            gameType.collisionOccured(collisition, players);
        }
    }
    
    private class EventController {
        
        private final List<EventSubscriber> eventSubscribers = new ArrayList<>();
        
        public void addSubscriber(EventSubscriber subscriber) {
            eventSubscribers.add(subscriber);
        }
        
        public void hookEvents(EventNotifier dispatcher) {
            eventSubscribers.stream().forEach(subscriber -> dispatcher.subscribe(subscriber));
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
