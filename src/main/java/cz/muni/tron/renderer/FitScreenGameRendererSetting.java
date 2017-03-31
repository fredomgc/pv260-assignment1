package cz.muni.tron.renderer;

/**
 * Setting for FitScreenGameRenderer
 *
 * @author Dominik Veselý <Dominik.Vesely@ysoft.com>
 * @author Ondřej Směták <posta@ondrejsmetak.cz>
 */
public class FitScreenGameRendererSetting {

    double horizontalScale;
    double verticalScale;
    double scale;

    int horizontalOffset;
    int verticalOffset;
    int blockSize;

    public double getHorizontalScale() {
        return horizontalScale;
    }

    public void setHorizontalScale(double horizontalScale) {
        this.horizontalScale = horizontalScale;
    }

    public double getVerticalScale() {
        return verticalScale;
    }

    public void setVerticalScale(double verticalScale) {
        this.verticalScale = verticalScale;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public int getHorizontalOffset() {
        return horizontalOffset;
    }

    public void setHorizontalOffset(int horizontalOffset) {
        this.horizontalOffset = horizontalOffset;
    }

    public int getVerticalOffset() {
        return verticalOffset;
    }

    public void setVerticalOffset(int verticalOffset) {
        this.verticalOffset = verticalOffset;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }
}
