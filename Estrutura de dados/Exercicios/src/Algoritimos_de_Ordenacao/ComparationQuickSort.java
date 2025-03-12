package Algoritimos_de_Ordenacao;

import java.util.Arrays;

public class ComparationQuickSort {
    /*Modifique o Quick Sort para selecionar o pivô como o valor mediano de três
elementos (início, meio e fim) e compare o desempenho com a versão
original.*/

    static double quickSortTime;
    static double quickSortModTime;

//Original
    public static void quickSort(int[] arr, int low, int high) {
        long startTime = System.nanoTime();
        if (low < high) {
            int pi = partition(arr, low, high);

            // Ordena as duas partes separadas
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
        long endTime = System.nanoTime();
        long durationBubbleSort = endTime - startTime;
        double durationInMs = durationBubbleSort / 1_000_000.0;
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

    //modificada


    // Função modificada de QuickSort com pivô sendo o valor mediano
    public static void quickSortMod(int[] arr, int low, int high) {
        long startTime = System.nanoTime();
        if (low < high) {
            // Encontra o índice do pivô usando o valor mediano
            int med = (low + high) / 2;  // Calculando o índice do meio

            // Escolhe o pivô como o valor mediano de arr[low], arr[med], arr[high]
            int pi = partitionMod(arr, low, med, high);

            // Ordena as duas partes separadas
            quickSortMod(arr, low, pi - 1);
            quickSortMod(arr, pi + 1, high);
        }
        long endTime = System.nanoTime();
        long durationBubbleSort = endTime - startTime;
        double durationInMs = durationBubbleSort / 1_000_000.0;
        quickSortModTime += durationInMs;
    }

    // Função para particionar o array e retornar o índice do pivô
    private static int partitionMod(int[] arr, int low, int med, int high) {
        // Encontrar o pivô (valor mediano de low, mid, high)
        int pivot = medianOfThree(arr, low, med, high);

        // Coloca o pivô na posição do final
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

    // Função para encontrar o valor mediano entre low, mid, high
    private static int medianOfThree(int[] arr, int low, int med, int high) {
        int a = arr[low];
        int b = arr[med];
        int c = arr[high];

        // Ordena low, mid, high e retorna o valor mediano
        if (a > b) {
            // c é o mediano
            if (a < c) {
                return a;  // a é o mediano
            } else return Math.max(b, c);  // b é o mediano
        } else {
            // c é o mediano
            if (a > c) {
                return a;  // a é o mediano
            } else return Math.min(b, c);  // b é o mediano
        }
    }



        public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        quickSort(arr, 0, arr.length - 1);
        quickSortMod(arr, 0, arr.length - 1);

            System.out.println("Modificado: " + quickSortModTime);
            System.out.println("Original: " + quickSortTime);
            System.out.println("Diferença: "+ (quickSortModTime>quickSortTime? quickSortModTime- quickSortTime +" Quick Sort original mais rapido" : quickSortTime - quickSortModTime +" Quick Sort modificado mais rapido"));
        }
}
