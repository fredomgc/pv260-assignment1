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
    
    public void run() {
        while (!game.isEndGame()) {
            tick();
        }
        printResult();
    }
    
    private void tick() {
        doGameStep();
        try {
            Thread.sleep(game.getGameSpeed());
        } catch (InterruptedException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.WARNING, null, ex);
        }
    }
    
    private void doGameStep() {
        game.tick();
        renderer.render(game.getCurrentFrame(), output);
    }
    
    private void printResult() {
        System.out.println("The game ended with result:");
        System.out.println(game.getResult());
    }
    
}
