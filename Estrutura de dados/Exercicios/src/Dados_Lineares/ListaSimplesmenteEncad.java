package Dados_Lineares;

public class ListaSimplesmenteEncad {

    private Node inicio;


    static class Node {
        int valor;
        Node proximo;

        Node(int valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }


    public void inserirNoInicio(int valor) {
        Node novoNo = new Node(valor);
        novoNo.proximo = inicio;
        inicio = novoNo;
    }


    public void inserirNoFinal(int valor) {
        Node novoNo = new Node(valor);
        if (inicio == null) {
            inicio = novoNo;
            return;
        }

        Node atual = inicio;
        while (atual.proximo != null) {
            atual = atual.proximo;
        }
        atual.proximo = novoNo;
    }


    public void removerDaPosicao(int posicao) {
        if (inicio == null) {
            System.out.println("Lista vazia.");
            return;
        }

        if (posicao == 0) {
            inicio = inicio.proximo;
            return;
        }

        Node atual = inicio;
        int cont = 0;
        while (atual != null && cont < posicao - 1) {
            atual = atual.proximo;
            cont++;
        }

        if (atual == null || atual.proximo == null) {
            System.out.println("Posição inválida.");
            return;
        }

        atual.proximo = atual.proximo.proximo;
    }


    public boolean buscarPorValor(int valor) {
        Node atual = inicio;
        while (atual != null) {
            if (atual.valor == valor) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
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
        ListaSimplesmenteEncad lista = new ListaSimplesmenteEncad();


        lista.inserirNoInicio(10);
        lista.inserirNoInicio(20);
        lista.inserirNoFinal(30);
        lista.inserirNoFinal(40);


        System.out.print("Lista após inserções: ");
        lista.imprimirLista();


        int valorBusca = 30;
        System.out.println("Elemento " + valorBusca + " encontrado? " + lista.buscarPorValor(valorBusca));


        lista.removerDaPosicao(2);
        System.out.print("Lista após remoção da posição 2: ");
        lista.imprimirLista();

    }
}
