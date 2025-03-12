package Casos_de_uso;
import java.util.*;

class Graph {
    private Map<String, List<String>> adjList;

    public Graph() {
        this.adjList = new HashMap<>();
    }

    // Adiciona um usuário à rede
    public void addUser(String user) {
        adjList.putIfAbsent(user, new ArrayList<>());
    }

    // Adiciona uma amizade entre dois usuários (grafo não-direcionado)
    public void addFriendship(String user1, String user2) {
        adjList.putIfAbsent(user1, new ArrayList<>());
        adjList.putIfAbsent(user2, new ArrayList<>());
        adjList.get(user1).add(user2);
        adjList.get(user2).add(user1);
    }

    // Exibe a rede social
    public void printNetwork() {
        for (String user : adjList.keySet()) {
            System.out.println(user + " -> " + adjList.get(user));
        }
    }

    // Algoritmo de BFS para encontrar a menor distância entre dois usuários
    public int shortestPath(String start, String end) {
        if (!adjList.containsKey(start) || !adjList.containsKey(end)) return -1;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, Integer> distance = new HashMap<>();

        queue.add(start);
        visited.add(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDistance = distance.get(current);

            if (current.equals(end)) return currentDistance;

            for (String neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    distance.put(neighbor, currentDistance + 1);
                }
            }
        }
        return -1; // Se não houver caminho entre os usuários
    }


    public static void main(String[] args) {
        Graph socialNetwork = new Graph();

        // Adiciona usuários
        socialNetwork.addUser("Alice");
        socialNetwork.addUser("Bob");
        socialNetwork.addUser("Charlie");
        socialNetwork.addUser("David");
        socialNetwork.addUser("Eve");

        // Adiciona amizades
        socialNetwork.addFriendship("Alice", "Bob");
        socialNetwork.addFriendship("Alice", "Charlie");
        socialNetwork.addFriendship("Bob", "David");
        socialNetwork.addFriendship("Charlie", "Eve");
        socialNetwork.addFriendship("David", "Eve");

        // Exibir rede social
        System.out.println("Rede Social:");
        socialNetwork.printNetwork();

        // Busca da menor distância entre Alice e Eve
        System.out.println("\nMenor distância entre Alice e Eve: " + socialNetwork.shortestPath("Alice", "Eve"));
        System.out.println("Menor distância entre Bob e Eve: " + socialNetwork.shortestPath("Bob", "Eve"));
        System.out.println("Menor distância entre Charlie e David: " + socialNetwork.shortestPath("Charlie", "David"));
    }
}
