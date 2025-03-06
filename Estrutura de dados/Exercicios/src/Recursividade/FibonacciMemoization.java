package Recursividade;

import java.util.HashMap;

public class FibonacciMemoization {
    private static final HashMap<Integer, Integer> memo = new HashMap<>();
    private static int index = 0;

    public static int fibonacci(int n) {

        if (n == 0) return 0;
        if (n == 1) return 1;
        if (memo.containsKey(n)) return memo.get(n);

        int result = fibonacci(n - 1) + fibonacci(n - 2);
        memo.put(n, result);
        index++;
        System.out.println(index);
        return result;

    }

    public static void main(String[] args) {
        int n = 6;
        System.out.println("Fibonacci de " + n + " é: " + fibonacci(n));
        System.out.println("Fibonacci de " + n + " é: " + fibonacci(9));
    }
}
