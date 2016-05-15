package net.kiberion.swampmachine.assets.loaders.api;

public interface Prioritizable extends Comparable<Prioritizable>{

    /**
     * 
     * @return priority of this entity. Lower value means entity has higher priority
     */
    default public int getPriority () {
        return 100;
    }
    
    @Override
    default int compareTo(Prioritizable o) {
        if (o == null) {
            return 1;
        }
        return Integer.compare(getPriority(), o.getPriority());
    }    
}
