package br.com.pc.screenmatch.principal;
import br.com.pc.screenmatch.exceptions.ErroConversaoDeAno;
import br.com.pc.screenmatch.modelos.Titulo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        ConsultaAPI consultaApi = new ConsultaAPI();
        GravacaoJson gravador = new GravacaoJson();

        String busca = "";

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("Digite um filme: ");
            busca = scanner.nextLine();

            if(busca.equalsIgnoreCase("sair")) break;

            try {
                Titulo titulo = consultaApi.retornaFilme(busca);
                System.out.println(titulo);
                titulos.add(titulo);

            } catch (NumberFormatException e) {
                System.out.println("Erro de conversão de número: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Argumento inválido na busca");
            } catch (ErroConversaoDeAno e) {
                System.out.println(e.getMensagem());
            }
        }

        gravador.salvarListaEmJson(titulos, "titulos.json");
        System.out.println(titulos.toString());
        System.out.println("O programa finalizou corretamente!!");
    }
}

