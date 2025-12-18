package lab5.task3;

import java.util.*;

public class ListUtils {

    /**
     * Возвращает список элементов, которые есть в list1, но отсутствуют в list2.
     * Каждый элемент включается только один раз (без дубликатов), порядок — как в list1.
     */
    public static <T> List<T> differenceUnique(List<T> list1, List<T> list2) {
        if (list1 == null || list2 == null) {
            throw new IllegalArgumentException("Списки не могут быть null");
        }

        Set<T> set2 = new HashSet<>(list2);      // для O(1) проверки "есть ли в L2"
        Set<T> added = new HashSet<>();          // чтобы не добавлять дубликаты в L
        List<T> result = new ArrayList<>();

        for (T item : list1) {
            if (!set2.contains(item) && added.add(item)) {
                result.add(item);
            }
        }

        return result;
    }
}
