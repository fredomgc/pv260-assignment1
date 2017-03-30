package cz.muni.tron.output;

import cz.muni.tron.engine.Output;
import java.awt.Color;

public class NullOutput implements Output {

    private static final int SIZE = 1;
    
    @Override
    public void drawRectangle(Color color, int x, int y, int width, int height) {
        // we ignore everything
    }

    @Override
    public int getWidth() {
        return SIZE;
    }

    @Override
    public int getHeight() {
        return SIZE;
    }

    @Override
    public void initialize() {
        //not needed
    }

    @Override
    public void dispose() {
        //not needed
    }

}
