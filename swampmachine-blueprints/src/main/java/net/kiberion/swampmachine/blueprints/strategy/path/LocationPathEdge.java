package net.kiberion.swampmachine.blueprints.strategy.path;

import org.jgrapht.graph.DefaultWeightedEdge;

public class LocationPathEdge<T> extends DefaultWeightedEdge {

    /**
     * 
     */
    private static final long serialVersionUID = 7315893279057171292L;

    @SuppressWarnings("unchecked")
    @Override
    public T getSource() {
        return (T) super.getSource();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getTarget() {
        return (T) super.getTarget();
    }
    
    @Override
    public double getWeight() {
        return super.getWeight();
    }

}
