public class MatrizEsparsaLista extends ListaEncadeadaTupla{
    private ListaEncadeadaTupla[] matriz;

    // Construtor que recebe a matriz esparsa estática gerada e converte para lista encadeada
    public MatrizEsparsaLista(int[][] matrizEstatica) {
        int n = matrizEstatica.length;
        matriz = new ListaEncadeadaTupla[n];

        // Inicializa cada linha da matriz com uma nova ListaEncadeadaTupla
        for (int i = 0; i < n; i++) {
            matriz[i] = new ListaEncadeadaTupla();  // Cria uma nova lista encadeada para a linha
        }

        // Preenche as listas encadeadas com as tuplas
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrizEstatica[i][j] != 0) {  // Só adiciona tupla para valores diferentes de zero
                    Tupla tupla = new Tupla(i, j, matrizEstatica[i][j]);
                    matriz[i].insere(tupla);  // Insere a tupla na lista correspondente à linha
                }
            }
        }
    }

    // Imprime a matriz esparsa na forma de listas encadeadas
    public void imprimeMatriz() {
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("Linha " + i + ": ");
            matriz[i].imprime();  // Imprime os elementos da linha
        }
    }

    // Verifica se um valor específico está presente na matriz
    public boolean buscaElemento(int linha, int coluna, int valor) {
        Tupla tupla = new Tupla(linha, coluna, valor);
        return matriz[linha].busca(tupla);
    }

    // Insere um novo elemento na matriz
    public void insereElemento(int linha, int coluna, int valor) {
        Tupla tupla = new Tupla(linha, coluna, valor);
        matriz[linha].insere(tupla);
    }

    // Remove um elemento da matriz
    public boolean removeElemento(int linha, int coluna, int valor) {
        Tupla tupla = new Tupla(linha, coluna, valor);
        return matriz[linha].remove(tupla);
    }

    // Verifica se a matriz é vazia
    public boolean matrizVazia() {
        for (int i = 0; i < matriz.length; i++) {
            if (!matriz[i].vazia()) {
                return false;  // Se pelo menos uma linha não for vazia, a matriz não é vazia
            }
        }
        return true;
    }

    // Verifica se a matriz é diagonal
    public boolean isDiagonal() {
        for (int i = 0; i < matriz.length; i++) {
            Elo p = matriz[i].prim;
            while (p != null) {
                if (p.dados.getLinha() != p.dados.getColuna()) {
                    return false;  // Se encontrar um elemento fora da diagonal, retorna falso
                }
                p = p.prox;
            }
        }
        return true;
    }

    // Verifica se a matriz é triangular inferior
    public boolean isTriangularInferior() {
        for (int i = 0; i < matriz.length; i++) {
            Elo p = matriz[i].prim;
            while (p != null) {
                if (p.dados.getColuna() < p.dados.getLinha()) {
                    return false;  // Se encontrar um elemento fora da parte inferior triangular, retorna falso
                }
                p = p.prox;
            }
        }
        return true;
    }

    // Verifica se a matriz é triangular superior
    public boolean isTriangularSuperior() {
        for (int i = 0; i < matriz.length; i++) {
            Elo p = matriz[i].prim;
            while (p != null) {
                if (p.dados.getColuna() > p.dados.getLinha()) {
                    return false;  // Se encontrar um elemento fora da parte superior triangular, retorna falso
                }
                p = p.prox;
            }
        }
        return true;
    }

    // Verifica se a matriz é simétrica
    public boolean isSimetrica() {
        for (int i = 0; i < matriz.length; i++) {
            Elo p = matriz[i].prim;
            while (p != null) {
                int linha = p.dados.getLinha();
                int coluna = p.dados.getColuna();
                if (!matriz[coluna].busca(new Tupla(coluna, linha, p.dados.getValor()))) {
                    return false;  // Se não encontrar o valor simétrico, retorna falso
                }
                p = p.prox;
            }
        }
        return true;
    }

    // Somar duas matrizes esparsas
    public MatrizEsparsaLista soma(MatrizEsparsaLista outraMatriz) {
        int n = matriz.length;
        MatrizEsparsaLista resultado = new MatrizEsparsaLista(new int[n][n]);

        for (int i = 0; i < n; i++) {
            Elo p = matriz[i].prim;
            while (p != null) {
                resultado.insereElemento(i, p.dados.getColuna(), p.dados.getValor());
                p = p.prox;
            }
        }

        for (int i = 0; i < n; i++) {
            Elo p = outraMatriz.matriz[i].prim;
            while (p != null) {
                resultado.insereElemento(i, p.dados.getColuna(), p.dados.getValor());
                p = p.prox;
            }
        }

        return resultado;
    }

    // Multiplicar duas matrizes esparsas
    public MatrizEsparsaLista multiplicar(MatrizEsparsaLista outraMatriz) {
        int n = matriz.length;
        MatrizEsparsaLista resultado = new MatrizEsparsaLista(new int[n][n]);

        // Percorre cada linha da primeira matriz
        for (int i = 0; i < n; i++) {
            // Percorre cada tupla na linha i da primeira matriz
            Elo p = matriz[i].prim;
            while (p != null) {
                // Percorre cada coluna da segunda matriz
                for (int j = 0; j < n; j++) {
                    // Procura o valor correspondente na segunda matriz (linha j, coluna p.dados.getColuna())
                    Tupla tuplaNaSegundaMatriz = new Tupla(p.dados.getColuna(), j, 0); // Criamos a tupla com coluna da primeira matriz e linha da segunda
                    boolean encontrou = false;
                    Elo q = outraMatriz.matriz[p.dados.getColuna()].prim;  // Percorre a linha da segunda matriz
                    while (q != null) {
                        // Verifica se a tupla tem a coluna correspondente
                        if (q.dados.getColuna() == j) {
                            int valor = p.dados.getValor() * q.dados.getValor();  // Multiplica os valores
                            // Insere o resultado na matriz de resultado
                            resultado.insereElemento(i, j, valor);
                            encontrou = true;
                            break;
                        }
                        q = q.prox;
                    }

                    // Se não encontrou a tupla correspondente, continue sem inserir
                    if (!encontrou && !resultado.buscaElemento(i, j, 0)) {
                        // Evita a inserção de 0 desnecessário na matriz esparsa
                        resultado.insereElemento(i, j, 0);
                    }
                }
                p = p.prox;
            }
        }

        return resultado;
    }

    // Obter a matriz transposta
    public MatrizEsparsaLista transposta() {
        int n = matriz.length;
        MatrizEsparsaLista transposta = new MatrizEsparsaLista(new int[n][n]);

        for (int i = 0; i < n; i++) {
            Elo p = matriz[i].prim;
            while (p != null) {
                transposta.insereElemento(p.dados.getColuna(), p.dados.getLinha(), p.dados.getValor());
                p = p.prox;
            }
        }

        return transposta;
    }
    public int[][] converteParaMatrizEstatica() {
        int n = matriz.length;
        int[][] matrizEstatica = new int[n][n];  // Inicializa com zeros

        // Percorre cada linha da matriz esparsa
        for (int i = 0; i < n; i++) {
            Elo p = matriz[i].prim;  // Obtém a lista encadeada da linha i

            // Percorre as tuplas da lista encadeada da linha
            while (p != null) {
                int coluna = p.dados.getColuna();
                int valor = p.dados.getValor();

                // Registra o valor na posição correta da matriz estática
                matrizEstatica[i][coluna] = valor;

                p = p.prox;  // Avança para a próxima tupla
            }
        }

        return matrizEstatica;  // Retorna a matriz estática preenchida
    }


}
