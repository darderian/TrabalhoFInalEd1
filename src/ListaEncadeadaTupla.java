public class ListaEncadeadaTupla {
// Responsável por administrar as tuplas contidas nas colunas da matriz
protected Elo prim;

protected class Elo {
    protected Tupla dados;
    protected Elo prox;

    public Elo() {
        prox = null;
    }

    public Elo(Tupla elem) {
        dados = elem;
        prox = null;
    }

    public Elo(Tupla elem, Elo proxElem) {
        dados = elem;
        prox = proxElem;
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

public Elo buscaElo(int coluna) {
    Elo atual = prim; // Começa do primeiro elemento
    while (atual != null) {
        // Comparação de coluna como inteiro
        if (atual.dados.getColuna() == coluna) {
            return atual; // Retorna o Elo encontrado
        }
        atual = atual.prox; // Avança para o próximo Elo
    }
    return null; // Retorna null se não encontrar
}

// Verifica se um determinado elemento está na lista
public boolean busca(Tupla elem) {
    Elo p;
    for (p = prim; p != null; p = p.prox) {
        if (p.dados.equals(elem)) { // Utiliza equals para comparar os dados
            return true;
        }
    }
    return false;
}

// Implementação recursiva do método de busca
public boolean buscaRecursiva(Tupla elem) {
    if (this.vazia()) {
        return false;
    }
    return buscaRecursiva(elem, prim);
}

private boolean buscaRecursiva(Tupla elem, Elo p) {
    if (p == null) {
        return false;
    }
    if (p.dados.equals(elem)) { // Utiliza equals para comparar os dados
        return true;
    }
    return buscaRecursiva(elem, p.prox);
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

// Imprime todos os elementos da lista
public void imprime() {
    Elo p;

    System.out.println("Elementos da lista:");

    for (p = prim; p != null; p = p.prox) {
        System.out.print(p.dados + " ");
    }

    System.out.println();
}

// Implementação recursiva da função de impressão
public void imprimeRecursivo() {
    System.out.println("Elementos da lista:");

    if (this.vazia()) {
        return;
    }

    imprimeRecursivo(prim);

    System.out.println();
}

private void imprimeRecursivo(Elo p) {
    if (p == null) {
        return;
    }

    System.out.print(p.dados + " ");

    imprimeRecursivo(p.prox);
}

// Calcula e retorna o tamanho da lista
public int tamanho() {
    int tam = 0;
    Elo p = prim;

    while (p != null) {
        tam++;
        p = p.prox;
    }

    return tam;
}

// Calcula e retorna o tamanho da lista de maneira recursiva
public int tamanhoRecursivo() {
    if (this.vazia()) {
        return 0;
    }

    return tamanhoRecursivo(prim);
}

private int tamanhoRecursivo(Elo p) {
    if (p == null) {
        return 0;
    }

    return 1 + tamanhoRecursivo(p.prox);
}
}
