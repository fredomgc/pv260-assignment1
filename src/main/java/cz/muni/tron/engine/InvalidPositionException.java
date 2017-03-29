package cz.muni.tron.engine;

public class InvalidPositionException extends IllegalArgumentException {

    public InvalidPositionException() {
    }

    public InvalidPositionException(String s) {
        super(s);
    }

}
