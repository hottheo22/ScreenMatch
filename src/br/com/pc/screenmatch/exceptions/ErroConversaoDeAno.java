package br.com.pc.screenmatch.exceptions;

public class ErroConversaoDeAno extends RuntimeException {

    private String mensagem;

    public ErroConversaoDeAno(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return this.mensagem;
    }
}
