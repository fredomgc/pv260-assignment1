package cz.muni.tron.renderer;

import cz.muni.tron.Position;
import cz.muni.tron.engine.GameFrame;
import cz.muni.tron.engine.GameRenderer;
import cz.muni.tron.engine.Output;
import cz.muni.tron.engine.Resolution;
import java.awt.Color;

/**
 * @author Ondřej Směták <posta@ondrejsmetak.cz>
 * @author Dominik Veselý <doumr1536@gmail.com>
 */
public class FixedSizeGameRenderer implements GameRenderer {

    private final int blockSize;
    private final HorizontalAlignment horizontalAlignment;
    private final VerticalAlignment verticalAlignment;

    public FixedSizeGameRenderer(int blockSize, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        this.blockSize = blockSize;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
    }

    public FixedSizeGameRenderer(int blockSize) {
        this(blockSize, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
    }

    @Override
    public void render(GameFrame frame, Output output) {
        OffsetCalculator offset = new OffsetCalculator(getScaledResolution(frame.getResolution()), output.getResolution(), horizontalAlignment, verticalAlignment);
        for (Position position : frame.getResolution()) {
            drawPoint(position, frame.getPoint(position), output, offset);
        }
    }

    private Resolution getScaledResolution(Resolution originalResolution) {
        return new Resolution(originalResolution.getWidth() * blockSize, originalResolution.getHeight() * blockSize);
    }

    private void drawPoint(Position position, Color color, Output output, OffsetCalculator offset) {
        output.drawRectangle(
                color,
                calculateRenderPosition(offset.getHorizontalOffset(), position.getColumn()),
                calculateRenderPosition(offset.getVerticalOffset(), position.getRow()),
                blockSize,
                blockSize);
    }

    private int calculateRenderPosition(int offset, int index) {
        return offset + index * blockSize;
    }

}
