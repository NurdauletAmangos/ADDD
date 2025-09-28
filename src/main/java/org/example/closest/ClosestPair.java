package org.example.closest;

import org.example.daa.metrics.Metrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Classic divide-and-conquer closest pair in 2D — O(n log n).
 */

public class ClosestPair {

    // основной метод
    public static double closest(Point[] pts, Metrics m) {
        if (pts == null || pts.length < 2) return Double.POSITIVE_INFINITY;

        Point[] byX = pts.clone();
        Arrays.sort(byX, Comparator.comparingDouble(Point::x));
        Point[] byY = pts.clone();
        Arrays.sort(byY, Comparator.comparingDouble(Point::y));

        return closestRec(byX, byY, 0, pts.length - 1, m);
    }

    // рекурсивный поиск
    private static double closestRec(Point[] byX, Point[] byY, int l, int r, Metrics m) {
        int n = r - l + 1;

        // если мало точек - просто перебор
        if (n <= 3) {
            double best = Double.POSITIVE_INFINITY;
            for (int i = l; i <= r; i++) {
                for (int j = i + 1; j <= r; j++) {
                    best = Math.min(best, Point.dist(byX[i], byX[j]));
                }
            }
            return best;
        }

        int mid = (l + r) / 2;
        double midx = byX[mid].x();

        List<Point> leftY = new ArrayList<>();
        List<Point> rightY = new ArrayList<>();
        for (Point p : byY) {
            if (p.x() <= midx) leftY.add(p);
            else rightY.add(p);
        }

        double dl = closestRec(byX, leftY.toArray(new Point[0]), l, mid, m);
        double dr = closestRec(byX, rightY.toArray(new Point[0]), mid + 1, r, m);
        double d = Math.min(dl, dr);

        // полоса шириной d
        List<Point> strip = new ArrayList<>();
        for (Point p : byY) {
            if (Math.abs(p.x() - midx) < d) strip.add(p);
        }

        // проверка только ближайших по y
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y() - strip.get(i).y()) < d; j++) {
                double dist = Point.dist(strip.get(i), strip.get(j));
                if (dist < d) d = dist;
            }
        }
        return d;
    }
}