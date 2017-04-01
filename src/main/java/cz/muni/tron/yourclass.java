package cz.muni.tron;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class yourclass extends Core implements KeyListener, MouseListener,
        MouseMotionListener {

    private Player player1 = new Player(new Position(40 / 5, 40 / 5), Direction.RIGHT, Color.GREEN);
    private Player player2 = new Player(new Position(600 / 5, 440 / 5), Direction.LEFT, Color.RED);
    private Grid gameGrid;
    
    int moveAmount = 5;

    public void init() {
        super.init();

        Window w = sm.getFullScreenWindow();
        w.addKeyListener(this);
        w.addMouseListener(this);
        w.addMouseMotionListener(this);
        
        gameGrid = new SingleStepRotaryGrid(sm.getHeight() / moveAmount, sm.getWidth() / moveAmount);
    }

    public static void main(String[] args) {
        new yourclass().run();
    }

    public void draw(Graphics2D g) {
        player1.move(gameGrid);
        player2.move(gameGrid);
        
        for (int x = 0; x < player1.getPath().size() - 1; x++) {
            if (((player1.getPosition().getColumn() == player1.getPath().get(x).getColumn()) && (player1.getPosition().getRow() == player1.getPath().get(x).getRow())) ||
                ((player2.getPosition().getColumn() == player2.getPath().get(x).getColumn()) && (player2.getPosition().getRow() == player2.getPath().get(x).getRow())) ||
                ((player1.getPosition().getColumn() == player2.getPath().get(x).getColumn()) && (player1.getPosition().getRow() == player2.getPath().get(x).getRow())) ||
                ((player2.getPosition().getColumn() == player1.getPath().get(x).getColumn()) && (player2.getPosition().getRow() == player1.getPath().get(x).getRow()))) {
                System.exit(0);
            }
        }
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
        for (int x = 0; x < player1.getPath().size(); x++) {
            Position player1Position = player1.getPath().get(x);
            Position player2Position = player2.getPath().get(x);
            g.setColor(player1.getColor());
            g.fillRect(player1Position.getColumn() * moveAmount, player1Position.getRow() * moveAmount, 10, 10);
            g.setColor(player2.getColor());
            g.fillRect(player2Position.getColumn() * moveAmount, player2Position.getRow() * moveAmount, 10, 10);
        }
    }

    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent arg0) {

    }

    public void mouseClicked(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }
}
