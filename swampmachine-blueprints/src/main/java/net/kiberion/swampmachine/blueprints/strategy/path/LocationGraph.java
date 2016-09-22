package net.kiberion.swampmachine.blueprints.strategy.path;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.google.common.collect.ImmutableList;

import net.kiberion.swampmachine.blueprints.strategy.path.pathfinder.DjikstraPathfinder;
import net.kiberion.swampmachine.blueprints.strategy.path.pathfinder.Pathfinder;

/**
 * This class is intended for calculation the optimal path between two
 * locations, when all locations have at least one link to another location (in
 * a form of graph)
 * 
 * @author kibertoad
 *
 * @param <V>
 */
public class LocationGraph<V> {

    private final Map<Pair<V, V>, List<V>> pathCache = new HashMap<>();
    private final Pathfinder pathfinder;

    private final WeightedGraph<V, LocationPathEdge<V>> graph = new SimpleDirectedWeightedGraph(
            LocationPathEdge.class);

    public LocationGraph() {
        this.pathfinder = new DjikstraPathfinder();
    }

    public LocationGraph(Pathfinder pathfinderAlgorithm) {
        this.pathfinder = pathfinderAlgorithm;
    }

    public void addLocation(V location) {
        graph.addVertex(location);
    }

    public void addLocations(Collection<V> locations) {
        for (V entry : locations) {
            addLocation(entry);
        }
    }

    public void addConnection(V sourceLocation, V targetLocation, float weight) {
        LocationPathEdge<V> edge = graph.addEdge(sourceLocation, targetLocation);
        graph.setEdgeWeight(edge, weight);

        edge = graph.addEdge(targetLocation, sourceLocation);
        graph.setEdgeWeight(edge, weight);
    }

    public List<V> getShortestRoute(V sourceLocation, V targetLocation) {
        Pair<V, V> key = Pair.of(sourceLocation, targetLocation);
        List<V> result = pathCache.get(key);
        if (result == null) {
            result = new ArrayList<>();
            for (LocationPathEdge<V> edge : getPathBetweenLocations(sourceLocation, targetLocation)) {
                result.add(edge.getTarget());
            }
            pathCache.put(key, result);
        }
        return ImmutableList.copyOf(result);
    }

    public double calculateShortestRouteCost(V sourceLocation, V targetLocation) {
        double result = 0;
        for (LocationPathEdge<V> edge : getPathBetweenLocations(sourceLocation, targetLocation)) {
            result += edge.getWeight();
        }
        return result;
    }

    private List<LocationPathEdge<V>> getPathBetweenLocations(V sourceLocation, V targetLocation) {
        return pathfinder.findPathBetween(graph, sourceLocation, targetLocation);
    }

}
