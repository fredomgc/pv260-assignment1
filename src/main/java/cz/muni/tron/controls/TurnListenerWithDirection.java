
package cz.muni.tron.controls;

import cz.muni.tron.Direction;

public interface TurnListenerWithDirection extends TurnListener {
    
    public Direction getDirection();
}
