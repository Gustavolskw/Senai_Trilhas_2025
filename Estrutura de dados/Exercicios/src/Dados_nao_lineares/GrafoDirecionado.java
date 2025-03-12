package Dados_nao_lineares;

import java.util.*;

public class GrafoDirecionado {

    private Map<Integer, List<Aresta>> adjList;

    public GrafoDirecionado() {
        adjList = new HashMap<>();
    }

    // Classe interna para representar uma aresta com peso
    class Aresta {
        int destino, peso;
        public Aresta(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    // Adicionar aresta com peso
    public void adicionarAresta(int origem, int destino, int peso) {
        adjList.putIfAbsent(origem, new ArrayList<>());
        adjList.get(origem).add(new Aresta(destino, peso));
    }

    // Algoritmo de Dijkstra para encontrar o caminho mais curto
    public void dijkstra(int start) {
        Map<Integer, Integer> distancias = new HashMap<>();
        PriorityQueue<Aresta> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.peso));

        // Inicializar distâncias para todos os nós como infinito, exceto o nó inicial
        for (int v : adjList.keySet()) {
            distancias.put(v, Integer.MAX_VALUE);
        }
        distancias.put(start, 0);

        pq.add(new Aresta(start, 0));

        while (!pq.isEmpty()) {
            Aresta aresta = pq.poll();
            int u = aresta.destino;
            int pesoU = aresta.peso;

            // Verificar se a distância encontrada é menor que a já registrada
            if (pesoU > distancias.get(u)) continue;

            for (Aresta adj : adjList.get(u)) {
                int v = adj.destino;
                int pesoV = adj.peso;

                // Relaxação das arestas
                if (distancias.get(u) + pesoV < distancias.get(v)) {
                    distancias.put(v, distancias.get(u) + pesoV);
                    pq.add(new Aresta(v, distancias.get(v)));
                }
            }
        }

        // Exibir as distâncias
        for (int v : distancias.keySet()) {
            System.out.println("Distância de " + start + " a " + v + " é: " + distancias.get(v));
        }
    }

    public static void main(String[] args) {
        GrafoDirecionado grafo = new GrafoDirecionado();

        grafo.adicionarAresta(0, 1, 10);
        grafo.adicionarAresta(0, 2, 5);
        grafo.adicionarAresta(1, 2, 2);
        grafo.adicionarAresta(1, 3, 1);
        grafo.adicionarAresta(2, 3, 9);
        grafo.adicionarAresta(2, 4, 2);
        grafo.adicionarAresta(3, 4, 4);

        System.out.println("Resultado do algoritmo de Dijkstra a partir do nó 0:");
        grafo.dijkstra(0);
    }
}
