package Dados_nao_lineares;

public class AVLTree {
    class Node {
        int valor, altura;
        Node esquerda, direita;

        public Node(int valor) {
            this.valor = valor;
            altura = 1;
            esquerda = direita = null;
        }
    }

    private Node raiz;

    // Obter altura do nó
    private int altura(Node N) {
        if (N == null) {
            return 0;
        }
        return N.altura;
    }

    // Calcular o fator de balanceamento
    private int getBalance(Node N) {
        if (N == null) {
            return 0;
        }
        return altura(N.esquerda) - altura(N.direita);
    }

    // Rotação à direita
    private Node rotateRight(Node y) {
        Node x = y.esquerda;
        Node T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    // Rotação à esquerda
    private Node rotateLeft(Node x) {
        Node y = x.direita;
        Node T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    // Inserir um novo valor na árvore AVL
    public Node inserir(Node node, int valor) {
        if (node == null) {
            return new Node(valor);
        }

        if (valor < node.valor) {
            node.esquerda = inserir(node.esquerda, valor);
        } else if (valor > node.valor) {
            node.direita = inserir(node.direita, valor);
        } else {
            return node;
        }

        node.altura = Math.max(altura(node.esquerda), altura(node.direita)) + 1;

        int balance = getBalance(node);

        if (balance > 1 && valor < node.esquerda.valor) {
            return rotateRight(node);
        }

        if (balance < -1 && valor > node.direita.valor) {
            return rotateLeft(node);
        }

        if (balance > 1 && valor > node.esquerda.valor) {
            node.esquerda = rotateLeft(node.esquerda);
            return rotateRight(node);
        }

        if (balance < -1 && valor < node.direita.valor) {
            node.direita = rotateRight(node.direita);
            return rotateLeft(node);
        }

        return node;
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.raiz = tree.inserir(tree.raiz, 10);
        tree.raiz = tree.inserir(tree.raiz, 20);
        tree.raiz = tree.inserir(tree.raiz, 30);

        System.out.println("Árvore AVL criada e balanceada.");
    }
}

