package Casos_de_uso;

import java.util.Random;

public class TSPComparison {
    private static final int N = 10; // Número de cidades
    private static int[][] graph;

    public static void main(String[] args) {
        generateRandomGraph(N);
        printGraph(graph);

        // Resolver usando Força Bruta (Solução Ótima)
        TSPBruteForce tspExact = new TSPBruteForce(graph);
        int exactCost = tspExact.solve();
        System.out.println("\nSolução Ótima (Força Bruta):");
        System.out.println("Custo mínimo: " + exactCost);

        // Resolver usando Vizinho Mais Próximo
        TSPNearestNeighbor tspApprox = new TSPNearestNeighbor(graph);
        int approxCost = tspApprox.solve(0);
        System.out.println("\nSolução Aproximada (Vizinho Mais Próximo):");
        System.out.println("Custo encontrado: " + approxCost);

        // Comparação
        System.out.println("\nDiferença entre as soluções:");
        System.out.println("Aproximação foi " + ((approxCost - exactCost) * 100.0 / exactCost) + "% pior que a ótima.");
    }

    // Gerar matriz de distâncias aleatória
    private static void generateRandomGraph(int n) {
        Random rand = new Random();
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    graph[i][j] = rand.nextInt(90) + 10; // Distâncias entre 10 e 99
                }
            }
        }
    }

    // Exibir a matriz de distâncias
    private static void printGraph(int[][] graph) {
        System.out.println("Matriz de Distâncias:");
        for (int[] row : graph) {
            for (int val : row) {
                System.out.printf("%3d ", val);
            }
            System.out.println();
        }
    }

    static class TSPNearestNeighbor {
        private int[][] graph;
        private int n;

        public TSPNearestNeighbor(int[][] graph) {
            this.graph = graph;
            this.n = graph.length;
        }

        public int solve(int start) {
            boolean[] visited = new boolean[n];
            int cost = 0, current = start;
            visited[current] = true;

            for (int i = 0; i < n - 1; i++) {
                int nextCity = findNearest(current, visited);
                cost += graph[current][nextCity];
                current = nextCity;
                visited[current] = true;
            }

            cost += graph[current][start]; // Retorna ao ponto inicial
            return cost;
        }

        private int findNearest(int city, boolean[] visited) {
            int minDist = Integer.MAX_VALUE;
            int nearest = -1;

            for (int i = 0; i < n; i++) {
                if (!visited[i] && graph[city][i] < minDist) {
                    minDist = graph[city][i];
                    nearest = i;
                }
            }
            return nearest;
        }
    }

    static class TSPBruteForce {
        private int[][] graph;
        private int n;
        private int minCost = Integer.MAX_VALUE;
        private int[] bestPath;

        public TSPBruteForce(int[][] graph) {
            this.graph = graph;
            this.n = graph.length;
            this.bestPath = new int[n];
        }

        public int solve() {
            int[] path = new int[n];
            for (int i = 0; i < n; i++) {
                path[i] = i;
            }
            permute(path, 0);
            return minCost;
        }

        private void permute(int[] path, int index) {
            if (index == n) {
                int cost = calculateCost(path);
                if (cost < minCost) {
                    minCost = cost;
                    bestPath = path.clone();
                }
                return;
            }
            for (int i = index; i < n; i++) {
                swap(path, i, index);
                permute(path, index + 1);
                swap(path, i, index); // Backtracking
            }
        }

        private void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        private int calculateCost(int[] path) {
            int cost = 0;
            for (int i = 0; i < n - 1; i++) {
                cost += graph[path[i]][path[i + 1]];
            }
            cost += graph[path[n - 1]][path[0]]; // Retorno ao início
            return cost;
        }
    }



}
