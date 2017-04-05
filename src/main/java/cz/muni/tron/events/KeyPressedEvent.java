package cz.muni.tron.events;

public interface KeyPressedEvent extends Event {

    /**
     * Returns the integer keyCode associated with the key in this event.
     */
    public int getKeyCode();
}
