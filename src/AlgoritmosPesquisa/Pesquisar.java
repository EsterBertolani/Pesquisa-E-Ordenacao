package AlgoritmosPesquisa;

public class Pesquisar {

    public long comparacoes = 0;
    public long tempoExecucao = 0;

    public int pesquisaSequencial(int num, int[] vetor) {
        comparacoes = 0;
        tempoExecucao = 0;
        long fim;
        long inicio = System.nanoTime();
        int i = 0;
        int pos = 0;

        while (i < vetor.length) {
            comparacoes++;
            if (num == vetor[i]) {
                fim = System.nanoTime();
                tempoExecucao = fim - inicio;
                return pos;
            }
            i++;
            pos++;
        }
        fim = System.nanoTime();
        tempoExecucao = fim - inicio;
        return -1;
    }

    public int pesquisaMelhorada(int num, int[] vetor) {
        comparacoes = 0;
        tempoExecucao = 0;
        long fim;
        long inicio = System.nanoTime();
        int i = 0;
        int pos = 0;
        while (i < vetor.length) {
            comparacoes++;
            if (num == vetor[i]) {
                fim = System.nanoTime();
                tempoExecucao = fim - inicio;
                return pos;
            }
            comparacoes++;
            if (num < vetor[i]) {
                fim = System.nanoTime();
                tempoExecucao = fim - inicio;
                return -1;
            }
            i++;
            pos++;
        }
        fim = System.nanoTime();
        tempoExecucao = fim - inicio;
        return -1;
    }

    public int pesquisaBinaria(int num, int[] vetor) {
        comparacoes = 0;
        tempoExecucao = 0;
        long fim;
        long inicio = System.nanoTime();
        int inicioVet = 0, fimVet = vetor.length - 1;
        int meio;

        if (num > vetor[fimVet]) {
            comparacoes++;
            fim = System.nanoTime();
            tempoExecucao = fim - inicio;
            return -1;
        } else {
            while (inicioVet <= fimVet) {
                meio = (inicioVet + fimVet) / 2;
                comparacoes++;

                if (num == vetor[meio]) {
                    fim = System.nanoTime();
                    tempoExecucao = fim - inicio;
                    return meio;
                }
                comparacoes++;
                if (num < vetor[meio]) {
                    fimVet = meio - 1;
                } else {
                    inicioVet = meio + 1;
                }
            }
            fim = System.nanoTime();
            tempoExecucao = fim - inicio;
            return -1;
        }
    }

    public int pesquisaHash(int[] vetor, int valorBuscado, int tamanhoTabela, int capacidadeEncadeamento) {
        comparacoes = 0;
        tempoExecucao = 0;
        long fim;
        long inicio = System.nanoTime();
        int[][] tabelaHash = new int[tamanhoTabela][capacidadeEncadeamento];
        int[][] posicoesOriginais = new int[tamanhoTabela][capacidadeEncadeamento];
        int valor;
        int indice;
        // Inicializa as tabelas
        for (int i = 0; i < tamanhoTabela; i++) {
            for (int j = 0; j < capacidadeEncadeamento; j++) {
                tabelaHash[i][j] = -1;
                posicoesOriginais[i][j] = -1;
            }
        }

        // Inserção dos valores e suas posições originais
        for (int pos = 0; pos < vetor.length; pos++) {
            valor = vetor[pos];
            indice = valor % tamanhoTabela;
            for (int i = 0; i < capacidadeEncadeamento; i++) {
                comparacoes++;
                if (tabelaHash[indice][i] == -1) {
                    tabelaHash[indice][i] = valor;
                    posicoesOriginais[indice][i] = pos; // salva a posição original
                    break;
                }
            }
        }

        // Busca do valor e retorno da posição original
        int indiceBusca = valorBuscado % tamanhoTabela;
        for (int i = 0; i < capacidadeEncadeamento; i++) {
            comparacoes++;
            if (tabelaHash[indiceBusca][i] == valorBuscado) {
                fim = System.nanoTime();
                tempoExecucao = fim - inicio;
                return posicoesOriginais[indiceBusca][i]; // posição no vetor original
            }
        }
        fim = System.nanoTime();
        tempoExecucao = fim - inicio;
        return -1; // não encontrado
    }

}
