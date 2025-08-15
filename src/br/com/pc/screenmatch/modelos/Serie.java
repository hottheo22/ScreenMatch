package br.com.pc.screenmatch.modelos;

public class Serie extends Titulo {
    private int temporadas;
    private int episodiosPorTemporada;
    private int DuracaoEmMinPorEpisodio;

    public Serie(String name, int year) {
        super(name, year);
    }


    public int getTemporadas() {
        return temporadas;
    }


    public int getDuracaoEmMinPorEpisodio() {
        return DuracaoEmMinPorEpisodio;
    }

    public void setDuracaoEmMinPorEpisodio(int duracaoEmMinPorEpisodio) {
        DuracaoEmMinPorEpisodio = duracaoEmMinPorEpisodio;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public int getEpisodiosPorTemporada() {
        return episodiosPorTemporada;
    }

    public void setEpisodiosPorTemporada(int episodiosPorTemporada) {
        this.episodiosPorTemporada = episodiosPorTemporada;
    }

    @Override
    public int getDuracaoEmMinutos() {
        return temporadas * episodiosPorTemporada * DuracaoEmMinPorEpisodio;
    }

    @Override
    public String toString() {
        return "Série: " + this.getName() + " (" + this.getYear() + ")";
    }
}
