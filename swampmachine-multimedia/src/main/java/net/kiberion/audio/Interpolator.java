package net.kiberion.audio;

import java.util.function.Consumer;

import com.badlogic.gdx.math.Interpolation;

/**
 * Interpolator.
 * 
 * @author caryoscelus
 * 
 * This is useful not only for audio, so should be moved into more generic place.
 */
public class Interpolator {
    public void update(float dt) {
        progress += dt/duration;
        if (isDone()) {
            makeDone();
        }
        action.accept(interpolation.apply(progress)*change);
    }
    
    public boolean isDone() {
        return progress >= 1.0f;
    }
    
    /**
     * Force finish interpolation
     */
    public void makeDone() {
        progress = 1.0f;
    }
    
    public Interpolator(
            Interpolation interpolation_,
            float duration_,
            float change_,
            Consumer<Float> action_
    ) {
        interpolation = interpolation_;
        duration = duration_;
        change = change_;
        action = action_;
        progress = 0.0f;
    }
    
    private Interpolation interpolation;
    private Consumer<Float> action;
    private float duration;
    private float change;
    private float progress;
}
