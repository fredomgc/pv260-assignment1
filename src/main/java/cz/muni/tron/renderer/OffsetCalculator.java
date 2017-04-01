package cz.muni.tron.renderer;

import cz.muni.tron.engine.Resolution;

public class OffsetCalculator {

    private final int horizontalOffset;
    private final int verticalOffset;

    public OffsetCalculator(Resolution inputResolution, Resolution outputResolution, HorizontalAlignment horizontalAlign, VerticalAlignment verticalAlign) {
        horizontalOffset = calculateHorizontalOffset(inputResolution, outputResolution, horizontalAlign);
        verticalOffset = calculateVerticalOffset(inputResolution, outputResolution, verticalAlign);
    }

    public int getHorizontalOffset() {
        return horizontalOffset;
    }

    public int getVerticalOffset() {
        return verticalOffset;
    }

    private int calculateHorizontalOffset(Resolution input, Resolution output, HorizontalAlignment align) {
        switch (align) {
            case LEFT:
                return 0;
            case CENTER:
                return getSpaceLeft(input.getWidth(), output.getWidth()) / 2;
            case RIGHT:
                return getSpaceLeft(input.getWidth(), output.getWidth());
            default:
                return 0;
        }
    }

    private int calculateVerticalOffset(Resolution input, Resolution output, VerticalAlignment align) {
        switch (align) {
            case TOP:
                return 0;
            case CENTER:
                return getSpaceLeft(input.getHeight(), output.getHeight()) / 2;
            case BOTTOM:
                return getSpaceLeft(input.getHeight(), output.getHeight());
            default:
                return 0;
        }
    }

    private int getSpaceLeft(int inputSpace, int outputSpace) {
        return outputSpace - inputSpace;
    }

}
