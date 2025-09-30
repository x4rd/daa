public class SelectionSort {
    public static int[] sort(int[] arr){
        int len = arr.length;
    
        for(int i = 0;i < len;i++){
            int ind = i;
            
            for (int j = i+1;j < len;j++){
                if (arr[j] < arr[ind]) ind = j;
            }

            if (ind != i){
                int tmp = arr[i];
                arr[i] = arr[ind];
                arr[ind] = tmp;
            }
        }

        return arr;
    }
}
