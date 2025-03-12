package Tabelas_Hash;

public class TabelaHashProbingLinear {
    private Entry[] tabela;

    // Construtor da tabela hash com probing linear
    public TabelaHashProbingLinear(int tamanho) {
        tabela = new Entry[tamanho];
    }

    // Função de hash simples para inteiros
    private int hash(int chave) {
        return chave % tabela.length;
    }

    // Função para inserir elementos na tabela
    public void inserir(int chave, String valor) {
        int indice = hash(chave);
        while (tabela[indice] != null) {
            indice = (indice + 1) % tabela.length;  // Probing linear
        }
        tabela[indice] = new Entry(chave, valor);
    }

    // Função para buscar elementos na tabela
    public String buscar(int chave) {
        int indice = hash(chave);
        while (tabela[indice] != null) {
            if (tabela[indice].chave == chave) {
                return tabela[indice].valor;
            }
            indice = (indice + 1) % tabela.length;  // Probing linear
        }
        return null;
    }

    // Função para remover elementos da tabela
    public boolean remover(int chave) {
        int indice = hash(chave);
        while (tabela[indice] != null) {
            if (tabela[indice].chave == chave) {
                tabela[indice] = null;
                return true;
            }
            indice = (indice + 1) % tabela.length;  // Probing linear
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
        TabelaHashProbingLinear tabela = new TabelaHashProbingLinear(10);
        tabela.inserir(42, "João");
        tabela.inserir(13, "Maria");
        tabela.inserir(25, "José");

        System.out.println("Valor para chave 42: " + tabela.buscar(42));  // João
        tabela.remover(42);
        System.out.println("Valor para chave 42 após remoção: " + tabela.buscar(42));  // null
    }
}
