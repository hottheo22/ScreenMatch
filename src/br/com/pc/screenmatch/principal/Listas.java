package br.com.pc.screenmatch.principal;

import br.com.pc.screenmatch.modelos.Filme;
import br.com.pc.screenmatch.modelos.Serie;
import br.com.pc.screenmatch.modelos.Titulo;

import java.util.*;

public class Listas {

    public static void main(String[] args) {
        Filme meuFilme = new Filme("Sinners", 2025);
        meuFilme.Avalia(10);
        Filme outroFilme = new Filme("Avatar",2023);
        outroFilme.Avalia(6);
        var filmeDoTheo = new Filme("Pump Fiction",2001);
        filmeDoTheo.Avalia(9);
        Serie minhaSerie = new Serie("Arrow",2012);

        List<Titulo> lista = new LinkedList<>();
        lista.add(filmeDoTheo);
        lista.add(meuFilme);
        lista.add(outroFilme);
        lista.add(minhaSerie);

        for(Titulo item : lista){
            System.out.println(item.getName());
            if (item instanceof Filme filme && filme.getClassificacao() > 2){
                System.out.println("Classificacao: " + filme.getClassificacao());
            }

        }

        ArrayList<String> buscaPorArtista = new ArrayList<>();
        buscaPorArtista.add("Theo");
        buscaPorArtista.add("Michael B Jordan");
        buscaPorArtista.add("Leonardo Dicaprio");
        Collections.sort(buscaPorArtista);
        System.out.println(buscaPorArtista);

        Collections.sort(lista);
        System.out.println(lista);

        lista.sort(Comparator.comparing(Titulo::getYear));
        System.out.println(lista);
    }
}
