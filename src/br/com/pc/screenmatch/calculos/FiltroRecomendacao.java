package br.com.pc.screenmatch.calculos;

import br.com.pc.screenmatch.modelos.Titulo;

public class FiltroRecomendacao {
    private String recomendacao;

    public void filtra(Classificacao classificacao){
        if (classificacao.getClassificacao() >= 4){
            System.out.println("Preferidos do público!");
        } else if (classificacao.getClassificacao() >= 2) {
            System.out.println("Muito bem avaliado!");
        } else {
            System.out.println("Assistir depois?");
        }
    }
}
