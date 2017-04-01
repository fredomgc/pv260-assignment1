package cz.muni.tron.events;


public interface EventSubscriber {
    
    public void subscribe(EventNotifier notifier);
    
}
