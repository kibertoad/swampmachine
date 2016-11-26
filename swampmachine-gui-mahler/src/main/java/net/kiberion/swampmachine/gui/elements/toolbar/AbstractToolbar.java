package net.kiberion.swampmachine.gui.elements.toolbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.kiberion.swampmachine.api.common.ParametrizedRecalculable;
import net.kiberion.swampmachine.entities.spatial.api.Position;
import net.kiberion.swampmachine.entityblocks.api.EntityProvider;
import net.kiberion.swampmachine.entityblocks.api.IdHolderBlock;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;
import net.kiberion.swampmachine.utils.MapUtils;

public abstract class AbstractToolbar<T extends IdHolderBlock, M extends EntityProvider<T>> extends AbstractStateSubView<M>
        implements ParametrizedRecalculable<T> {

    private final Map<String, CommonToolbarCell<T>> cellMap = new LinkedHashMap<>();
    private Table cellTable;
    
    protected int distanceBetweenCells = 100;

    public void setPosition (float x, float y) {
        cellTable.setX(x);
        cellTable.setY(y);
        
    }
    
    public void setPosition (Position position) {
        setPosition (position.getX(), position.getY());
    }
    
    @Override
    public void initGUIElements() {
        super.initGUIElements();
        cellTable = new Table();
        getMainStage().addActor(cellTable);
    }

    protected abstract String getEntityName (T entity);
    
    protected CommonToolbarCell<T> initCell(T entity) {
        CommonToolbarCell<T> cell = new CommonToolbarCell<>(entity.getId(), getGuiManager().getImageForEntity(entity.getId()), getEntityName(entity));
        getMainStage().addActor(cell);
        return cell;
    }

    protected void addEntity(T entity) {
        CommonToolbarCell<T> cell = initCell(entity);
        cellMap.put(entity.getId(), cell);
        cellTable.addActor(cell);

        cell.setX(0 + (distanceBetweenCells * cellMap.size()));
        cell.setY(0);
    }

    protected void initialFill() {
        for (T entity : getModel().getAllEntities()) {
            addEntity(entity);
        }
    }

    protected Collection<T> getAllEntities () {
        return getModel().getAllEntities();        
    }
    
    @Override
    public void update() {
        Collection<CommonToolbarCell<T>> removedCells = new ArrayList<>(cellMap.values());
        for (T entity : getAllEntities()) {
            CommonToolbarCell<T> cell = cellMap.get(entity.getId());
            if (internalUpdate(entity)) {
                removedCells.remove(cell);
            }
        }
        MapUtils.removeAll(cellMap, removedCells);
        super.update();
    }

    @Override
    public void update(T updatedEntity) {
        internalUpdate(updatedEntity);
    }

    // returns true if entity existed
    protected boolean internalUpdate(T updatedEntity) {
        CommonToolbarCell<T> cell = cellMap.get(updatedEntity.getId());
        if (cell != null) {
            cell.update(updatedEntity);
            return true;
        } else {
            addEntity(updatedEntity);
            return false;
        }
    }

    @Override
    public String toString() {
        return "Elements: " + cellMap;
    }

}
