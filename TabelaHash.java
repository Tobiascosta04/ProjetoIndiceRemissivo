package collections;

public class TabelaHash {
    private ArvoreBinariaBusca<Palavra>[] tabela;
    private int nElementos;
    private static final int TAMANHO = 26; // A-Z

    @SuppressWarnings("unchecked")
    public TabelaHash() {
        this.tabela = new ArvoreBinariaBusca[TAMANHO];
        this.nElementos = 0;

        // Inicializa cada bucket com uma ABB vazia
        for (int i = 0; i < TAMANHO; i++) {
            this.tabela[i] = new ArvoreBinariaBusca<>();
        }
    }

    public int tamanho() {
        return this.nElementos;
    }

    // Função hash baseada na primeira letra da palavra
    private int funcaoHash(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return 0;
        }
        char primeiraLetra = Character.toLowerCase(palavra.charAt(0));
        return primeiraLetra - 'a'; // 'a'→0, 'b'→1, ..., 'z'→25
    }

    // Busca uma palavra na tabela hash e retorna a Palavra encontrada
    public Palavra busca(String palavra) {
        int chave = funcaoHash(palavra);
        ArvoreBinariaBusca<Palavra> arvore = this.tabela[chave];

        // Cria um objeto Palavra temporário para busca
        Palavra palavraBusca = new Palavra(palavra);
        return arvore.buscaElemento(palavraBusca);
    }

    // Insere ou atualiza uma palavra com sua linha
    public void insere(String palavra, int numeroLinha) {
        int chave = funcaoHash(palavra);
        ArvoreBinariaBusca<Palavra> arvore = this.tabela[chave];

        Palavra novaPalavra = new Palavra(palavra);
        Palavra palavraExistente = arvore.buscaElemento(novaPalavra);

        if (palavraExistente != null) {
            // Palavra já existe, apenas adiciona a ocorrência
            palavraExistente.adicionarOcorrencia(numeroLinha);
        } else {
            // Palavra nova, insere na árvore
            novaPalavra.adicionarOcorrencia(numeroLinha);
            arvore.insere(novaPalavra);
            this.nElementos++;
        }
    }

    // Gera índice apenas para as palavras-chave (ordenadas), usando apenas TADs do projeto
    public String gerarIndiceOrdenado(ListaEncadeada<String> palavrasChave) {
        StringBuilder indice = new StringBuilder();

        if (palavrasChave == null || palavrasChave.tamanho() == 0) {
            return "";
        }

        // 1) construir uma lista ordenada de strings (ListaEncadeada<String>)
        ListaEncadeada<String> ordenada = new ListaEncadeada<>();

        for (int i = 0; i < palavrasChave.tamanho(); i++) {
            String p = palavrasChave.obter(i);
            if (p == null) continue;
            p = p.trim().toLowerCase();
            if (p.isEmpty()) continue;

            // inserir p em 'ordenada' no local correto (insertion into ordered linked list)
            if (ordenada.tamanho() == 0) {
                ordenada.insereFinal(p);
                continue;
            }

            // procura posição: primeiro elemento >= p
            boolean inserido = false;
            for (int j = 0; j < ordenada.tamanho(); j++) {
                String atual = ordenada.obter(j);
                int cmp = atual.compareTo(p); // compara lexicograficamente
                if (cmp == 0) {
                    // já existe a palavra na lista ordenada -> não inserir duplicata
                    inserido = true;
                    break;
                } else if (cmp > 0) {
                    // atual > p -> insere p antes de atual (na posição j)
                    ordenada.inserePosicao(j, p);
                    inserido = true;
                    break;
                }
            }
            if (!inserido) {
                // p é maior que todos -> insere no final
                ordenada.insereFinal(p);
            }
        }

        // 2) agora a lista 'ordenada' contém as palavras-chave em ordem alfabética (sem duplicatas)
        //    para cada palavra procuramos na TabelaHash e, se encontrada, adicionamos ao índice
        for (int i = 0; i < ordenada.tamanho(); i++) {
            String chave = ordenada.obter(i);
            if (chave == null || chave.isEmpty()) continue;

            Palavra encontrada = this.busca(chave); // seu busca já retorna Palavra
            if (encontrada != null) {
                indice.append(encontrada.toString()).append("\n");
            }
        }

        return indice.toString();
    }



    public void imprime() {
        System.out.println("=== TABELA HASH ===");
        for (int i = 0; i < TAMANHO; i++) {
            char letra = (char) ('a' + i);
            System.out.println("Bucket '" + letra + "':");
            if (!tabela[i].estaVazia()) {
                tabela[i].imprimeEmOrdem();
            } else {
                System.out.println("(vazio)");
            }
        }
    }

    public String todasAsPalavrasEmOrdem() {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < TAMANHO; i++) {
            ArvoreBinariaBusca<Palavra> arvore = tabela[i];

            if (!arvore.estaVazia()) {
                char letra = (char) ('a' + i);
                resultado.append(letra).append(":\n");

                arvore.percorrerEmOrdem(p -> {
                    resultado.append("  ").append(p.toString()).append("\n");
                });

                resultado.append("\n");
            }
        }

        return resultado.toString();
    }

}
