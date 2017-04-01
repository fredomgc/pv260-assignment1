package cz.muni.tron.controls;

import cz.muni.tron.Direction;
import cz.muni.tron.events.EventNotifier;
import cz.muni.tron.events.KeyPressedEvent;
import cz.muni.tron.events.KeyPressedEventSubscriber;


public class SingleKeyDirectionController implements KeyPressedEventSubscriber {
    
    private final DirectionListener listener;
    private final int key;
    private final Direction direction;

    public SingleKeyDirectionController(DirectionListener listener, int key, Direction direction) {
        this.listener = listener;
        this.key = key;
        this.direction = direction;
    }

    @Override
    public void keyPresed(KeyPressedEvent event) {
        if (event.getKeyCode() == key) {
            listener.directionUpdate(direction);
        }
    }

    @Override
    public void subscribe(EventNotifier notifier) {
        notifier.subscribeKeyPressed(this);
    }
    
}
