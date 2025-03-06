package Dados_Lineares;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Vetores {
    static Integer[] arr = new Integer[10];
    public static void main(String[] args) {

        arrayPopulate();
        Arrays.stream(arr).forEach(System.out::println);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(searchForNumber(n));


        System.out.println("Numeros Para droppar do array:");
        Scanner sc2 = new Scanner(System.in);
        int dropNum = sc2.nextInt();
        dropNumberFromArray(dropNum);

        System.out.println("Array final:");

        showArray();

    }

    public static void arrayPopulate(){
        for(int i = 0; i < 10; i++){
            arr[i] =  (int)(Math.random()*100);
        }
    }

    public static String searchForNumber(Integer num ){
        int index = 1;
        for(Integer i : arr){
            if(Objects.equals(i, num)) {
                return "Numero encontrado na posição: "+index +" de valor : "+num;
            }
            index++;
        }
        return "Numero não econtrado!";
    }

    public static void showArray(){
        for(Integer u: arr){
            System.out.println(u);
        }
    }



    public static String dropNumberFromArray(Integer num) {

        int indexToRemove = -1;
        for (int i = 0; i < arr.length; i++) {
            if (Objects.equals(arr[i], num)) {
                indexToRemove = i;
                break;
            }
        }


        if (indexToRemove == -1) {
            return "Número não encontrado!";
        }


        for (int i = indexToRemove; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }


        arr[arr.length - 1] = null;

        return "Número " + num + " removido com sucesso!";
    }
}