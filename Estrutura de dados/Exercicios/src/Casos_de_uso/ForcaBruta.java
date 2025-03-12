package Casos_de_uso;

public class ForcaBruta {

    public static int buscaForcaBruta(String texto, String padrao) {
        int n = texto.length();
        int m = padrao.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (texto.charAt(i + j) != padrao.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                return i; // Encontrou o padrão na posição i
            }
        }
        return -1; // Não encontrou o padrão
    }

    public static void main(String[] args) {
        String texto = "ABAAABCDABCAABC";
        String padrao = "ABCA";

        int resultado = buscaForcaBruta(texto, padrao);
        System.out.println("Posição encontrada (Força Bruta): " + resultado);
    }
}
