package main.java.algorithms;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Insertion Sort implementation with:
 * - Optimizations for nearly-sorted data (shifting instead of swapping)
 * - Metrics collection (comparisons, swaps/shifts, array accesses)
 * - Input validation and error handling
 * - CLI interface for testing
 */
public class InsertionSort {

    // Metrics
    private static long comparisons = 0;
    private static long shifts = 0;
    private static long arrayAccesses = 0;
    private static long memoryAllocations = 0;

    /**
     * Optimized insertion sort:
     * - Uses shifting instead of repeated swapping
     * - Works well for nearly-sorted data
     */
    public static void insertionSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            arrayAccesses++; // read key
            int j = i - 1;

            // Compare and shift
            while (j >= 0) {
                comparisons++;
                arrayAccesses++; // read arr[j]

                if (arr[j] > key) {
                    arr[j + 1] = arr[j]; // shift
                    arrayAccesses += 2; // read arr[j], write arr[j+1]
                    shifts++;
                    j--;
                } else {
                    break; // no need to check further if array[j] <= key
                }
            }
            arr[j + 1] = key;
            arrayAccesses++; // write key back
        }
    }

    /** Reset metrics before each run */
    public static void resetMetrics() {
        comparisons = shifts = arrayAccesses = memoryAllocations = 0;
    }

    /** Print collected metrics */
    public static void printMetrics() {
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Shifts: " + shifts);
        System.out.println("Array Accesses: " + arrayAccesses);
        System.out.println("Memory Allocations: " + memoryAllocations);
    }

    /** CLI interface */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter size of array: ");
            int n = sc.nextInt();
            if (n < 0) {
                throw new IllegalArgumentException("Array size must be non-negative.");
            }

            int[] arr = new int[n];
            memoryAllocations++; // one array allocation
            Random rand = new Random();

            for (int i = 0; i < n; i++) {
                arr[i] = rand.nextInt(1000);
                arrayAccesses++;
            }

            System.out.println("Original array: " + Arrays.toString(arr));
            resetMetrics();
            insertionSort(arr);
            System.out.println("Sorted array:   " + Arrays.toString(arr));
            printMetrics();

        } catch (InputMismatchException e) {
            System.err.println("Invalid input! Please enter an integer.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
