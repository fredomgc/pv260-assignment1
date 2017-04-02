package cz.muni.tron.output;

import java.awt.Graphics2D;

public class EagerScreenControl extends ScreenControl {

    @Override
    public Graphics2D getGraphics() {
        return (Graphics2D) getFrame().getGraphics();
    }

    @Override
    public void update() {
        //not needed
    }

}
