package cz.muni.tron.output;

import cz.muni.tron.engine.Output;
import java.awt.Color;

public class NullOutput implements Output {

    @Override
    public void drawRectangle(Color color, int x, int y, int width, int height) {
        // we ignore everything
    }

}
