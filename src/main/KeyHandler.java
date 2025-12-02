package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed,downPressed,leftPressed,rightPressed;

    // On ne vas pas l'utiliser
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        int code  = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                upPressed = true;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                downPressed = true;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {

        int code  = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
        }

    }



}
