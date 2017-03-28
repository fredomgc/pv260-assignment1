/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.tron;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author Doumr
 */
public class Player {

	private Direction currentDirection;
	private final Color color;
	private int keyUp;
	private int keyDown;
	private int keyLeft;
	private int keyRight;

	private final List<Position> path = new ArrayList();

	public Player(Position position, Direction currentDirection, Color color, int keyUp, int keyDown, int keyLeft, int keyRight) {
		this.currentDirection = currentDirection;
		this.color = color;
		this.keyUp = keyUp;
		this.keyDown = keyDown;
		this.keyLeft = keyLeft;
		this.keyRight = keyRight;
		this.path.add(position);
	}

	public Position getPosition() {
		return path.get(path.size() - 1);
	}

	public Color getColor() {
		return color;
	}

	public List<Position> getPath() {
		return path;
	}

	public void move(Grid inGrid) {
		path.add(
				inGrid.move(getPosition(), currentDirection)
		);
	}

	public void changeDirection(KeyEvent e) {
		if (e.getKeyCode() == keyUp) {
			if (!currentDirection.isOpositeTo(Direction.UP)) {
				currentDirection = Direction.UP;
			}
		} else if (e.getKeyCode() == keyDown) {
			if (!currentDirection.isOpositeTo(Direction.DOWN)) {
				currentDirection = Direction.DOWN;
			}
		} else if (e.getKeyCode() == keyRight) {
			if (!currentDirection.isOpositeTo(Direction.RIGHT)) {
				currentDirection = Direction.RIGHT;
			}
		} else if (e.getKeyCode() == keyLeft) {
			if (!currentDirection.isOpositeTo(Direction.LEFT)) {
				currentDirection = Direction.LEFT;
			}
		}
	}

	private Direction doChangeDirection(MouseEvent e, Direction current) {
		if (SwingUtilities.isLeftMouseButton(e) || SwingUtilities.isRightMouseButton(e)) {
			boolean left = SwingUtilities.isLeftMouseButton(e);

			switch (currentDirection) {
				case LEFT:
					return left ? Direction.DOWN : Direction.UP;
				case UP:
					return left ? Direction.LEFT : Direction.RIGHT;
				case RIGHT:
					return left ? Direction.UP : Direction.DOWN;
				case DOWN:
					return left ? Direction.RIGHT : Direction.LEFT;
				default:
					break;
			}
		}

		return current;
	}

	public void changeDirection(MouseEvent e) {
		currentDirection = doChangeDirection(e, currentDirection);
	}
}
