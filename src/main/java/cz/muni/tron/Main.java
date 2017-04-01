package cz.muni.tron;

import cz.muni.tron.output.ScreenOutput;
import cz.muni.tron.controls.KeyArrowsTurnController;
import cz.muni.tron.controls.MouseButtonsTurnController;
import cz.muni.tron.engine.GameEngine;
import cz.muni.tron.output.EagerScreenControl;
import cz.muni.tron.renderer.FixedSizeGameRenderer;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {
        TronGame game = new TronGame(new SingleStepRotaryGrid(100, 100));
        Player player1 = new Player(new Position(5, 5), Direction.RIGHT, Color.YELLOW);
        game.addPlayer(player1);
        game.addController(new KeyArrowsTurnController(player1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT));
        game.addController(new MouseButtonsTurnController(player1));
        new GameEngine(
                game,
                new FixedSizeGameRenderer(10),
                new ScreenOutput(new EagerScreenControl())
        ).run();
    }

}
