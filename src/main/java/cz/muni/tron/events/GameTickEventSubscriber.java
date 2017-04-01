package cz.muni.tron.events;

public interface GameTickEventSubscriber extends EventSubscriber {
    
    public void gameTick();
    
}
