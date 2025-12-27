package lab5.task3;

import java.util.*;

public class ListUtils {

    public static <T> List<T> differenceUnique(List<T> list1, List<T> list2) {
        if (list1 == null || list2 == null) {
            throw new IllegalArgumentException("Списки не могут быть null");
        }

        Set<T> set2 = new HashSet<>(list2);
        Set<T> added = new HashSet<>();
        List<T> result = new ArrayList<>();

        for (T item : list1) {
            if (!set2.contains(item) && added.add(item)) {
                result.add(item);
            }
        }

        return result;
    }
}
