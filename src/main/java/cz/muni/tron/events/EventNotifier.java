package cz.muni.tron.events;


public interface EventNotifier {
    
    public void subscribeKeyPressed(KeyPressedEventSubscriber subscriber);
    
    public void subscribeMouseClicked(MouseClickedEventSubscriber subscriber);
}
