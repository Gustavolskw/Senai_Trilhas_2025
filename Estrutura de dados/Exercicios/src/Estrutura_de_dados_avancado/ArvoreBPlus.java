package Estrutura_de_dados_avancado;

import java.util.ArrayList;
import java.util.Collections;

public class ArvoreBPlus {
    private static final int MIN_DEGREE = 2;  // Grau mínimo da árvore B+
    private Node raiz;

    public ArvoreBPlus() {
        raiz = new Node(true);  // Inicializa a raiz como um nó folha
    }

    // Classe para representar um nó
    private class Node {
        boolean folha;  // Se o nó é folha ou não
        ArrayList<Integer> chaves;
        ArrayList<Node> filhos;

        Node(boolean folha) {
            this.folha = folha;
            chaves = new ArrayList<>();
            filhos = new ArrayList<>();
        }
    }

    // Inserir valor na árvore B+
    public void inserir(int valor) {
        Node raiz = this.raiz;
        if (raiz.chaves.size() == 2 * MIN_DEGREE - 1) {
            Node novaRaiz = new Node(false);
            novaRaiz.filhos.add(this.raiz);
            dividir(novaRaiz, 0, raiz);
            this.raiz = novaRaiz;
        }
        inserirNaoCheio(this.raiz, valor);
    }

    // Dividir nó quando ele estiver cheio
    private void dividir(Node pai, int i, Node cheio) {
        Node novo = new Node(cheio.folha);
        int mid = MIN_DEGREE - 1;
        pai.chaves.add(i, cheio.chaves.get(mid));
        pai.filhos.add(i + 1, novo);
        novo.chaves.addAll(cheio.chaves.subList(mid + 1, cheio.chaves.size()));
        if (!cheio.folha) {
            novo.filhos.addAll(cheio.filhos.subList(mid + 1, cheio.filhos.size()));
        }
        cheio.chaves.subList(mid, cheio.chaves.size()).clear();
        cheio.filhos.subList(mid + 1, cheio.filhos.size()).clear();
    }

    // Inserir valor em nó não cheio
    private void inserirNaoCheio(Node x, int valor) {
        int i = Collections.binarySearch(x.chaves, valor);
        if (i >= 0) return;
        i = -(i + 1);
        if (x.folha) {
            x.chaves.add(i, valor);
        } else {
            Node filho = x.filhos.get(i);
            if (filho.chaves.size() == 2 * MIN_DEGREE - 1) {
                dividir(x, i, filho);
                if (valor > x.chaves.get(i)) {
                    i++;
                }
            }
            inserirNaoCheio(x.filhos.get(i), valor);
        }
    }

    // Exibir a estrutura da árvore B+
    public void exibir() {
        exibir(this.raiz, 0);
    }

    private void exibir(Node x, int nivel) {
        System.out.print("Nivel " + nivel + ": ");
        for (int chave : x.chaves) {
            System.out.print(chave + " ");
        }
        System.out.println();
        if (!x.folha) {
            for (Node filho : x.filhos) {
                exibir(filho, nivel + 1);
            }
        }
    }

    public static void main(String[] args) {
        ArvoreBPlus arvoreBPlus = new ArvoreBPlus();
        int[] valores = {15, 5, 25, 10, 20, 30, 35};

        // Inserir valores e exibir a estrutura após cada inserção
        for (int valor : valores) {
            arvoreBPlus.inserir(valor);
            arvoreBPlus.exibir();
        }
    }
}
