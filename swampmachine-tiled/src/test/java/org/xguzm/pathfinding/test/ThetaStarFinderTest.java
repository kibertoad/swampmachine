package org.xguzm.pathfinding.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import org.xguzm.pathfinding.grid.finders.GridFinderOptions;
import org.xguzm.pathfinding.grid.finders.ThetaStarGridFinder;

import net.kiberion.tiled.MapContextBasedTest;
import net.kiberion.tiled.processors.CollisionInfoProvider;

//ToDo fix tests
@Ignore
public class ThetaStarFinderTest extends MapContextBasedTest{

	NavigationGrid<GridCell> grid;
	ThetaStarGridFinder<GridCell> finder;
	GridFinderOptions opt;
	
	@Autowired
	private CollisionInfoProvider collisionInfoProvider;
	
	@Before
	public void init(){
	    Validate.notNull(collisionInfoProvider);
	    
		grid = NavGraphFactory.getGridCellMap();	
		opt = new GridFinderOptions();
		finder = new ThetaStarGridFinder<GridCell>(GridCell.class, opt);
		
		grid.setCollisionInfoProvider(collisionInfoProvider);
	}
	
	@Test
	public void basicMovementTest() {
		System.out.println("\nRunning ThetaStarFinderTest.basicMovementTest");
		GridCell start = grid.getCell(2, 0), end = grid.getCell(4, 7);
		
		//test orthogonal movement only
		opt.allowDiagonal = false;
		
		List<GridCell> path = finder.findPath(start,  end,  grid);
		assertNotNull(String.format("No path found from %s to %s for orthogonal movement", start, end), path);
				
		//TODO: smarter test...how to make sure path is smooth?
		int i =0 ;
		System.out.println("  Path: no diagonal movement allowed ");
		for(GridCell cell : path){
			System.out.println("    (" + (i++) + ") " + cell);
		}
		
		//test diagonal movement 
		opt.allowDiagonal = true;
		
		path = finder.findPath(start, end, grid);
		assertNotNull(String.format("No path found from %s to %s for diagnoal movement", start, end), path);
		
		//TODO: smarter test...how to make sure path is smooth?
		i =0 ;
		System.out.println("  Diagonal movement allowed ");
		for(GridCell cell : path){
			System.out.println("    (" + (i++) + ") " + cell);
		}
	}

}
