package Dados;

public class Resultado {

    private int posicao;
    private long comparacoes;
    private long tempoExecucao;

    public Resultado(int pos, long comp, long temp) {
        this.posicao = pos;
        this.comparacoes = comp;
        this.tempoExecucao = temp;
    }

    public int getPosicao() {
        return posicao;
    }

    public long getComparacoes() {
        return comparacoes;
    }

    public long getTempoExecucao() {
        return tempoExecucao;
    }

}
