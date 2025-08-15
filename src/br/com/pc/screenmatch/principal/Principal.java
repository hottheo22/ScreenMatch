package br.com.pc.screenmatch.principal;

import br.com.pc.screenmatch.calculos.CalculadoraDeTempo;
import br.com.pc.screenmatch.calculos.FiltroRecomendacao;
import br.com.pc.screenmatch.modelos.Episodio;
import br.com.pc.screenmatch.modelos.Filme;
import br.com.pc.screenmatch.modelos.Serie;

import java.util.ArrayList;
import java.util.Collections;

public class Principal {
    public static void main(String[] args) {
        Filme meuFilme = new Filme("Sinners", 2025);
        meuFilme.setDuracaoEmMin(145);

        Filme outroFilme = new Filme("Avatar",2023);
        outroFilme.setDuracaoEmMin(180);

        meuFilme.ExibirFicha();
        meuFilme.Avalia(8);
        meuFilme.Avalia(5);
        meuFilme.Avalia(7.5);
        System.out.println("Total de avaliações: " + meuFilme.getTotalAvaliacoes());
        System.out.printf("Média de avaliação do filme é %.1f%n",meuFilme.PegaMedia());

        var filmeDoTheo = new Filme("Pump Fiction",2001);
        filmeDoTheo.setDuracaoEmMin(200);
        filmeDoTheo.Avalia(10);

        ArrayList<Filme> listaDeFilme = new ArrayList<>();
        listaDeFilme.add(filmeDoTheo);
        listaDeFilme.add(meuFilme);
        listaDeFilme.add(outroFilme);
        System.out.println("Tamanho da lista: " + listaDeFilme.size());
        System.out.println("Primeiro filme adicionado: " + listaDeFilme.get(0).getName());
        System.out.println(listaDeFilme);
        System.out.println("To string do filme: " + listaDeFilme.get(0).toString());


        Serie minhaSerie = new Serie("Arrow",2012);
        minhaSerie.ExibirFicha();
        minhaSerie.setTemporadas(2);
        minhaSerie.setEpisodiosPorTemporada(12);
        minhaSerie.setDuracaoEmMinPorEpisodio(40);
        System.out.println("Duração da série: " + minhaSerie.getDuracaoEmMinutos());

        CalculadoraDeTempo calc = new CalculadoraDeTempo();
        calc.inclui(meuFilme);
        calc.inclui(outroFilme);
        calc.inclui(minhaSerie);
        System.out.println("Tempo total para maratonar meus Filmes: " + calc.getTempoTotal());

        FiltroRecomendacao filtro = new FiltroRecomendacao();
        filtro.filtra(meuFilme);

        Episodio ep = new Episodio();
        ep.setNumero(1);
        ep.setSerie(minhaSerie);
        ep.setTotalLikes(200);
        filtro.filtra(ep);



    }
}
