package Recursividade;

public class TorreDeHanoi {

    public static void moverDiscos(int n, String origem, String destino, String auxiliar) {
        System.out.println(n);
        if (n == 1) {
            System.out.println("Mover disco 1 de " + origem + " para " + destino);
            return;
        }
        moverDiscos(n - 1, origem, auxiliar, destino);
        System.out.println("Mover disco " + n + " de " + origem + " para " + destino);
        moverDiscos(n - 1, auxiliar, destino, origem);
    }

    public static void main(String[] args) {
        int n = 6;
        moverDiscos(n, "A", "C", "B");
    }
}
/*Tempo: A complexidade de tempo é O(2^n), pois a função é chamada de maneira exponencial*/