package br.com.pc.screenmatch.modelos;

import br.com.pc.screenmatch.calculos.Classificacao;

public class Filme extends Titulo implements Classificacao {
    private String diretor;

    public Filme(String name, int year) {
        super(name, year);
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    @Override
    public int getClassificacao() {
        return (int) PegaMedia() / 2;
    }

    @Override
    public String toString() {
        return "Filme: " + this.getName() + " ("+this.getYear()+")";
    }
}
