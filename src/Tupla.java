public class Tupla {
    private int linha;
    private int coluna;
    private double valor;

    // Construtor completo
    public Tupla(int linha, int coluna, double valor) {
        this.linha = linha;
        this.coluna = coluna;
        this.valor = valor;
    }

    // Getters
    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public double getValor() {
        return valor;
    }

    // Setters
    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    // Representação em texto
    @Override
    public String toString() {
        return "Tupla{" +
                "linha=" + linha +
                ", coluna=" + coluna +
                ", valor=" + valor +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tupla tupla = (Tupla) obj;
        return linha == tupla.linha &&
                coluna == tupla.coluna &&
                Double.compare(tupla.valor, valor) == 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(linha, coluna, valor);
    }
}
