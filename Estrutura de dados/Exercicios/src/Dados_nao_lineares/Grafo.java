package Dados_nao_lineares;

import java.util.*;

public class Grafo {

    private Map<Integer, List<Integer>> adjList;

    public Grafo() {
        adjList = new HashMap<>();
    }

    // Adicionar aresta no grafo não direcionado
    public void adicionarAresta(int v1, int v2) {
        adjList.putIfAbsent(v1, new ArrayList<>());
        adjList.putIfAbsent(v2, new ArrayList<>());
        adjList.get(v1).add(v2);
        adjList.get(v2).add(v1); // Grafo não direcionado
    }

    // Algoritmo DFS (Busca em Profundidade)
    public void DFS(int start) {
        Set<Integer> visitados = new HashSet<>();
        DFSRecursivo(start, visitados);
    }

    private void DFSRecursivo(int v, Set<Integer> visitados) {
        visitados.add(v);
        System.out.print(v + " ");

        for (int adj : adjList.get(v)) {
            if (!visitados.contains(adj)) {
                DFSRecursivo(adj, visitados);
            }
        }
    }

    // Algoritmo BFS (Busca em Largura)
    public void BFS(int start) {
        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> fila = new LinkedList<>();

        fila.add(start);
        visitados.add(start);

        while (!fila.isEmpty()) {
            int v = fila.poll();
            System.out.print(v + " ");

            for (int adj : adjList.get(v)) {
                if (!visitados.contains(adj)) {
                    fila.add(adj);
                    visitados.add(adj);
                }
            }
        }
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.adicionarAresta(0, 1);
        grafo.adicionarAresta(0, 2);
        grafo.adicionarAresta(1, 3);
        grafo.adicionarAresta(2, 4);

        System.out.print("DFS a partir do nó 0: ");
        grafo.DFS(0); // Saída esperada: 0 1 3 2 4

        System.out.println();

        System.out.print("BFS a partir do nó 0: ");
        grafo.BFS(0); // Saída esperada: 0 1 2 3 4
    }
}

