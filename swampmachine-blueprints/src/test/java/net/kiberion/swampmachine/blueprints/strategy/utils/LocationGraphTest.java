package net.kiberion.swampmachine.blueprints.strategy.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import net.kiberion.swampmachine.blueprints.strategy.path.LocationGraph;
import net.kiberion.swampmachine.utils.AssertUtils;
import net.kiberion.utils.InlineGList;

public class LocationGraphTest {

    @Test
    public void testGraph() {
        LocationGraph<String> graph = new LocationGraph<>();

        final String location1 = "1";
        final String location2 = "2";
        final String location6 = "6";
        final String location10 = "10";

        graph.addLocations(new InlineGList<>(location1, location2, location6, location10));

        graph.addConnection(location1, location10, 1);
        graph.addConnection(location1, location2, 2 - 1);
        graph.addConnection(location2, location6, 6 - 2);
        graph.addConnection(location6, location10, 10 - 6);

        AssertUtils.assertListEquals(graph.getShortestRoute(location1, location10), location10);
        assertEquals (1d, graph.calculateShortestRouteCost(location1, location10), 0.1);
        
        AssertUtils.assertListEquals(graph.getShortestRoute(location10, location1), location1);
        assertEquals (1d, graph.calculateShortestRouteCost(location10, location1), 0.1);

        //These two paths are equivalent in price, so either goes
        AssertUtils.assertListEquals(graph.getShortestRoute(location1, location6), location10, location6);
        assertEquals (5d, graph.calculateShortestRouteCost(location1, location6), 0.1);
        AssertUtils.assertListEquals(graph.getShortestRoute(location6, location1), location2, location1);
        assertEquals (5d, graph.calculateShortestRouteCost(location6, location1), 0.1);
    }

}
