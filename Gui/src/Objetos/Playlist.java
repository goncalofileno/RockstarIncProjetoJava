package Objetos;

import java.util.ArrayList;

public class Playlist extends GrupoDeMusicas{
    private boolean visibilidade;

    public Playlist(ArrayList<Musica> musicas, String nome, boolean visibilidade) {
        super(musicas, nome);
        this.visibilidade = visibilidade;
    }

    public Playlist(String nome, boolean visibilidade) {
        super(nome);
        this.visibilidade = visibilidade;
    }
}
