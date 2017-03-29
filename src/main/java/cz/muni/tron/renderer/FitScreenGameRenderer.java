package cz.muni.tron.renderer;

import cz.muni.tron.Position;
import cz.muni.tron.engine.GameFrame;
import cz.muni.tron.engine.GameRenderer;
import cz.muni.tron.engine.Output;


public class FitScreenGameRenderer implements GameRenderer {

    @Override
    public void render(GameFrame frame, Output output) {
        double horizontalScale = getAxisScale(output.getWidth(), frame.getWidth());
        double verticalScale = getAxisScale(output.getHeight(), frame.getWidth());
        double scale = getScale(verticalScale, horizontalScale);
        int horizontalOffset = getAxisOffset(output.getWidth(), frame.getWidth(), scale);
        int verticalOffset = getAxisOffset(output.getHeight(), frame.getHeight(), scale);
        int blockSize = getRectangleSize(scale);
        for (int row = 0; row < frame.getHeight(); row++) {
            for (int column = 0; column < frame.getWidth(); column++) {
                output.drawRectangle(
                        frame.getPoint(new Position(row, column)),
                        getDrawPosition(column, horizontalOffset, scale),
                        getDrawPosition(row, verticalOffset, scale),
                        blockSize,
                        blockSize);
            }
        }
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
    
}
