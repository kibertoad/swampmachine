/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.swampmachine.entities.common.impl;

/**
 *
 * @author kibertoad
 */

public class Int {

    private int value;

    public int getIntValue() {
		return value;
	}

	public void setIntValue(int value) {
		this.value = value;
	}

	public Int (int setValue) {
        value = setValue;
    }

    @Override
    public String toString (){
        return Integer.toString(value);
    }

    public void decrease (int byValue) {
        value = value - byValue;
        if (value < 0) {value = 0;}
    }
    
    public void increase (Int byValue) {
        value = value + byValue.value;
    }

    public void increase (int byValue) {
        value = value + byValue;
    }
    

    public int getPercentFrom (Int fullValue) {
        if (!(this.value == 0)) {
        return (fullValue.value * this.value / 100);
        } else {
            return 0;
        }
    }

    public int getPercent (int percent) {
        return value * percent / 100;
    }

    public void decrease(Int byValue) {
        value = value - byValue.value;
    }

    public void plus(int byValue) {
        increase(byValue);
    }

    public boolean percentBetween(Int fullValue, int from, int to) {
        return ((getPercentFrom(fullValue) >= from) && (getPercentFrom(fullValue) <= to));
    }
}
