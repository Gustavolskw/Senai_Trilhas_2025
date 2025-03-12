package Casos_de_uso;

import java.util.Scanner;

public class Fila {

    private int[] fila;
    private int frente, tras, capacidade, tamanho;

    // Construtor para inicializar a fila circular
    public Fila(int capacidade) {
        this.capacidade = capacidade;
        fila = new int[capacidade];
        frente = 0;
        tras = -1;
        tamanho = 0;
    }

    // Verificar se a fila está cheia
    public boolean isFull() {
        return tamanho == capacidade;
    }

    // Verificar se a fila está vazia
    public boolean isEmpty() {
        return tamanho == 0;
    }

    // Enqueue: Inserir um elemento no final da fila
    public void enqueue(int cliente) {
        if (isFull()) {
            System.out.println("A fila está cheia! Não é possível adicionar mais clientes.");
        } else {
            tras = (tras + 1) % capacidade;
            fila[tras] = cliente;
            tamanho++;
            System.out.println("Impressão " + cliente + " entrou na fila.");
        }
    }

    // Dequeue: Remover o elemento do início da fila
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("A fila está vazia! Não há clientes para atender.");
            return -1;
        } else {
            int clienteAtendido = fila[frente];
            frente = (frente + 1) % capacidade;
            tamanho--;
            return clienteAtendido;
        }
    }

    // Exibir os clientes na fila
    public void exibirFila() {
        if (isEmpty()) {
            System.out.println("A fila está vazia.");
        } else {
            System.out.print("PDF2.Fila: ");
            for (int i = 0; i < tamanho; i++) {
                System.out.print(fila[(frente + i) % capacidade] + " ");
            }
            System.out.println();
        }
    }

    // Simular o atendimento no banco
    public static void simularAtendimento() {
        Scanner sc = new Scanner(System.in);

        // Criar uma fila circular com capacidade de 5 clientes
        Dados_Lineares.Fila filaBanco = new Dados_Lineares.Fila(5);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Adicionar Impressão à fila");
            System.out.println("2 - Imprimir");
            System.out.println("3 - Exibir fila");
            System.out.println("4 - Sair");
            int escolha = sc.nextInt();

            if (escolha == 1) {
                System.out.print("Digite o número do protocolo: ");
                int cliente = sc.nextInt();
                filaBanco.enqueue(cliente);
            } else if (escolha == 2) {
                int clienteAtendido = filaBanco.dequeue();
                if (clienteAtendido != -1) {
                    System.out.println("Impressão " + clienteAtendido + " foi feita.");
                }
            } else if (escolha == 3) {
                filaBanco.exibirFila();
            } else if (escolha == 4) {
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }


    public static void main(String[] args) {
        simularAtendimento();
    }
}
