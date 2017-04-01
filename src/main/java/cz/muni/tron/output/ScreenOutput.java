package cz.muni.tron.output;

import cz.muni.tron.engine.Output;
import cz.muni.tron.engine.Resolution;
import cz.muni.tron.events.EventListener;
import cz.muni.tron.events.KeyPressedEventImpl;
import cz.muni.tron.events.MouseButton;
import cz.muni.tron.events.MouseClickedEventImpl;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;

/**
 * Output to the screen represented via JFrame window
 *
 * @author Ondřej Směták <posta@ondrejsmetak.cz>
 */
public class ScreenOutput implements Output {

    /**
     * Graphics used to draw into window
     */
    private Graphics2D graphics;

    /**
     * Control for the window
     */
    private final ScreenControl screenControl;

    public ScreenOutput(ScreenControl screenControl) {
        this.screenControl = screenControl;
    }

    @Override
    public void drawRectangle(Color color, int x, int y, int width, int height) {
        if (this.graphics == null) {
            throw new IllegalStateException("You can't call drawRectangle on uninitialized object.");
        }

        this.graphics.setColor(color);
        this.graphics.fillRect(x, y, width, height);
    }

    @Override
    public Resolution getResolution() {
        return new Resolution(screenControl.getWidth(), screenControl.getHeight());
    }

    @Override
    public void initialize(EventListener eventListener) {
        screenControl.openFullscreenWindow();
        graphics = screenControl.getGraphics();
        screenControl.addKeyListener(new AWTKeyEventBinder(eventListener));
        screenControl.addMouseListener(new AWTMouseEventBinder(eventListener));
    }

    @Override
    public void dispose() {
        screenControl.hideFullscreenWindow();
    }

    @Override
    public void update() {
        screenControl.update();
    }

    private class AWTKeyEventBinder implements KeyListener {

        private final EventListener eventListener;

        public AWTKeyEventBinder(EventListener eventListener) {
            this.eventListener = eventListener;
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            eventListener.keyPressed(new KeyPressedEventImpl(e.getKeyCode()));
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }

    private class AWTMouseEventBinder implements MouseListener {

        private final EventListener eventListener;

        public AWTMouseEventBinder(EventListener eventListener) {
            this.eventListener = eventListener;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            MouseButton button = translateMouseButton(e);
            if (button != null) {
                eventListener.mouseClicked(new MouseClickedEventImpl(button));
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        private MouseButton translateMouseButton(MouseEvent event) {
            if (SwingUtilities.isLeftMouseButton(event)) {
                return MouseButton.LEFT;
            }
            if (SwingUtilities.isRightMouseButton(event)) {
                return MouseButton.RIGHT;
            }
            if (SwingUtilities.isMiddleMouseButton(event)) {
                return MouseButton.MIDDLE;
            }
            return null;
        }

    }

}
