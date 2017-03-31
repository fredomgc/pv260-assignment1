package cz.muni.tron;

import cz.muni.tron.engine.Output;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

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

    public ScreenOutput() {
        screenControl = new ScreenControl();
    }

    @Override
    public void drawRectangle(Color color, int x, int y, int width, int height) {
        if(this.graphics == null){
            throw new IllegalStateException("You can't call drawRectangle on uninitialized object.");
        }
        
        this.graphics.setColor(color);
        this.graphics.fillRect(x, y, width, height);
    }

    @Override
    public int getWidth() {
        return screenControl.getWidth();
    }

    @Override
    public int getHeight() {
        return screenControl.getHeight();
    }

    @Override
    public void initialize() {
        screenControl.openFullscreenWindow();
        this.graphics = screenControl.getGraphics();
    }

    @Override
    public void dispose() {
        screenControl.hideFullscreenWindow();
    }

    @Override
    public void update() {
        screenControl.update();
    }

    public synchronized void addMouseListener(MouseListener l) {
        screenControl.addMouseListener(l);
    }

    public synchronized void addKeyListener(KeyListener l) {
        screenControl.addKeyListener(l);
    }

}
