import collections.TabelaHash;
import collections.ListaEncadeada;
import java.io.*;

public class Main {

    private static final String CAMINHO_BASE = "C:\\Users\\tobia\\OneDrive\\Documents\\Pessoal\\EDDAV3\\";

    public static void main(String[] args) {
        System.out.println("=== GERADOR DE ÍNDICE REMISSIVO ===\n");

        try {
            String arquivoTexto = CAMINHO_BASE + "texto.txt";
            String arquivoPalavrasChave = CAMINHO_BASE + "palavras_chaves.txt";
            String arquivoSaida = CAMINHO_BASE + "indice_remissivo.txt";

            if (!new File(arquivoTexto).exists()) {
                System.err.println("ERRO: Arquivo texto.txt não encontrado!");
                return;
            }

            if (!new File(arquivoPalavrasChave).exists()) {
                System.err.println("ERRO: Arquivo palavras_chaves.txt não encontrado!");
                return;
            }

            // Criar tabela hash
            TabelaHash tabela = new TabelaHash();

            // Ler arquivo de texto
            ListaEncadeada<String> linhas = lerArquivo(arquivoTexto);

            // Processar texto
            processarTexto(linhas, tabela);

            // Ler palavras-chave
            ListaEncadeada<String> palavrasChave = lerPalavrasChave(arquivoPalavrasChave);

            // Gerar índice ordenado
            String indice = tabela.gerarIndiceOrdenado(palavrasChave);

            // Salvar no arquivo
            salvarIndice(arquivoSaida, indice);

            System.out.println("\n=== ÍNDICE REMISSIVO GERADO ===");
            System.out.println(indice);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -----------------------------
    // MÉTODOS AUXILIARES ESTÁTICOS
    // -----------------------------

    private static ListaEncadeada<String> lerArquivo(String caminho) throws IOException {
        ListaEncadeada<String> linhas = new ListaEncadeada<>();
        BufferedReader reader = new BufferedReader(new FileReader(caminho));

        String linha;
        while ((linha = reader.readLine()) != null) {
            linhas.insereFinal(linha);
        }

        reader.close();
        return linhas;
    }

    private static void processarTexto(ListaEncadeada<String> linhasTexto, TabelaHash tabela) {
        int numeroLinha = 1;

        for (int i = 0; i < linhasTexto.tamanho(); i++) {
            String linha = linhasTexto.obter(i);

            String[] palavras = linha.replaceAll("[^a-zA-Z- ]", "")
                    .toLowerCase()
                    .split("\\s+");

            for (String palavra : palavras) {
                if (!palavra.isEmpty()) {
                    tabela.insere(palavra, numeroLinha);
                }
            }

            numeroLinha++;
        }
    }

    private static ListaEncadeada<String> lerPalavrasChave(String caminho) throws IOException {
        ListaEncadeada<String> palavras = new ListaEncadeada<>();
        BufferedReader reader = new BufferedReader(new FileReader(caminho));

        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.toLowerCase().split(",");
            for (String p : partes) {
                p = p.trim();
                if (!p.isEmpty()) {
                    palavras.insereFinal(p);
                }
            }
        }

        reader.close();
        return palavras;
    }

    private static void salvarIndice(String caminho, String indice) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(caminho));
        writer.write(indice);
        writer.close();
    }
}

