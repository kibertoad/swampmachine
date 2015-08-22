package pyramide9.slick2d.util.pathfinding.heuristics;

import pyramide9.slick2d.util.pathfinding.AStarHeuristic;
import pyramide9.slick2d.util.pathfinding.Mover;
import pyramide9.slick2d.util.pathfinding.TileBasedMap;


/**
 * A heuristic that uses the tile that is closest to the target
 * as the next best tile.
 * 
 * @author Kevin Glass
 */
public class ClosestHeuristic implements AStarHeuristic {
	/**
	 * @see AStarHeuristic#getCost(TileBasedMap, Mover, int, int, int, int)
	 */
        @Override
	public float getCost(TileBasedMap map, Mover mover, int x, int y, int tx, int ty) {		
		float dx = tx - x;
		float dy = ty - y;
		
		float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
		
		return result;
	}

}
