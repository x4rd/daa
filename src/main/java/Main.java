package main.java;

import main.java.algorithms.SelectionSort;

public class Main {
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};

        SelectionSort.sort(arr, true);

        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}