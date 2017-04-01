package cz.muni.tron.output;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * Creates and controls the screen
 *
 * @author Ondřej Směták <posta@ondrejsmetak.cz>
 */
public abstract class ScreenControl {

    /**
     * Type of MouseEvent
     */
    private enum MouseEventType {
        CLICKED, PRESSED, RELEASED, ENTERED, EXITED
    }

    /**
     * Type of KeyEvent
     */
    private enum KeyEventType {
        TYPED, PRESSED, RELEASED
    }

    /**
     * Frame, that represents the screen
     */
    private JFrame frame;

    /**
     * Collection of the registered MouseListeners
     */
    private java.util.List<MouseListener> mouseListeners = new ArrayList<>();

    /**
     * Collection of the registered KeyListeners
     */
    private java.util.List<KeyListener> keyListeners = new ArrayList<>();

    public ScreenControl() {
        frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        handleMouseEvents();
        handlekeyEvents();
    }
    
    public void openFullscreenWindow() {
        frame.setVisible(true);
        frame.createBufferStrategy(2);
    }

    public void hideFullscreenWindow() {
        frame.setVisible(false);
        frame.dispose();
    }

    public int getWidth() {
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }
    
    protected JFrame getFrame() {
        return frame;
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

    /**
     * Returns standard Graphics2D object for drawing into
     *
     * @return Graphics2D object
     */
    abstract public Graphics2D getGraphics();

    /**
     * Redraws the screen
     */
    public void update() {
        
    }

    /**
     * Fires the specified event in all registered key listeners
     *
     * @param type the type of the event
     * @param e key event
     */
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

    /**
     * Fires the specified event in all registered mouse listeners
     *
     * @param type the type of the event
     * @param e mouse event
     */
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

    /**
     * Adds the specified mouse listener to receive mouse events from this
     * screen
     *
     * @param l the mouse listener
     */
    public synchronized void addMouseListener(MouseListener l) {
        if (l == null) {
            return;
        }

        mouseListeners.add(l);
    }

    /**
     * Adds the specified key listener to receive key events from this screen
     *
     * @param l the key listener
     */
    public synchronized void addKeyListener(KeyListener l) {
        if (l == null) {
            return;
        }

        keyListeners.add(l);
    }

}
