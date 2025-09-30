package main.java.algorithms;

import main.java.metrics.PerformanceTracker;

public class SelectionSort {

    public int[] sort(int[] arr, boolean earlyTerminate, PerformanceTracker tracker) {
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            int ind = i;
            boolean sorted = true;

            for (int j = i + 1; j < len; j++) {
                tracker.incComparisons();
                tracker.incArrayAccesses(2); // read arr[j], arr[ind]
                if (arr[j] < arr[ind]) {
                    ind = j;
                    sorted = false;
                }
            }

            if (ind != i) {
                int tmp = arr[i];
                arr[i] = arr[ind];
                arr[ind] = tmp;
                tracker.incArrayAccesses(4); // 2 reads, 2 writes
            } else if (earlyTerminate && sorted) {
                break;
            }
        }

        return arr;
    }
}
