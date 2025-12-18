package lab5.task7;

import java.util.*;

public class Polyline {
    private List<Point> points;

    public Polyline(List<Point> points) {
        this.points = new ArrayList<>(points);
    }

    @Override
    public String toString() {
        return "Линия " + points.toString();
    }

    // Геттер для тестирования (опционально)
    public List<Point> getPoints() {
        return new ArrayList<>(points);
    }
}