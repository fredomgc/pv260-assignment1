package cz.muni.tron.engine;

import cz.muni.tron.FixedRatioGameRenderer;
import cz.muni.tron.ScreenManager;
import cz.muni.tron.ScreenOutput;
import cz.muni.tron.SecondScreenManager;
import cz.muni.tron.engine.GameFrame;
import cz.muni.tron.engine.InvalidPositionException;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * SMAZAT
 * @author Ondřej Směták <posta@ondrejsmetak.cz>
 */
public class GameEngineTest {
    
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame() {
            @Override
            public int getWidth() {
                return 500;
            }

            @Override
            public int getHeight() {
                return 500;
            }

            @Override
            public Color getPoint(int row, int column) throws InvalidPositionException {
                return  Math.random() >  0.5 ? Color.RED : Color.BLUE;
            }
        };
        
        SecondScreenManager sm = new SecondScreenManager();
        sm.openFullscreenWindow();
        
        GameRenderer gameRenderer = new FixedRatioGameRenderer();
        gameRenderer.render(gameFrame, sm.getOutput());
        sm.update();
        
        
        
          
        sm.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.err.println("Kliklo se na canvas.");
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
        
        
        sm.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.err.println("Na canvasu se zmackla klavesa: " + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //
            }
        });
        
    }
}
