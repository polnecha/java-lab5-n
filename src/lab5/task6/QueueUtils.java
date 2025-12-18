package lab5.task6;

import java.util.*;

public class QueueUtils {
    /**
     * Переписывает элементы из очереди L1 в очередь L2 в обратном порядке.
     * Очередь L1 становится пустой.
     */
    public static <T> void reverseQueue(Queue<T> L1, Queue<T> L2) {
        if (L1 == null || L2 == null) {
            throw new IllegalArgumentException("Очереди не могут быть null");
        }

        // Очистим L2 на всякий случай
        L2.clear();

        // Используем стек для инверсии порядка
        Deque<T> stack = new ArrayDeque<>();

        // Перекладываем все из L1 в стек
        while (!L1.isEmpty()) {
            stack.push(L1.poll());
        }

        // Перекладываем из стека в L2
        while (!stack.isEmpty()) {
            L2.offer(stack.pop());
        }
    }
}