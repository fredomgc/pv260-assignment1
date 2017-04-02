package cz.muni.tron.output;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

/**
 * Creates and controls the screen
 *
 * @author Ondřej Směták <posta@ondrejsmetak.cz>
 */
public abstract class ScreenControl {

    /**
     * Frame, that represents the screen
     */
    private final JFrame frame;

    public ScreenControl() {
        frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
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

    /**
     * Adds the specified mouse listener to receive mouse events from this
     * screen
     *
     * @param keyListener the mouse listener
     */
    public synchronized void addMouseListener(MouseListener keyListener) {
        frame.addMouseListener(keyListener);
    }

    /**
     * Adds the specified key listener to receive key events from this screen
     *
     * @param keyListener the key listener
     */
    public synchronized void addKeyListener(KeyListener keyListener) {
        frame.addKeyListener(keyListener);
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
    abstract public void update();

    protected JFrame getFrame() {
        return frame;
    }

}
