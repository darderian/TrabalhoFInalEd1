import java.math.BigInteger;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int[] numPontos = {10,20,30,40,50,100,200,500,1000};
        for (int n : numPontos) {
            System.out.println("Matriz Estática\nN=" + n);
            System.out.println("Fase 1 Geração");
            long totalTime = 0, totalTime2 = 0, totalTime3 = 0;
            MatrizEsparsaEstatica matrizEsparsaEstatica = null;
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                // Geração de uma matriz esparsa estática usando o
                matrizEsparsaEstatica = new MatrizEsparsaEstatica(n);
                if (i == 0 && n == 10) matrizEsparsaEstatica.imprimirMatriz();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de geração em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");
            System.out.println("Fase 2 inserção ,remoção e busca");

            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaEstatica.inserirElemento(n - 1, n - 1, new BigInteger("1"));
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
                startTime = System.nanoTime();
                matrizEsparsaEstatica.buscarElemento(n - 1, n - 1, new BigInteger("1"));
                endTime = System.nanoTime();
                totalTime2 += (endTime - startTime);
                startTime = System.nanoTime();
                matrizEsparsaEstatica.removerElemento(n - 1, n - 1);
                endTime = System.nanoTime();
                totalTime3 += (endTime - startTime);
            }
            System.out.println("--Tempo médio de inserção em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");
            System.out.println("--Tempo médio de busca em nanosegundos: " + totalTime2 / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime2 / 10000000 + " ms");
            System.out.println("--Tempo médio de remoção em nanosegundos: " + totalTime3 / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime3 / 10000000 + " ms");

            System.out.println("Fase 3 representação e verificaçoes");
            //Representa vazia
            matrizEsparsaEstatica.inicializarMatrizVazia();
            if (n == 10) matrizEsparsaEstatica.imprimirMatriz();
            if (n == 10) System.out.println("É Matriz Vazia?:" + matrizEsparsaEstatica.ehMatrizVazia());
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaEstatica.ehMatrizVazia();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de verificaçao Vazia em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");

            //criando e verificando matriz diagonal
            for (int i = 0; i < n; i++) {
                matrizEsparsaEstatica.inserirElemento(i, i, new BigInteger("1"));
            }
            if (n == 10) matrizEsparsaEstatica.imprimirMatriz();
            if (n == 10) System.out.println("É Matriz Diagonal?:" + matrizEsparsaEstatica.ehMatrizDiagonal());
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaEstatica.ehMatrizDiagonal();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de verificaçao Diagonal em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");

            if (n == 10) System.out.println("É Matriz Simetrica?:" + matrizEsparsaEstatica.ehSimetrica());
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaEstatica.ehSimetrica();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de verificaçao Simetrica em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");

            matrizEsparsaEstatica.inicializarMatrizVazia();
            //criando e verificando matriz Linha
            for (int i = 0; i < n; i++) {
                matrizEsparsaEstatica.inserirElemento(0, i, new BigInteger("1"));
            }
            if (n == 10) matrizEsparsaEstatica.imprimirMatriz();
            if (n == 10) System.out.println("É Matriz Linha?:" + matrizEsparsaEstatica.ehMatrizLinha());
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaEstatica.ehMatrizLinha();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de verificaçao Linha em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");

            //criando e verificando matriz Coluna
            matrizEsparsaEstatica.inicializarMatrizVazia();
            for (int i = 0; i < n; i++) {
                matrizEsparsaEstatica.inserirElemento(i, 0, new BigInteger("1"));
            }
            if (n == 10) matrizEsparsaEstatica.imprimirMatriz();
            if (n == 10) System.out.println("É Matriz Coluna?:" + matrizEsparsaEstatica.ehMatrizColuna());
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaEstatica.ehMatrizColuna();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de verificaçao Coluna em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");

            //criando e verificando triangular inferior
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    matrizEsparsaEstatica.inserirElemento(i, j, new BigInteger("1"));
                }
            }
            if (n == 10) matrizEsparsaEstatica.imprimirMatriz();
            if (n == 10)
                System.out.println("É Matriz Triangular inferior?:" + matrizEsparsaEstatica.ehMatrizTriangularInferior());
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaEstatica.ehMatrizTriangularInferior();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de verificaçao Triangular inferior em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");

            //criando e verificando triangular superior
            matrizEsparsaEstatica.inicializarMatrizVazia();
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    matrizEsparsaEstatica.inserirElemento(i, j, new BigInteger("1"));
                }
            }
            if (n == 10) matrizEsparsaEstatica.imprimirMatriz();
            if (n == 10)
                System.out.println("É Matriz Triangular superior?:" + matrizEsparsaEstatica.ehMatrizTriangularSuperior());
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaEstatica.ehMatrizTriangularSuperior();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de verificaçao Triangular Superior em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");
            System.out.println("Fase 4 soma,multiplicação e transposição");
            if (n > 10)matrizEsparsaEstatica = new MatrizEsparsaEstatica(n);
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                if (i < 9||(i==9&&n>10)) matrizEsparsaEstatica.somar(matrizEsparsaEstatica.getMatriz());
                else matrizEsparsaEstatica.setMatriz(matrizEsparsaEstatica.somar(matrizEsparsaEstatica.getMatriz()));
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            if (n == 10) matrizEsparsaEstatica.imprimirMatriz();

            System.out.println("--Tempo médio de soma em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                if (i < 9||(i==9&&n>10)) matrizEsparsaEstatica.multiplicar(matrizEsparsaEstatica.getMatriz());
                else
                    matrizEsparsaEstatica.setMatriz(matrizEsparsaEstatica.multiplicar(matrizEsparsaEstatica.getMatriz()));
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            if (n == 10) matrizEsparsaEstatica.imprimirMatriz();
            System.out.println("--Tempo médio de multiplicação em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                if (i < 9||(i==9&&n>10)) matrizEsparsaEstatica.transpor();
                else matrizEsparsaEstatica.setMatriz(matrizEsparsaEstatica.transpor());
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            if (n == 10) matrizEsparsaEstatica.imprimirMatriz();
            System.out.println("--Tempo médio de transposição em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");


            if (n == 10) System.out.println("Clique Enter para começar os Testes para o tipo Lista");
            if (n == 10) scanner.next();


            System.out.println("Matriz Lista\nN=" + n);
            System.out.println("Fase 1 Geração");
            totalTime = 0;
            totalTime2 = 0;
            totalTime3 = 0;
            MatrizEsparsaLista matrizEsparsaLista = null;
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                // Geração de uma matriz esparsa estática usando o GeradorEsparso
                matrizEsparsaLista = new MatrizEsparsaLista(n);
                if (i == 0 && n == 10) matrizEsparsaLista.imprimirMatriz();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de geração em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");
            System.out.println("Fase 2 inserção ,remoção e busca");
            totalTime = 0;

            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaLista.insereElemento(n - 1, n - 1, new BigInteger("1"));
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
                startTime = System.nanoTime();
                matrizEsparsaLista.buscaElemento(n - 1, n - 1, new BigInteger("1"));
                endTime = System.nanoTime();
                totalTime2 += (endTime - startTime);
                startTime = System.nanoTime();
                matrizEsparsaLista.removeElemento(n - 1, n - 1, new BigInteger("1"));
                endTime = System.nanoTime();
                totalTime3 += (endTime - startTime);
            }
            System.out.println("--Tempo médio de inserção em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");
            System.out.println("--Tempo médio de busca em nanosegundos: " + totalTime2 / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime2 / 10000000 + " ms");
            System.out.println("--Tempo médio de remoção em nanosegundos: " + totalTime3 / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime3 / 10000000 + " ms");

            System.out.println("Fase 3 representação e verificaçoes");
            //Representa vazia
            matrizEsparsaLista.inicializarMatrizVazia();
            if (n == 10) matrizEsparsaLista.imprimirMatriz();
            if (n == 10) System.out.println("É Matriz Vazia?:" + matrizEsparsaLista.matrizVazia());
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaLista.matrizVazia();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de verificaçao Vazia em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");

            //criando e verificando matriz diagonal
            for (int i = 0; i < n; i++) {
                matrizEsparsaLista.insereElemento(i, i, new BigInteger("1"));
            }
            if (n == 10) matrizEsparsaLista.imprimirMatriz();
            if (n == 10) System.out.println("É Matriz Diagonal?:" + matrizEsparsaLista.ehMatrizDiagonal());
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaLista.ehMatrizDiagonal();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de verificaçao Diagonal em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");
            if (n == 10) System.out.println("É Matriz Simetrica?:" + matrizEsparsaLista.ehSimetrica());
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaLista.ehSimetrica();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de verificaçao Simetrica em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");
            matrizEsparsaLista.inicializarMatrizVazia();
            //criando e verificando matriz Linha
            for (int i = 0; i < n; i++) {
                matrizEsparsaLista.insereElemento(0, i, new BigInteger("1"));
            }
            if (n== 10) matrizEsparsaLista.imprimirMatriz();
            if (n == 10) System.out.println("É Matriz Linha?:" + matrizEsparsaLista.ehMatrizLinha());
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaLista.ehMatrizLinha();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de verificaçao Linha em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");
            //criando e verificando matriz Coluna
            matrizEsparsaLista.inicializarMatrizVazia();
            for (int i = 0; i < n; i++) {
                matrizEsparsaLista.insereElemento(i, 0, new BigInteger("1"));
            }
            if (n == 10) matrizEsparsaLista.imprimirMatriz();
            if (n == 10) System.out.println("É Matriz Coluna?:" + matrizEsparsaLista.ehMatrizColuna());
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaLista.ehMatrizColuna();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de verificaçao Coluna em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");

            //criando e verificando triangular inferior
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    matrizEsparsaLista.insereElemento(i, j, new BigInteger("1"));
                }
            }
            if (n == 10) matrizEsparsaLista.imprimirMatriz();
            if (n == 10)
                System.out.println("É Matriz Triangular inferior?:" + matrizEsparsaLista.ehMatrizTriangularInferior());
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaLista.ehMatrizTriangularInferior();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de verificaçao Triangular inferior em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");

            //criando e verificando triangular superior
            matrizEsparsaLista.inicializarMatrizVazia();
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    matrizEsparsaLista.insereElemento(i, j, new BigInteger("1"));
                }
            }
            if (n == 10) matrizEsparsaLista.imprimirMatriz();
            if (n == 10)
                System.out.println("É Matriz Triangular superior?:" + matrizEsparsaLista.ehMatrizTriangularSuperior());
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                matrizEsparsaLista.ehMatrizTriangularSuperior();
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            System.out.println("--Tempo médio de verificaçao Triangular Superior em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");
            System.out.println("Fase 4 soma,multiplicação e transposição");
            if (n > 10)matrizEsparsaLista = new MatrizEsparsaLista(n);
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                if (i < 9||(i==9&&n>10)) matrizEsparsaLista.soma(matrizEsparsaLista.getMatriz());
                else matrizEsparsaLista.setMatriz(matrizEsparsaLista.soma(matrizEsparsaLista.getMatriz()));
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }

            if (n == 10) matrizEsparsaLista.imprimirMatriz();

            System.out.println("--Tempo médio de soma em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");

            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                if (i < 9||(i==9&&n>10)) matrizEsparsaLista.multiplicar(matrizEsparsaLista.getMatriz());
                else matrizEsparsaLista.setMatriz(matrizEsparsaLista.multiplicar(matrizEsparsaLista.getMatriz()));
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            if (n == 10) matrizEsparsaLista.imprimirMatriz();
            System.out.println("--Tempo médio de multiplicação em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                if (i < 9||(i==9&&n>10)) matrizEsparsaLista.transpor(matrizEsparsaLista.getMatriz());
                else matrizEsparsaLista.setMatriz(matrizEsparsaLista.transpor(matrizEsparsaLista.getMatriz()));
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            if (n == 10) matrizEsparsaLista.imprimirMatriz();
            System.out.println("--Tempo médio de transposição em nanosegundos: " + totalTime / 10 + " ns ;" + "Tempo médio de execução em milissegundos: " + totalTime / 10000000 + " ms");
            if (n == 10) System.out.println("Impressão Estilo Lista");
            if (n == 10) matrizEsparsaLista.imprimeMatriz();
            if (n >= 500 && n < 1000)
                System.out.println("Clique Enter para começar os Testes para o proximo valor de n");
            if (n >= 500 && n < 1000) scanner.next();
        }
    }
}
