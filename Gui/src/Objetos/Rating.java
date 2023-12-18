package Objetos;

import java.util.ArrayList;

public class Rating {
    private Cliente cliente;
    private Musica musica;
    private int avaliacao;

    public Rating(Cliente cliente, Musica musica, int avaliacao) {
        this.cliente = cliente;
        this.musica = musica;
        this.avaliacao = avaliacao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }
}
