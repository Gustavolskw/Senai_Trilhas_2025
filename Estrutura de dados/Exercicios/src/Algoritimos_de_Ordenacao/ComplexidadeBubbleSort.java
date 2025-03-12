package Algoritimos_de_Ordenacao;

public class ComplexidadeBubbleSort {

    public static void bubbleSort(int[] arr) {
        long startTime = System.nanoTime();  // Marca o tempo de início

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Troca os elementos
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        long endTime = System.nanoTime();  // Marca o tempo de fim
        long durationBubbleSort = endTime - startTime;  // Calcula o tempo total de execução
        double durationInMs = durationBubbleSort / 1_000_000.0;
        System.out.println("Duração do Bubble Sort: " + durationInMs + " milisegundos");
    }

    public static void main(String[] args) {
        int[] arr = new int[100];

        for (int i = 0; i < 100; i++) {
            arr[i] = (int)(Math.random()*100);
        }
        System.out.println("Array não ordenado:");
        bubbleSort(arr);
        int[] arr2 = new int[100];
        for (int i = 0; i <100; i++) {
            arr2[i] = i;
        }
        System.out.println("Array ordenado:");
       bubbleSort(arr2);

    }
}
