package algorithm;

import metrics.PerformanceTracker;
import algorithms.InsertionSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

    @Test
    void testEmptyArray() {
        int[] arr = {};
        PerformanceTracker tracker = new PerformanceTracker();
        InsertionSort.insertionSort(arr, tracker);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        PerformanceTracker tracker = new PerformanceTracker();
        InsertionSort.insertionSort(arr, tracker);
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        PerformanceTracker tracker = new PerformanceTracker();
        InsertionSort.insertionSort(arr, tracker);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        PerformanceTracker tracker = new PerformanceTracker();
        InsertionSort.insertionSort(arr, tracker);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testArrayWithDuplicates() {
        int[] arr = {3, 1, 2, 3, 2, 1};
        PerformanceTracker tracker = new PerformanceTracker();
        InsertionSort.insertionSort(arr, tracker);
        assertArrayEquals(new int[]{1, 1, 2, 2, 3, 3}, arr);
    }

    @Test
    void testRandomArrayAgainstJavaSort() {
        int[] arr = {10, -1, 7, 3, 2, 15};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        PerformanceTracker tracker = new PerformanceTracker();
        InsertionSort.insertionSort(arr, tracker);

        assertArrayEquals(expected, arr);
    }
    @Test
    void testPerformanceOnLargeArrays() {
        int[] sizes = {1000, 10000, 100000};
        for (int size : sizes) {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = (int) (Math.random() * size);
            }

            PerformanceTracker tracker = new PerformanceTracker();
            tracker.start();
            InsertionSort.insertionSort(arr, tracker);
            tracker.stop();

            System.out.printf("InsertionSort | n=%d | time=%d ms | comparisons=%d | shifts=%d | accesses=%d%n",
                    size,
                    tracker.getElapsedTimeMillis(),
                    tracker.getComparisons(),
                    tracker.getShifts(),
                    tracker.getArrayAccesses());

            assertTrue(isSorted(arr));
        }
    }

    private boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) return false;
        }
        return true;
    }

}
