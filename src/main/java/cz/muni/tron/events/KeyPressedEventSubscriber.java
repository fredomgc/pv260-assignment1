package cz.muni.tron.events;

import java.util.Arrays;
import java.util.List;

public interface KeyPressedEventSubscriber extends EventSubscriber {

    public void keyPresed(KeyPressedEvent event);

    @Override
    public default void eventOccured(Event event) {
        if (event instanceof KeyPressedEvent) {
            keyPresed((KeyPressedEvent)event);
        }
    }

    @Override
    public default List<Class<? extends Event>> getSubscribedEvents() {
        return Arrays.asList(KeyPressedEvent.class);
    }
    
}
