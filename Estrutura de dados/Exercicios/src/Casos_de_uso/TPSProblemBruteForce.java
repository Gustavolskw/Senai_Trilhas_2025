package Casos_de_uso;

import java.util.Arrays;

public class TPSProblemBruteForce {
    private int[][] graph; // matrix
    private int n; //graph size
    private int minCost = Integer.MAX_VALUE;
    private int[] bestPath; // just new array list to save the best path on array

    public TPSProblemBruteForce(int[][] graph) {
        this.graph = graph;
        this.n = graph.length;
        this.bestPath  = new int[n];
    }

    public int solve() {
        int[] path = new int[n];
        for (int i = 0; i < n; i++) {
            path[i] = i;
        }
        permute(path, 0 );
        System.out.println("Melhor caminho: " + Arrays.toString(bestPath));
        return minCost;
    }

    private void permute(int[] path, int index) {
        if (index == n) {
            int cost  = calculateCost(path);
            if (cost < minCost) {
                minCost = cost;
                bestPath = path.clone();
            }
            return;
        }
        for (int i = index; i < n; i++) {
            swap(path, i, index);
            permute(path, index + 1); // Chama a recursão
            swap(path, i, index); // Desfaz a troca (backtracking)
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private int calculateCost(int[] path) {
        int cost = 0;
        for(int i  = 0; i < n -1; i++){
            cost += graph[path[i]][path[i+1]];
        }
        cost += graph[path[n-1]][path[0]];
        return cost;
    }



    public static void main(String[] args) {
        int[][] graph = {
                { 0, 10, 15, 78 },
                { 11, 0, 28, 25 },
                { 18, 47, 0, 37 },
                { 87, 8, 85, 0 }
        };

        TPSProblemBruteForce tsp = new TPSProblemBruteForce(graph);
        int minCost = tsp.solve();
        System.out.println("Custo mínimo: " + minCost);

    }

}

