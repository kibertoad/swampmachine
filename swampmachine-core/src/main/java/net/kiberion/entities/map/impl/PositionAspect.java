package net.kiberion.entities.map.impl;

import org.apache.commons.math3.util.Precision;

import net.kiberion.entities.map.api.Position;

public class PositionAspect implements Position {

    private float x;
    private float y;

    public PositionAspect(float x, float y) {
        setX(x);
        setY(y);
    }

    public PositionAspect() {
        this(0, 0);
    }

    public PositionAspect(Position position) {
        copyFrom (position);
    }
    
    public void copyFrom(Position position) {
        setX(position.getX());
        setY(position.getY());
    }
    

    @Override
    public float getY() {
        return y;
    }

    @Override
    public PositionAspect setY(float y) {
        this.y = y;
        return this;
    }

    @Override
    public float getX() {
        return x;
    } 

    @Override
    public PositionAspect setX(float x) {
        this.x = x;
        return this;
    }

    @Override
    public void applyDelta(float x, float y) {
        this.x += x;
        this.y += y;
    }
    
    public void applyDelta(Position position) {
        applyDelta (position.getX(), position.getY());
    }
    
 
    @Override
    public PositionAspect produceCloneWithAppliedDelta(float x, float y) {
        return new PositionAspect(this.getX() + x, this.getY() + y);
    }
    
    @Override
    public PositionAspect produceCloneWithAppliedDelta(Position delta) {
        return new PositionAspect(this.getX() + delta.getX(), this.getY() + delta.getY());
    }
    

    @Override
    public String toString() {
        return String.format("%.2f/%.2f", getX(), getY());
    }

    @Override
    public int getIntX() {
        return (int) x;
    }

    @Override
    public int getIntY() {
        return (int) y;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Position) {
            Position objP = (Position) obj;
            return (Precision.equals(this.getX(), objP.getX(), 0.1f) && Precision.equals(this.getY(), objP.getY(), 0.1f));
        }

        return super.equals(obj);
    }
    
    @Override
    public int hashCode() {
      return (int) ((this.getX() * 100000) + (this.getY())); 
    }

    @Override
    public float distanceTo (Position other) {
        float x1 = this.x;
        float y1 = this.y;
        float x2 = other.getX();
        float y2 = other.getY();

        return Math.round(Math.sqrt(Math.pow((x2 - x1), 2) +  Math.pow((y2 - y1), 2)));
    }

    public Position produceCloneWithTrimmedValues() {
        return new PositionAspect (getIntX(), getIntY());
    }

    @Override
    public Position invertY(int mapHeight) {
        return new PositionAspect (getX(), mapHeight - (int) getY());
    }
}
