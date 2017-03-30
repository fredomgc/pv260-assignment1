package cz.muni.tron;

import cz.muni.tron.engine.Output;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 *
 * @author Ondřej Směták <posta@ondrejsmetak.cz>
 */
public class ScreenOutput implements Output {

    private Graphics2D graphics;

    private ScreenControl screenControl;

    public ScreenOutput() {
        screenControl = new ScreenControl();
        
    }

    @Override
    public void drawRectangle(Color color, int x, int y, int width, int height) {
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
    
    public synchronized void addMouseListener(MouseListener l) {
       screenControl.addMouseListener(l);
    }
    
    
    public synchronized void addKeyListener(KeyListener l) {
        screenControl.addKeyListener(l);
    }
    
    
}
