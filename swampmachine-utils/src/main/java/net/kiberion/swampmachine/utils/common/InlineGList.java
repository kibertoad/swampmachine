package net.kiberion.swampmachine.utils.common;

import java.util.ArrayList;

public class InlineGList<T> extends ArrayList<T> {

    /**
     * 
     */
    private static final long serialVersionUID = 4640610874122198760L;

    @SafeVarargs
    public InlineGList(T... entities) {
        super();
        if (entities != null) {
            for (T entity : entities) {
                add(entity);
            }
        }
    }
}
