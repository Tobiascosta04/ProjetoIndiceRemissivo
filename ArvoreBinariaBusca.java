package collections;

public class ArvoreBinariaBusca<T extends Comparable<T>> {
    class Nodo {
        public T elemento;
        public Nodo esquerdo;
        public Nodo direito;

        public Nodo(T elemento) {
            this.elemento = elemento;
            this.esquerdo = null;
            this.direito = null;
        }
    }

    public Nodo raiz;
    public int nElementos;

    public ArvoreBinariaBusca() {
        this.raiz = null;
        this.nElementos = 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public boolean estaVazia() {
        return this.raiz == null;
    }

    public void imprimeEmLargura() {
        FilaDinamica<Nodo> fila = new FilaDinamica<Nodo>();

        if (this.raiz != null) {
            fila.enfileira(this.raiz);
        }

        while (!fila.estaVazia()) {
            Nodo cursor = fila.desenfileira();
            System.out.print(cursor.elemento + " ");

            if (cursor.esquerdo != null) {
                fila.enfileira(cursor.esquerdo);
            }

            if (cursor.direito != null) {
                fila.enfileira(cursor.direito);
            }
        }
        System.out.println();
    }

    public void imprimePreOrdem() {
        this.preOrdem(this.raiz);
        System.out.println();
    }

    public void imprimePosOrdem() {
        this.posOrdem(this.raiz);
        System.out.println();
    }

    public void imprimeEmOrdem() {
        this.emOrdem(this.raiz);
        System.out.println();
    }

    private void preOrdem(Nodo nodo) {
        if (nodo == null) return;
        System.out.print(nodo.elemento + " ");
        this.preOrdem(nodo.esquerdo);
        this.preOrdem(nodo.direito);
    }

    private void posOrdem(Nodo nodo) {
        if (nodo == null) return;
        this.posOrdem(nodo.esquerdo);
        this.posOrdem(nodo.direito);
        System.out.print(nodo.elemento + " ");
    }

    private void emOrdem(Nodo nodo) {
        if (nodo == null) return;
        this.emOrdem(nodo.esquerdo);
        System.out.print(nodo.elemento + " ");
        this.emOrdem(nodo.direito);
    }

    // INSERÇÃO - Versão melhorada
    public void insere(T elemento) {
        this.raiz = this.insere(elemento, this.raiz);
    }

    private Nodo insere(T elemento, Nodo nodo) {
        if (nodo == null) {
            this.nElementos++;
            return new Nodo(elemento);
        }

        int comparacao = elemento.compareTo(nodo.elemento);

        if (comparacao < 0) {
            nodo.esquerdo = this.insere(elemento, nodo.esquerdo);
        } else if (comparacao > 0) {
            nodo.direito = this.insere(elemento, nodo.direito);
        }
        // Se igual, não insere duplicata

        return nodo;
    }

    private Nodo maiorElemento(Nodo nodo) {
        while (nodo.direito != null) {
            nodo = nodo.direito;
        }
        return nodo;
    }

    private Nodo menorElemento(Nodo nodo) {
        while (nodo.esquerdo != null) {
            nodo = nodo.esquerdo;
        }
        return nodo;
    }

    // REMOÇÃO
    public boolean remove(T elemento) {
        int tamanhoAntes = this.nElementos;
        this.raiz = this.remove(elemento, this.raiz);
        return this.nElementos < tamanhoAntes;
    }

    private Nodo remove(T elemento, Nodo nodo) {
        if (nodo == null) {
            return null;
        }

        int comparacao = elemento.compareTo(nodo.elemento);

        if (comparacao < 0) {
            nodo.esquerdo = this.remove(elemento, nodo.esquerdo);
        } else if (comparacao > 0) {
            nodo.direito = this.remove(elemento, nodo.direito);
        } else {
            // Nó encontrado para remoção
            if (nodo.esquerdo == null) {
                this.nElementos--;
                return nodo.direito;
            } else if (nodo.direito == null) {
                this.nElementos--;
                return nodo.esquerdo;
            } else {
                // Nó com dois filhos
                Nodo substituto = this.menorElemento(nodo.direito);
                nodo.elemento = substituto.elemento;
                nodo.direito = this.remove(substituto.elemento, nodo.direito);
            }
        }
        return nodo;
    }

    // BUSCA que retorna boolean
    public boolean busca(T elemento) {
        return this.busca(elemento, this.raiz) != null;
    }

    // BUSCA que retorna o ELEMENTO encontrado (IMPORTANTE para o projeto)
    public T buscaElemento(T elemento) {
        return this.busca(elemento, this.raiz);
    }

    private T busca(T elemento, Nodo nodo) {
        if (nodo == null) {
            return null;
        }

        int comparacao = elemento.compareTo(nodo.elemento);

        if (comparacao < 0) {
            return this.busca(elemento, nodo.esquerdo);
        } else if (comparacao > 0) {
            return this.busca(elemento, nodo.direito);
        } else {
            return nodo.elemento; // Retorna o elemento encontrado
        }
    }

    private int altura(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }

        int alturaEsquerda = this.altura(nodo.esquerdo);
        int alturaDireita = this.altura(nodo.direito);

        return Math.max(alturaEsquerda, alturaDireita) + 1;
    }

    public int altura() {
        return this.altura(this.raiz);
    }


    public void percorrerEmOrdem(java.util.function.Consumer<T> acao) {
        percorrerEmOrdem(this.raiz, acao);
    }

    private void percorrerEmOrdem(Nodo nodo, java.util.function.Consumer<T> acao) {
        if (nodo == null) return;
        percorrerEmOrdem(nodo.esquerdo, acao);
        acao.accept(nodo.elemento);
        percorrerEmOrdem(nodo.direito, acao);
    }
}