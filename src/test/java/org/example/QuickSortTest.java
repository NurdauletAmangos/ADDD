package org.example;

import org.example.daa.metrics.Metrics;
import org.example.Sort.QuickSort;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuickSortTest {
    @Test
    public void randomCorrectnessAndDepth() {
        Random rnd = new Random(99);
        for (int t = 0; t < 10; t++) {
            Integer[] a = rnd.ints(20000, -1000000, 1000000).boxed().toArray(Integer[]::new);
            Integer[] copy = a.clone();
            java.util.Arrays.sort(copy);

            Metrics m = new Metrics();
            QuickSort.sort(a, m);
            assertArrayEquals(copy, a);

            double log2 = Math.log(copy.length) / Math.log(2);
            // допускаем некоторую константу: проверяем, что стек не превысил 10*log2(n)
            assertTrue(m.getMaxDepth() < 10 * Math.max(1.0, log2));
        }
    }
}
