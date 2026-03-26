package br.com.theo.screenmatch.principal;

import br.com.theo.screenmatch.model.*;
import br.com.theo.screenmatch.repository.IRepositorio;
import br.com.theo.screenmatch.service.ConsumoApi;
import br.com.theo.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private IRepositorio repositorio;


    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    List<DadosSerie> dadosSeries = new ArrayList<>();

    public Principal(IRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    private List<Serie> series = new ArrayList<>();

    public void exibeMenu() {

        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    4 - Buscar por título
                    5 - Buscar por categoria
                    6 - *Desafio* 
                    
                    0 - Sair""";

            System.out.println(menu);

            opcao = leitura.nextInt();
            leitura.nextLine();
            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    ListarSeries();
                    break;
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarPorCategoria();
                    break;
                case 6:
                    buscaDesafio();
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
        repositorio.save(serie);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie() {
        ListarSeries();
        System.out.println("Escolha uma série pelo nome: ");
        var nomeSerie = leitura.nextLine();


        Optional<Serie> serie = series.stream().filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase()))
                .findFirst();

        if (serie.isPresent()) {

            var Serieencontrada = serie.get();


            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= Serieencontrada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO + Serieencontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> Episodios = temporadas.stream().flatMap(d -> d.episodios().stream().map(e -> new Episodio(d.numero(),e))).collect(Collectors.toList());
            Serieencontrada.setEpisodios(Episodios);
            repositorio.save(Serieencontrada);


        }else System.out.println("Série não encontrada =(");


    }

    private void buscarSeriePorTitulo(){
        System.out.println("Digite o título da série ");
        var nomeTitulo = leitura.nextLine();
        Optional<Serie> tituloBuscado = repositorio.findBytituloContainingIgnoreCase(nomeTitulo);

        if(tituloBuscado.isPresent()){
            System.out.println("Dados da série: " + tituloBuscado.get());
        }
        else {
            System.out.println("Série não encontrada");
        }
    }

    private void buscarPorCategoria(){
        System.out.println("Digite a categoria da série ");
        var nomeCategoria = leitura.nextLine();
        Categoria categoria = Categoria.fromPort(nomeCategoria);
        List<Serie> serieCategoria = repositorio.findBygenero(categoria);
        System.out.println("Series da categoria: " + categoria);
        serieCategoria.forEach(System.out::println);

    }

    private void buscaDesafio(){
        System.out.println("Quantidade de temporadas ");
        var qntdTemps = leitura.nextInt();
        leitura.nextLine();
        System.out.println("Nota para avaliação: ");
        var avaliacao = leitura.nextDouble();

        List<Serie> serieFiltradas = repositorio.findBytotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(qntdTemps,avaliacao);
        System.out.printf("Séries com %d ou menos temporadas e com nota de %.1f \n",qntdTemps, avaliacao );
        serieFiltradas.forEach(s -> System.out.println(s.getTitulo() + " avaliação: " + s.getAvaliacao()));
    }

    private void ListarSeries() {
       series = repositorio.findAll();
       series.stream()
               .sorted(Comparator.comparing(Serie::getGenero))
               .forEach(System.out::println);

    }
}