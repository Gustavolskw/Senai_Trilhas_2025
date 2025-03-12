package Dados_Lineares;

public class Pilha {

    private char[] pilha;
    private int topo;
    private int capacidade;

    public Pilha(int capacidade) {
        this.capacidade = capacidade;
        pilha = new char[capacidade];
        topo = -1;
    }


    public boolean isFull() {
        return topo == capacidade - 1;
    }


    public boolean isEmpty() {
        return topo == -1;
    }


    public void push(char c) {
        if (isFull()) {
            System.out.println("A pilha está cheia!");
        } else {
            pilha[++topo] = c;
        }
    }


    public char pop() {
        if (isEmpty()) {
            System.out.println("A pilha está vazia!");
            return '\0'; // Retorna caractere nulo
        } else {
            return pilha[topo--];
        }
    }


    public char peek() {
        if (isEmpty()) {
            System.out.println("A pilha está vazia!");
            return '\0';
        } else {
            return pilha[topo];
        }
    }


    public static boolean verificarParentesesBalanceados(String expressao) {
        Pilha pilha = new Pilha(expressao.length());

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);


            if (c == '(') {
                pilha.push(c);
            }

            else if (c == ')') {

                if (pilha.isEmpty()) {
                    return false;
                }
                pilha.pop();
            }
        }

        return pilha.isEmpty();
    }

    public static void main(String[] args) {
        Pilha pilha = new Pilha(5);

        pilha.push('a');
        pilha.push('b');
        pilha.push('c');

        System.out.println("Topo da pilha: " + pilha.peek()); // Saída: c

        pilha.pop();

        System.out.println("Topo da pilha após pop: " + pilha.peek()); // Saída: b

        String expressao = "((1+2) * (3/4))";
        if (verificarParentesesBalanceados(expressao)) {
            System.out.println("A expressão está com parênteses balanceados.");
        } else {
            System.out.println("A expressão está com parênteses desbalanceados.");
        }
    }
}