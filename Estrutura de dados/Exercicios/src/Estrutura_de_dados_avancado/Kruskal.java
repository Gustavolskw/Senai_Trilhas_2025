package Estrutura_de_dados_avancado;

import java.util.*;

public class Kruskal {
    // Classe para representar uma aresta
    static class Edge {
        int u, v, weight;
        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public static List<Edge> kruskal(List<Edge> edges, int numVertices) {
        UnionFind uf = new UnionFind(numVertices);
        List<Edge> mst = new ArrayList<>();

        // Ordena as arestas pelo peso
        edges.sort(Comparator.comparingInt(e -> e.weight));

        for (Edge edge : edges) {
            if (!uf.connected(edge.u, edge.v)) {
                uf.union(edge.u, edge.v);
                mst.add(edge);
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        List<Edge> mst = kruskal(edges, 4);

        System.out.println("Árvore Geradora Mínima:");
        for (Edge e : mst) {
            System.out.println("Aresta: " + e.u + " - " + e.v + " Peso: " + e.weight);
        }
    }

    public static class DetectaCiclo {
        public static boolean hasCycle(List<Edge> edges, int numVertices) {
            UnionFind uf = new UnionFind(numVertices);

            for (Edge edge : edges) {
                if (uf.connected(edge.u, edge.v)) {
                    return true;  // Se já estão conectados, há um ciclo
                }
                uf.union(edge.u, edge.v);
            }
            return false;  // Não há ciclo
        }

        public static void main(String[] args) {
            List<Edge> edges = new ArrayList<>();
            edges.add(new Edge(0, 1, 10));
            edges.add(new Edge(1, 2, 20));
            edges.add(new Edge(2, 0, 30));  // Causando ciclo

            System.out.println("O grafo tem ciclo? " + hasCycle(edges, 3));  // true
        }
    }

}
