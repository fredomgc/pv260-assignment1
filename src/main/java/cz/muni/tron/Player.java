/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.tron;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Doumr
 */
public class Player {

    private int currentDirection1;
    private int keyUp;
    private int keyDown;
    private int keyLeft;
    private int keyRight;

    private final List<Position> path = new ArrayList();
    
    public Player(Position position, int currentDirection1, int keyUp, int keyDown, int keyLeft, int keyRight) {
        this.currentDirection1 = currentDirection1;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
        this.path.add(position);
    }

    public Position getPosition() {
        return path.get(path.size() - 1);
    }
    
    public List<Position> getPath() {
        return path;
    }
    
    public void move(int moveAmount, int width, int height) {
        Position newPosition;
        int currentRow = getPosition().getRow();
        int currentColumn = getPosition().getColumn();
        switch (currentDirection1) {
            case 0:
                if (currentRow > 0) {
                    newPosition = new Position(currentRow - moveAmount, currentColumn);
                } else {
                    newPosition = new Position(height, currentColumn);
                }
                break;
            case 1:
                if (currentColumn < width) {
                    newPosition = new Position(currentRow, currentColumn + moveAmount);
                } else {
                    newPosition = new Position(currentRow, 0);
                }
                break;
            case 2:
                if (currentRow < height) {
                    newPosition = new Position(currentRow + moveAmount, currentColumn);
                } else {
                    newPosition = new Position(0, currentColumn);
                }
                break;
            case 3:
                if (currentColumn > 0) {
                    newPosition = new Position(currentRow, currentColumn - moveAmount);
                } else {
                    newPosition = new Position(currentRow, width);
                }
                break;
            default:
                newPosition = new Position(currentRow, currentColumn);
        }
        path.add(newPosition);
    }
    
    public void changeDirection(KeyEvent e) {
        if (e.getKeyCode() == keyUp) {
            if (currentDirection1 != 2) {
                currentDirection1 = 0;
            }
        } else if (e.getKeyCode() == keyDown) {
            if (currentDirection1 != 0) {
                currentDirection1 = 2;
            }
        } else if (e.getKeyCode() == keyRight) {
            if (currentDirection1 != 3) {
                currentDirection1 = 1;
            }
        } else if (e.getKeyCode() == keyLeft) {
            if (currentDirection1 != 1) {
                currentDirection1 = 3;
            }
        }
    }
}
