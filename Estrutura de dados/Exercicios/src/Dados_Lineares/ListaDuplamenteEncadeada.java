package Dados_Lineares;

class ListaDuplamenteEncadeada {
    private Node inicio;
    private Node fim;


    static class Node {
        int valor;
        Node proximo;
        Node anterior;

        Node(int valor) {
            this.valor = valor;
            this.proximo = null;
            this.anterior = null;
        }
    }


    public void inserirNoInicio(int valor) {
        Node novoNo = new Node(valor);

        if (inicio == null) {
            inicio = fim = novoNo;
        } else {
            novoNo.proximo = inicio;
            inicio.anterior = novoNo;
            inicio = novoNo;
        }
    }


    public void removerDoFinal() {
        if (fim == null) {
            System.out.println("Lista vazia.");
            return;
        }

        if (inicio == fim) {
            inicio = fim = null;
        } else {
            fim = fim.anterior;
            fim.proximo = null;
        }
    }


    public void percorrerEmAmbasDirecoes() {
        System.out.println("Percorrendo da frente para trás:");
        // Percorrendo da frente para trás
        Node atual = inicio;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();

        System.out.println("Percorrendo de trás para frente:");

        atual = fim;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.anterior;
        }
        System.out.println();
    }


    public void imprimirLista() {
        Node atual = inicio;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();


        lista.inserirNoInicio(10);
        lista.inserirNoInicio(20);
        lista.inserirNoInicio(30);


        System.out.print("Lista após inserções no início: ");
        lista.imprimirLista();


        lista.percorrerEmAmbasDirecoes();


        lista.removerDoFinal();
        System.out.print("Lista após remoção do final: ");
        lista.imprimirLista();


        lista.percorrerEmAmbasDirecoes();
    }
}
