package org.example;

import org.example.daa.metrics.Metrics;
import org.example.Sort.DeterministicSelect;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectTest {
    @Test
    public void selectMatchesSort() {
        Random rnd = new Random(123);
        for (int t = 0; t < 100; t++) {
            Integer[] a = rnd.ints(500, -10000, 10000).boxed().toArray(Integer[]::new);
            int k = rnd.nextInt(a.length);
            Metrics m = new Metrics();
            Integer found = DeterministicSelect.select(a.clone(), k, m);
            Integer[] copy = a.clone();
            java.util.Arrays.sort(copy);
            assertEquals(copy[k], found);
        }
    }
}

