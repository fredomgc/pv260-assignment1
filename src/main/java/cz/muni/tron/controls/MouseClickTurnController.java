package cz.muni.tron.controls;

import cz.muni.tron.Turn;
import cz.muni.tron.events.EventNotifier;
import cz.muni.tron.events.MouseButton;
import cz.muni.tron.events.MouseClickedEvent;
import cz.muni.tron.events.MouseClickedEventSubscriber;

public class MouseClickTurnController implements MouseClickedEventSubscriber {
    private final TurnListener listener;
    private final MouseButton turnButton;
    private final Turn turn;

    public MouseClickTurnController(TurnListener listener, MouseButton turnButton, Turn turn) {
        this.listener = listener;
        this.turnButton = turnButton;
        this.turn = turn;
    }

    @Override
    public void mouseClicked(MouseClickedEvent event) {
        if (event.getButton() == turnButton) {
            listener.turn(turn);
        }
    }

    @Override
    public void subscribe(EventNotifier notifier) {
        notifier.subscribeMouseClicked(this);
    }
    
    
}
