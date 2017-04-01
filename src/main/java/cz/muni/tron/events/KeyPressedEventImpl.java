package cz.muni.tron.events;

public class KeyPressedEventImpl implements KeyPressedEvent {

    private final int keyCode;

    public KeyPressedEventImpl(int keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public int getKeyCode() {
        return keyCode;
    }

}
