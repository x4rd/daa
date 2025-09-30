package main.java.metrics;

public class PerformanceTracker {
    private long startTime;
    private long endTime;
    private boolean running;

    public void start() {
        startTime = System.nanoTime();
        running = true;
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
        return elapsed / 1_000_000; // convert to milliseconds
    }
}
