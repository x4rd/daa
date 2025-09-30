public class Main {
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};

        // Assuming SelectionSort class is in the same package
        SelectionSort.sort(arr);

        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}