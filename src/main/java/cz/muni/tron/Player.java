/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.tron;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Doumr
 */
public class Player {

    int centrex1;
    int centrey1;
    int currentDirection1;
    int keyUp;
    int keyDown;
    int keyLeft;
    int keyRight;

    ArrayList<Integer> pathx1 = new ArrayList();
    ArrayList<Integer> pathy1 = new ArrayList();
    
    public Player(int centrex1, int centrey1, int currentDirection1, int keyUp, int keyDown, int keyLeft, int keyRight) {
        this.centrex1 = centrex1;
        this.centrey1 = centrey1;
        this.currentDirection1 = currentDirection1;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
    }

    
    
    public void move(int moveAmount, int width, int height) {
        switch (currentDirection1) {
            case 0:
                if (centrey1 > 0) {
                    centrey1 -= moveAmount;
                } else {
                    centrey1 = height;
                }
                break;
            case 1:
                if (centrex1 < width) {
                    centrex1 += moveAmount;
                } else {
                    centrex1 = 0;
                }
                break;
            case 2:
                if (centrey1 < height) {
                    centrey1 += moveAmount;
                } else {
                    centrey1 = 0;
                }
                break;
            case 3:
                if (centrex1 > 0) {
                    centrex1 -= moveAmount;
                } else {
                    centrex1 = width;
                }
                break;
        }
        pathx1.add(centrex1);
        pathy1.add(centrey1);
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
