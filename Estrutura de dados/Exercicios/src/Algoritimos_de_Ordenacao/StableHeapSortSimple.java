package Algoritimos_de_Ordenacao;

import java.util.Arrays;

public class StableHeapSortSimple {

    public static void stableHeapSort(int[] arr) {
        int n = arr.length;
        int[] indices = new int[n];

        // Inicializa os índices originais dos elementos
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Construir o heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, indices, n, i);
        }

        // Extrair elementos do heap um por um
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            swap(indices, 0, i);
            heapify(arr, indices, i, 0);
        }
    }

    private static void heapify(int[] arr, int[] indices, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Comparação modificada: se valores forem iguais, usa o índice original
        if (left < n && (arr[left] > arr[largest] || (arr[left] == arr[largest] && indices[left] < indices[largest]))) {
            largest = left;
        }

        if (right < n && (arr[right] > arr[largest] || (arr[right] == arr[largest] && indices[right] < indices[largest]))) {
            largest = right;
        }

        // Se o maior não for o nó raiz, troca e continua
        if (largest != i) {
            swap(arr, i, largest);
            swap(indices, i, largest);
            heapify(arr, indices, n, largest);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 5, 3, 2, 1};
        System.out.println("Array antes do Heap Sort estável: " + Arrays.toString(arr));
        stableHeapSort(arr);
        System.out.println("Array depois do Heap Sort estável: " + Arrays.toString(arr));
    }
}
