package br.com.pc.screenmatch.calculos;


import br.com.pc.screenmatch.modelos.Titulo;

public class CalculadoraDeTempo {
    private int tempoTotal;

    public int getTempoTotal() {
        return tempoTotal;
    }


    public void inclui(Titulo t) {
        tempoTotal += t.getDuracaoEmMinutos();
    }
}
