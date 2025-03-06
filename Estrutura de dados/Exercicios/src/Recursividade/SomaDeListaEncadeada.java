package Recursividade;

import java.util.ArrayList;

public class SomaDeListaEncadeada {


    public static int soma(ArrayList<Integer> list, int index) {

        if (index >= list.size()) {
            return 0;
        }

        return list.get(index) + soma(list, index + 1);
    }

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);


        System.out.println("Soma dos elementos do ArrayList: " + soma(list, 0));
    }
}
