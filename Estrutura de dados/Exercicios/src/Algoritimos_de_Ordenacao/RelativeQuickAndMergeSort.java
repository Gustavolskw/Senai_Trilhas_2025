package Algoritimos_de_Ordenacao;

import java.util.Arrays;

public class RelativeQuickAndMergeSort {

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }




        public static void mergeSort(int[] arr) {
            if (arr.length <= 1) {
                return;
            }

            int mid = arr.length / 2;
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);

            mergeSort(left);
            mergeSort(right);

            merge(arr, left, right);
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


    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 5, 3, 2, 1};
        System.out.println("Array antes do Quick Sort: " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Array depois do Quick Sort: " + Arrays.toString(arr));



        int[] arrMer = {4, 3, 2, 5, 3, 2, 1};
        System.out.println("Array antes do Merge Sort: " + Arrays.toString(arrMer));
        mergeSort(arrMer);
        System.out.println("Array depois do Merge Sort: " + Arrays.toString(arrMer));
    }
}
