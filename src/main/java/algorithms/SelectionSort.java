package main.java.algorithms;

public class SelectionSort implements SortingAlgorithm{
    public  int[] sort(int[] arr, boolean earlyTerminate){
        int len = arr.length;

        for(int i = 0;i < len;i++){
            int ind = i;
            boolean sorted = true;

            for (int j = i+1;j < len;j++){
                if (arr[j] < arr[ind]) {
                    ind = j;
                    sorted = false;
                }
            }

            if (ind != i){
                int tmp = arr[i];
                arr[i] = arr[ind];
                arr[ind] = tmp;
            }else if (earlyTerminate == true && sorted == true){
                break;
            }
        }

        return arr;
    }
}
