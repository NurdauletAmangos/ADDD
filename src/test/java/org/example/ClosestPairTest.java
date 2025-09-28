package org.example;

import org.example.closest.ClosestPair;
import org.example.closest.Point;
import org.example.daa.metrics.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    @Test
    public void simpleTest() {
        Point[] pts = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(7, 7),
                new Point(1, 1)
        };
        Metrics m = new Metrics();
        double d = ClosestPair.closest(pts, m);
        assertEquals(Math.sqrt(2), d, 1e-9);
    }

    @Test
    public void identicalPoints() {
        Point[] pts = {
                new Point(2, 2),
                new Point(2, 2),
                new Point(5, 5)
        };
        Metrics m = new Metrics();
        double d = ClosestPair.closest(pts, m);
        assertEquals(0.0, d, 1e-9);
    }
}
