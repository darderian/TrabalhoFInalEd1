import java.math.BigInteger;
import java.util.Random;

public class MatrizEsparsaLista extends ListaEncadeadaTupla {
    private ListaEncadeadaTupla[] matriz;
    // Construtor que recebe a matriz esparsa estática gerada e converte para lista encadeada
    public MatrizEsparsaLista(int n) {
        matriz = new ListaEncadeadaTupla[n];
        // Inicializa cada linha da matriz com uma nova ListaEncadeadaTupla
        for (int i = 0; i < n; i++) {
            matriz[i] = new ListaEncadeadaTupla();  // Cria uma nova lista encadeada para a linha
        }
        preencheMatriz(n);
    }
    public ListaEncadeadaTupla[] getMatriz() {return matriz;}
    public void setMatriz(ListaEncadeadaTupla[] matrizE) {matriz = matrizE;}

    public void preencheMatriz(int tamanho) {
        int totalElementos = tamanho * tamanho;
        int elementosNaoZero = (int) (totalElementos * 0.4); // 40% dos elementos não serão zero
        Random random = new Random();
        // Preenche aleatoriamente os 40% de elementos diferentes de zero
        while (elementosNaoZero > 0)
        {
            int linha = random.nextInt(tamanho);
            int coluna = random.nextInt(tamanho);
            Tupla tupla = new Tupla(linha, coluna, BigInteger.valueOf(random.nextInt(10) + 1)); // Valores não zero entre 1 e 10)
            this.matriz[linha].insereTupla(tupla);
            elementosNaoZero--;
        }
    }

