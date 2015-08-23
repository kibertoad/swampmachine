/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.kiberion.entities.common.impl.Int;
import net.kiberion.entities.common.impl.Interval;


//import transientlibs.processors.combat.RollOutcome;
//import transientlibs.processors.combat.RollOutcomeList;

/**
 * @author kibertoad
 */
public class Dice {

    public List<Interval> rollOptions = new ArrayList<Interval>();
    public int nextIndex = 0;
    public int resultID;
    public int rolledValue;
    public int rolledRandomValue;
    public int maxValue; //max value that can be rolled
    public static final Random rng = new Random();

    public int gaussianRoll(int maxValue) {
        rolledValue = (int) ((rng.nextGaussian() + 2) / 4 * maxValue);
        return rolledValue;
    }

    public void addOption(int intervalSize, int setID) {
        if (intervalSize > 0) {
            rollOptions.add(new Interval(nextIndex, nextIndex + intervalSize, setID));

            nextIndex = nextIndex + intervalSize + 1;
        }
    }

    public void reset() {
        rollOptions.clear();
        nextIndex = 0;
        resultID = -1;
        rolledValue = -1;
    }

    /**
     * Pick a random option from a weighted list
     */
    public void rollDice() {
        rolledValue = rng.nextInt(nextIndex);
        resultID = Interval.returnFittingInterval(rollOptions, rolledValue).id;
    }

    /**
     * Just generate a random value
     */
    public void rollDiceForValue() {
        rolledValue = rng.nextInt(maxValue);
    }

    public static Int getRandomIntBestOfRolls(int min, int max, int dices) {
        int value = 0;
        for (int x = 0; x < dices; x++) {
            int roll = getRandomValue(min, max);

            if (roll > value) {
                value = roll;
            }
        }
        return new Int (value);
    }

    public static Int getRandomIntWorstOfRolls(int min, int max, int dices) {
        int value = max;
        for (int x = 0; x < dices; x++) {
            int roll = getRandomValue(min, max);

            if (roll < value) {
                value = roll;
            }
        }
        return new Int (value);
    }
    
    public static int getSumOfRolls (int amountOfDices, int amountOfSides) {
        int value = 0;
        
        for (int x = 0; x < amountOfDices; x++) {
            value += getRandomValue (1, amountOfSides+1);
        }
        
        return value;
    }


    public static Int getRandomInt(int min, int max) {
        return new Int(getRandomValue(min, max));
    }

    public static int getRandomValue(int min, int max) {
        int roll = rng.nextInt(max - min) + min;

        return roll;
    }

  
}