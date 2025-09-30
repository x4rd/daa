package main.java.cli;

import main.java.metrics.PerformanceTracker;

public class BenchmarkRunner {
    public static void main(String[] args) {
        String algorithm = "default";
        int arrayLength = 10;
        int[] array = new int[arrayLength];

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-a":
                    algorithm = args[i + 1];
                    break;
                case "-length":
                    arrayLength = Integer.parseInt(args[i + 1]);
                    break;
            }
        }
        array = generateRandomArray(arrayLength);
        PerformanceTracker timer = new PerformanceTracker();
        Algorithm

    }

    private static int[] generateRandomArray(int arrayLength) {
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        return array;
    }
}
