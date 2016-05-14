/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.swampmachine.entities.common.impl;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author kibertoad
 */
public class Interval {

    public int from;
    public int to;
    public int id;
    public int count; // position in a list

    public Interval(int setFrom, int setTo, int setID) {
        from = setFrom;
        to = setTo;
        id = setID;
    }

    public Interval(int setFrom, int setTo) {
        from = setFrom;
        to = setTo;
        id = -1;
    }

    public Interval(int setFrom, int setTo, int setID, int setCount) {
        from = setFrom;
        to = setTo;
        id = setID;
        count = setCount;
    }

    public boolean fits(int getID) {
        return ((getID >= from) && (getID <= to));
    }

    public static Interval returnFittingInterval(List<Interval> fromList, int inValue) {
        Interval nowInterval;
        Interval result = null;

        Iterator<Interval> i = fromList.iterator();

        while ((i.hasNext()) && (result == null)) {
            nowInterval = i.next();

            if (nowInterval.fits(inValue)) {
                result = nowInterval;
            }
        }

        return result;
    }

}
