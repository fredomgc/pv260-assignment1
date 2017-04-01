package cz.muni.tron.events;

public class MouseClickedEventImpl implements MouseClickedEvent {

    private final MouseButton button;

    public MouseClickedEventImpl(MouseButton button) {
        this.button = button;
    }

    @Override
    public MouseButton getButton() {
        return button;
    }

}
