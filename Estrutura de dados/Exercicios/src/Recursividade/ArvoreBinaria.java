package Recursividade;


    public class ArvoreBinaria {
        static class Node {
            int valor;
            Node esquerda, direita;

            public Node(int item) {
                valor = item;
                esquerda = direita = null;
            }
        }

        Node raiz;

        public void inOrder(Node raiz) {
            if (raiz != null) {
                inOrder(raiz.esquerda);
                System.out.print(raiz.valor + " ");
                inOrder(raiz.direita);
            }
        }

        public void preOrder(Node raiz) {
            if (raiz != null) {
                System.out.print(raiz.valor + " ");
                preOrder(raiz.esquerda);
                preOrder(raiz.direita);
            }
        }

        public void postOrder(Node raiz) {
            if (raiz != null) {
                postOrder(raiz.esquerda);
                postOrder(raiz.direita);
                System.out.print(raiz.valor + " ");
            }
        }

        public static void main(String[] args) {
            ArvoreBinaria arvore = new ArvoreBinaria();
            arvore.raiz = new Node(1);
            arvore.raiz.esquerda = new Node(2);
            arvore.raiz.direita = new Node(3);
            arvore.raiz.esquerda.esquerda = new Node(4);
            arvore.raiz.esquerda.direita = new Node(5);

            System.out.print("In-Order: ");
            arvore.inOrder(arvore.raiz);

            System.out.print("\nPre-Order: ");
            arvore.preOrder(arvore.raiz);

            System.out.print("\nPost-Order: ");
            arvore.postOrder(arvore.raiz);
        }
    }

