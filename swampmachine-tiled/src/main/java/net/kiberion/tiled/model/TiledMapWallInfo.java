package net.kiberion.tiled.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import net.kiberion.swampmachine.entities.spatial.api.PositionAspect;

public class TiledMapWallInfo {

    private static final Logger log = LogManager.getLogger();

    private int mapHeightInTiles;
    private int mapWidthInTiles;

    public final List<List<Boolean>> walls;

    public TiledMapWallInfo(TiledMapInfo info) {
        walls = new ArrayList<List<Boolean>>();
        this.mapHeightInTiles = info.getMapHeight();
        this.mapWidthInTiles = info.getMapWidth();
    }

    public void loadWalls(TiledMap map) {

        boolean isWallsInitted = false;

        for (TiledMapTileLayer layer : map.getLayers().getByType(TiledMapTileLayer.class)) {
            if (layer.getName().startsWith("walls")) {
                loadWalls(layer);
                isWallsInitted = true;
            }
        }
        for (TiledMapTileLayer layer : map.getLayers().getByType(TiledMapTileLayer.class)) {
            if ("base".equals(layer.getName())) {
                loadBase(layer);
                isWallsInitted = true;
            }
        }

        if (isWallsInitted == false) {
            System.out.print("No walls found, init blank ones");
            initBlankWalls();
        }
    }

    private void loadWalls(TiledMapTileLayer layer) {
        if (walls.isEmpty()) {
            for (int y = 0; y < layer.getHeight(); y++) {
                walls.add(new ArrayList<Boolean>());
                for (int x = 0; x < layer.getWidth(); x++) {
                    final TiledMapTileLayer.Cell cell = layer.getCell(x, layer.getHeight() - y - 1);
                    walls.get(y).add(cell != null);
                }
            }
        } else {
            setWalls(layer);
        }
    }

    /**
     * Set empty spaces as walls
     *
     * @param layer
     */
    private void loadBase(TiledMapTileLayer layer) {
        for (int y = 0; y < layer.getHeight(); y++) {
            for (int x = 0; x < layer.getWidth(); x++) {
                final TiledMapTileLayer.Cell cell = layer.getCell(x, layer.getHeight() - y - 1);
                if (cell == null) {
                    walls.get(y).set(x, true);
                }
            }
        }
    }

    private void setWalls(TiledMapTileLayer layer) {
        for (int y = 0; y < layer.getHeight(); y++) {
            for (int x = 0; x < layer.getWidth(); x++) {
                final TiledMapTileLayer.Cell cell = layer.getCell(x, layer.getHeight() - y - 1);
                if (cell != null) {
                    walls.get(y).set(x, true);
                }
            }
        }
    }

    private void initBlankWalls() {

        for (int y = 0; y < this.mapHeightInTiles; y++) {
            walls.add(new ArrayList<Boolean>());
            for (int x = 0; x < this.mapWidthInTiles; x++) {
                walls.get(y).add(false);
            }
        }

    }

    public boolean isWall(int x, int y) {
        if (y < 0 || y >= walls.size()) {
            return true;
        }

        if (x < 0 || x >= walls.get(0).size()) {
            return true;
        }

        return walls.get(y).get(x);
    }

    public void outputAllWalls() {

        int y = 0;
        int x;

        for (List<Boolean> listList : walls) {

            x = 0;
            for (Boolean b : listList) {

                if (b) {
                    log.info("Have wall at: " + x + "/" + y);
                }

                x++;
            }
            y++;
        }
    }

    public void addWall(int x, int y) {
        walls.get(y).set(x, true);
    }
    
    public void addWall(PositionAspect position) {
        addWall (position.getIntX(), position.getIntY());
    }
    
    
    public void removeWall(int x, int y) {
        walls.get(y).set(x, false);
    }
    
    public void removeWall(PositionAspect position) {
        removeWall (position.getIntX(), position.getIntY());
    }
    
    

}
