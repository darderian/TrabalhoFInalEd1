import java.math.BigInteger;
import java.util.Random;

public class MatrizEsparsaEstatica {
    private BigInteger[][] matriz;
    int n;
    // Construtor que inicializa gerando automaticamente
    public MatrizEsparsaEstatica(int n) {
        this.n = n;
        if (n <= 0) {
            throw new IllegalArgumentException("O tamanho da matriz deve ser maior que zero.");
        }
        this.matriz = new BigInteger[n][n];
        // Inicializa todos os elementos com BigInteger.ZERO
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.matriz[i][j] = BigInteger.ZERO;  // Garantir que os elementos não sejam nulos
            }
        }
        int totalElementos = n * n;
        int elementosNaoZero = (int) (totalElementos * 0.4); // 40% dos elementos não serão zero
        Random random = new Random();
        // Preenche aleatoriamente os 40% de elementos diferentes de zero
        while (elementosNaoZero > 0) {
            int linha = random.nextInt(n);
            int coluna = random.nextInt(n);
            // Apenas insere se o valor na posição atual for zero
            if (this.matriz[linha][coluna].equals(BigInteger.ZERO)) {
                this.matriz[linha][coluna] = BigInteger.valueOf(random.nextInt(10) + 1); // Valores não zero entre 1 e 10
                elementosNaoZero--;
            }
        }
    }
    public BigInteger[][] getMatriz() {
        return matriz;
    }
    public void setMatriz(BigInteger[][] matriz) {
        this.matriz = matriz;
    }
    // 1. Inserir um elemento
    public void inserirElemento(int linha, int coluna, BigInteger valor) {
        validarIndices(linha, coluna);
        matriz[linha][coluna] = valor;
    }
    // 2. Remover um elemento
    public BigInteger removerElemento(int linha, int coluna) {
        validarIndices(linha, coluna);
        BigInteger valor = matriz[linha][coluna];
        matriz[linha][coluna] = BigInteger.ZERO; // Definir como zero usando BigInteger.ZERO
        return valor;
    }
    // 3. Buscar um elemento
    public boolean buscarElemento(int linha, int coluna, BigInteger valor) {
        validarIndices(linha, coluna);
        return matriz[linha][coluna].equals(valor);
    }
    // 4. Imprimir a matriz
    public void imprimirMatriz() {
        imprimirMatriz(this.matriz);
    }
    private void imprimirMatriz(BigInteger[][] matriz) {
        if (matriz == null || matriz.length == 0) {
            System.out.println("Matriz vazia ou nula.");
            return;
        }
        for (BigInteger[] linha : matriz) {
            for (BigInteger elemento : linha) {
                System.out.print(elemento + "\t");
            }
            System.out.println();
        }
    }
    // 5. Representar uma matriz vazia
    public void inicializarMatrizVazia() {
        this.matriz = new BigInteger[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.matriz[i][j] = BigInteger.ZERO;
            }
        }
    }
    // 6. Verificar se é uma matriz vazia
    public boolean ehMatrizVazia() {
        for (BigInteger[] linha : matriz) {
            for (BigInteger valor : linha) {
                // Não há necessidade de verificar null, pois todos os valores são inicializados como BigInteger.ZERO
                if (!valor.equals(BigInteger.ZERO)) {
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
                if (i != j && !matriz[i][j].equals(BigInteger.ZERO)) {
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
                if (!matriz[i][j].equals(BigInteger.ZERO)) {
                    contemElemento = true;
                    break;
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
        int colunas = matriz[0].length;
        int colunaComElementos = -1;
        for (int j = 0; j < colunas; j++) {
            boolean contemElemento = false;
            for (BigInteger[] bigIntegers : matriz) {
                if (!bigIntegers[j].equals(BigInteger.ZERO)) {
                    contemElemento = true;
                    break;
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
                if (!matriz[i][j].equals(BigInteger.ZERO)) {
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
                if (!matriz[i][j].equals(BigInteger.ZERO)) {
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
                if (!matriz[i][j].equals(matriz[j][i])) {
                    return false;
                }
            }
        }
        return true;
    }
    // 13. Somar duas matrizes esparsas
    public BigInteger[][] somar(BigInteger[][] outraMatriz) {
        validarDimensoes(matriz, outraMatriz);
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        BigInteger[][] resultado = new BigInteger[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                resultado[i][j] = matriz[i][j].add(outraMatriz[i][j]);
            }
        }
        return resultado;
    }
    // 14. Multiplicar duas matrizes esparsas
    public BigInteger[][] multiplicar(BigInteger[][] outraMatriz) {
        BigInteger[][] resultado = new BigInteger[n][n];
        // Inicializa a matriz resultado com BigInteger.ZERO
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                resultado[i][j] = BigInteger.ZERO;
            }
        }
        // Multiplicação de matrizes esparsas
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (!matriz[i][k].equals(BigInteger.ZERO)) { // Apenas elementos não nulos
                    for (int j = 0; j < n; j++) {
                        if (!outraMatriz[k][j].equals(BigInteger.ZERO)) { // Apenas elementos não nulos
                            resultado[i][j] = resultado[i][j].add(matriz[i][k].multiply(outraMatriz[k][j]));
                        }
                    }
                }
            }
        }
        return resultado;
    }
    // 15. Obter a matriz transposta
    public BigInteger[][] transpor() {
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        BigInteger[][] transposta = new BigInteger[colunas][linhas];
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
    private void validarDimensoes(BigInteger[][] matriz1, BigInteger[][] matriz2) {
        if (matriz1.length != matriz2.length || matriz1[0].length != matriz2[0].length) {
            throw new IllegalArgumentException("As matrizes devem ter as mesmas dimensões.");
        }
    }
}
