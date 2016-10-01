package net.kiberion.swampmachine.api.sources;

import java.util.List;

public interface EntrySource<T> {

    public List<T> getEntries();

}
