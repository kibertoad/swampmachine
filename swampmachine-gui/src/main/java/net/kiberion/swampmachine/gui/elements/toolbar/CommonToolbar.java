package net.kiberion.swampmachine.gui.elements.toolbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.kiberion.entities.common.api.ParametrizedRecalculable;
import net.kiberion.swampmachine.entityblocks.api.EntityProvider;
import net.kiberion.swampmachine.entityblocks.api.MetadataHolderBlock;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;
import net.kiberion.swampmachine.utils.MapUtils;

public class CommonToolbar<T extends MetadataHolderBlock, M extends EntityProvider<T>> extends AbstractStateSubView<M>
        implements ParametrizedRecalculable<T> {

    private final Map<String, CommonToolbarCell<T>> cellMap = new LinkedHashMap<>();
    private Table cellTable;

    @Override
    public void initGUIElements() {
        super.initGUIElements();
        cellTable = new Table();
        getMainStage().addActor(cellTable);
    }

    protected CommonToolbarCell<T> initCell(T entity) {
        CommonToolbarCell<T> cell = new CommonToolbarCell<>(entity.getId(), getGuiManager().getImageForEntity(entity.getId()), entity.getMetadata().getName());
        getMainStage().addActor(cell);
        return cell;
    }

    protected void addEntity(T entity) {
        CommonToolbarCell<T> cell = initCell(entity);
        cellMap.put(entity.getId(), cell);
        cellTable.addActor(cell);

        cell.setX(300 + (100 * cellMap.size()));
        cell.setY(10);
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
