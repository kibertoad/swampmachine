package net.kiberion.swampmachine.blueprints.strategy.path.pathfinder;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;

public class DjikstraPathfinder implements Pathfinder{

    @Override
    public <V, E> List<E> findPathBetween(Graph<V, E> graph, V startVertex, V endVertex) {
        return DijkstraShortestPath.findPathBetween(graph, startVertex, endVertex);
    }

}
