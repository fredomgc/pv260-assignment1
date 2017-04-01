package cz.muni.tron.controls;

import cz.muni.tron.events.EventNotifier;
import cz.muni.tron.events.GameTickEventSubscriber;
import java.util.Random;


public class AiRandomTurnControler implements GameTickEventSubscriber {
    
    private final TurnListener listener;
    private final double volatility;
    private final Random randomGenerator;

    public AiRandomTurnControler(TurnListener listener, double volatility) {
        this.listener = listener;
        this.volatility = cropVolatility(volatility);
        randomGenerator = new Random();
    }
    
    @Override
    public void gameTick() {
        if (shouldTurn()) {
            doTurn();
        }
    }

    @Override
    public void subscribe(EventNotifier notifier) {
        notifier.subscribeGameTick(this);
    }
    
    private double cropVolatility(double volatility) {
        return Math.min(Math.max(volatility, 0d), 1d);
    }
    
    private boolean shouldTurn() {
        return randomGenerator.nextDouble() <= volatility;
    }
    
    private void doTurn() {
        listener.turn(getRandomTurn());
    }
    
    private Turn getRandomTurn() {
        return randomGenerator.nextBoolean()
                ? Turn.LEFT
                : Turn.RIGHT;
    }
    
}
