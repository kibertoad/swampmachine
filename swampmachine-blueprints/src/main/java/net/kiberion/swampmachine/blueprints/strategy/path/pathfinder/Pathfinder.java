package net.kiberion.swampmachine.blueprints.strategy.path.pathfinder;

import java.util.List;

import org.jgrapht.Graph;

public interface Pathfinder {

    public <V, E> List<E> findPathBetween(Graph<V, E> graph, V startVertex, V endVertex);

}
