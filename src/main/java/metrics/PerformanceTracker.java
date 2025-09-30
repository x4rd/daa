package main.java.metrics;

import java.io.FileWriter;
import java.io.IOException;

public class PerformanceTracker {
    private long startTime;
    private long endTime;
    private boolean running;

    // Metrics
    private long comparisons;
    private long shifts;
    private long arrayAccesses;
    private long memoryAllocations;

    public void start() {
        startTime = System.nanoTime();
        running = true;
        resetMetrics();
    }

    public void stop() {
        if (running) {
            endTime = System.nanoTime();
            running = false;
        }
    }

    public long getElapsedTimeMillis() {
        long elapsed;
        if (running) {
            elapsed = System.nanoTime() - startTime;
        } else {
            elapsed = endTime - startTime;
        }
        return elapsed / 1_000_000; // convert to ms
    }

    // ==== Metric counters ====
    public void incComparisons() { comparisons++; }
    public void incShifts() { shifts++; }
    public void incArrayAccesses(int n) { arrayAccesses += n; }
    public void incMemoryAllocations() { memoryAllocations++; }

    public long getComparisons() { return comparisons; }
    public long getShifts() { return shifts; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getMemoryAllocations() { return memoryAllocations; }

    public void resetMetrics() {
        comparisons = shifts = arrayAccesses = memoryAllocations = 0;
    }

    public void printMetrics() {
        System.out.println("Execution Time (ms): " + getElapsedTimeMillis());
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Shifts: " + shifts);
        System.out.println("Array Accesses: " + arrayAccesses);
        System.out.println("Memory Allocations: " + memoryAllocations);
    }

    // Export metrics to CSV
    public void exportCSV(String filename, String algorithmName) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.append(algorithmName).append(",")
                    .append(String.valueOf(getElapsedTimeMillis())).append(",")
                    .append(String.valueOf(comparisons)).append(",")
                    .append(String.valueOf(shifts)).append(",")
                    .append(String.valueOf(arrayAccesses)).append(",")
                    .append(String.valueOf(memoryAllocations)).append("\n");
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }
}
