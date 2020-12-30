package devices;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*  Make it more object orientated
    https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
    Boolean array of size 256, which will be used to store the state of the keys on the keyboard.
 */

public class KeyboardControl implements KeyListener {

    private boolean[] keyStatus;

    public KeyboardControl(){
        keyStatus = new boolean[256];
    }

    public boolean getKeyStatus(int keyCode){
        if(keyCode < 0 || keyCode > 255)
        {
            return false;
        } else {
            return keyStatus[keyCode];
        }
    }

    public void resetController(){
        keyStatus = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keyStatus[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keyStatus[keyEvent.getKeyCode()] = false;
    }
}
