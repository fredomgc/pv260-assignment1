package cz.muni.tron;

import cz.muni.tron.engine.Output;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class SecondScreenManager {

    private enum MouseEventType {
        CLICKED, PRESSED, RELEASED, ENTERED, EXITED
    }
    
    private enum KeyEventType {
        TYPED, PRESSED, RELEASED
    }
    

    private JFrame frame;

    private java.util.List<MouseListener> mouseListeners = new ArrayList<>();
    
    private java.util.List<KeyListener> keyListeners = new ArrayList<>();

    public SecondScreenManager(JFrame frame) {
        this.frame = frame;
    }

    public SecondScreenManager() {
    }

    public void openFullscreenWindow() {
        frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        //frame.createBufferStrategy(2);
        
        handleMouseEvents();
        handlekeyEvents();
    }

    private void handlekeyEvents() {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                fireKeyEvent(KeyEventType.TYPED, e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                fireKeyEvent(KeyEventType.PRESSED, e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                fireKeyEvent(KeyEventType.RELEASED, e);
            }
        });
    }
    
    private void handleMouseEvents() {
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fireMouseEvent(MouseEventType.CLICKED, e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                fireMouseEvent(MouseEventType.PRESSED, e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                fireMouseEvent(MouseEventType.RELEASED, e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                fireMouseEvent(MouseEventType.ENTERED, e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                fireMouseEvent(MouseEventType.EXITED, e);
            }
        });
    }

    public Output getOutput() {
        return new ScreenOutput((Graphics2D) frame.getGraphics());
        
        //return new ScreenOutput((Graphics2D) frame.getBufferStrategy().getDrawGraphics());
    }

    public void update() {
        frame.getBufferStrategy().show();
    }

    private void fireKeyEvent(KeyEventType type, KeyEvent e) {
        for (KeyListener kl : keyListeners) {
            switch (type) {
                case TYPED:
                    kl.keyTyped(e);
                    break;

                case PRESSED:
                    kl.keyPressed(e);
                    break;

                case RELEASED:
                    kl.keyReleased(e);
                    break;
            }
        }
    }
    
    private void fireMouseEvent(MouseEventType type, MouseEvent e) {
        for (MouseListener ml : mouseListeners) {
            switch (type) {
                case CLICKED:
                    ml.mouseClicked(e);
                    break;

                case PRESSED:
                    ml.mousePressed(e);
                    break;

                case RELEASED:
                    ml.mouseReleased(e);
                    break;

                case ENTERED:
                    ml.mouseEntered(e);
                    break;

                case EXITED:
                    ml.mouseExited(e);
                    break;
            }
        }
    }

    public synchronized void addMouseListener(MouseListener l) {
        if (l == null) {
            return;
        }

        mouseListeners.add(l);
    }
    
    
    public synchronized void addKeyListener(KeyListener l) {
        if (l == null) {
            return;
        }

        keyListeners.add(l);
    }

}
