package net.kiberion.mvc;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.mvc.api.StateView;

public class StateViewBase<T> extends Container<Actor> implements PostInjectionProcessed, StateView{

    @Getter
    @Setter
    private Stage stage;

    @Getter
    @Setter
    @Autowired
    private T model;
    
    public void show() {
    	setVisible(true);
    }
    
    public void hide() {
    	setVisible(false);
    }

    @Override
    public void act(float delta) {
    	/*
        for (PyramideAnimation animation : animationList) {
            animation.act(delta);

            if (animation.isCompleted() && (!animation.isLooped())) {
                animationGarbageList.add(animation);
            }
        }

        while (!animationGarbageList.isEmpty()) {
            animationList.remove(animationGarbageList.poll());
        }

        while (!animationAdditionList.isEmpty()) {
            animationList.remove(animationAdditionList.poll());
        }
        */

    }

	@Override
	public void postInjection() {
	}

    /*
    public void addAnimation(PyramideAnimation animation) {
        animationList.add(animation);
    }

    public void addAnimationSafe(PyramideAnimation animation) {
        animationAdditionList.add(animation);
    }
    */	
}
