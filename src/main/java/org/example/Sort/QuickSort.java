package org.example.Sort;

import org.example.daa.metrics.Metrics;

import java.util.concurrent.ThreadLocalRandom;

/**
 * QuickSort:
 * - randomized pivot
 * - recurse into smaller partition, iterate into larger (tail recursion elimination)
 * - cutoff -> insertion sort
 */


public class QuickSort {

    public static <T extends Comparable<T>> void sort(T[] a, Metrics m) {
        if (a == null || a.length < 2) return;
        sort(a, 0, a.length - 1, m);
    }

    private static <T extends Comparable<T>> void sort(T[] a, int l, int r, Metrics m) {
        if (l >= r) return;
        m.enterRecursion();

        int pivotIndex = ThreadLocalRandom.current().nextInt(l, r + 1);
        T pivot = a[pivotIndex];
        int i = l, j = r;
        while (i <= j) {
            while (a[i].compareTo(pivot) < 0) { i++; m.incComparisons(1); }
            while (a[j].compareTo(pivot) > 0) { j--; m.incComparisons(1); }
            if (i <= j) {
                T tmp = a[i]; a[i] = a[j]; a[j] = tmp;
                i++; j--;
                m.incArrayAccesses(4);
            }
        }

        if (l < j) sort(a, l, j, m);
        if (i < r) sort(a, i, r, m);

        m.exitRecursion();
    }
}