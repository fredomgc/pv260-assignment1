package cz.muni.tron.engine;

import cz.muni.tron.events.EventDispatcher;
import cz.muni.tron.events.EventNotifier;
import cz.muni.tron.events.KeyPressedEvent;
import cz.muni.tron.events.KeyPressedEventSubscriber;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GameEngine {
    
    private final Game game;
    private final GameRenderer renderer;
    private final Output output;
    private final EventDispatcher eventDispatcher = new EventDispatcher();
    private boolean gameRunning = false;
    private boolean gameTerminated = false;

    public GameEngine(Game game, GameRenderer renderer, Output output) {
        this.game = game;
        this.renderer = renderer;
        this.output = output;
    }
    
    public void run() {
        initialize();
        runTheGame();
        printResult();
        dispose();
    }
    
    private void initialize() {
        output.initialize(eventDispatcher);
        game.initialize(eventDispatcher);
        hookGlobalEvents();
    }
    
    private void dispose() {
        output.dispose();
    }
    
    private void runTheGame() {
        gameRunning = true;
        while (isGameRunning()) {
            tick();
        }
        gameRunning = false;
    }
    
    private boolean isGameRunning() {
        return !game.isEndGame() && !gameTerminated;
    }
    
    private void tick() {
        if (gameRunning) {
            doGameStep();
        }
        sleep();
    }
    
    private void sleep() {
        try {
            Thread.sleep(game.getGameSpeed());
        } catch (InterruptedException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.WARNING, null, ex);
        }
    }
    
    private void doGameStep() {
        try {
            game.tick();
            renderCurrentScene();
        } catch (Exception ex) {
            throw new IllegalStateException("An error occured durring the game", ex);
        }
    }
    
    private void renderCurrentScene() {
        renderer.render(game.getCurrentFrame(), output);
        output.update();
    }
    
    private void printResult() {
        if (gameTerminated) {
            System.out.println("The game was terminated");
        } else {
            System.out.println("The game ended with result:");
            System.out.println(game.getResult());
        }
    }
    
    private void playPauseGame() {
        gameRunning = !gameRunning;
    }
    
    private void terminateGame() {
        gameTerminated = true;
    }
    
    private void hookGlobalEvents() {
        eventDispatcher.subscribeKeyPressed(new GlobalKeyPressedSubscriber());
    }
    
    private class GlobalKeyPressedSubscriber implements KeyPressedEventSubscriber {

        @Override
        public void keyPresed(KeyPressedEvent event) {
            switch(event.getKeyCode()) {
                case KeyEvent.VK_P:
                    playPauseGame();
                    break;
                case KeyEvent.VK_ESCAPE:
                    terminateGame();
                    break;
            }
        }

        @Override
        public void subscribe(EventNotifier notifier) {
            
        }
        
    }
    
}
