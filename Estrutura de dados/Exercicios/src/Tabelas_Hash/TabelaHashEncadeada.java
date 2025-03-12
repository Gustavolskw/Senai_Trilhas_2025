package Tabelas_Hash;

import java.util.LinkedList;

public class TabelaHashEncadeada {
    private LinkedList<Entry>[] tabela;

    // Construtor da tabela hash com encadeamento
    public TabelaHashEncadeada(int tamanho) {
        tabela = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    // Função de hash simples para inteiros
    private int hash(int chave) {
        return chave % tabela.length;
    }

    // Função para inserir elementos na tabela
    public void inserir(int chave, String valor) {
        int indice = hash(chave);
        tabela[indice].add(new Entry(chave, valor));
    }

    // Função para buscar elementos na tabela
    public String buscar(int chave) {
        int indice = hash(chave);
        for (Entry entry : tabela[indice]) {
            if (entry.chave == chave) {
                return entry.valor;
            }
        }
        return null;
    }

    // Função para remover elementos da tabela
    public boolean remover(int chave) {
        int indice = hash(chave);
        for (Entry entry : tabela[indice]) {
            if (entry.chave == chave) {
                tabela[indice].remove(entry);
                return true;
            }
        }
        return false;
    }

    // Classe interna para representar um par (chave, valor)
    static class Entry {
        int chave;
        String valor;

        Entry(int chave, String valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }

    public static void main(String[] args) {
        TabelaHashEncadeada tabela = new TabelaHashEncadeada(10);
        tabela.inserir(42, "João");
        tabela.inserir(13, "Maria");
        tabela.inserir(25, "José");

        System.out.println("Valor para chave 42: " + tabela.buscar(42));  // João
        tabela.remover(42);
        System.out.println("Valor para chave 42 após remoção: " + tabela.buscar(42));  // null
    }
}
