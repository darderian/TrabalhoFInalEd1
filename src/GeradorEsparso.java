import java.util.Random;

public class GeradorEsparso {

    /*
      Gera uma matriz esparsa aleatória de tamanho N x N.
    Uma matriz esparsa estatica no formato de int[][].
     */
    public int[][] gerarMatrizEsparsa(int tamanho) {
        if (tamanho <= 0) {
            throw new IllegalArgumentException("O tamanho da matriz deve ser maior que zero.");
        }

        int[][] matriz = new int[tamanho][tamanho];
        int totalElementos = tamanho * tamanho;
        int elementosNaoZero = (int) (totalElementos * 0.4); // 40% dos elementos não serão zero
        Random random = new Random();

        // Preenche aleatoriamente os 40% de elementos diferentes de zero
        while (elementosNaoZero > 0) {
            int linha = random.nextInt(tamanho);
            int coluna = random.nextInt(tamanho);

            // Apenas insere se o valor na posição atual for zero
            if (matriz[linha][coluna] == 0) {
                matriz[linha][coluna] = random.nextInt(100) + 1; // Valores não zero entre 1 e 100
                elementosNaoZero--;
            }
        }

        return matriz;
    }

    /**
     * Imprime a matriz no console para visualização.
     * @param matriz A matriz a ser impressa.
     */
    public void imprimirMatriz(int[][] matriz) {
        if (matriz == null || matriz.length == 0) {
            System.out.println("Matriz vazia ou nula.");
            return;
        }

        for (int[] linha : matriz) {
            for (int elemento : linha) {
                System.out.print(elemento + "\t");
            }
            System.out.println();
        }
    }

   /* public static void main(String[] args) {
        GeradorEsparso gerador = new GeradorEsparso();
        int tamanho = 20; // Exemplo: matriz 5x5
        int[][] matrizEsparsa = gerador.gerarMatrizEsparsa(tamanho);

        System.out.println("Matriz Esparsa Gerada:");
        gerador.imprimirMatriz(matrizEsparsa);
    }*/
}
