package main.java.algorithms;

import main.java.metrics.PerformanceTracker;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class InsertionSort {

    public static void insertionSort(int[] arr, PerformanceTracker tracker) {
        if (arr == null) throw new IllegalArgumentException("Array cannot be null.");

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            tracker.incArrayAccesses(1); // read key
            int j = i - 1;

            while (j >= 0) {
                tracker.incComparisons();
                tracker.incArrayAccesses(1); // read arr[j]

                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    tracker.incArrayAccesses(2); // read + write
                    tracker.incShifts();
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
            tracker.incArrayAccesses(1); // write key
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PerformanceTracker tracker = new PerformanceTracker();

        try {
            System.out.print("Enter size of array: ");
            int n = sc.nextInt();
            if (n < 0) throw new IllegalArgumentException("Array size must be non-negative.");

            int[] arr = new int[n];
            tracker.incMemoryAllocations();
            Random rand = new Random();

            for (int i = 0; i < n; i++) {
                arr[i] = rand.nextInt(1000);
                tracker.incArrayAccesses(1);
            }

            System.out.println("Original: " + Arrays.toString(arr));
            tracker.start();
            insertionSort(arr, tracker);
            tracker.stop();
            System.out.println("Sorted:   " + Arrays.toString(arr));

            tracker.printMetrics();
            tracker.exportCSV("metrics.csv", "InsertionSort");

        } catch (InputMismatchException e) {
            System.err.println("Invalid input! Enter an integer.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
