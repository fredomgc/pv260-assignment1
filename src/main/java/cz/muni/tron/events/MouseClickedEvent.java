package cz.muni.tron.events;

public interface MouseClickedEvent extends Event {

    /**
     * Returns which button was clicked
     */
    public MouseButton getButton();
}
