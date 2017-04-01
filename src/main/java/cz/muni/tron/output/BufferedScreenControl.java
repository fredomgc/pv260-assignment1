package cz.muni.tron.output;

import java.awt.Graphics2D;

public class BufferedScreenControl extends ScreenControl {

    @Override
    public Graphics2D getGraphics() {
        return (Graphics2D) getFrame().getBufferStrategy().getDrawGraphics();
    }

    @Override
    public void update() {
        getFrame().getBufferStrategy().show();
    }

}