    //1  Insere um novo elemento na matriz
    public void insereElemento(int linha, int coluna, BigInteger valor) {
        matriz[linha].insereTupla(new Tupla(linha, coluna, valor));
    }
    //2 Remove um elemento da matriz
    public boolean removeElemento(int linha, int coluna, BigInteger valor) {
        Tupla tupla = new Tupla(linha, coluna, valor);
        return matriz[linha].remove(tupla);
    }
    //3 Verifica se um valor específico está presente na matriz
    public boolean buscaElemento(int linha, int coluna, BigInteger valor) {
        Tupla tupla = new Tupla(linha, coluna, valor);
        return matriz[linha].busca(tupla);
    }
    //4 Imprime a matriz esparsa na forma de listas encadeadas
    public void imprimeMatriz() {
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("\nLinha " + (i+1) + ": ");
            matriz[i].imprime();  // Imprime os elementos da linha
        }
    }
    // 4.2 Imprimir a matriz na forma estatica
    public void imprimirMatriz() {
        BigInteger[][] matrizAux = converteParaMatrizEstatica();
        imprimirMatriz( matrizAux);
    }
    private void imprimirMatriz(BigInteger[][] matrizAux) {
        if (matrizAux == null || matrizAux.length == 0) {
            System.out.println("Matriz vazia ou nula.");
            return;
        }

        for (BigInteger[] linha : matrizAux) {
            for (BigInteger elemento : linha) {
                System.out.print(elemento + "\t");
            }
            System.out.println();
        }
    }
    // 5. Representar uma matriz vazia
    public void inicializarMatrizVazia() {
        for (int i = 0; i < matriz.length; i++) {
            matriz[i] = new ListaEncadeadaTupla();
        }
    }
    // 6 Verifica se a matriz é vazia
    public boolean matrizVazia() {
        for (ListaEncadeadaTupla listaEncadeadaTupla : matriz) {
            if (!listaEncadeadaTupla.vazia()) {
                return false;  // Se pelo menos uma linha não for vazia, a matriz não é vazia
            }
        }
        return true;
    }
    //7 Verifica se a matriz é diagonal
    public boolean ehMatrizDiagonal() {
        for (ListaEncadeadaTupla listaEncadeadaTupla : matriz) {
            Elo p = listaEncadeadaTupla.prim;
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
        int linhaComElementos = -1;

        for (ListaEncadeadaTupla listaEncadeadaTupla : matriz) {
            Elo p = listaEncadeadaTupla.prim;

            if (p == null) {
                continue;
            }

            while (p != null) {
                int linha = p.dados.getLinha();
                if (linhaComElementos == -1) {
                    linhaComElementos = linha;
                } else if (linhaComElementos != linha) {
                    return false;
                }
                p = p.prox;
            }
        }
        return linhaComElementos != -1;
    }
    // 9. Verificar se é uma matriz coluna
    public boolean ehMatrizColuna() {
        int colunaComElementos = -1;
        for (ListaEncadeadaTupla listaEncadeadaTupla : matriz) {
            Elo p = listaEncadeadaTupla.prim;

            if (p == null) {
                continue;
            }

            while (p != null) {
                int coluna = p.dados.getColuna();
                if (colunaComElementos == -1) {
                    colunaComElementos = coluna;
                } else if (colunaComElementos != coluna) {
                    return false;
                }
                p = p.prox;
            }
        }
        return colunaComElementos != -1;
    }
    // 10 Verifica se a matriz é triangular inferior
    public boolean ehMatrizTriangularInferior() {
        for (ListaEncadeadaTupla listaEncadeadaTupla : matriz) {
            Elo p = listaEncadeadaTupla.prim;
            while (p != null) {
                if (p.dados.getColuna() > p.dados.getLinha()) {
                    return false;
                }
                p = p.prox;
            }
        }
        return true;
    }
    //11 Verifica se a matriz é triangular superior
    public boolean ehMatrizTriangularSuperior() {
        for (ListaEncadeadaTupla listaEncadeadaTupla : matriz) {
            Elo p = listaEncadeadaTupla.prim;
            while (p != null) {
                if (p.dados.getColuna() < p.dados.getLinha()) {
                    return false;
                }
                p = p.prox;
            }
        }
        return true;
    }
    //12 Verifica se a matriz é simétrica
    public boolean ehSimetrica() {
        for (int i = 0; i < matriz.length; i++) {
            Elo p = matriz[i].prim;
            while (p != null) {
                int linha = p.dados.getLinha();
                int coluna = p.dados.getColuna();
                BigInteger valor = p.dados.getValor();
                Elo q = matriz[coluna].prim;
                boolean encontrado = false;
                while (q != null) {
                    if (q.dados.getLinha() == coluna && q.dados.getColuna() == linha && q.dados.getValor().equals(valor)) {
                        encontrado = true;
                        break;
                    }
                    q = q.prox;
                }
                if (!encontrado) {
                    return false;
                }
                p = p.prox;
            }
        }
        return true;
    }
    //13 Somar duas matrizes esparsas
    public ListaEncadeadaTupla[] soma(ListaEncadeadaTupla[] matrizB) {
        int n = matriz.length;
        ListaEncadeadaTupla[] resultado = new ListaEncadeadaTupla[n];
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
            Elo q = matrizB[i].prim;
            while (q != null) {
                int linha = q.dados.getLinha();
                int coluna = q.dados.getColuna();
                BigInteger valorOutro = q.dados.getValor();
                Elo r = resultado[i].prim;
                boolean encontrado = false;
                while (r != null) {
                    if (r.dados.getColuna() == coluna) {
                        r.dados.setValor(r.dados.getValor().add(valorOutro));
                        encontrado = true;
                        break;
                    }
                    r = r.prox;
                }
                if (!encontrado) {
                    resultado[i].insereTupla(new Tupla(linha, coluna, valorOutro));
                }
                q = q.prox;
            }
        }
        return resultado;
    }
    //14 Multiplicar duas matrizes esparsas
    public ListaEncadeadaTupla[] multiplicar(ListaEncadeadaTupla[] matrizB) {
        int n = matriz.length;
        ListaEncadeadaTupla[] resultado = new ListaEncadeadaTupla[n];
        // Inicializa o vetor de resultados
        for (int i = 0; i < n; i++) {
            resultado[i] = new ListaEncadeadaTupla();
        }
        if (matriz.length != matrizB.length) {
            throw new IllegalArgumentException("As matrizes não são compatíveis para multiplicação.");
        }
        for (int i = 0; i < n; i++) {
            // Array temporário para acumular resultados da linha atual
            BigInteger[] acumulador = new BigInteger[n];
            for (int k = 0; k < n; k++) {
                acumulador[k] = BigInteger.ZERO;
            }
            Elo p = matriz[i].prim;
            while (p != null) {
                int colunaNaPrimeira = p.dados.getColuna();
                BigInteger valorNaPrimeira = p.dados.getValor();
                Elo q = matrizB[colunaNaPrimeira].prim;
                while (q != null) {
                    int colunaNaSegunda = q.dados.getColuna();
                    BigInteger valorNaSegunda = q.dados.getValor();
                    // Acumula o valor temporariamente
                    acumulador[colunaNaSegunda] = acumulador[colunaNaSegunda].add(valorNaPrimeira.multiply(valorNaSegunda));
                    q = q.prox;
                }
                p = p.prox;
            }
            // Insere os valores do acumulador na lista encadeada resultado[i]
            for (int k = 0; k < n; k++) {
                if (!acumulador[k].equals(BigInteger.ZERO)) {
                    resultado[i].insereTupla(new Tupla(i, k, acumulador[k]));
                }
            }
        }
        return resultado;
    }
    //15 Obter a matriz transposta
    public ListaEncadeadaTupla[] transpor(ListaEncadeadaTupla[] matriz) {
        int n = matriz.length;
        ListaEncadeadaTupla[] transposta = new ListaEncadeadaTupla[n];
        for (int i = 0; i < n; i++) {
            transposta[i] = new ListaEncadeadaTupla();
        }
        for (int i = 0; i < n; i++) {
            Elo p = matriz[i].prim;
            while (p != null) {
                int colunaOriginal = p.dados.getColuna();
                BigInteger valor = p.dados.getValor();
                transposta[colunaOriginal].insereTupla(new Tupla(colunaOriginal, i, valor));
                p = p.prox;
            }
        }
        return transposta;
    }
    public BigInteger[][] converteParaMatrizEstatica() {
        int n = matriz.length;
        BigInteger[][] matrizEstatica = new BigInteger[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizEstatica[i][j] = BigInteger.ZERO;
            }
        }
        for (int i = 0; i < n; i++) {
            Elo p = matriz[i].prim;
            while (p != null) {
                matrizEstatica[i][p.dados.getColuna()] = p.dados.getValor();
                p = p.prox;
            }
        }
        return matrizEstatica;
    }
}
