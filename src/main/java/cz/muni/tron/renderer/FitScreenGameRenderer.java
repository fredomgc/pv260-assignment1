package cz.muni.tron.renderer;

import cz.muni.tron.Position;
import cz.muni.tron.engine.GameFrame;
import cz.muni.tron.engine.GameRenderer;
import cz.muni.tron.engine.Output;
import cz.muni.tron.engine.Resolution;

/**
 * Renderer, that scales GameFrame to fit the screen
 *
 * @author Dominik Veselý <doumr1536@gmail.com>
 * @author Ondřej Směták <posta@ondrejsmetak.cz>
 */
public class FitScreenGameRenderer implements GameRenderer {
    
    @Override
    public void render(GameFrame frame, Output output) {
        FitScreenGameRendererSetting setting = createSetting(frame, output);
        Resolution frameResolution = frame.getResolution();
        for (int row = 0; row < frameResolution.getHeight(); row++) {
            for (int column = 0; column < frameResolution.getWidth(); column++) {
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
        
        s.setHorizontalScale(getAxisScale(output.getResolution().getWidth(), frame.getResolution().getWidth()));
        s.setVerticalScale(getAxisScale(output.getResolution().getHeight(), frame.getResolution().getWidth()));
        s.setScale(getScale(s.getVerticalScale(), s.getHorizontalScale()));
        
        s.setHorizontalOffset(getAxisOffset(output.getResolution().getWidth(), frame.getResolution().getWidth(), s.getScale()));
        s.setVerticalScale(getAxisOffset(output.getResolution().getHeight(), frame.getResolution().getHeight(), s.getScale()));
        s.setBlockSize(getRectangleSize(s.getScale()));
        
        return s;
    }
}
