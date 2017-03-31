package cz.muni.tron;

import cz.muni.tron.engine.GameEngine;
import cz.muni.tron.engine.GameRenderer;
import cz.muni.tron.engine.Output;
import cz.muni.tron.output.NullOutput;
import cz.muni.tron.renderer.FitScreenGameRenderer;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {

    public static void main(String[] args) {
        TronGame game = new TronGame(new SingleStepRotaryGrid(100, 100));
        game.addPlayer(new Player(new Position(5, 5), Direction.RIGHT, Color.YELLOW, 0, 0, 0, 0));

        Output screenOutput = createScreenOutput();
        screenOutput.initialize();

        GameRenderer renderer;
        renderer = new FitScreenGameRenderer();

        new GameEngine(game, renderer, screenOutput)
                .run();
        
        screenOutput.dispose();
    }

    private static Output createNullOutput() {
        return new NullOutput();
    }

    private static Output createScreenOutput() {
        ScreenOutput so = new ScreenOutput();
        so.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.err.println("Klikno se mysi");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //
            }
        });

        return so;
    }

}
