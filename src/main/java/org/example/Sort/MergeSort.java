package org.example.Sort;

import org.example.daa.metrics.Metrics;

@SuppressWarnings("unchecked")
public class MergeSort {

    // Call without metrics
    public static <T extends Comparable<T>> void sort(T[] arr) {
        sort(arr, new Metrics());
    }

    // Call with metrics
    public static <T extends Comparable<T>> void sort(T[] arr, Metrics metrics) {
        if (arr == null || arr.length <= 1) return;
        metrics.incAllocations(1); // буфер — новая аллокация
        T[] buffer = (T[]) new Comparable[arr.length];
        sort(arr, buffer, 0, arr.length - 1, metrics);
    }

    private static <T extends Comparable<T>> void sort(T[] arr, T[] buffer, int left, int right, Metrics metrics) {
        metrics.enterRecursion();
        if (left >= right) {
            metrics.exitRecursion();
            return;
        }

        int mid = (left + right) / 2;
        sort(arr, buffer, left, mid, metrics);
        sort(arr, buffer, mid + 1, right, metrics);
        merge(arr, buffer, left, mid, right, metrics);

        metrics.exitRecursion();
    }

    private static <T extends Comparable<T>> void merge(T[] arr, T[] buffer, int left, int mid, int right, Metrics metrics) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            metrics.incComparisons(1);
            metrics.incArrayAccesses(2);

            if (arr[i].compareTo(arr[j]) <= 0) {
                buffer[k++] = arr[i++];
                metrics.incArrayAccesses(1);
            } else {
                buffer[k++] = arr[j++];
                metrics.incArrayAccesses(1);
            }
        }

        while (i <= mid) {
            buffer[k++] = arr[i++];
            metrics.incArrayAccesses(2); 
        }
        while (j <= right) {
            buffer[k++] = arr[j++];
            metrics.incArrayAccesses(2);
        }

        for (int p = left; p <= right; p++) {
            arr[p] = buffer[p];
            metrics.incArrayAccesses(2);
        }
    }
}
