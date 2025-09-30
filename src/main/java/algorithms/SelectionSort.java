package algorithms;

import metrics.PerformanceTracker;

public class SelectionSort {

    public int[] sort(int[] arr, boolean earlyTerminate, PerformanceTracker tracker) {
        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {
            int ind = i;
            boolean tailSorted = true;

            for (int j = i + 1; j < len; j++) {
                tracker.incComparisons();
                tracker.incArrayAccesses(2); // arr[j], arr[ind]
                if (arr[j] < arr[ind]) {
                    ind = j;
                }

                if (earlyTerminate) {
                    tracker.incComparisons();
                    tracker.incArrayAccesses(2); // arr[j], arr[j-1]
                    if (arr[j] < arr[j - 1]) {
                        tailSorted = false;
                    }
                }
            }

            if (ind != i) {
                tracker.incArrayAccesses(4); // 2 reads, 2 writes for swap
                int tmp = arr[i];
                arr[i] = arr[ind];
                arr[ind] = tmp;
            }

            if (earlyTerminate && tailSorted) {
                break;
            }
        }

        return arr;
    }


}
