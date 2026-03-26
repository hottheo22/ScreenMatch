package br.com.theo.screenmatch.model;

public enum Categoria {
    Acao("Action", "Ação"),
    Aventura("Romance","Romance"),
    Comedia("Comedy","Comédia"),
    Drama("Drama","Drama"),
    Ficcao("Ficction","Ficção"),
    Terror("Horror","Terror"),
    Documentario("Documentary","Documentário"),
    Fantasia("Fantasy","Fantasia"),
    Animacao("Animation","Animação"),
    Musical("Musical","Musical"),
    Crime("Crime","Crime");

    private String categoriaOmdb;
    private String categoriaPort;

    Categoria(String categoriaOmdb, String categoriaPort) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPort = categoriaPort;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Categoria fromPort(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPort.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

}
