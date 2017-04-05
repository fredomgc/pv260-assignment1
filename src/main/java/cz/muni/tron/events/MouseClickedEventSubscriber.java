package cz.muni.tron.events;

import java.util.Arrays;
import java.util.List;

public interface MouseClickedEventSubscriber extends EventSubscriber {

    public void mouseClicked(MouseClickedEvent event);

    @Override
    public default void eventOccured(Event event) {
        if (event instanceof MouseClickedEvent) {
            mouseClicked((MouseClickedEvent)event);
        }
    }

    @Override
    public default List<Class<? extends Event>> getSubscribedEvents() {
        return Arrays.asList(MouseClickedEvent.class);
    }
    
}
