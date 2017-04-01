package cz.muni.tron.engine;

import java.util.logging.Level;
import java.util.logging.Logger;


public class GameEngine {
    
    private final Game game;
    private final GameRenderer renderer;
    private final Output output;

    public GameEngine(Game game, GameRenderer renderer, Output output) {
        this.game = game;
        this.renderer = renderer;
        this.output = output;
    }
    
    private void initialize() {
        
    }
    
    public void run() {
        initialize();
        runTheGame();
        printResult();
    }
    
    private void runTheGame() {
        while (!game.isEndGame()) {
            tick();
        }
    }
    
    private void tick() {
        try {
            doGameStep();
        } catch (Exception ex) {
            throw new IllegalStateException("An error occured durring the game", ex);
        }
        
        try {
            Thread.sleep(game.getGameSpeed());
        } catch (InterruptedException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.WARNING, null, ex);
        }
    }
    
    private void doGameStep() {
        game.tick();
        renderer.render(game.getCurrentFrame(), output);
        output.update();
    }
    
    private void printResult() {
        System.out.println("The game ended with result:");
        System.out.println(game.getResult());
    }
    
}
