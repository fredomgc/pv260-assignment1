package cz.muni.tron;

import cz.muni.tron.engine.Output;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Ondřej Směták <posta@ondrejsmetak.cz>
 */
public class ScreenOutput implements Output{

    private Graphics2D graphics;
    
    public ScreenOutput(Graphics2D graphics) {
        this.graphics = graphics;
    }

    @Override
    public void drawRectangle(Color color, int x, int y, int width, int height) {
        this.graphics.setColor(color);
        this.graphics.fillRect(x, y, width, height);    
    }
    
    //je to potreba??
    //        this.graphics.dispose();

    @Override
    public int getWidth() {
        return 1920; // TODO
    }

    @Override
    public int getHeight() {
        return 1080; // TODO
    }
}
