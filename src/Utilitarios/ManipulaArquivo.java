package Utilitarios;

import java.io.*;
import AlgoritmosPesquisa.Ordenadores;

public class ManipulaArquivo {

    public static int[] gerarVetor(int qtdNum) {
        int[] numeros = new int[qtdNum];
        for (int i = 0; i < qtdNum; i++) {
            numeros[i] = (int) (Math.random() * 1000);
        }
        return numeros;
    }

    public static String gerarArquivo(int qtdNum, String nomeArquivo) {
        int[] numeros = gerarNum(qtdNum, 10000);

        try (BufferedWriter gerar = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (int num : numeros) {
                gerar.write(String.valueOf(num));
                gerar.newLine();
            }
            return "Arquivo \"" + nomeArquivo + "\" criado com sucesso contendo " + numeros.length + " números!";
        } catch (Exception e) {
            return "Erro ao criar o arquivo \"" + nomeArquivo + "\": " + e.getMessage();
        }
    }

    public static int[] lerArquivo(String nomeArquivo) throws IOException {
        int[] numeros = new int[tamArquivo(nomeArquivo)];

        try (BufferedReader ler = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            int i = 0;
            while ((linha = ler.readLine()) != null) {
                numeros[i++] = Integer.parseInt(linha.trim());
            }
        }

        return numeros.length > 0 ? numeros : null;
    }

    public static int[] ordenarVetor(int[] vetor) {
        if (vetor != null) {
            if (vetor.length < 1000) {
                Ordenadores.insercaoDireta(vetor);
            } else {
                Ordenadores.quicksort(vetor);
            }
            return vetor;
        } else {
            return null;
        }
    }

    public static int[] ordenarArquivo(String nomeArquivo) throws IOException {
        int[] vetor = lerArquivo(nomeArquivo);
        if (vetor != null) {
            if (vetor.length < 1000)
                Ordenadores.insercaoDireta(vetor);
            else
                Ordenadores.quicksort(vetor);
        }
        return vetor;
    }

    private static int[] gerarNum(int qtdNum, int limiteMax) {
        int[] numeros = new int[qtdNum];
        for (int i = 0; i < qtdNum; i++) {
            numeros[i] = (int) (Math.random() * limiteMax);
        }
        return numeros;
    }

    // ================== MAIOR, MENOR, MODA ==================

    public static String maiorMenor(int[] vet) {
        ordenarVetor(vet);
        int menor = vet[0];
        int maior = vet[vet.length - 1];

        return "O maior número do vetor é: " + maior + "\nE o menor número é: " + menor;

    }

    // moda

    public static String acharModa(int[] vet) {

        ordenarVetor(vet);

        StringBuilder moda = new StringBuilder("A moda deste vetor é: ");
        int maxCont = 0;
        int contAtual = 1;
        int numAnt = vet[0];

        for (int i = 1; i <= vet.length; i++) {

            if (i == vet.length || vet[i] != numAnt) {

                if (contAtual > maxCont) {
                    maxCont = contAtual;
                    moda.setLength(0); // apaga a moda antiga
                    moda.append(numAnt); // adiciona a moda com mais numeros de repetições atualmente

                } else if (contAtual == maxCont) { // caso tenha moda com o msm numero de repeticoes
                    moda.append(", ");
                    moda.append(numAnt);
                }
                if (i < vet.length) {
                    contAtual = 1;
                    numAnt = vet[i];
                }
            } else {
                contAtual++; // msm número, só incrementa no fim do loop
            }
        }

        if (maxCont == 1) {
            return "Nenhuma moda";
        }

        return moda.toString();
    }

    // ================== Métodos private / auxiliares ==================

    private static int tamArquivo(String nomeArquivo) {
        int cont = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            while (reader.readLine() != null)
                cont++;
        } catch (IOException e) {
            return 0;
        }
        return cont;
    }

    public static String toString(int[] numeros) {
        String msg = "| ";
        for (int i : numeros) {
            msg += i + " | ";
        }
        return msg;
    }
}
