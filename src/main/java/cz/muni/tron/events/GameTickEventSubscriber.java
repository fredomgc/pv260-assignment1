package cz.muni.tron.events;

import java.util.Arrays;
import java.util.List;

public interface GameTickEventSubscriber extends EventSubscriber {
    
    public void gameTick(GameTickEvent event);

    @Override
    public default void eventOccured(Event event) {
        if (event instanceof GameTickEvent) {
            gameTick((GameTickEvent)event);
        }
    }

    @Override
    public default List<Class<? extends Event>> getSubscribedEvents() {
        return Arrays.asList(GameTickEvent.class);
    }
    
}
