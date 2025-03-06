package Dados_nao_lineares;

class BST {
    class Node {
        int valor;
        Node esquerda, direita;

        public Node(int valor) {
            this.valor = valor;
            esquerda = direita = null;
        }
    }

    Node raiz;

    // Inserir na árvore binária de busca
    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private Node inserirRecursivo(Node raiz, int valor) {
        if (raiz == null) {
            raiz = new Node(valor);
            return raiz;
        }

        if (valor < raiz.valor) {
            raiz.esquerda = inserirRecursivo(raiz.esquerda, valor);
        } else if (valor > raiz.valor) {
            raiz.direita = inserirRecursivo(raiz.direita, valor);
        }
        return raiz;
    }

    // Buscar um valor na árvore
    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(Node raiz, int valor) {
        if (raiz == null) {
            return false;
        }
        if (valor == raiz.valor) {
            return true;
        } else if (valor < raiz.valor) {
            return buscarRecursivo(raiz.esquerda, valor);
        } else {
            return buscarRecursivo(raiz.direita, valor);
        }
    }

    // Remover um valor da árvore
    public void remover(int valor) {
        raiz = removerRecursivo(raiz, valor);
    }

    private Node removerRecursivo(Node raiz, int valor) {
        if (raiz == null) {
            return null;
        }
        if (valor < raiz.valor) {
            raiz.esquerda = removerRecursivo(raiz.esquerda, valor);
        } else if (valor > raiz.valor) {
            raiz.direita = removerRecursivo(raiz.direita, valor);
        } else {
            if (raiz.esquerda == null) {
                return raiz.direita;
            } else if (raiz.direita == null) {
                return raiz.esquerda;
            }
            raiz.valor = minValor(raiz.direita);
            raiz.direita = removerRecursivo(raiz.direita, raiz.valor);
        }
        return raiz;
    }

    private int minValor(Node raiz) {
        int minValue = raiz.valor;
        while (raiz.esquerda != null) {
            minValue = raiz.esquerda.valor;
            raiz = raiz.esquerda;
        }
        return minValue;
    }

    public static void main(String[] args) {
        BST arvore = new BST();
        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(70);
        arvore.inserir(60);
        arvore.inserir(80);

        System.out.println("Buscar 40: " + arvore.buscar(40));  // true
        System.out.println("Buscar 100: " + arvore.buscar(100));  // false

        arvore.remover(20);
        System.out.println("Buscar 20 após remoção: " + arvore.buscar(20));  // false
    }
}

