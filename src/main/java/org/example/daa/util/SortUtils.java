package org.example.daa.util;

import org.example.daa.metrics.Metrics;

import java.util.Random;

public final class SortUtils {
    private SortUtils() {}

    public static <T extends Comparable<T>> int cmp(T a, T b, Metrics m) {
        m.incComparisons(1);
        return a.compareTo(b);
    }

    public static <T> void swap(T[] a, int i, int j, Metrics m) {
        if (i == j) return;
        m.incArrayAccesses(4); // read/read/write/write approximation
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void shuffle(int[] a, Random rnd, Metrics m) {
        for (int i = a.length - 1; i > 0; --i) {
            int j = rnd.nextInt(i + 1);
            m.incArrayAccesses(4);
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
    }

    public static <T extends Comparable<T>> void insertionSort(T[] a, int l, int rInclusive, Metrics m) {
        for (int i = l + 1; i <= rInclusive; i++) {
            T key = a[i]; m.incArrayAccesses(1);
            int j = i - 1;
            while (j >= l && cmp(a[j], key, m) > 0) {
                m.incArrayAccesses(2);
                a[j + 1] = a[j];
                j--;
            }
            m.incArrayAccesses(1);
            a[j + 1] = key;
        }
    }
}
