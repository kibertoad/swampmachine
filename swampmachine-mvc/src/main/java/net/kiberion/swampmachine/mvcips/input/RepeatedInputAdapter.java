package net.kiberion.swampmachine.mvcips.input;

import com.badlogic.gdx.Input;

import net.kiberion.swampmachine.api.common.RealtimeUpdatable;


public class RepeatedInputAdapter extends GenericInputAdapter implements RealtimeUpdatable{
    
    public float repeatDelay = 0.1f;
    private float timeLeftTillRepeat;

    private boolean isPressedUp;
    private boolean isPressedLeft;
    private boolean isPressedRight;
    private boolean isPressedDown;

    public boolean isSleeping;
    
    public boolean isPressedUp() {
        return isPressedUp && !waitsForRepeat;
    }

    public boolean isPressedLeft() {
        return isPressedLeft && !waitsForRepeat;
    }

    public boolean isPressedRight() {
        return isPressedRight && !waitsForRepeat;
    }

    public boolean isPressedDown() {
        return isPressedDown && !waitsForRepeat;
    }

    public boolean waitsForRepeat;
    

    /*
    Animation for last action has ended, you can move to the next one now
     */
    public void wake() {
        isSleeping = false;
    }
    
    /*
    Wait till animation is finished
     */
    public void sleep() {
        isSleeping = true;
    }    
    
    public void update(float delta) {
        if (waitsForRepeat) {
            timeLeftTillRepeat -= delta;

            if ((timeLeftTillRepeat) <= 0) {
                waitsForRepeat = false;
                timeLeftTillRepeat = repeatDelay + timeLeftTillRepeat;
            }
        }
    }
    
    /*
    Button press was consumed, wait till it can be processed again
     */
    public void waitForRepeat() {
        if (timeLeftTillRepeat <= 0) {
            timeLeftTillRepeat += repeatDelay;
        }
        waitsForRepeat = true;
    }    
    
    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.DPAD_LEFT) {
            isPressedLeft = false;
            return true;
        }

        if (keycode == Input.Keys.DPAD_RIGHT) {
            isPressedRight = false;
            return true;
        }

        if (keycode == Input.Keys.DPAD_UP) {
            isPressedUp = false;
            return true;
        }

        if (keycode == Input.Keys.DPAD_DOWN) {
            isPressedDown = false;
            return true;
        }

        return false;
    }
    
    @Override
    public boolean keyDown(int keycode) {
        /*
        Note that you have to process isPressed* conditions by game controller inside
        update(float delta) loop
        and then call waitForRepeat()
         */

        if (keycode == Input.Keys.DPAD_LEFT) {
            isPressedLeft = true;
            return true;
        }

        if (keycode == Input.Keys.DPAD_RIGHT) {
            isPressedRight = true;
            return true;
        }

        if (keycode == Input.Keys.DPAD_UP) {
            isPressedUp = true;
            return true;
        }

        if (keycode == Input.Keys.DPAD_DOWN) {
            isPressedDown = true;
            return true;
        }

        return super.keyDown(keycode);
    }    

}
