package Algoritimos_de_Ordenacao;

public class StableSelectionSort {


    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {

                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }

            }
            // Em vez de trocar diretamente, armazena o menor elemento
            int minValue = arr[minIndex];

            // Move os elementos à direita para preservar a estabilidade
            while (minIndex > i) {
                arr[minIndex] = arr[minIndex - 1];
                minIndex--;
            }

            // Insere o menor elemento na posição correta
            arr[i] = minValue;
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 64, 25, 12, 22, 25, 11, 11};
        selectionSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

}
