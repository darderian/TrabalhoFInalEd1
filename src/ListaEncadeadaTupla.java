public class ListaEncadeadaTupla {
        protected Elo prim;
    protected class Elo {
        protected Tupla dados;
        protected Elo prox;

        public Elo(Tupla elem) {
            dados = elem;
            prox = null;
        }
    }
    public ListaEncadeadaTupla() {
        prim = null;
    }
    // Testa se a lista está vazia.
    public boolean vazia() {
        return prim == null;
    }
    public void insereTupla(Tupla novo) {
        Elo novoElo = new Elo(novo);
        // Caso a lista esteja vazia
        if (prim == null) {
            prim = novoElo;
            return;
        }
        Elo atual = prim;
        Elo anterior = null;
        // Percorre a lista para encontrar a posição correta
        while (atual != null) {
            // Comparação de linha e coluna como inteiros
            if (atual.dados.getLinha() == novo.getLinha() &&
                    atual.dados.getColuna() == novo.getColuna()) {
                // Atualiza o valor se já existir uma tupla com a mesma posição
                atual.dados.setValor(novo.getValor());
                return;
            } else if (atual.dados.getColuna() > novo.getColuna()) {
                // Encontra a posição de inserção
                break;
            }
            anterior = atual;
            atual = atual.prox;
        }
        // Insere o novo elo na posição correta
        if (anterior == null) {
            // Insere no início
            novoElo.prox = prim;
            prim = novoElo;
        } else {
            // Insere entre `anterior` e `atual`
            anterior.prox = novoElo;
            novoElo.prox = atual;
        }
    }
    // Implementação recursiva do método de busca
    public boolean busca(Tupla elem) {
        if (this.vazia()) {
            return false;
        }
        return busca(elem, prim);
    }
    private boolean busca(Tupla elem, Elo p) {
        if (p == null) {
            return false;
        }
        if (p.dados.equals(elem)) { // Utiliza equals para comparar os dados
            return true;
        }
        return busca(elem, p.prox);
    }
    // Remove da lista o primeiro elemento com valor igual a "elem". Retorna true se removeu
    public boolean remove(Tupla elem) {
        Elo p;
        Elo ant = null; // Referência para anterior
        for (p = prim; ((p != null) && (!p.dados.equals(elem))); p = p.prox) {
            ant = p;
        }
        // Se p é null, então não encontrou elemento
        if (p == null) {
            return false;
        }
        if (p == prim) {
            prim = prim.prox; // Remove elemento do início
        } else {
            ant.prox = p.prox; // Remove elemento do meio
        }
        // Remove a última referência para o elo a ser removido
        p = null;
        return true;
    }
    // Implementação recursiva da função de impressão
    public void imprime() {
        System.out.println("Elementos da lista:");
        if (this.vazia()) {
            return;
        }
        imprime(prim);
    }
    private void imprime(Elo p) {
        if (p == null) {
            return;
        }
        System.out.print(p.dados + " ");
        imprime(p.prox);
    }
}
