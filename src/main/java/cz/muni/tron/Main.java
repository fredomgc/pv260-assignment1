package cz.muni.tron;

import cz.muni.tron.engine.GameEngine;
import cz.muni.tron.engine.Output;
import cz.muni.tron.output.NullOutput;
import java.awt.Color;


public class Main {
    
    public static void main(String[] args) {
        TronGame game = new TronGame(new SingleStepRotaryGrid(100, 100));
        game.addPlayer(new Player(new Position(5, 5), Direction.RIGHT, Color.YELLOW, 0, 0, 0, 0));
        new GameEngine(game, new FixedRatioGameRenderer(), createFullScreenOutput())
                .run();
    }
    
    private static Output createNullOutput() {
        return new NullOutput();
    }
    
    private static Output createFullScreenOutput() {
        SecondScreenManager sm = new SecondScreenManager();
        sm.openFullscreenWindow();
        return sm.getOutput();
    }
    
}
