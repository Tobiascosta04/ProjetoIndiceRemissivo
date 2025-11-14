package collections;

public abstract class ListaDinamica<T> implements Lista<T> {
    protected class Nodo {
        T valor;
        Nodo proximo;

        public Nodo(T valor) {this.valor = valor; }
    }

    protected int tamanho;
    protected Nodo inicio;
    protected Nodo fim;

    protected void checarPosicao(int index){
        if(index >= tamanho || index < 0) throw new IndexOutOfBoundsException();
    }

    protected Nodo pegarNodoPorPosicao(int index){
        Nodo nodoAuxiliar = inicio;
        for(int i = 0; i < index; i++){
            nodoAuxiliar = nodoAuxiliar.proximo;
        }
        return nodoAuxiliar;
    }

    @Override
    public boolean estaVazia() {
        return tamanho == 0;
    }

    @Override
    public int tamanho() {
        return tamanho;
    }

    @Override
    public String toString() {
        String dataLista = "[";
        Nodo nodoAuxiliar = inicio;
        while(nodoAuxiliar != null){
            dataLista += nodoAuxiliar.valor;
            dataLista += " -> ";
            nodoAuxiliar = nodoAuxiliar.proximo;
        }
        return dataLista + "]";
    }

}
