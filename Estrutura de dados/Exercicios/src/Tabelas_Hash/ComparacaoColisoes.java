package Tabelas_Hash;

public class ComparacaoColisoes {
    public static void main(String[] args) {
        int tamanho = 1000;

        // Teste com Encadeamento
        TabelaHashEncadeada encadeamento = new TabelaHashEncadeada(tamanho);
        long startTime = System.nanoTime();
        for (int i = 0; i < tamanho; i++) {
            encadeamento.inserir(i, "Valor " + i);
        }
        long endTime = System.nanoTime();
        long durationEncadeamentoInsercao = endTime - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < tamanho; i++) {
            encadeamento.buscar(i);
        }
        endTime = System.nanoTime();
        long durationEncadeamentoBusca = endTime - startTime;

        // Teste com Probing Linear
        TabelaHashProbingLinear probing = new TabelaHashProbingLinear(tamanho);
        startTime = System.nanoTime();
        for (int i = 0; i < tamanho; i++) {
            probing.inserir(i, "Valor " + i);
        }
        endTime = System.nanoTime();
        long durationProbingInsercao = endTime - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < tamanho; i++) {
            probing.buscar(i);
        }
        endTime = System.nanoTime();
        long durationProbingBusca = endTime - startTime;

        // Exibindo resultados
        System.out.println("Tempo de inserção com Encadeamento: " + durationEncadeamentoInsercao);
        System.out.println("Tempo de busca com Encadeamento: " + durationEncadeamentoBusca);
        System.out.println("Tempo de inserção com Probing Linear: " + durationProbingInsercao);
        System.out.println("Tempo de busca com Probing Linear: " + durationProbingBusca);
    }
}
