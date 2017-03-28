package cz.muni.tron.engine;

/**
 * Represents a type of GameRenderer which convers a GameFrames and renders them
 * to some Output. GameRenderer copes with problems of scaling GameFrame to the
 * Output, perceiving aspect ratio, etc.
 */
public interface GameRenderer {

    /**
     * Performs a transformation of GameFrame to the desired Ouput
     *
     * @param frame Input frame to be rendered
     * @param output Output device to render frame to
     */
    public void render(GameFrame frame, Output output);
}
