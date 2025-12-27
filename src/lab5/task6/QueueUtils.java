package lab5.task6;

import java.util.*;

public class QueueUtils {

    public static <T> void reverseQueue(Queue<T> L1, Queue<T> L2) {
        if (L1 == null || L2 == null) {
            throw new IllegalArgumentException("Очереди не могут быть null");
        }

        L2.clear();

        Deque<T> stack = new ArrayDeque<>();

        while (!L1.isEmpty()) {
            stack.push(L1.poll());
        }

        while (!stack.isEmpty()) {
            L2.offer(stack.pop());
        }
    }
}