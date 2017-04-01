package cz.muni.tron.controls;

import cz.muni.tron.events.EventNotifier;
import cz.muni.tron.events.KeyPressedEvent;
import cz.muni.tron.events.KeyPressedEventSubscriber;


public class KeyArrowsTurnController implements KeyPressedEventSubscriber {

    private final TurnListenerWithDirection listener;
    private final SingleKeyDirectionController keyUp;
    private final SingleKeyDirectionController keyDown;
    private final SingleKeyDirectionController keyLeft;
    private final SingleKeyDirectionController keyRight;

    public KeyArrowsTurnController(TurnListenerWithDirection listener, int keyUp, int keyDown, int keyLeft, int keyRight) {
        this.listener = listener;
        this.keyUp = new SingleKeyDirectionController(new DirectionTurnTransformer(), keyUp, Direction.UP);
        this.keyDown = new SingleKeyDirectionController(new DirectionTurnTransformer(), keyDown, Direction.DOWN);
        this.keyLeft = new SingleKeyDirectionController(new DirectionTurnTransformer(), keyLeft, Direction.LEFT);
        this.keyRight = new SingleKeyDirectionController(new DirectionTurnTransformer(), keyRight, Direction.RIGHT);
    }
    
    @Override
    public void keyPresed(KeyPressedEvent event) {
        keyUp.keyPresed(event);
        keyDown.keyPresed(event);
        keyLeft.keyPresed(event);
        keyRight.keyPresed(event);
    }

    @Override
    public void subscribe(EventNotifier notifier) {
        notifier.subscribeKeyPressed(this);
    }
    
    private class DirectionTurnTransformer implements DirectionListener {

        @Override
        public void directionUpdate(Direction newDirection) {
            Turn turn = listener.getDirection().toTurn(newDirection);
            if (turn != null) {
                listener.turn(turn);
            }
        }

    }
    
}
