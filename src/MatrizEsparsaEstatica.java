public class MatrizEsparsaEstatica {

    private int[][] matriz;

    // Construtor que inicializa a matriz
    public MatrizEsparsaEstatica(int[][] matrizGerada) {
        this.matriz = matrizGerada;
    }

    // 1. Inserir um elemento
    public void inserirElemento(int linha, int coluna, int valor) {
        validarIndices(linha, coluna);
        matriz[linha][coluna] = valor;
    }

    // 2. Remover um elemento
    public void removerElemento(int linha, int coluna) {
        validarIndices(linha, coluna);
        matriz[linha][coluna] = 0;
    }

    // 3. Buscar um elemento
    public int buscarElemento(int linha, int coluna) {
        validarIndices(linha, coluna);
        return matriz[linha][coluna];
    }

    // 4. Imprimir a matriz
    public void imprimirMatriz() {
        for (int[] linha : matriz) {
            for (int valor : linha) {
                System.out.print(valor + "\t");
            }
            System.out.println();
        }
    }

    // 5. Representar uma matriz vazia
    public void inicializarMatrizVazia(int linhas, int colunas) {
        this.matriz = new int[linhas][colunas];
    }

    // 6. Verificar se é uma matriz vazia
    public boolean ehMatrizVazia() {
        for (int[] linha : matriz) {
            for (int valor : linha) {
                if (valor != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // 7. Verificar se é uma matriz diagonal
    public boolean ehMatrizDiagonal() {
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        if (linhas != colunas) return false; // Só faz sentido para matrizes quadradas

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (i != j && matriz[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // 8. Verificar se é uma matriz linha
    public boolean ehMatrizLinha() {
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        int linhaComElementos = -1;

        for (int i = 0; i < linhas; i++) {
            boolean contemElemento = false;
            for (int j = 0; j < colunas; j++) {
                if (matriz[i][j] != 0) {
                    contemElemento = true;
                }
            }
            if (contemElemento) {
                if (linhaComElementos != -1) {
                    return false; // Mais de uma linha com elementos
                }
                linhaComElementos = i;
            }
        }
        return true;
    }

    // 9. Verificar se é uma matriz coluna
    public boolean ehMatrizColuna() {
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        int colunaComElementos = -1;

        for (int j = 0; j < colunas; j++) {
            boolean contemElemento = false;
            for (int i = 0; i < linhas; i++) {
                if (matriz[i][j] != 0) {
                    contemElemento = true;
                }
            }
            if (contemElemento) {
                if (colunaComElementos != -1) {
                    return false; // Mais de uma coluna com elementos
                }
                colunaComElementos = j;
            }
        }
        return true;
    }

    // 10. Verificar se é uma matriz triangular inferior
    public boolean ehMatrizTriangularInferior() {
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        if (linhas != colunas) return false;

        for (int i = 0; i < linhas; i++) {
            for (int j = i + 1; j < colunas; j++) {
                if (matriz[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // 11. Verificar se é uma matriz triangular superior
    public boolean ehMatrizTriangularSuperior() {
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        if (linhas != colunas) return false;

        for (int i = 1; i < linhas; i++) {
            for (int j = 0; j < i; j++) {
                if (matriz[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // 12. Verificar se a matriz é simétrica
    public boolean ehSimetrica() {
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        if (linhas != colunas) return false;

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (matriz[i][j] != matriz[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    // 13. Somar duas matrizes esparsas
    public int[][] somar(int[][] outraMatriz) {
        validarDimensoes(matriz, outraMatriz);
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        int[][] resultado = new int[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                resultado[i][j] = matriz[i][j] + outraMatriz[i][j];
            }
        }
        return resultado;
    }

    // 14. Multiplicar duas matrizes esparsas
    public int[][] multiplicar(int[][] outraMatriz) {
        if (matriz[0].length != outraMatriz.length) {
            throw new IllegalArgumentException("Dimensões incompatíveis para multiplicação.");
        }

        int linhas = matriz.length;
        int colunasOutra = outraMatriz[0].length;
        int[][] resultado = new int[linhas][colunasOutra];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunasOutra; j++) {
                for (int k = 0; k < matriz[0].length; k++) {
                    resultado[i][j] += matriz[i][k] * outraMatriz[k][j];
                }
            }
        }
        return resultado;
    }

    // 15. Obter a matriz transposta
    public int[][] transpor() {
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        int[][] transposta = new int[colunas][linhas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                transposta[j][i] = matriz[i][j];
            }
        }
        return transposta;
    }

    // Validação de índices
    private void validarIndices(int linha, int coluna) {
        if (linha < 0 || linha >= matriz.length || coluna < 0 || coluna >= matriz[0].length) {
            throw new IndexOutOfBoundsException("Índices fora dos limites da matriz.");
        }
    }

    // Validação de dimensões para operações
    private void validarDimensoes(int[][] matriz1, int[][] matriz2) {
        if (matriz1.length != matriz2.length || matriz1[0].length != matriz2[0].length) {
            throw new IllegalArgumentException("As matrizes devem ter as mesmas dimensões.");
        }
    }
}
