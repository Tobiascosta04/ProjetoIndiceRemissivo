package collections;

import exceptions.ListaVaziaException;

public interface Lista<T> {
    void insereInicio(T valor);
    void insereFinal(T valor);
    void inserePosicao(int index, T valor);
    T removeInicio() throws ListaVaziaException;
    T removeFinal() throws ListaVaziaException;
    T removePosicao(int index) throws ListaVaziaException;
    void limpar();
    boolean estaVazia();
    int tamanho();
}
