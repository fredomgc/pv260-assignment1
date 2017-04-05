package cz.muni.tron.events;

import java.util.List;


public interface EventSubscriber {
    
    public void eventOccured(Event event);
    
    public List<Class<? extends Event>> getSubscribedEvents();
    
}
