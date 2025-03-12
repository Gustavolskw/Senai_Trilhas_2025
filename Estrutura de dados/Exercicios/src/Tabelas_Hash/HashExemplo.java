package Tabelas_Hash;

public class HashExemplo {
    // Função de hash simples para inteiros
    public static int hashInteiro(int chave) {
        return chave % 10;
    }

    public static int hashString(String chave) {
        int somaASCII = 0;
        for (int i = 0; i < chave.length(); i++) {
            somaASCII += chave.charAt(i);  // Soma os valores ASCII de cada caractere
        }
        return somaASCII % 10;  // Retorna o índice da tabela
    }


    public static void main(String[] args) {
        int chave = 42;
        System.out.println("Índice para chave " + chave + ": " + hashInteiro(chave));

        String stringChave= "exemplo";
        System.out.println("Índice para chave '" + chave + "': " + hashString(stringChave));
    }
}