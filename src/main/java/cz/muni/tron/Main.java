package cz.muni.tron;

import cz.muni.tron.controls.AiRandomTurnControler;
import cz.muni.tron.trongame.Player;
import cz.muni.tron.trongame.TronGame;
import cz.muni.tron.controls.Direction;
import cz.muni.tron.output.ScreenOutput;
import cz.muni.tron.controls.KeyArrowsTurnController;
import cz.muni.tron.controls.MouseButtonsTurnController;
import cz.muni.tron.engine.GameEngine;
import cz.muni.tron.output.BufferedScreenControl;
import cz.muni.tron.output.EagerScreenControl;
import cz.muni.tron.renderer.FitScreenGameRenderer;
import cz.muni.tron.trongame.ColliderLoosesGameType;
import cz.muni.tron.trongame.LastOneStandingGameType;
import cz.muni.tron.trongame.RotaryEmptyGrid;
import cz.muni.tron.trongame.StandardCollisionDetector;
import cz.muni.tron.trongame.WalledEmptyGrid;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {
        // Ondřej Směták 448279
        // Dominik Veselý 448261
        
        TronGame game = new TronGame(
                new RotaryEmptyGrid(100, 100),
                new LastOneStandingGameType(),
                new StandardCollisionDetector());
        Player player1 = new Player("Yellow", new Position(5, 5), Direction.RIGHT, Color.YELLOW);
        game.addPlayer(player1);
        game.addController(new KeyArrowsTurnController(player1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT));
        game.addController(new MouseButtonsTurnController(player1));
        Player player2 = new Player("Red", new Position(10, 80), Direction.DOWN, Color.RED);
        game.addPlayer(player2);
        game.addController(new AiRandomTurnControler(player2, .02));
        Player player3 = new Player("Blue", new Position(95, 80), Direction.LEFT, Color.BLUE);
        game.addPlayer(player3);
        game.addController(new AiRandomTurnControler(player3, .08));
        new GameEngine(
                game,
                new FitScreenGameRenderer(), //optionally, you can use FixedSizeGameRenderer
                new ScreenOutput(new BufferedScreenControl()) //optionally, you can use BufferedScreenControl
        ).run();
    }

}
