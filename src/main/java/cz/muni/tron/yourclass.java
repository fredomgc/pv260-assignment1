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

    private Player player1 = new Player(40, 40, 1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
    private Player player2 = new Player(600, 440, 3, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
    
    int moveAmount = 5;

    public void init() {
        super.init();

        Window w = sm.getFullScreenWindow();
        w.addKeyListener(this);
        w.addMouseListener(this);
        w.addMouseMotionListener(this);
    }

    public static void main(String[] args) {
        new yourclass().run();
    }

    public void draw(Graphics2D g) {
        player1.move(moveAmount, sm.getWidth(), sm.getHeight());
        player2.move(moveAmount, sm.getWidth(), sm.getHeight());
        
        for (int x = 0; x < player1.pathx1.size() - 1; x++) {
            if (((player1.centrex1 == player1.pathx1.get(x)) && (player1.centrey1 == player1.pathy1.get(x))) ||
                ((player2.centrex1 == player2.pathx1.get(x)) && (player2.centrey1 == player2.pathy1.get(x))) ||
                ((player1.centrex1 == player2.pathx1.get(x)) && (player1.centrey1 == player2.pathy1.get(x))) ||
                ((player2.centrex1 == player1.pathx1.get(x)) && (player2.centrey1 == player1.pathy1.get(x)))) {
                System.exit(0);
            }
        }
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
        for (int x = 0; x < player1.pathx1.size(); x++) {
            g.setColor(Color.green);
            g.fillRect(player1.pathx1.get(x), player1.pathy1.get(x), 10, 10);
            g.setColor(Color.red);
            g.fillRect(player2.pathx1.get(x), player2.pathy1.get(x), 10, 10);
        }
    }

    public void keyPressed(KeyEvent e) {
        player1.changeDirection(e);
        player2.changeDirection(e);
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
