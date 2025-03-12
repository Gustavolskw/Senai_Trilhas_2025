package Algoritimos_de_Ordenacao;

public class Algoritimos {
    // Selection Sort
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Troca
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
//    public static void main(String[] args) {
//        int[] arr = {64, 25, 12, 22, 11};
//        selectionSort(arr);
//        for (int num : arr) {
//            System.out.print(num + " ");
//        }
//    }






    //Insertion Sort
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    //    public static void main(String[] args) {
//        int[] arr = {64, 25, 12, 22, 11};
//        insertionSort(arr);
//        for (int num : arr) {
//            System.out.print(num + " ");
//        }
//    }




    //Bubble Sort
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Troca
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


//    public static void main(String[] args) {
//        int[] arr = {64, 25, 12, 22, 11};
//        insertionSort(arr);
//        for (int num : arr) {
//            System.out.print(num + " ");
//        }
//    }


    //Merge Sort

    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }

        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        // Divide o array em dois subarrays
        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

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


//    public static void main(String[] args) {
//        int[] arr = {64, 25, 12, 22, 11};
//        mergeSort(arr);
//        for (int num : arr) {
//            System.out.print(num + " ");
//        }
//    }





    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            // Ordena as duas partes separadas
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Função para particionar o array e retornar o índice do pivô
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);  // Índice do menor elemento

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // Troca arr[i] com arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Troca o pivô com o elemento em arr[i + 1]
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }


//    public static void main(String[] args) {
//        int[] arr = {64, 25, 12, 22, 11};
//        quickSort(arr, 0, arr.length - 1);
//        for (int num : arr) {
//            System.out.print(num + " ");
//        }
//    }




/*Heap Sort*/
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Constrói a max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extrai elementos da heap um por um
        for (int i = n - 1; i >= 0; i--) {
            // Troca o root (maior) com o último elemento
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Chama heapify na raiz
            heapify(arr, i, 0);
        }
    }

    // Função para transformar o array em uma heap
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }



    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        heapSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }


}
