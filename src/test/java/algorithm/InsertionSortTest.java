package algorithm;

import main.java.algorithms.InsertionSort;
import main.java.metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

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
}
