package collections;

import exceptions.ListaVaziaException;

public class ListaEncadeada<T> extends ListaDinamica<T> {

    @Override
    public void insereInicio(T valor) {
        Nodo novoNodo = new Nodo(valor);
        if(estaVazia()){
            inicio = novoNodo;
            fim = novoNodo;
        } else {
            novoNodo.proximo = inicio;
            inicio = novoNodo;
        }
        tamanho++;
    }

    @Override
    public void insereFinal(T valor) {
        Nodo novoNodo = new Nodo(valor);

        if(estaVazia()){
            inicio = novoNodo;
            fim = novoNodo;
        } else {
            fim.proximo = novoNodo;
            fim = novoNodo;
        }
        tamanho++;
    }

    @Override
    public void inserePosicao(int index, T valor) {
        if(index <= 0){
            insereInicio(valor);
        } else if(index >= tamanho){
            insereFinal(valor);
        } else {
            Nodo novoNodo = new Nodo(valor);
            Nodo nodoAuxiliar = pegarNodoPorPosicao(index - 1);
            novoNodo.proximo = nodoAuxiliar.proximo;
            nodoAuxiliar.proximo = novoNodo;
            tamanho++;
        }
    }

    @Override
    public T removeInicio() throws ListaVaziaException {
        if(estaVazia()) throw new ListaVaziaException("A lista está vazia!");
        T valor = inicio.valor;

        if(tamanho == 1){
            inicio = null;
            fim = null;
        } else {
            inicio = inicio.proximo;
        }
        tamanho--;
        return valor;
    }

    @Override
    public T removeFinal() throws ListaVaziaException {
        if(estaVazia()) throw new ListaVaziaException("A lista está vazia");
        T valor = fim.valor;

        if(tamanho == 1){
            inicio = null;
            fim = null;
        } else {
            Nodo nodoAuxiliar = pegarNodoPorPosicao(tamanho - 2);
            fim = nodoAuxiliar;
            fim.proximo = null;
        }
        tamanho--;
        return valor;
    }

    @Override
    public T removePosicao(int index) throws ListaVaziaException {
        checarPosicao(index);
        T valor;

        if(index == 0){
            valor = removeInicio();
        } else if(index == tamanho - 1){
            valor = removeFinal();
        } else {
            Nodo nodoAuxiliar = pegarNodoPorPosicao(index - 1);
            valor = nodoAuxiliar.proximo.valor;
            nodoAuxiliar.proximo = nodoAuxiliar.proximo.proximo;
            tamanho--;
        }
        return valor;
    }

    @Override
    public void limpar() {
        inicio = null;
        fim = null;
        tamanho = 0;
    }

    public T obter(int index) {
        checarPosicao(index);
        return pegarNodoPorPosicao(index).valor;
    }

    public void definir(int index, T valor) {
        checarPosicao(index);
        pegarNodoPorPosicao(index).valor = valor;
    }
}
