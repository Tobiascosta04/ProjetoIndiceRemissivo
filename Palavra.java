package collections;

public class Palavra implements Comparable<Palavra> {
    private String palavra;
    private ListaEncadeada<Integer> ocorrencias;

    public Palavra(String palavra) {
        this.palavra = palavra.toLowerCase();
        this.ocorrencias = new ListaEncadeada<>();
    }

    public String getPalavra() {
        return palavra;
    }

    public void adicionarOcorrencia(int linha) {
        ocorrencias.insereFinal(linha);
    }

    @Override
    public String toString() {
        String ocorrenciasStr = ocorrencias.toString();
        return palavra + " " + ocorrenciasStr.replace(" -> ", " ")
                .replace("[", "")
                .replace("]", "");
    }

    @Override
    public int compareTo(Palavra outra) {
        return this.palavra.compareTo(outra.palavra);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Palavra outra = (Palavra) obj;
        return palavra.equals(outra.palavra);
    }

    public ListaEncadeada<Integer> getOcorrencias() {
        return ocorrencias;
    }
}
