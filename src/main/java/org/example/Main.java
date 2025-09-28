package org.example;

import java.util.Random;
import java.util.stream.IntStream;

import org.example.daa.metrics.Metrics;
import org.example.Sort.MergeSort;
import org.example.Sort.QuickSort;
import org.example.Sort.DeterministicSelect;
import org.example.closest.ClosestPair;
import org.example.closest.Point;
import org.example.io.CsvWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] sizes = new int[] {1000, 5000, 20000, 80000};
        int trials = 3;
        Random rnd = new Random(123);

        try (CsvWriter csv = new CsvWriter("results.csv")) {
            for (int n : sizes) {
                for (int t = 0; t < trials; t++) {
                    Integer[] arr = IntStream.range(0, n).mapToObj(i -> rnd.nextInt()).toArray(Integer[]::new);

                    // MergeSort
                    Metrics m = new Metrics();
                    long start = System.nanoTime();
                    MergeSort.sort(arr);
                    long time = System.nanoTime() - start;
                    csv.writeRow("MergeSort", n, t, time, m);

                    // QuickSort
                    m = new Metrics();
                    start = System.nanoTime();
                    QuickSort.sort(arr.clone(), m);
                    time = System.nanoTime() - start;
                    csv.writeRow("QuickSort", n, t, time, m);

                    // DeterministicSelect - median
                    m = new Metrics();
                    Integer[] arr2 = arr.clone();
                    start = System.nanoTime();
                    Integer med = DeterministicSelect.select(arr2, arr2.length / 2, m);
                    time = System.nanoTime() - start;
                    csv.writeRow("DeterministicSelect", n, t, time, m);

                    // ClosestPair - generate points
                    Point[] pts = new Point[n];
                    for (int i = 0; i < n; ++i)
                        pts[i] = new Point(rnd.nextDouble() * 1000, rnd.nextDouble() * 1000);
                    m = new Metrics();
                    start = System.nanoTime();
                    double d = ClosestPair.closest(pts, m);
                    time = System.nanoTime() - start;
                    csv.writeRow("ClosestPair", n, t, time, m);
                }
                System.out.println("Completed size: " + n);
            }
        }
        System.out.println("Done. results.csv written.");
    }
}
