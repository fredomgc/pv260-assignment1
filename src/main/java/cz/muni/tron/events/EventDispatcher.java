package cz.muni.tron.events;

import java.util.ArrayList;
import java.util.List;

public class EventDispatcher implements EventListener, EventNotifier {

    private final KeyPressEventHandler keyPressEventHandler = new KeyPressEventHandler();
    private final MouseClickEventHandler mouseClickEventHandler = new MouseClickEventHandler();
    private final GameTickEventHandler gameTickEventHandler = new GameTickEventHandler();

    @Override
    public void keyPressed(KeyPressedEvent event) {
        keyPressEventHandler.notifyKeyPressed(event);
    }

    @Override
    public void mouseClicked(MouseClickedEvent event) {
        mouseClickEventHandler.notifyMouseClicked(event);
    }

    @Override
    public void gameTick() {
        gameTickEventHandler.notifyGameTick();
    }

    @Override
    public void subscribeKeyPressed(KeyPressedEventSubscriber subscriber) {
        keyPressEventHandler.addSubscriber(subscriber);
    }

    @Override
    public void subscribeMouseClicked(MouseClickedEventSubscriber subscriber) {
        mouseClickEventHandler.addSubscriber(subscriber);
    }

    @Override
    public void subscribeGameTick(GameTickEventSubscriber subscriber) {
        gameTickEventHandler.addSubscriber(subscriber);
    }

    private abstract class EventHandler<T> {

        private final List<T> subscribers = new ArrayList<>();

        public void addSubscriber(T subscriber) {
            subscribers.add(subscriber);
        }

        protected List<T> getSubscribers() {
            return subscribers;
        }

    }

    private class KeyPressEventHandler extends EventHandler<KeyPressedEventSubscriber> {

        public void notifyKeyPressed(KeyPressedEvent event) {
            getSubscribers().stream().forEach(subscriber -> subscriber.keyPresed(event));
        }

    }

    private class MouseClickEventHandler extends EventHandler<MouseClickedEventSubscriber> {

        public void notifyMouseClicked(MouseClickedEvent event) {
            getSubscribers().stream().forEach(subscriber -> subscriber.mouseClicked(event));
        }

    }

    private class GameTickEventHandler extends EventHandler<GameTickEventSubscriber> {

        public void notifyGameTick() {
            getSubscribers().stream().forEach(subscriber -> subscriber.gameTick());
        }

    }

}
