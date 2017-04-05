package cz.muni.tron.events;


public interface EventNotifier {
    
    public void subscribe(EventSubscriber subscriber);
    
}
