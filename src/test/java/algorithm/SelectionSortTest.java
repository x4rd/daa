package algorithm;

import metrics.PerformanceTracker;
import algorithms.SelectionSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {

    @Test
    void testEmptyArray() {
        int[] arr = {};
        PerformanceTracker tracker = new PerformanceTracker();
        int[] sorted = new SelectionSort().sort(arr, false, tracker);
        assertArrayEquals(new int[]{}, sorted);
    }

    @Test
    void testSingleElement() {
        int[] arr = {99};
        PerformanceTracker tracker = new PerformanceTracker();
        int[] sorted = new SelectionSort().sort(arr, false, tracker);
        assertArrayEquals(new int[]{99}, sorted);
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        PerformanceTracker tracker = new PerformanceTracker();
        int[] sorted = new SelectionSort().sort(arr, false, tracker);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sorted);
    }

    @Test
    void testReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        PerformanceTracker tracker = new PerformanceTracker();
        int[] sorted = new SelectionSort().sort(arr, false, tracker);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sorted);
    }

    @Test
    void testArrayWithDuplicates() {
        int[] arr = {3, 1, 2, 3, 2, 1};
        PerformanceTracker tracker = new PerformanceTracker();
        int[] sorted = new SelectionSort().sort(arr, false, tracker);
        assertArrayEquals(new int[]{1, 1, 2, 2, 3, 3}, sorted);
    }

    @Test
    void testRandomArrayAgainstJavaSort() {
        int[] arr = {12, -5, 7, 3, 0, 9};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        PerformanceTracker tracker = new PerformanceTracker();
        int[] sorted = new SelectionSort().sort(arr, false, tracker);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void testEarlyTerminateOptimization() {
        int[] arr = {1, 2, 3, 4, 5};
        PerformanceTracker tracker = new PerformanceTracker();
        int[] sorted = new SelectionSort().sort(arr, true, tracker);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sorted);
    }
    @Test
    void testPerformanceOnLargeArrays() {
        int[] sizes = {1000, 10000, 10_0000};
        for (int size : sizes) {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = (int) (Math.random() * size);
            }

            PerformanceTracker tracker = new PerformanceTracker();
            tracker.start();
            new SelectionSort().sort(arr, false, tracker);
            tracker.stop();

            System.out.printf("SelectionSort | n=%d | time=%d ms | comparisons=%d | shifts=%d | accesses=%d%n",
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
