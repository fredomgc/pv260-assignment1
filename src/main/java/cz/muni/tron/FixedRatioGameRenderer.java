package cz.muni.tron;

import cz.muni.tron.engine.GameFrame;
import cz.muni.tron.engine.GameRenderer;
import cz.muni.tron.engine.Output;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics2D;

/**
 *
 * @author Ondřej Směták <posta@ondrejsmetak.cz>
 */
public class FixedRatioGameRenderer implements GameRenderer {

    @Override
    public void render(GameFrame frame, Output output) {
        for (int row = 0; row < frame.getHeight(); row++) {
            for (int column = 0; column < frame.getWidth(); column++) {
                output.drawRectangle(frame.getPoint(new Position(row, column)), column, row, 1, 1);
            }
        }
    }
}
