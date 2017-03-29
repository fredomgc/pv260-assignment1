package cz.muni.tron.engine;

import java.awt.Color;

/**
 * Representation of the output to which the frame will be rendered to. (eg.
 * screen)
 */
public interface Output {
    
    
    //nejake pripraveny obrazovky?
    //public void initialize();
    
    /**
     * Fills the specified rectangle.
     * @param color color used to fill the rectangle
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public void drawRectangle(Color color, int x, int y, int width, int height);
    
    
}
