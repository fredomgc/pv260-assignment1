package cz.muni.tron.renderer;

import cz.muni.tron.Position;
import cz.muni.tron.engine.GameFrame;
import cz.muni.tron.engine.GameRenderer;
import cz.muni.tron.engine.Output;

/**
 * Renderer, that scales GameFrame to fit the screen
 *
 * @author Dominik Veselý <Dominik.Vesely@ysoft.com>
 * @author Ondřej Směták <posta@ondrejsmetak.cz>
 */
public class FitScreenGameRenderer implements GameRenderer {
    
    @Override
    public void render(GameFrame frame, Output output) {
        FitScreenGameRendererSetting setting = createSetting(frame, output);
        
        for (int row = 0; row < frame.getHeight(); row++) {
            for (int column = 0; column < frame.getWidth(); column++) {
                doDrawRectangle(frame, output, row, column, setting);
            }
        }
    }
    
    private void doDrawRectangle(GameFrame frame, Output output, int row, int column, FitScreenGameRendererSetting setting) {
        output.drawRectangle(
                frame.getPoint(new Position(row, column)),
                getDrawPosition(column, setting.getHorizontalOffset(), setting.getScale()),
                getDrawPosition(row, setting.getVerticalOffset(), setting.getScale()),
                setting.getBlockSize(),
                setting.getBlockSize());
    }
    
    private double getAxisScale(int outputSize, int frameSize) {
        return (double) outputSize / frameSize;
    }
    
    private double getScale(double verticalScale, double horizontalScale) {
        return Math.min(verticalScale, horizontalScale);
    }
    
    private int getAxisOffset(int outputSize, int frameSize, double scale) {
        return (int) ((outputSize - frameSize * scale) / 2);
    }
    
    private int getDrawPosition(int position, int offset, double scale) {
        return offset + (int) Math.round(position * scale);
    }
    
    private int getRectangleSize(double scale) {
        return (int) Math.round(Math.max(scale, 1));
    }
    
    private FitScreenGameRendererSetting createSetting(GameFrame frame, Output output) {
        FitScreenGameRendererSetting s = new FitScreenGameRendererSetting();
        
        s.setHorizontalScale(getAxisScale(output.getWidth(), frame.getWidth()));
        s.setVerticalScale(getAxisScale(output.getHeight(), frame.getWidth()));
        s.setScale(getScale(s.getVerticalScale(), s.getHorizontalScale()));
        
        s.setHorizontalOffset(getAxisOffset(output.getWidth(), frame.getWidth(), s.getScale()));
        s.setVerticalScale(getAxisOffset(output.getHeight(), frame.getHeight(), s.getScale()));
        s.setBlockSize(getRectangleSize(s.getScale()));
        
        return s;
    }
}
