package net.kiberion.swampmachine.entities.spatial.impl;

import org.apache.commons.math3.util.Precision;

import net.kiberion.swampmachine.entities.spatial.api.Position;

public class CommonPosition implements Position {

    private float x;
    private float y;

    public CommonPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    public CommonPosition() {
        this(0, 0);
    }

    public CommonPosition(Position position) {
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
    public CommonPosition setY(float y) {
        this.y = y;
        return this;
    }

    @Override
    public float getX() {
        return x;
    } 

    @Override
    public CommonPosition setX(float x) {
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
    public CommonPosition produceCloneWithAppliedDelta(float x, float y) {
        return new CommonPosition(this.getX() + x, this.getY() + y);
    }
    
    @Override
    public CommonPosition produceCloneWithAppliedDelta(Position delta) {
        return new CommonPosition(this.getX() + delta.getX(), this.getY() + delta.getY());
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
        return new CommonPosition (getIntX(), getIntY());
    }

    @Override
    public Position invertY(int mapHeight) {
        return new CommonPosition (getX(), mapHeight - (int) getY());
    }
}
