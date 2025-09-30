package cli;

import metrics.PerformanceTracker;
import algorithms.InsertionSort;
import algorithms.SelectionSort;

import java.util.Arrays;

public class BenchmarkRunner {
    public static void main(String[] args) {
        String algorithm = "insertion"; // default
        int arrayLength = 10;
        boolean earlyTerminate = false;

        // parse CLI arguments
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-a":
                    algorithm = args[i + 1].toLowerCase();
                    i++;
                    break;
                case "-length":
                    arrayLength = Integer.parseInt(args[i + 1]);
                    i++;
                    break;
                case "-early":
                    earlyTerminate = Boolean.parseBoolean(args[i + 1]);
                    i++;
                    break;
            }
        }

        int[] array = generateRandomArray(arrayLength);
        System.out.println("Original: " + Arrays.toString(array));

        PerformanceTracker tracker = new PerformanceTracker();
        tracker.incMemoryAllocations(); // one array allocation

        // Run selected algorithm
        tracker.start();
        switch (algorithm) {
            case "insertion":
                InsertionSort.insertionSort(array, tracker);
                break;
            case "selection":
                SelectionSort selectionSort = new SelectionSort();
                selectionSort.sort(array, earlyTerminate, tracker);
                break;
            default:
                System.err.println("Unknown algorithm: " + algorithm);
                return;
        }
        tracker.stop();

        // Print & export
        System.out.println("Sorted:   " + Arrays.toString(array));
        tracker.printMetrics();
        tracker.exportCSV("metrics.csv", algorithm);
        System.out.println("Results exported to metrics.csv");
    }

    private static int[] generateRandomArray(int arrayLength) {
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        return array;
    }
}
