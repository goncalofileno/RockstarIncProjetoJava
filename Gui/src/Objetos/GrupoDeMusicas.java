package Objetos;

import java.util.ArrayList;

public abstract class GrupoDeMusicas {
    private ArrayList<Musica> musicas;
    private String nome;

    public GrupoDeMusicas(ArrayList<Musica> musicas, String nome) {
        this.musicas = musicas;
        this.nome = nome;
    }

    public GrupoDeMusicas(String nome) {
        this.nome = nome;
        musicas=new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void addMusica(Musica musica){
        musicas.add(musica);
    }

    public ArrayList<Musica> getMusicas(){
        return musicas;
    }
}
