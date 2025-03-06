package Dados_nao_lineares;

public class MaxHeap {

    private int[] heap;
    private int tamanho;
    private int capacidade;

    public MaxHeap(int capacidade) {
        this.capacidade = capacidade;
        heap = new int[capacidade];
        tamanho = 0;
    }

    private int pai(int i) { return (i - 1) / 2; }
    private int esquerda(int i) { return 2 * i + 1; }
    private int direita(int i) { return 2 * i + 2; }

    private void heapify(int i) {
        int maior = i;
        int esq = esquerda(i);
        int dir = direita(i);

        if (esq < tamanho && heap[esq] > heap[maior]) {
            maior = esq;
        }
        if (dir < tamanho && heap[dir] > heap[maior]) {
            maior = dir;
        }
        if (maior != i) {
            int temp = heap[i];
            heap[i] = heap[maior];
            heap[maior] = temp;
            heapify(maior);
        }
    }

    public void inserir(int valor) {
        if (tamanho == capacidade) {
            System.out.println("Heap cheia!");
            return;
        }
        heap[tamanho] = valor;
        int i = tamanho;
        tamanho++;

        while (i != 0 && heap[pai(i)] < heap[i]) {
            int temp = heap[i];
            heap[i] = heap[pai(i)];
            heap[pai(i)] = temp;
            i = pai(i);
        }
    }

    public int remover() {
        if (tamanho <= 0) {
            return Integer.MIN_VALUE;
        }
        if (tamanho == 1) {
            tamanho--;
            return heap[0];
        }

        int root = heap[0];
        heap[0] = heap[tamanho - 1];
        tamanho--;
        heapify(0);

        return root;
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.inserir(3);
        maxHeap.inserir(2);
        maxHeap.inserir(15);
        maxHeap.inserir(5);

        System.out.println("Maior valor removido: " + maxHeap.remover());  // Saída: 15
    }
}
