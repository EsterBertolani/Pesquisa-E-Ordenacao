package AlgoritmosPesquisa;

public class Ordenadores {

    // Insertion Sort - para vetores com menos de 1000 elementos
    public static void insercaoDireta(int[] vetor) {
        int i, j;
        int temp;
        for (i = 1; i < vetor.length; i++) {
            temp = vetor[i];
            j = i - 1;

            while (j >= 0 && vetor[j] > temp) {
                vetor[j + 1] = vetor[j];
                j--;
            }
            vetor[j + 1] = temp;

        }
    }

    // QuickSort - para vetores com 1000 ou mais elementos
    public static void quicksort(int[] vetor) {
        ordena(vetor, 0, vetor.length - 1);
    }

    private static void ordena(int[] vetor, int esq, int dir) {
        int i = esq, j = dir;
        int pivo = vetor[(i + j) / 2];
        int temp;

        while (i <= j) {
            while (vetor[i] < pivo) i++;
            while (vetor[j] > pivo) j--;

            if (i <= j) {
                temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
                i++;
                j--;
            }
        }

        if (esq < j) ordena(vetor, esq, j);
        if (i < dir) ordena(vetor, i, dir);
    }

}
