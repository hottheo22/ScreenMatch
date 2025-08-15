package br.com.pc.screenmatch.principal;

import br.com.pc.screenmatch.modelos.Titulo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GravacaoJson {

    public void salvarListaEmJson(List<Titulo> titulos, String nomeArquivo) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileWriter file = new FileWriter(nomeArquivo)) {
            file.write(gson.toJson(titulos));
        }


    }
}

