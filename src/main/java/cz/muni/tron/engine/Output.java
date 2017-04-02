package cz.muni.tron.engine;

import cz.muni.tron.events.EventListener;
import java.awt.Color;

/**
 * Representation of the output to which the frame will be rendered to. (eg.
 * screen)
 */
public interface Output {

    /**
     * Prepares this output
     * @param eventListener subscribing EventListener
     */
    public void initialize(EventListener eventListener);

    /**
     * Use to redraw output
     */
    public void update();

    /**
     * Closes this output
     */
    public void dispose();

    /**
     * Fills the specified rectangle.
     *
     * @param color color used to fill the rectangle
     * @param x the x coordinate of the rectangle to be filled
     * @param y the y coordinate of the rectangle to be filled
     * @param width the width of the rectangle to be filled
     * @param height the height of the rectangle to be filled
     */
    public void drawRectangle(Color color, int x, int y, int width, int height);

    /**
     * The given resolution can be null if given output does not support it.
     * @return Available resolution to draw into
     */
    public Resolution getResolution();

}
