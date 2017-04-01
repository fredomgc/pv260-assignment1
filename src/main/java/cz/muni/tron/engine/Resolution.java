package cz.muni.tron.engine;

import cz.muni.tron.Position;
import java.util.Iterator;

public class Resolution implements Iterable<Position> {

    private final int width;
    private final int height;

    public Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public Iterator<Position> iterator() {
        return new PositionIterator();
    }

    @Override
    public String toString() {
        return width + "x" + height;
    }
    
    private class PositionIterator implements Iterator<Position> {
        private int widthIndex = 0;
        private int heightIndex = 0;
        @Override
        public boolean hasNext() {
            return heightIndex < height;
        }

        @Override
        public Position next() {
            Position position = new Position(widthIndex, heightIndex);
            moveNext();
            return position;
        }
        
        private void moveNext() {
            widthIndex++;
            if (widthIndex >= width) {
                heightIndex++;
                widthIndex = 0;
            }
        }
        
    }

}
