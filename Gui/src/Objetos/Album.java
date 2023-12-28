package Objetos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Album extends GrupoDeMusicas{
    ///////////////////////////////////ATRIBUTOS////////////////////////////////////////////////////////////////////////
    private String genero;
    private Artista artista;
    private LocalDate dataLancamento;
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////CONSTRUTORES////////////////////////////////////////////////////////////////////////
    public Album(ArrayList<Musica> musicas, String nome, String genero, Artista artista) {
        super(musicas, nome);
        this.genero = genero;
        this.artista = artista;
        this.dataLancamento = LocalDate.now();
        artista.addAlbum(this);
        for(int i=0;i<musicas.size();i++){
            artista.removerMusicaDeSingles(musicas.get(i));
        }
    }
    /**
     * Contrutor de album sem qualquer musica adicionada, ou seja, cria album vazio.
     */
    public Album(String nome, String genero, Artista artista) {
        super(nome);
        this.genero = genero;
        this.artista = artista;
        this.dataLancamento = LocalDate.now();
        artista.addAlbum(this);

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////MÉTODOS DIVERSOS////////////////////////////////////////////////////////////////////

    public String getGenero() {
        return genero;
    }
}
