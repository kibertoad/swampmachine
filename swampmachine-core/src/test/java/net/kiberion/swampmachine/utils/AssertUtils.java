package net.kiberion.swampmachine.utils;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class AssertUtils {

    private AssertUtils() {
    }

    @SuppressWarnings("unchecked")
    public static <T> void assertListEquals(List<T> list, T... expectedEntries) {
        assertNotNull(list);
        assertEquals("Size of actual and expected list is different. Actual entries: " + list, expectedEntries.length,
                list.size());

        IntStream.rangeClosed(0, list.size() - 1).forEach(counter -> {
            assertEquals("Actual entries: " + list + "; expected entries: " + Arrays.toString(expectedEntries),
                    list.get(counter), expectedEntries[counter]);
        });
    }

}
