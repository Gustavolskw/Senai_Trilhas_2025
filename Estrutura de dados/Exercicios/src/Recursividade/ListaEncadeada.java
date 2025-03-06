package Recursividade;

public class ListaEncadeada {
    private static class Node {
        int valor;
        Node proximo;

        public Node(int valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }

    public static int contarNos(Node head) {
        if (head == null) return 0;
        return 1 + contarNos(head.proximo);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.proximo = new Node(2);
        head.proximo.proximo = new Node(3);
        head.proximo.proximo.proximo = new Node(4);

        System.out.println("Número de nós: " + contarNos(head));
    }
}
