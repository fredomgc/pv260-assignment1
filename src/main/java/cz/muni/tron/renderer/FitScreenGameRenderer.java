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
        new Renderer(frame, output).render();
    }

    private int round(double number) {
        return (int) Math.round(number);
    }

    private class Renderer {

        private final GameFrame frame;
        private final Output output;
        private final ScaleCalculator scale;
        private final OffsetCalculator offset;
        private double positionX;
        private double positionY;

        public Renderer(GameFrame frame, Output output) {
            this.frame = frame;
            this.output = output;
            scale = new ScaleCalculator(frame.getResolution(), output.getResolution());
            offset = new OffsetCalculator(scale.getScaledResolution(), output.getResolution(), HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
        }

        public void render() {
            preparePositionY();
            renderRows();
        }

        private void renderRows() {
            for (int row = 0; row < frame.getResolution().getHeight(); row++) {
                renderRow(row);
                movePositionY();
            }
        }

        private void renderRow(int row) {
            preparePositionX();
            for (int column = 0; column < frame.getResolution().getWidth(); column++) {
                renderSquare(row, column);
                movePositionX();
            }
        }

        private void renderSquare(int row, int column) {
            output.drawRectangle(
                    frame.getPoint(new Position(row, column)),
                    round(positionX),
                    round(positionY),
                    scale.getBlockSize(),
                    scale.getBlockSize());
        }

        private void preparePositionY() {
            positionY = offset.getVerticalOffset();
        }

        private void preparePositionX() {
            positionX = offset.getHorizontalOffset();
        }

        private void movePositionY() {
            positionY += scale.getScale();
        }

        private void movePositionX() {
            positionX += scale.getScale();
        }

    }

    private class ScaleCalculator {

        private final double scale;
        private final Resolution scaledResolution;
        private final int blockSize;

        public ScaleCalculator(Resolution inputResolution, Resolution outputResolution) {
            scale = getScale(inputResolution, outputResolution);
            scaledResolution = scaleResolution(inputResolution);
            blockSize = calculateBlockSize();
        }

        public double getScale() {
            return scale;
        }

        public Resolution getScaledResolution() {
            return scaledResolution;
        }

        public int getBlockSize() {
            return blockSize;
        }

        private double getAxisScale(int inputSize, int outputSize) {
            return (double) outputSize / inputSize;
        }

        private double getScale(Resolution inputResolution, Resolution outputResolution) {
            return getScale(
                    getAxisScale(inputResolution.getWidth(), outputResolution.getWidth()),
                    getAxisScale(inputResolution.getHeight(), outputResolution.getHeight()));
        }

        private double getScale(double horizontalScale, double verticalScale) {
            return Math.min(horizontalScale, verticalScale);
        }

        private Resolution scaleResolution(Resolution resolution) {
            return new Resolution(scaleAxis(resolution.getWidth()), scaleAxis(resolution.getHeight()));
        }

        private int scaleAxis(int axis) {
            return round(axis * scale);
        }

        private int calculateBlockSize() {
            return (int) Math.ceil(scale);
        }

    }

}
