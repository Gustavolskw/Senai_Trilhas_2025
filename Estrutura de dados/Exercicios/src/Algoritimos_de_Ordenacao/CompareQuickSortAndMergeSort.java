package Algoritimos_de_Ordenacao;

public class CompareQuickSortAndMergeSort {


    static double mergeSortTime;
    static double quickSortTime;




    public static void mergeSort(int[] arr) {
        long startTime = System.nanoTime();
        if (arr.length < 2) {
            return;
        }

        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];


        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
        long endTime = System.nanoTime();
        long durationBubbleSort = endTime - startTime;
        double durationInMs = durationBubbleSort / 1_000_000.0;
        mergeSortTime += durationInMs;
    }


    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }



    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        long startTime = System.nanoTime();
        if (low < high) {
            int pi = partition(arr, low, high);


            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }

        long endTime = System.nanoTime();
        long durationBubbleSort = endTime - startTime;
        double durationInMs = durationBubbleSort / 1_000_000.0;
        quickSortTime += durationInMs;
    }


    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // Troca arr[i] com arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }


        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }


        public static void main(String[] args) {
        int[] arr =new int[50];
        for (int i = 0; i < 50; i++) {
            arr[i] = (int)(Math.random()*100);
        }
        quickSort(arr, 0, arr.length - 1);
        mergeSort(arr);

        System.out.println("Merge Sort Time: " + mergeSortTime + "ms");
        System.out.println("Quick Sort Time: " + quickSortTime + "ms");
        System.out.println("Diferença: "+ (mergeSortTime>quickSortTime? mergeSortTime- quickSortTime +" Quick Sort mais rapido" : quickSortTime - mergeSortTime +" Merge Sort mais rapido"));
    }
}
