# Índice Remissivo – Estruturas de Dados (Java)

Projeto acadêmico desenvolvido para a disciplina de Estrutura de Dados, com o objetivo de implementar do zero um sistema capaz de gerar um índice remissivo a partir de um arquivo de texto.
Foram utilizadas exclusivamente estruturas implementadas manualmente: Lista Encadeada, Árvore Binária de Busca (ABB) e Tabela Hash com encadeamento externo.

Nota: parte do refinamento conceitual e esclarecimento de dúvidas durante o desenvolvimento contou com apoio de Inteligência Artificial como ferramenta complementar de estudo.

## 📘 Descrição Geral

O programa lê:

✔ Um arquivo de texto (texto.txt)
✔ Um arquivo de palavras-chave (palavras_chaves.txt)

E produz:

📄 Um arquivo indice_remissivo.txt contendo todas as palavras-chave que aparecem no texto, junto das linhas onde ocorrem, em ordem alfabética.

O desafio central é construir o sistema sem utilizar estruturas prontas do Java, implementando manualmente:

uma lista encadeada,

uma ABB,

e uma tabela hash com 26 árvores (uma para cada letra do alfabeto).

## 🧱 Arquitetura do Projeto
✔️ ListaEncadeada<T>

Lista encadeada simples com:

insereFinal

insereInicio

obter(pos)

tamanho()

Usada para:

armazenar linhas do texto,

palavras-chave,

e ocorrências das palavras.

✔️ Palavra

Classe que representa cada palavra lida do texto.

Contém:

texto da palavra em minúsculo,

ListaEncadeada<Integer> com todas as linhas onde aparece.

Implementa:

compareTo (ordenação alfabética)

toString (formato do índice remissivo)

✔️ ArvoreBinariaBusca<T extends Comparable<T>>

ABB genérica, totalmente implementada manualmente.

Métodos:

insere(T)

buscaElemento(T)

remove(T)

percorrerEmOrdem(...)

Usada dentro da Tabela Hash para manter palavras ordenadas sem usar bibliotecas.

✔️ TabelaHash

Tabela hash com 26 buckets (A–Z), onde cada bucket é uma ABB.

Funções:

insere(String palavra, int linha)

busca(String palavra)

gerarIndiceRemissivo(...)

gerarIndiceOrdenado(...) (versão usada no projeto)

Hash utilizada:

primeiraLetra - 'a'

✔️ Main

Responsável por:

ler arquivos,

processar palavras,

preencher a Tabela Hash,

gerar o índice,

salvar o arquivo final.

## 📁 Formato dos Arquivos
texto.txt

Arquivo contendo o texto a ser indexado.

Exemplo:

Estruturas de dados são importantes.
A tabela hash armazena valores rapidamente.
A árvore organiza elementos.


palavras_chaves.txt

Palavras separadas por vírgula ou linha.

árvore, tabela, dados, hash

indice_remissivo.txt

Gerado automaticamente:

arvore 3
dados 1
hash 2
tabela 2

## ▶️ Como Executar

Coloque os arquivos em uma pasta e mude a linha do codigo onde tem escrito um endereço de memoria


Compile:

javac Main.java


Execute:

java Main


O arquivo indice_remissivo.txt será gerado no mesmo diretório.

🖥 Exemplo de Execução

Terminal:

=== ÍNDICE REMISSIVO GERADO ===
arvore 3
dados 1
hash 2
tabela 2

## 🧠 Conceitos Aplicados

Tabela Hash sem bibliotecas

ABB genérica para manter ordenação

Lista Encadeada como estrutura auxiliar

Processamento de arquivos e texto

Modularização orientada a objetos

Hash function simples com divisões por letra

## 🚀 Melhorias Futuras

Trocar ABB por AVL para balanceamento automático

Ignorar stopwords (“de”, “a”, “o”, “e”, etc.)

Tratar plural/singular (“arvore” ↔ “arvores”)

Suporte a textos grandes (streaming)

Interface gráfica do índice

## 🤝 Apoio e Créditos

Este projeto foi desenvolvido manualmente como prática de Estruturas de Dados, e contou com apoio complementar de IA para estudo e esclarecimento de dúvidas durante o processo — sem substituir a implementação manual das estruturas.


