package Algoritimos_de_Ordenacao;

public class SelectionSortSimpleImpl {

    public static void selectionSort(int[] arr) {
        int trocas = 0;
        int compare = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                compare++;
                if (arr[j] < arr[minIndex]) {
                    trocas++;
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        System.out.println("Comparações Realizadas: " + compare);
        System.out.println("Trocas Realizadas: " + trocas);
    }


    public static void insertionSort(String[] arr) {
        int n = arr.length;


        for (int i = 1; i < n; i++) {
            String chave = arr[i];
            int j = i - 1;


            while (j >= 0 && arr[j].compareTo(chave) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j + 1] = chave;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11, 58, 278, 1, 96, 17};
        selectionSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }


        String[] stringArr = {"banana", "maçã", "laranja", "uva", "morango", "kiwi", "abacaxi"};


        insertionSort(stringArr);


        System.out.println("\n\nStrings ordenadas alfabeticamente:");
        for (String s : stringArr) {
            System.out.print(s + " ");
        }
    }

}
