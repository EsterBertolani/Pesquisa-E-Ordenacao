package Principal;

import java.util.InputMismatchException;
import java.util.Scanner;

import AlgoritmosPesquisa.Ordenadores;
import AlgoritmosPesquisa.Pesquisar;
import Dados.Resultado;
import Utilitarios.ManipulaArquivo;
import Utilitarios.TratamentoErros;

public class Main {

    // Recursos compartilhados pela aplicação
    public static Scanner scan = new Scanner(System.in);
    public static int[] numeros = null;
    public static Pesquisar pesquisar = new Pesquisar();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.print("\n===== MENU PRINCIPAL =====\n"
                    + "1. Gerenciar vetor\n"
                    + "2. Análise do vetor\n"
                    + "3. Algoritmos de Pesquisa\n"
                    + "4. Análise de Desempenho\n"
                    + "0. SAIR\n"
                    + " | Opção: ");

            try {
                opcao = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nErro: Por favor, digite apenas números.");
                scan.nextLine();
                opcao = -1; // Define uma opção inválida para continuar no loop
            }

            switch (opcao) {
                case 1:
                    menuGerenciarVetor();
                    break;
                case 2:
                    menuAnaliseVetor();
                    break;
                case 3:
                    menuAlgoritmosPesquisa();
                    break;
                case 4:
                    compararDesempenho(scan, numeros, pesquisar);
                    break;
                case 0:
                    System.out.println("\nEncerrando o programa...");
                    break;
                default:
                    System.out.println("\nInválido. Tente novamente.");
            }

        } while (opcao != 0);

        scan.close();
    }

    private static void menuGerenciarVetor() {
        System.out.print("\n===== Gerenciar vetor =====\n"
                + "1. Criar vetor aleatório\n"
                + "2. Carregar de Arquivo\n"
                + "0. Voltar\n"
                + " | Opção: ");
        int opcao2 = scan.nextInt();

        switch (opcao2) {
            case 1:
                System.out.print("\nDigite a quantidade de números que deseja no vetor: ");
                int qtdNum = scan.nextInt();
                numeros = ManipulaArquivo.gerarVetor(qtdNum); // Salva no vetor principal
                System.out.println("Vetor gerado com sucesso.");
                System.out.println(ManipulaArquivo.toString(numeros));
                break;

            case 2:
                System.out.print("\nVocê já tem o arquivo? [s - escolher] | [n - gerar novo]: ");
                String resposta = scan.next();
                scan.nextLine();

                if (resposta.equalsIgnoreCase("s")) {
                    System.out.print("\nDigite o nome do arquivo [ex: vetor.txt]: ");
                    String nomeArquivo = scan.nextLine();

                    try {
                        numeros = ManipulaArquivo.lerArquivo(nomeArquivo);

                        if (numeros != null) {
                            System.out.println("Vetor carregado com sucesso.");
                        } else {
                            System.out.println("Arquivo vazio ou inválido.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao ler o arquivo: " + e.getMessage());
                    }

                } else if (resposta.equalsIgnoreCase("n")) {
                    System.out.print("\nDigite o nome do arquivo para salvar [ex: arquivo.txt]: ");
                    String nomeArquivo = scan.nextLine();

                    System.out.print("\nDigite a quantidade de números: ");
                    int qtdNums = scan.nextInt();

                    ManipulaArquivo.gerarArquivo(qtdNums, nomeArquivo);
                    System.out.println("Arquivo gerado! Agora carregue-o no menu 1.");
                }
                break;

            case 0:
                System.out.println("\nVoltando ao menu principal...");
                break;

            default:
                System.out.println("\nInválido. Tente novamente.");
        }
    }

    private static void menuAnaliseVetor() {
        if (numeros == null) {
            System.out.println("\nErro: Você precisa carregar ou criar um vetor primeiro! (Menu 1)");
            return;
        }

        System.out.print("\n===== Análise do vetor =====\n"
                + "1. Encontrar Menor e Maior valor\n"
                + "2. Calcular a Moda\n"
                + "0. Voltar\n"
                + " | Opção: ");
        int opcao2 = scan.nextInt();

        switch (opcao2) {
            case 1:
                System.out.println(ManipulaArquivo.maiorMenor(numeros));
                break;

            case 2:
                System.out.println(ManipulaArquivo.acharModa(numeros));
                break;

            case 0:
                System.out.println("\nVoltando ao menu principal...");
                break;

            default:
                System.out.println("\nInválido. Tente novamente.");
        }
    }

    private static void menuAlgoritmosPesquisa() {
        if (numeros == null) {
            System.out.println("\nErro: Você precisa carregar ou criar um vetor primeiro! (Menu 1)");
            return;
        }

        int opcao2;
        do {
            System.out.print("\n===== Algoritmos de Pesquisa =====\n"
                    + "1. Pesquisa Sequencial Simples\n"
                    + "2. Pesquisa Sequencial Otimizada (Ordena)\n"
                    + "3. Pesquisa Binária (Ordena)\n"
                    + "4. Pesquisa com Hashing\n"
                    + "0. Voltar\n"
                    + " | Opção: ");
            opcao2 = scan.nextInt();

            int numPesquisa;
            int indice;

            switch (opcao2) {
                case 1:
                    System.out.print("\nDigite o número que deseja pesquisar: ");
                    numPesquisa = scan.nextInt();
                    indice = pesquisar.pesquisaSequencial(numPesquisa, numeros);
                    imprimirResultadoPesquisa(indice);
                    break;

                case 2:
                    System.out.print("\nDigite o número que deseja pesquisar: ");
                    numPesquisa = scan.nextInt();
                    ManipulaArquivo.ordenarVetor(numeros);
                    System.out.println("Vetor ordenado para pesquisa otimizada.");
                    indice = pesquisar.pesquisaMelhorada(numPesquisa, numeros);
                    imprimirResultadoPesquisa(indice);
                    break;

                case 3:
                    System.out.print("\nDigite o número que deseja pesquisar: ");
                    numPesquisa = scan.nextInt();
                    ManipulaArquivo.ordenarVetor(numeros);
                    System.out.println("Vetor ordenado para pesquisa binária.");
                    indice = pesquisar.pesquisaBinaria(numPesquisa, numeros);
                    imprimirResultadoPesquisa(indice);
                    break;
                case 4:
                    System.out.print("\nDigite o número que deseja pesquisar: ");
                    numPesquisa = scan.nextInt();
                    ManipulaArquivo.ordenarVetor(numeros);
                    System.out.println("Vetor ordenado para pesquisa com Hashing.");
                    indice = pesquisar.pesquisaHash(numeros, numPesquisa, 50, 10);
                    imprimirResultadoPesquisa(indice);
                    break;

                case 0:
                    System.out.println("\nVoltando ao menu principal...");
                    break;

                default:
                    System.out.println("\nInválido. Tente novamente.");
            }
        } while (opcao2 != 0);
    }

    private static void compararDesempenho(Scanner sc, int[] vetor, Pesquisar pesquisar) {
        if (numeros == null) {
            System.out.println("\nErro: Você precisa carregar ou criar um vetor primeiro! (Menu 1)");
            return;
        }

        int valor = TratamentoErros.lerInteiro(sc, "Valor para buscar: ");

        Resultado seq = new Resultado(
                pesquisar.pesquisaSequencial(valor, vetor),
                pesquisar.comparacoes,
                pesquisar.tempoExecucao);

        Ordenadores.quicksort(vetor);

        Resultado bin = new Resultado(
                pesquisar.pesquisaBinaria(valor, vetor),
                pesquisar.comparacoes,
                pesquisar.tempoExecucao);

        System.out.println("\n=== COMPARATIVO ===");
        System.out
                .println("Sequencial --> Comparações: " + seq.getComparacoes() + " | Tempo: " + seq.getTempoExecucao());
        System.out.println("Binária --> Comparações: " + bin.getComparacoes() + " | Tempo: " + bin.getTempoExecucao());
    }

    /**
     * //Método auxiliar para imprimir o resultado da pesquisa.
     * 
     * @param indice
     *               O índice retornado pelo método de pesquisa
     */
    private static void imprimirResultadoPesquisa(int indice) {
        if (indice != -1) {
            System.out.println("O número que deseja está na posição: " + indice);
        } else {
            System.out.println("O número que deseja não está no vetor.");
        }
    }
}