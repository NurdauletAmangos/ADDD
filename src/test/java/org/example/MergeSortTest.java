package org.example;

import org.example.daa.metrics.Metrics;
import org.example.Sort.MergeSort;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {
    @Test
    public void randomCorrectness() {
        Random rnd = new Random(42);
        for (int t = 0; t < 10; t++) {
            Integer[] a = rnd.ints(1000, -100000, 100000).boxed().toArray(Integer[]::new);
            Integer[] copy = a.clone();
            Metrics m = new Metrics();
            MergeSort.sort(a, m);
            java.util.Arrays.sort(copy);
            assertArrayEquals(copy, a);
        }
    }
}
