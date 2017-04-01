package cz.muni.tron.engine;

import cz.muni.tron.events.EventDispatcher;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GameEngine {
    
    private final Game game;
    private final GameRenderer renderer;
    private final Output output;
    private final EventDispatcher eventDispatcher = new EventDispatcher();

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
    }
    
    private void dispose() {
        output.dispose();
    }
    
    private void runTheGame() {
        while (!game.isEndGame()) {
            tick();
        }
    }
    
    private void tick() {
        doGameStep();
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
        System.out.println("The game ended with result:");
        System.out.println(game.getResult());
    }
    
}
