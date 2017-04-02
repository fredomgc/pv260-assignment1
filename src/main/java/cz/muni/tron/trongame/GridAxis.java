package cz.muni.tron.trongame;

public abstract class GridAxis {

    private final int positionLimit;

    public GridAxis(int positionLimit) {
        this.positionLimit = positionLimit;
    }
    
    public int getPositionLimit() {
        return positionLimit;
    }
    
    public abstract int normalizePosition(int position) throws NomralizationException;
    
    class NomralizationException extends Exception {
        
    }

}
