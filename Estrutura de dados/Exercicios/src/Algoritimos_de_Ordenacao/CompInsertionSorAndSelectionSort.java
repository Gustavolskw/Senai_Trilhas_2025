package Algoritimos_de_Ordenacao;

public class CompInsertionSorAndSelectionSort {

    public static void insertionSort(int[] arr) {
        int comparation = 0;
        for (int i = 1; i < arr.length; i++) {
            comparation++;
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {

                arr[j + 1] = arr[j];
                j--;

            }
            arr[j + 1] = key;

        }
        System.out.println("insertion Sort Numero de comparações: " + comparation);
    }

    public static void selectionSort(int[] arr) {
        int comparation = 0 ;
        for (int i = 0; i < arr.length - 1; i++) {
            comparation++;
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {

                if (arr[j] < arr[minIndex]) {

                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;

        }
        System.out.println("Slection Sort Numero de comparações: " + comparation);
    }

        public static void main(String[] args) {
        int[] arr =new int[150];
        for (int i = 0; i< 150; i++){
            arr[i] = (int) (Math.random()*100);
        }
        selectionSort(arr);
        insertionSort(arr);
    }


}
