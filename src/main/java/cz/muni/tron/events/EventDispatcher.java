package cz.muni.tron.events;

import java.util.ArrayList;
import java.util.List;

public class EventDispatcher implements EventListener, EventNotifier {
    
    private final KeyPressEventHandler keyPressEventHandler = new KeyPressEventHandler();
    private final MouseClickEventHandler mouseClickEventHandler = new MouseClickEventHandler();
    
    @Override
    public void keyPressed(KeyPressedEvent event) {
        keyPressEventHandler.notifyKeyPressed(event);
    }
    
    @Override
    public void mouseClicked(MouseClickedEvent event) {
        mouseClickEventHandler.notifyMouseClicked(event);
    }

    @Override
    public void subscribeKeyPressed(KeyPressedEventSubscriber subscriber) {
        keyPressEventHandler.addSubscriber(subscriber);
    }

    @Override
    public void subscribeMouseClicked(MouseClickedEventSubscriber subscriber) {
        mouseClickEventHandler.addSubscriber(subscriber);
    }
    
    
    private class KeyPressEventHandler {
        
        private final List<KeyPressedEventSubscriber> keyPressedSubscribers = new ArrayList<>();
        
        public void addSubscriber(KeyPressedEventSubscriber subscriber) {
            keyPressedSubscribers.add(subscriber);
        }
        
        public void notifyKeyPressed(KeyPressedEvent event) {
            keyPressedSubscribers.stream().forEach(subscriber -> subscriber.keyPresed(event));
        }
        
    }
    
    private class MouseClickEventHandler {
        
        private final List<MouseClickedEventSubscriber> mouseClickSubscribers = new ArrayList<>();
        
        public void addSubscriber(MouseClickedEventSubscriber subscriber) {
            mouseClickSubscribers.add(subscriber);
        }
        
        public void notifyMouseClicked(MouseClickedEvent event) {
            mouseClickSubscribers.stream().forEach(subscriber -> subscriber.mouseClicked(event));
        }
        
    }
    
}
