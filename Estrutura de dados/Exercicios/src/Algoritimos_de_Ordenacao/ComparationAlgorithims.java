package Algoritimos_de_Ordenacao;

public class ComparationAlgorithims {
    /*Compare o desempenho de Merge Sort, Quick Sort e Heap Sort com listas
de 100, 1000 e 10.000 elementos aleatórios. Qual algoritmo apresenta o
melhor desempenho?*/


    static double  mergeSortTime;
    static double quickSortTime;
    static double heapSortTime;


    public static void heapSort(int[] arr) {
        long startTime = System.nanoTime();
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
        long endTime = System.nanoTime();
        long durationSortTime = endTime - startTime;
        double durationInMs = durationSortTime / 1_000_000.0;
        heapSortTime += durationInMs;
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


    public static void quickSort(int[] arr, int low, int high) {
        long startTime = System.nanoTime();
        if (low < high) {
            int pi = partition(arr, low, high);

            // Ordena as duas partes separadas
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
        long endTime = System.nanoTime();
        long durationSortTime = endTime - startTime;
        double durationInMs = durationSortTime / 1_000_000.0;
        quickSortTime += durationInMs;
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


    public static void mergeSort(int[] arr) {
        long startTime = System.nanoTime();
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
        long endTime = System.nanoTime();
        long durationSortTime = endTime - startTime;
        double durationInMs = durationSortTime / 1_000_000.0;
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


    public static void main(String[] args) {

        int[] arrHund = new  int[100];
        int[] arrThous = new  int[1000];
        int[] arrTenThous = new  int[10000];
        for(int i = 0; i < 100; i++){
        arrHund[i] = (int)(Math.random() * 100);
        }

        for(int i = 0; i < 1000; i++){
            arrThous[i] = (int)(Math.random() * 100);
        }

        for(int i = 0; i < 10000; i++){
            arrTenThous[i] = (int)(Math.random() * 100);
        }

        System.out.println("Lista de 100 Elementos");
        mergeSort(arrHund);
        heapSort(arrHund);
        quickSort(arrHund, 0 ,arrHund.length-1);
        System.out.println("Merge Sort: " + mergeSortTime );
        System.out.println("Quick Sort: " + quickSortTime );
        System.out.println("heap Sort: " + heapSortTime );
        quickSortTime = 0;
        heapSortTime = 0;
        mergeSortTime = 0;
        System.out.println("===============================================");

        System.out.println("Lista de 1000 Elementos");
        mergeSort(arrThous);
        heapSort(arrThous);
        quickSort(arrThous, 0 ,arrThous.length-1);
        System.out.println("Merge Sort: " + mergeSortTime );
        System.out.println("Quick Sort: " + quickSortTime );
        System.out.println("heap Sort: " + heapSortTime );
        quickSortTime = 0;
        heapSortTime = 0;
        mergeSortTime = 0;
        System.out.println("===============================================");

        System.out.println("Lista de 10000 Elementos");
        mergeSort(arrTenThous);
        heapSort(arrTenThous);
        quickSort(arrTenThous, 0 ,arrTenThous.length-1);
        System.out.println("Merge Sort: " + mergeSortTime );
        System.out.println("Quick Sort: " + quickSortTime );
        System.out.println("heap Sort: " + heapSortTime );
        quickSortTime = 0;
        heapSortTime = 0;
        mergeSortTime = 0;
        System.out.println("===============================================");


    }
}
