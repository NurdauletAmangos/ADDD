package org.example.Sort;

import org.example.daa.metrics.Metrics;
import org.example.daa.util.SortUtils;

/**
 * Deterministic Select (Median-of-Medians) — group size 5.
 * Returns k-th smallest (0-based).
 */

// Алгоритм Median of Medians (выбор k-го элемента)
public class DeterministicSelect {

    public static <T extends Comparable<T>> T select(T[] a, int k, Metrics m) {
        if (k < 0 || k >= a.length) throw new IllegalArgumentException("k out of range");
        return select(a, 0, a.length - 1, k, m);
    }

    private static <T extends Comparable<T>> T select(T[] a, int l, int r, int k, Metrics m) {
        if (l == r) return a[l];
        int n = r - l + 1;

        // если маленький участок - просто сортируем
        if (n <= 32) {
            SortUtils.insertionSort(a, l, r, m);
            return a[l + k];
        }

        int numMedians = 0;
        for (int i = l; i <= r; i += 5) {
            int subR = Math.min(i + 4, r);
            SortUtils.insertionSort(a, i, subR, m);
            int medianIdx = i + (subR - i) / 2;
            SortUtils.swap(a, l + numMedians, medianIdx, m);
            numMedians++;
        }

        T mom = select(a, l, l + numMedians - 1, numMedians / 2, m);
        int pivotIndex = partitionAroundValue(a, l, r, mom, m);
        int rank = pivotIndex - l;

        if (k == rank) return a[pivotIndex];
        else if (k < rank) return select(a, l, pivotIndex - 1, k, m);
        else return select(a, pivotIndex + 1, r, k - rank - 1, m);
    }

    private static <T extends Comparable<T>> int partitionAroundValue(T[] a, int l, int r, T pivotValue, Metrics m) {
        int idx = -1;
        for (int i = l; i <= r; i++) {
            if (a[i].compareTo(pivotValue) == 0) { idx = i; break; }
        }
        if (idx == -1) idx = (l + r) / 2;
        SortUtils.swap(a, idx, r, m);

        int store = l;
        for (int i = l; i < r; i++) {
            if (a[i].compareTo(a[r]) < 0) {
                SortUtils.swap(a, i, store, m);
                store++;
            }
        }
        SortUtils.swap(a, store, r, m);
        return store;
    }
}

