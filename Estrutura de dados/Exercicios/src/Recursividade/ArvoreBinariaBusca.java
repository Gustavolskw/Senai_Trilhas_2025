package Recursividade;

class ArvoreBinariaBusca {

    class Node {
        int valor;
        Node esquerda, direita;

        public Node(int valor) {
            this.valor = valor;
            esquerda = direita = null;
        }
    }

    Node raiz;

    public boolean buscar(Node raiz, int valor) {
        if (raiz == null) {
            return false;
        }
        if (raiz.valor == valor) {
            return true;
        } else if (valor < raiz.valor) {
            return buscar(raiz.esquerda, valor);
        } else {
            return buscar(raiz.direita, valor);
        }
    }

    public static void main(String[] args) {
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
        arvore.raiz = arvore.new Node(10);
        arvore.raiz.esquerda = arvore.new Node(5);
        arvore.raiz.direita = arvore.new Node(15);

        System.out.println("Elemento encontrado: " + arvore.buscar(arvore.raiz, 15));
    }
}