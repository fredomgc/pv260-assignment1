package cz.muni.tron.events;

import java.util.ArrayList;
import java.util.List;

public class EventDispatcher implements EventListener, EventNotifier {

    private final List<EventSubscriber> subscribers = new ArrayList<>();

    @Override
    public void eventOccured(Event event) {
        subscribers.stream()
                .filter(subscriber -> subscriber.getSubscribedEvents()
                        .stream()
                        .anyMatch(eventClass -> eventClass.isInstance(event)))
                .forEach(subscriber -> subscriber.eventOccured(event));
    }

    @Override
    public void subscribe(EventSubscriber subscriber) {
        subscribers.add(subscriber);
    }

}
