import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instância do GeradorEsparso
        GeradorEsparso gerador = new GeradorEsparso();

        // Recebe o valor N para as dimensões das matrizes
        System.out.print("Digite o valor de N (dimensões da matriz NxN): ");
        int n = scanner.nextInt();

        // Geração de uma matriz esparsa estática usando o GeradorEsparso
        int[][] matrizEstatica = gerador.gerarMatrizEsparsa(n);
        System.out.println("Matriz Estática gerada:");
        gerador.imprimirMatriz(matrizEstatica);

        // Geração de uma matriz esparsa representada por listas encadeadas
        MatrizEsparsaLista matrizEsparsaLista = new MatrizEsparsaLista(matrizEstatica);
        System.out.println("Matriz Esparsa criada a partir de Lista Encadeada:");
        gerador.imprimirMatriz(matrizEsparsaLista.converteParaMatrizEstatica());
        matrizEsparsaLista.imprimeMatriz();

        // Operações de multiplicação entre a matriz estática e a matriz esparsa
        System.out.println("Multiplicando a matriz estática com a matriz esparsa...");
        MatrizEsparsaLista resultadoMultiplicacao = matrizEsparsaLista.multiplicar(matrizEsparsaLista);
        resultadoMultiplicacao.imprimeMatriz();
    }
}
