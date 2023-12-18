package Objetos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Album extends GrupoDeMusicas{
    private String genero;
    private Artista artista;
    private LocalDate dataLancamento;

    public Album(ArrayList<Musica> musicas, String nome, String genero, Artista artista, LocalDate dataLancamento) {
        super(musicas, nome);
        this.genero = genero;
        this.artista = artista;
        this.dataLancamento = dataLancamento;
    }

    public Album(String nome, String genero, Artista artista, LocalDate dataLancamento) {
        super(nome);
        this.genero = genero;
        this.artista = artista;
        this.dataLancamento = dataLancamento;
    }
}
