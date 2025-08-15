package br.com.pc.screenmatch.principal;

import br.com.pc.screenmatch.modelos.OMdbTitulo;
import br.com.pc.screenmatch.modelos.Titulo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaAPI {
    public Titulo retornaFilme(String busca) throws IOException, InterruptedException {

        String endereco = "https://www.omdbapi.com/?t=" + busca.replace(' ', '+') + "&apikey=9a64ae1e";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        OMdbTitulo meuFilmeOmdb = gson.fromJson(json, OMdbTitulo.class);
        return new Titulo(meuFilmeOmdb);
    }
}

