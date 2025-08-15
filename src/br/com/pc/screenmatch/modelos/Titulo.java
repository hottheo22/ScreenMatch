package br.com.pc.screenmatch.modelos;

import br.com.pc.screenmatch.exceptions.ErroConversaoDeAno;
import com.google.gson.annotations.SerializedName;

public class Titulo implements Comparable<Titulo> {

    @SerializedName("title")
    private String name;
    private int year;
    private boolean incluidoNoPlano;
    private double SomaAvaliacao;
    private int totalAvaliacoes;
    private int duracaoEmMinutos;

    public Titulo(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public Titulo(OMdbTitulo meuFilmeOmdb) {
        this.name = meuFilmeOmdb.title();

        if(meuFilmeOmdb.year().length() > 4){
            throw new ErroConversaoDeAno("Erro na conversão do ano!");
        }

        this.year = Integer.parseInt(meuFilmeOmdb.year());
        this.duracaoEmMinutos = Integer.valueOf(meuFilmeOmdb.runtime().substring(0,3));
    }

    //Getters e Setters

    public int getTotalAvaliacoes(){
        return totalAvaliacoes;
    }

    public String getName(){
        return name;
    }

    public int getYear(){
        return year;
    }

    public void setIncluidoNoPlano(boolean incluidoNoPlano) {
        this.incluidoNoPlano = incluidoNoPlano;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    //Métodos

    public void ExibirFicha(){
        System.out.println("Nome do filme: " + name);
        System.out.println("Ano de lançamento: " + year);
    }

    public void Avalia(double nota){
        totalAvaliacoes++;
        SomaAvaliacao += nota;
    }

    public double PegaMedia(){
        return SomaAvaliacao/totalAvaliacoes;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public void setDuracaoEmMin(int duracaoEmMin) {
        this.duracaoEmMinutos = duracaoEmMin;
    }

    @Override
    public int compareTo(Titulo outroTitulo) {
        return this.getName().compareTo(outroTitulo.getName());
    }

    @Override
    public String toString() {
        return "(Nome: " +  name + "\n" + "Ano de lançamento: " + year  + "\n" + "Duração: " + duracaoEmMinutos + ")";
    }
}

