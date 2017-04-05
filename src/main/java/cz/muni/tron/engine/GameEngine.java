package cz.muni.tron.engine;

import cz.muni.tron.events.EventDispatcher;
import cz.muni.tron.events.EventNotifier;
import cz.muni.tron.events.KeyPressedEvent;
import cz.muni.tron.events.KeyPressedEventSubscriber;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GameEngine {
    
    private final GameRunner gameRunner;
    private final EventDispatcher eventDispatcher = new EventDispatcher();
    private boolean gameRunning = false;
    private boolean gameTerminated = false;

    public GameEngine(Game game, GameRenderer renderer, Output output) {
        gameRunner = new GameRunner(game, renderer, output);
    }
    
    public void run() {
        initialize();
        runTheGame();
        gameRunner.printResult();
        dispose();
    }
    
    private void initialize() {
        gameRunner.initialize(eventDispatcher);
        hookGlobalEvents();
    }
    
    private void dispose() {
        gameRunner.dispose();
    }
    
    private void runTheGame() {
        gameRunning = true;
        runTillTheEnd();
        gameRunning = false;
    }
    
    private void runTillTheEnd() {
        while (isGameRunning()) {
            tick();
        }
    }
    
    private boolean isGameRunning() {
        return gameRunner.isGameRunning() && !gameTerminated;
    }
    
    private void tick() {
        if (gameRunning) {
            gameRunner.tick();
            eventDispatcher.gameTick();
        }
        sleep();
    }
    
    private void sleep() {
        try {
            Thread.sleep(gameRunner.getGameSpeed());
        } catch (InterruptedException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.WARNING, null, ex);
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
    
    private class GameRunner {
        
        private final Game game;
        private final GameRenderer renderer;
        private final Output output;
        private int gameTicks = 0;

        public GameRunner(Game game, GameRenderer renderer, Output output) {
            this.game = game;
            this.renderer = renderer;
            this.output = output;
        }
        
        public void initialize(EventDispatcher eventDispatcher) {
            output.initialize(eventDispatcher);
            game.initialize(eventDispatcher);
        }
        
        public void dispose() {
            output.dispose();
        }
        
        public boolean isGameRunning() {
            return !game.isEndGame();
        }
        
        public int getGameSpeed() {
            return game.getGameSpeed();
        }
        
        public void tick() {
            gameTicks++;
            try {
                doGameStep();
            } catch (Exception ex) {
                throw new IllegalStateException("An error occured durring the game", ex);
            }
        }
        
        public void printResult() {
            if (gameTerminated) {
                System.out.println("The game was terminated after " + gameTicks + " ticks");
            } else {
                System.out.println("The game ended after " + gameTicks + " ticks with result:");
                System.out.println(game.getResult());
            }
        }
        
        private void doGameStep() {
            game.tick();
            renderCurrentScene();
        }
        
        private void renderCurrentScene() {
            renderer.render(game.getCurrentFrame(), output);
            output.update();
        }
        
    }
    
}
