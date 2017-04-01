package cz.muni.tron.controls;

import cz.muni.tron.Turn;
import cz.muni.tron.events.EventNotifier;
import cz.muni.tron.events.MouseButton;
import cz.muni.tron.events.MouseClickedEvent;
import cz.muni.tron.events.MouseClickedEventSubscriber;


public class MouseButtonsTurnController implements MouseClickedEventSubscriber {

    private final MouseClickTurnController leftTurner;
    private final MouseClickTurnController rightTurner;

    public MouseButtonsTurnController(TurnListener listener, MouseButton turnLeft, MouseButton turnRight) {
        leftTurner = new MouseClickTurnController(listener, turnLeft, Turn.LEFT);
        rightTurner = new MouseClickTurnController(listener, turnRight, Turn.RIGHT);
    }
    
    public MouseButtonsTurnController(TurnListener listener) {
        this(listener, MouseButton.LEFT, MouseButton.RIGHT);
    }
    
    @Override
    public void mouseClicked(MouseClickedEvent event) {
        leftTurner.mouseClicked(event);
        rightTurner.mouseClicked(event);
    }

    @Override
    public void subscribe(EventNotifier notifier) {
        notifier.subscribeMouseClicked(this);
    }
    
}
