package Recursividade;

public class FatorialRecursivo {

    public static int fatorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * fatorial(n - 1);
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println("Fatorial de " + n + " é: " + fatorial(n));
    }
}
/*Tempo: A complexidade de tempo é O(n), pois há uma chamada recursiva para cada valor de 𝑛 até n = 1*/