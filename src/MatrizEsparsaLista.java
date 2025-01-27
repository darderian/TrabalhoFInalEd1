import java.util.Random;

public class MatrizEsparsaLista extends ListaEncadeadaTupla{
    private ListaEncadeadaTupla[] matriz;

    // Construtor que recebe a matriz esparsa estática gerada e converte para lista encadeada
    public MatrizEsparsaLista( int n ) {
        long[][] matrizEstatica= gerarMatrizEsparsa(n);
        matriz = new ListaEncadeadaTupla[n];

        // Inicializa cada linha da matriz com uma nova ListaEncadeadaTupla
        for (int i = 0; i < n; i++) {
            matriz[i] = new ListaEncadeadaTupla();  // Cria uma nova lista encadeada para a linha
        }

        preencherDeMatrizEstatica( matrizEstatica );
    }

    public ListaEncadeadaTupla[] getMatriz() {
        return matriz;
    }
    public void preencherDeMatrizEstatica(long[][] matrizEstatica) {
        int n = matrizEstatica.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrizEstatica[i][j] != 0) {
                    Tupla tupla = new Tupla(i, j, matrizEstatica[i][j]);
                    this.matriz[i].insereTupla(tupla);
                }
            }
        }
    }
    public void setMatriz(ListaEncadeadaTupla[] matrizE)
    {
        matriz = matrizE;
    }
    //1  Insere um novo elemento na matriz
    public void insereElemento(int linha, int coluna, long valor) {
        Tupla tupla = new Tupla(linha, coluna, valor);
        matriz[linha].insereTupla(tupla);
    }
    //2 Remove um elemento da matriz
    public boolean removeElemento(int linha, int coluna, long valor) {
        Tupla tupla = new Tupla(linha, coluna, valor);
        return matriz[linha].remove(tupla);
    }
    //3 Verifica se um valor específico está presente na matriz
    public boolean buscaElemento(int linha, int coluna, long valor) {
        Tupla tupla = new Tupla(linha, coluna, valor);
        return matriz[linha].busca(tupla);
    }

    //4 Imprime a matriz esparsa na forma de listas encadeadas
    public void imprimeMatriz() {
        for (int i = 0; i < this.matriz.length; i++) {
            System.out.print("Linha " + i + ": ");
            this.matriz[i].imprime();  // Imprime os elementos da linha
        }
    }
    public void imprimeMatriz(ListaEncadeadaTupla[] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("Linha " + i + ": ");
            matriz[i].imprime();  // Imprime os elementos da linha
        }
    }
    // 4.2 Imprimir a matriz na forma estatica
    public void imprimirMatriz() {
        long [][] matrizAux = converteParaMatrizEstatica();
        if (matrizAux == null || matrizAux.length == 0) {
            System.out.println("Matriz vazia ou nula.");
            return;
        }

        for (long[] linha : matrizAux) {
            for (long elemento : linha) {
                System.out.print(elemento + "\t");
            }
            System.out.println();
        }
    }
    public void imprimirMatriz(long [][] matrizAux) {

        if (matrizAux == null || matrizAux.length == 0) {
            System.out.println("Matriz vazia ou nula.");
            return;
        }

        for (long[] linha : matrizAux) {
            for (long elemento : linha) {
                System.out.print(elemento + "\t");
            }
            System.out.println();
        }
    }
    // 5. Representar uma matriz vazia
    public void inicializarMatrizVazia()
    {
        for (int i = 0; i < matriz.length; i++) {matriz[i] = new ListaEncadeadaTupla();}
    }
    // 6 Verifica se a matriz é vazia
    public boolean matrizVazia() {
        for (int i = 0; i < matriz.length; i++) {
            if (!matriz[i].vazia()) {
                return false;  // Se pelo menos uma linha não for vazia, a matriz não é vazia
            }
        }
        return true;
    }

    //7 Verifica se a matriz é diagonal
    public boolean ehMatrizDiagonal() {
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
    // 8. Verificar se é uma matriz linha
    public boolean ehMatrizLinha() {
        // Verifica se a matriz tem apenas uma linha com elementos não nulos.
        boolean encontrouLinhaComElementos = false;
        int linhaComElementos = -1;

        // Percorre cada linha da matriz (cada linha é uma lista encadeada)
        for (int i = 0; i < matriz.length; i++) {
            Elo p = matriz[i].prim; // Obtém a lista encadeada de tuplas da linha i

            // Se a linha não tem nenhum elemento, continua para a próxima
            if (p == null) {
                continue;
            }

            // Percorre os elementos da linha (tuplas)
            while (p != null) {
                int linha = p.dados.getLinha(); // Obtém a linha da tupla

                // Verifica se encontramos um elemento em uma linha
                if (linhaComElementos == -1) {
                    // Se não encontrarmos ainda uma linha com elementos, armazena essa linha
                    linhaComElementos = linha;
                } else if (linhaComElementos != linha) {
                    // Se já encontramos uma linha com elementos e a atual é diferente,
                    // então não é uma matriz linha
                    return false;
                }

                p = p.prox; // Avança para o próximo elo
            }
        }

        // Se encontramos pelo menos uma linha com elementos, então é uma matriz linha
        return linhaComElementos != -1;
    }

    // 9. Verificar se é uma matriz coluna
    public boolean ehMatrizColuna() {
        // Verifica se a matriz tem apenas uma coluna com elementos não nulos.
        boolean encontrouColunaComElementos = false;
        int colunaComElementos = -1;

        // Percorre cada linha da matriz (cada linha é uma lista encadeada)
        for (int i = 0; i < matriz.length; i++) {
            Elo p = matriz[i].prim; // Obtém a lista encadeada de tuplas da linha i

            // Se a linha não tem nenhum elemento, continua para a próxima
            if (p == null) {
                continue;
            }

            // Percorre os elementos da linha (tuplas)
            while (p != null) {
                int coluna = p.dados.getColuna(); // Obtém a coluna da tupla

                // Verifica se encontramos um elemento em uma coluna
                if (colunaComElementos == -1) {
                    // Se não encontrarmos ainda uma coluna com elementos, armazena essa coluna
                    colunaComElementos = coluna;
                } else if (colunaComElementos != coluna) {
                    // Se já encontramos uma coluna com elementos e a atual é diferente,
                    // então não é uma matriz coluna
                    return false;
                }

                p = p.prox; // Avança para o próximo elo
            }
        }

        // Se encontramos pelo menos uma coluna com elementos, então é uma matriz coluna
        return colunaComElementos != -1;
    }
    // 10 Verifica se a matriz é triangular inferior
    public boolean ehMatrizTriangularInferior() {
        for (int i = 0; i < matriz.length; i++) {
            Elo p = matriz[i].prim;
            while (p != null) {
                if (p.dados.getColuna() > p.dados.getLinha()) {
                    return false;  // Se encontrar um elemento fora da parte inferior triangular, retorna falso
                }
                p = p.prox;
            }
        }
        return true;  // Todos os elementos acima da diagonal principal são zero
    }

    //11 Verifica se a matriz é triangular superior
    public boolean ehMatrizTriangularSuperior() {
        for (int i = 0; i < matriz.length; i++) {
            Elo p = matriz[i].prim;
            while (p != null) {
                if (p.dados.getColuna() < p.dados.getLinha()) {
                    return false;  // Se encontrar um elemento fora da parte superior triangular, retorna falso
                }
                p = p.prox;
            }
        }
        return true;  // Todos os elementos abaixo da diagonal principal são zero
    }


    //12 Verifica se a matriz é simétrica
    public boolean ehSimetrica() {
        for (int i = 0; i < matriz.length; i++) {
            Elo p = matriz[i].prim;
            while (p != null) {
                int linha = p.dados.getLinha();
                int coluna = p.dados.getColuna();
                long valor = p.dados.getValor();

                // Verifica se o elemento simétrico existe e tem o mesmo valor
                Elo q = matriz[coluna].prim;
                boolean encontrado = false;

                while (q != null) {
                    if (q.dados.getLinha() == coluna && q.dados.getColuna() == linha && q.dados.getValor() == valor) {
                        encontrado = true;
                        break;
                    }
                    q = q.prox;
                }

                if (!encontrado) {
                    return false; // Não encontrou o elemento simétrico
                }

                p = p.prox;
            }
        }
        return true; // Todos os elementos têm correspondência simétrica
    }

    //13 Somar duas matrizes esparsas
    public ListaEncadeadaTupla[] soma( ListaEncadeadaTupla[] matrizB) {
        int n = matriz.length;
        ListaEncadeadaTupla[] resultado = new ListaEncadeadaTupla[n];

        // Inicializa as listas encadeadas no resultado
        for (int i = 0; i < n; i++) {
            resultado[i] = new ListaEncadeadaTupla();
        }

        // Copia os valores de matrizA para o resultado
        for (int i = 0; i < n; i++) {
            Elo atual = matriz[i].prim;
            while (atual != null) {
                Tupla novaTupla = new Tupla(atual.dados.getLinha(), atual.dados.getColuna(), atual.dados.getValor());
                resultado[i].insereTupla(novaTupla);
                atual = atual.prox;
            }
        }

        // Soma os valores de matrizB no resultado
        for (int i = 0; i < n; i++) {
            Elo q = matrizB[i].prim; // Percorre os elementos da linha da matrizB

            while (q != null) {
                int linha = q.dados.getLinha();
                int coluna = q.dados.getColuna();
                long valorOutro = q.dados.getValor();

                // Obtém o valor atual na lista do resultado
                Elo r = resultado[i].prim;
                boolean encontrado = false;

                while (r != null) {
                    if (r.dados.getColuna() == coluna) {
                        // Atualiza o valor com a soma
                        r.dados.setValor(r.dados.getValor() + valorOutro);
                        encontrado = true;
                        break;
                    }
                    r = r.prox;
                }

                // Se não encontrado, adiciona um novo nó com o valor
                if (!encontrado) {
                    resultado[i].insereTupla(new Tupla(linha, coluna, valorOutro));
                }

                q = q.prox; // Avança para o próximo elemento de matrizB
            }
        }

        return resultado;
    }


    //14 Multiplicar duas matrizes esparsas
    public ListaEncadeadaTupla[] multiplicar( ListaEncadeadaTupla[] matrizB) {
        int n = matriz.length;
        ListaEncadeadaTupla[] resultado = new ListaEncadeadaTupla[n];

        // Inicializa as listas encadeadas no resultado
        for (int i = 0; i < n; i++) {
            resultado[i] = new ListaEncadeadaTupla();
        }

        // Verifica se a multiplicação é possível (colunas da primeira = linhas da segunda)
        if (matriz.length != matrizB.length) {
            throw new IllegalArgumentException("As matrizes não são compatíveis para multiplicação.");
        }

        // Percorre cada linha da primeira matriz
        for (int i = 0; i < n; i++) {
            Elo p = matriz[i].prim; // Elementos da linha i da matrizA
            while (p != null) {
                int colunaNaPrimeira = p.dados.getColuna();
                long valorNaPrimeira = p.dados.getValor();

                // Percorre os elementos da linha correspondente na matrizB
                Elo q = matrizB[colunaNaPrimeira].prim; // Linha correspondente na matrizB
                while (q != null) {
                    int colunaNaSegunda = q.dados.getColuna();
                    long valorNaSegunda = q.dados.getValor();

                    // Calcula o produto e soma ao valor atual na matriz resultado
                    Elo existente = resultado[i].buscaElo(colunaNaSegunda);
                    if (existente != null) {
                        existente.dados.setValor(existente.dados.getValor() + valorNaPrimeira * valorNaSegunda);
                    } else {
                        resultado[i].insereTupla(new Tupla(i, colunaNaSegunda, valorNaPrimeira * valorNaSegunda));
                    }

                    q = q.prox;
                }
                p = p.prox;
            }
        }

        return resultado;
    }


    //15 Obter a matriz transposta
    public ListaEncadeadaTupla[] transpor(ListaEncadeadaTupla[] matriz)
    {
        int n = matriz.length;
        ListaEncadeadaTupla[] transposta = new ListaEncadeadaTupla[n];

        // Inicializa as listas encadeadas da matriz transposta
        for (int i = 0; i < n; i++) {
            transposta[i] = new ListaEncadeadaTupla();
        }

        // Percorre cada linha da matriz original
        for (int i = 0; i < n; i++) {
            Elo p = matriz[i].prim;

            // Para cada tupla na linha, insere na coluna correspondente da transposta
            while (p != null) {
                int linhaOriginal = i;
                int colunaOriginal = p.dados.getColuna();
                long valor = p.dados.getValor();

                // Adiciona o valor na posição transposta
                transposta[colunaOriginal].insereTupla(new Tupla(colunaOriginal, linhaOriginal, valor));

                p = p.prox; // Avança para o próximo elemento
            }
        }

        return transposta; // Retorna a matriz transposta
    }
    public long[][] converteParaMatrizEstatica() {
        int n = matriz.length;
        long[][] matrizEstatica = new long[n][n];  // Inicializa com zeros

        // Percorre cada linha da matriz esparsa
        for (int i = 0; i < n; i++) {
            Elo p = matriz[i].prim;  // Obtém a lista encadeada da linha i

            // Percorre as tuplas da lista encadeada da linha
            while (p != null) {
                int coluna = p.dados.getColuna();
                long valor = p.dados.getValor();

                // Registra o valor na posição correta da matriz estática
                matrizEstatica[i][coluna] = valor;

                p = p.prox;  // Avança para a próxima tupla
            }
        }

        return matrizEstatica;  // Retorna a matriz estática preenchida
    }

    private long[][] gerarMatrizEsparsa(int tamanho) {
        if (tamanho <= 0) {
            throw new IllegalArgumentException("O tamanho da matriz deve ser maior que zero.");
        }

        long[][] matrize = new long[tamanho][tamanho];
        int totalElementos = tamanho * tamanho;
        int elementosNaoZero = (int) (totalElementos * 0.4); // 40% dos elementos não serão zero
        Random random = new Random();

        // Preenche aleatoriamente os 40% de elementos diferentes de zero
        while (elementosNaoZero > 0) {
            int linha = random.nextInt(tamanho);
            int coluna = random.nextInt(tamanho);

            // Apenas insere se o valor na posição atual for zero
            if (matrize[linha][coluna] == 0) {
                matrize[linha][coluna] = random.nextInt(10) + 1; // Valores não zero entre 1 e 10
                elementosNaoZero--;
            }
        }

        return matrize;
    }
}
