package Tabelas_Hash;

import java.util.HashMap;
import java.util.Scanner;

public class Dicionario {
    private HashMap<String, String> dicionario;

    public Dicionario() {
        dicionario = new HashMap<>();
    }

    // Adicionar uma palavra e seu significado
    public void adicionarPalavra(String palavra, String significado) {
        dicionario.put(palavra, significado);
    }

    // Buscar o significado de uma palavra
    public String buscarPalavra(String palavra) {
        return dicionario.getOrDefault(palavra, "Palavra não encontrada!");
    }

    // Remover uma palavra do dicionário
    public void removerPalavra(String palavra) {
        dicionario.remove(palavra);
    }

    public static void main(String[] args) {
        Dicionario dicionario = new Dicionario();
        dicionario.adicionarPalavra("gato", "Animal felino");
        dicionario.adicionarPalavra("cachorro", "Animal canino");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite uma palavra para buscar:");
        String palavra = scanner.nextLine();
        System.out.println("Significado: " + dicionario.buscarPalavra(palavra));

        dicionario.removerPalavra("gato");
        System.out.println("Significado de 'gato' após remoção: " + dicionario.buscarPalavra("gato"));
    }
}
